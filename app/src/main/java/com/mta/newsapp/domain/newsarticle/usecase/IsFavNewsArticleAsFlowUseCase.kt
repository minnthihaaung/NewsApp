package com.mta.newsapp.domain.newsarticle.usecase

import com.mta.newsapp.domain.FlowUseCase
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import com.mta.newsapp.domain.newsarticle.usecase.IsFavNewsArticleAsFlowUseCase.Params
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFavNewsArticleAsFlowUseCase @Inject constructor(private val newsArticleRepository: NewsArticleRepository) :
  FlowUseCase<Params, Boolean>() {

  override fun provide(params: Params): Flow<Boolean> {
    return newsArticleRepository.isFavNewsArticleAsFlow(url = params.url)
  }

  data class Params(val url: String)
}