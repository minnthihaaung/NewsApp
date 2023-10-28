package com.mta.newsapp.feature.topheadline

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.mta.newsapp.R
import com.mta.newsapp.core.BaseViewBindingFragment
import com.mta.newsapp.databinding.FragmentTopHeadlinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlinesFragment : BaseViewBindingFragment<FragmentTopHeadlinesBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentTopHeadlinesBinding::inflate

  private lateinit var topHeadlinesPagerAdapter: TopHeadlinesPagerAdapter

  private val sharedViewModel: TopHeadlinesSharedViewModel by viewModels()

  private val navController: NavController by lazy {
    findNavController()
  }

  override fun onBindView() {
    super.onBindView()

    topHeadlinesPagerAdapter = TopHeadlinesPagerAdapter(this)

    binding.viewPager.apply {
      adapter = topHeadlinesPagerAdapter
    }

    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      tab.text = TabNamesConstants.tabNamesList.get(position)
    }.attach()

    sharedViewModel.onNewsArticleDetailClickLiveData.observe(viewLifecycleOwner) { newsArticle ->

      val destination =
        TopHeadlinesFragmentDirections.actionTopHeadlinesFragmentToNewsArticleDetailFragment(
          url = newsArticle.url, title = newsArticle.title, favourite = newsArticle.isFavourite
        )

      navController.navigate(destination)

    }
  }
}