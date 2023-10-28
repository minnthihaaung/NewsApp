package com.mta.newsapp.feature.newsdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.usecase.IsFavNewsArticleAsFlowUseCase
import com.mta.newsapp.domain.newsarticle.usecase.IsFavNewsArticleAsFlowUseCase.Params
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase
import com.mta.newsapp.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsArticleDetailViewModel @Inject constructor(
  private val isFavNewsArticleAsFlowUseCase: IsFavNewsArticleAsFlowUseCase,
  private val updateNewsArticleFavouriteUseCase: UpdateNewsArticleFavouriteUseCase,
) :
  ViewModel() {

  val isFavNewsArticlesLiveData = SingleLiveEvent<Boolean>()

  fun isFavNewsArticlesAsFlow(url: String) =
    isFavNewsArticleAsFlowUseCase.execute(Params(url = url))
      .onEach { isFavNewsArticlesLiveData.postValue(it) }
      .catch { t -> Timber.e(t) }
      .launchIn(viewModelScope)

  fun updateNewsArticleFavourite(
    url: String,
    isFavourite: Boolean,
  ) {
    viewModelScope.launch {

      updateNewsArticleFavouriteUseCase.execute(
        UpdateNewsArticleFavouriteUseCase.Params(
          url = url, isFavourite = isFavourite.not()
        )
      )

    }
  }
}