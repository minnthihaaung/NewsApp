package com.mta.newsapp.feature.topheadline.category

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.mta.newsapp.core.BaseViewBindingFragment
import com.mta.newsapp.databinding.FragmentNewsArticleListBinding
import com.mta.newsapp.feature.commonadapter.NewsArticleListAdapter
import com.mta.newsapp.feature.topheadline.TabNamesConstants
import com.mta.newsapp.feature.topheadline.TopHeadlinesSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryNewsArticleFragment : BaseViewBindingFragment<FragmentNewsArticleListBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentNewsArticleListBinding::inflate

  companion object {
    private const val EXTRA_CATEGORY = "category"

    fun newInstance(category: String) = CategoryNewsArticleFragment().apply {
      arguments = bundleOf(EXTRA_CATEGORY to category)
    }
  }

  private val category: String by lazy {
    arguments?.getString(EXTRA_CATEGORY) ?: TabNamesConstants.tabNamesList[0]
  }

  private val viewModel: CategoryNewsArticleViewModel by viewModels()

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

    viewModel.getNewsArticlesWithCategory(category = category)
    viewModel.newsArticleWithCategoryLiveData.observe(viewLifecycleOwner) { result ->
      result.onSuccess { data ->
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
        newsArticleListAdapter.submitList(data)
      }.onFailure {
        Toast.makeText(requireContext(), "Error fetching news article", Toast.LENGTH_SHORT).show()
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