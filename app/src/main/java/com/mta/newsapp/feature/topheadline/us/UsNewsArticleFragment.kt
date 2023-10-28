package com.mta.newsapp.feature.topheadline.us

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.mta.newsapp.core.BaseViewBindingFragment
import com.mta.newsapp.databinding.FragmentNewsArticleListBinding
import com.mta.newsapp.domain.exception.NetworkException
import com.mta.newsapp.feature.commonadapter.NewsArticleListAdapter
import com.mta.newsapp.feature.topheadline.TopHeadlinesSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsNewsArticleFragment : BaseViewBindingFragment<FragmentNewsArticleListBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentNewsArticleListBinding::inflate

  companion object {
    fun newInstance() = UsNewsArticleFragment()
  }

  private val viewModel: UsNewsArticleViewModel by viewModels()

  private val sharedViewModel: TopHeadlinesSharedViewModel by lazy {
    ViewModelProvider(requireParentFragment()).get(TopHeadlinesSharedViewModel::class.java)
  }

  private lateinit var newsArticleListAdapter: NewsArticleListAdapter

  override fun onBindView() {
    super.onBindView()

    newsArticleListAdapter = NewsArticleListAdapter(onItemClick = { item ->

      sharedViewModel.onNewsArticleDetailClickLiveData.postValue(item)

    }, onItemBookmarkClick = { item ->

      viewModel.updateNewsArticleFavourite(newsArticle = item)
    })

    setUpRecyclerView()

    viewModel.getNewsArticles()
    viewModel.allNewsArticlesLiveData.observe(viewLifecycleOwner) { result ->
      result.onSuccess { data ->
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
        newsArticleListAdapter.submitList(data)
      }.onFailure {
        if (it is NetworkException)
          Toast.makeText(
            requireContext(), "Error fetching news article error code ${(it).errorCode}",
            Toast.LENGTH_SHORT
          ).show()
        else
          Toast.makeText(
            requireContext(), "Error fetching news article ${it.message ?: "something"}",
            Toast.LENGTH_SHORT
          ).show()
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