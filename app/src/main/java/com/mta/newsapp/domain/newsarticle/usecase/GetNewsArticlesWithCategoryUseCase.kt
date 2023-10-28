package com.mta.newsapp.domain.newsarticle.usecase

import com.mta.newsapp.domain.CoroutineUseCase
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import javax.inject.Inject

class GetNewsArticlesWithCategoryUseCase @Inject constructor(private val newsArticleRepository: NewsArticleRepository) :
  CoroutineUseCase<NewsCategory, List<NewsArticle>>() {

  override suspend fun provide(params: NewsCategory): List<NewsArticle> {
    return newsArticleRepository.getNewsArticlesWithCategory(category = params)
  }
}