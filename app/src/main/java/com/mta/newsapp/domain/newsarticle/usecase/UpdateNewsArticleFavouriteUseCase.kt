package com.mta.newsapp.domain.newsarticle.usecase

import com.mta.newsapp.domain.CoroutineUseCase
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import com.mta.newsapp.domain.newsarticle.usecase.UpdateNewsArticleFavouriteUseCase.Params
import javax.inject.Inject

class UpdateNewsArticleFavouriteUseCase @Inject constructor(private val newsArticleRepository: NewsArticleRepository) :
  CoroutineUseCase<Params, Unit>() {

  override suspend fun provide(params: Params) {
    newsArticleRepository.updateNewsArticleFavourite(
      url = params.url, isFavourite = params.isFavourite
    )
  }

  data class Params(
    val url: String,
    val isFavourite: Boolean,
  )
}