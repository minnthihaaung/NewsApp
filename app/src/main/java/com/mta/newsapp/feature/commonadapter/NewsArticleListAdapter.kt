package com.mta.newsapp.feature.commonadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale.FIT
import com.mta.newsapp.R
import com.mta.newsapp.databinding.ItemNewsArticleBinding
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.ext.changeColorTint
import com.mta.newsapp.ext.toPrettyTime
import com.mta.newsapp.feature.commonadapter.NewsArticleListAdapter.NewsArticleListViewHolder
import com.mta.newsapp.helper.diffCallBackWith

class NewsArticleListAdapter constructor(
  private val onItemClick: (item: NewsArticle) -> Unit,
  private val onItemBookmarkClick: (item: NewsArticle) -> Unit,
) :
  ListAdapter<NewsArticle, NewsArticleListViewHolder>(
    diffCallBackWith(
      areItemTheSame = { item1: NewsArticle, item2: NewsArticle -> item1 == item2 },
      areContentsTheSame = { item1: NewsArticle, item2: NewsArticle -> item1 == item2 })
  ) {

  class NewsArticleListViewHolder(val binding: ItemNewsArticleBinding) : RecyclerView.ViewHolder(
    binding.root
  )

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int,
  ): NewsArticleListViewHolder {
    val binding = ItemNewsArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    return NewsArticleListViewHolder(binding).also { viewHolder ->
      viewHolder.itemView.setOnClickListener {
        val itemAtIndex = getItem(viewHolder.bindingAdapterPosition)
        onItemClick(itemAtIndex)
      }

      viewHolder.binding.btnBookmark.setOnClickListener {
        val itemAtIndex = getItem(viewHolder.bindingAdapterPosition)
        onItemBookmarkClick(itemAtIndex)
      }
    }
  }

  override fun onBindViewHolder(
    holder: NewsArticleListViewHolder,
    position: Int,
  ) {
    getItem(position)?.let { itemAtIndex ->
      holder.binding.apply {
        tvTitle.text = itemAtIndex.title
        tvTime.text = itemAtIndex.publishedAt.toPrettyTime()
        imageView.load(itemAtIndex.imageUrl) {
          crossfade(true)
          placeholder(R.drawable.placeholder)
          scale(FIT)
        }

        if (itemAtIndex.isFavourite) {
          btnBookmark.changeColorTint(R.color.purple_500)
        } else {
          btnBookmark.changeColorTint(R.color.grey)
        }
      }
    }
  }
}