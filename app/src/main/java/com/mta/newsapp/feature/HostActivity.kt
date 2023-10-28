package com.mta.newsapp.feature

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mta.newsapp.R
import com.mta.newsapp.core.BaseViewBindingActivity
import com.mta.newsapp.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : BaseViewBindingActivity<ActivityHostBinding>() {

  override val binding: ActivityHostBinding by lazy {
    ActivityHostBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    navController.addOnDestinationChangedListener { _, destination, _ ->

      when (destination.id) {
        R.id.topHeadlinesFragment -> {
          binding.toolbar.visibility = View.VISIBLE
          binding.bottomNavigation.visibility = View.VISIBLE
        }

        R.id.savedNewsFragment -> {
          binding.toolbar.visibility = View.VISIBLE
          binding.bottomNavigation.visibility = View.VISIBLE
        }

        else -> {
          binding.toolbar.visibility = View.GONE
          binding.bottomNavigation.visibility = View.GONE
        }
      }
    }

    val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
    navView.setupWithNavController(navController)
  }
}