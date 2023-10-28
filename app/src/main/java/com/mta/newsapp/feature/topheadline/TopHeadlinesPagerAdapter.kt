package com.mta.newsapp.feature.topheadline

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mta.newsapp.feature.topheadline.category.CategoryNewsArticleFragment
import com.mta.newsapp.feature.topheadline.us.UsNewsArticleFragment

class TopHeadlinesPagerAdapter constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

  override fun getItemCount(): Int = TabNamesConstants.tabNamesList.size

  override fun createFragment(position: Int): Fragment {

    val fragment = when (position) {
      0 -> {
        UsNewsArticleFragment.newInstance()
      }

      else -> {
        CategoryNewsArticleFragment.newInstance(
          category = TabNamesConstants.tabNamesList[position].lowercase()
        )
      }
    }

    /* fragment.arguments = Bundle().apply {
       // The object is just an integer.
       putInt(ARG_OBJECT, position + 1)
     }*/

    return fragment
  }
}
