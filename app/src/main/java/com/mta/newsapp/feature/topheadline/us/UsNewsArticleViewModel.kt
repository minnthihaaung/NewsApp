package com.mta.newsapp.feature.topheadline.us

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.usecase.GetNewsArticlesAsFlowUseCase
import com.mta.newsapp.domain.newsarticle.usecase.GetNewsArticlesUseCase
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase.Params
import com.mta.newsapp.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UsNewsArticleViewModel @Inject constructor(
  private val getNewsArticlesAsFlowUseCase: GetNewsArticlesAsFlowUseCase,
  private val updateNewsArticleFavouriteUseCase: UpdateNewsArticleFavouriteUseCase
) :
  ViewModel() {

  val allNewsArticlesLiveData = SingleLiveEvent<Result<List<NewsArticle>>>()

  fun getNewsArticles() {
    viewModelScope.launch {
      val result = runCatching {

        getNewsArticlesAsFlowUseCase.execute(Unit).collectLatest { newsArticles ->
          allNewsArticlesLiveData.postValue(Result.success(newsArticles))
        }
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        allNewsArticlesLiveData.postValue(Result.failure(e))
      }

    }
  }

  fun updateNewsArticleFavourite(newsArticle: NewsArticle) {
    viewModelScope.launch {

      updateNewsArticleFavouriteUseCase.execute(
        Params(url = newsArticle.url, isFavourite = newsArticle.isFavourite.not())
      )

    }
  }
}