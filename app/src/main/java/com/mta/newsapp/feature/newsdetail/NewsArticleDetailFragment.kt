package com.mta.newsapp.feature.newsdetail

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.mta.newsapp.R
import com.mta.newsapp.core.BaseViewBindingFragment
import com.mta.newsapp.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsArticleDetailFragment : BaseViewBindingFragment<FragmentNewsDetailBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentNewsDetailBinding::inflate

  private val navSafeArgs: NewsArticleDetailFragmentArgs by navArgs()

  private val title: String by lazy {
    navSafeArgs.title
  }

  private val url: String by lazy {
    navSafeArgs.url
  }

  private val isFavourite: Boolean by lazy {
    navSafeArgs.favourite
  }

  private val navController: NavController by lazy {
    findNavController()
  }

  private val viewModel: NewsArticleDetailViewModel by viewModels()

  override fun onBindView() {
    super.onBindView()

    binding.tvTitle.text = title
    binding.tvTitle.isSelected = true
    binding.webView.loadUrl(url)

    setFavourite(isFav = isFavourite)

    viewModel.isFavNewsArticlesAsFlow(url = url)
    viewModel.isFavNewsArticlesLiveData.observe(viewLifecycleOwner) {
      setFavourite(it)
    }

    binding.btnBack.setOnClickListener {
      navController.popBackStack()
    }

    binding.fab.setOnClickListener {
      viewModel.updateNewsArticleFavourite(url = url, isFavourite = isFavourite)
    }
  }

  private fun setFavourite(isFav: Boolean) {
    if (isFav) {
      binding.fab.setImageResource(R.drawable.baseline_favorite_24)
    } else {
      binding.fab.setImageResource(R.drawable.baseline_favorite_border_24)
    }
  }
}