package com.mta.newsapp.domain.newsarticle.usecase

import com.mta.newsapp.domain.FlowUseCase
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavouriteNewsArticlesAsFlowUseCase @Inject constructor(private val newsArticleRepository: NewsArticleRepository) :
  FlowUseCase<Unit, List<NewsArticle>>() {

  override fun provide(params: Unit): Flow<List<NewsArticle>> {
    return newsArticleRepository.getAllFavouriteNewsArticlesAsFlow()
  }
}