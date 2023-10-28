package com.mta.newsapp.domain.newsarticle.usecase

import com.mta.newsapp.domain.CoroutineUseCase
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import javax.inject.Inject

class GetNewsArticlesUseCase @Inject constructor(private val newsArticleRepository: NewsArticleRepository) :
  CoroutineUseCase<Unit, List<NewsArticle>>() {

  override suspend fun provide(params: Unit): List<NewsArticle> {
    return newsArticleRepository.getNewsArticles()
  }
}