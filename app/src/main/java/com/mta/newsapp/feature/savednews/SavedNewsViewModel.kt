package com.mta.newsapp.feature.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.usecase.GetAllFavouriteNewsArticlesAsFlowUseCase
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase.Params
import com.mta.newsapp.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
  private val getAllFavouriteNewsArticlesAsFlowUseCase: GetAllFavouriteNewsArticlesAsFlowUseCase,
  private val updateNewsArticleFavouriteUseCase: UpdateNewsArticleFavouriteUseCase,
):ViewModel() {

  val allFavNewsArticlesLiveData = SingleLiveEvent<Result<List<NewsArticle>>>()

  fun getNewsArticles() {
    viewModelScope.launch {
      val result = runCatching {

        getAllFavouriteNewsArticlesAsFlowUseCase.execute(Unit).collectLatest { newsArticles ->
          allFavNewsArticlesLiveData.postValue(Result.success(newsArticles))
        }
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        allFavNewsArticlesLiveData.postValue(Result.failure(e))
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