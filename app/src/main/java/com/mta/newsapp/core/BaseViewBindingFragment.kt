package com.mta.newsapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<VB : ViewBinding> : Fragment() {

  private var _binding: ViewBinding? = null

  @Suppress("UNCHECKED_CAST")
  protected val binding
    get() = _binding!! as VB

  protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = bindingInflater(inflater)

    onBindView()

    return binding.root
  }

  protected open fun onBindView() {
    //Do Nothing
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}