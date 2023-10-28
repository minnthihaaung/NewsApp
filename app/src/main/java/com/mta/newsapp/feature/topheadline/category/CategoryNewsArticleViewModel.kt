package com.mta.newsapp.feature.topheadline.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import com.mta.newsapp.domain.newsarticle.usecase.GetNewsArticlesWithCategoryAsFlowUseCase
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase.Params
import com.mta.newsapp.helper.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryNewsArticleViewModel @Inject constructor(
  private val getNewsArticlesWithCategoryAsFlowUseCase: GetNewsArticlesWithCategoryAsFlowUseCase,
  private val updateNewsArticleFavouriteUseCase: UpdateNewsArticleFavouriteUseCase,
) :
  ViewModel() {

  val newsArticleWithCategoryLiveData = SingleLiveEvent<Result<List<NewsArticle>>>()

  fun getNewsArticlesWithCategory(category: String) {

    viewModelScope.launch {
      val result = runCatching {

        getNewsArticlesWithCategoryAsFlowUseCase.execute(NewsCategory.valueOf(category))
          .collect { newsArticles ->
            newsArticleWithCategoryLiveData.postValue(Result.success(newsArticles))
          }
      }

      result.exceptionOrNull()?.let { e ->
        Timber.e(e)
        newsArticleWithCategoryLiveData.postValue(Result.failure(e))
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