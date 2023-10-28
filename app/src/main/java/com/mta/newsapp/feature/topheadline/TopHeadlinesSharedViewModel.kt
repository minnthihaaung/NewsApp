package com.mta.newsapp.feature.topheadline

import androidx.lifecycle.ViewModel
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel

class TopHeadlinesSharedViewModel : ViewModel() {

  val onNewsArticleDetailClickLiveData = SingleLiveEvent<NewsArticle>()
}