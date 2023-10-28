package com.mta.newsapp.domain.newsarticle.usecase

import com.mta.newsapp.domain.FlowUseCase
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsArticlesWithCategoryAsFlowUseCase @Inject constructor(private val newsArticleRepository: NewsArticleRepository) :
  FlowUseCase<NewsCategory, List<NewsArticle>>() {

  override fun provide(params: NewsCategory): Flow<List<NewsArticle>> {
    return newsArticleRepository.getNewsArticlesWithCategoryAsFlow(category = params)
  }
}