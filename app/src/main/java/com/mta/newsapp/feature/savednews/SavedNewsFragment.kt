package com.mta.newsapp.feature.savednews

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.mta.newsapp.core.BaseViewBindingFragment
import com.mta.newsapp.databinding.FragmentSavedNewsBinding
import com.mta.newsapp.feature.commonadapter.NewsArticleListAdapter
import com.mta.newsapp.feature.topheadline.TopHeadlinesFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : BaseViewBindingFragment<FragmentSavedNewsBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentSavedNewsBinding::inflate

  private val viewModel: SavedNewsViewModel by viewModels()

  private val navController: NavController by lazy {
    findNavController()
  }

  private lateinit var newsArticleListAdapter: NewsArticleListAdapter

  override fun onBindView() {
    super.onBindView()

    newsArticleListAdapter = NewsArticleListAdapter(onItemClick = { newsArticle ->

      //go to detail page
      val destination =
        SavedNewsFragmentDirections.actionSavedNewsFragmentToNewsArticleDetailFragment(
          url = newsArticle.url, title = newsArticle.title, favourite = newsArticle.isFavourite
        )

      navController.navigate(destination)

    }, onItemBookmarkClick = { item ->

      viewModel.updateNewsArticleFavourite(newsArticle = item)
    })

    setUpRecyclerView()

    viewModel.getNewsArticles()
    viewModel.allFavNewsArticlesLiveData.observe(viewLifecycleOwner) { result ->
      result.onSuccess { data ->
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
        newsArticleListAdapter.submitList(data)
      }.onFailure {
        Toast.makeText(requireContext(), "Error fetching saved news article", Toast.LENGTH_SHORT)
          .show()
      }
    }
  }

  private fun setUpRecyclerView() {
    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
      adapter = newsArticleListAdapter
    }
  }
}