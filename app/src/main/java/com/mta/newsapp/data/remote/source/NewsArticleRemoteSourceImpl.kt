package com.mta.newsapp.data.remote.source

import com.mta.newsapp.data.common.newsarticle.NewsArticleRemoteSource
import com.mta.newsapp.data.remote.api.NewsApiService
import com.mta.newsapp.domain.exception.NetworkException
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import javax.inject.Inject

class NewsArticleRemoteSourceImpl @Inject constructor(private val newsApiService: NewsApiService) :
  NewsArticleRemoteSource {

  override fun getNewsArticles(): List<NewsArticle> {
    val response = newsApiService.getTopHeadlines().execute()

    if (response.isSuccessful) {
      return response.body()?.articles?.map { item ->
        item.mapToNewsArticle(category = NewsCategory.us)
      } ?: throw NetworkException(
        errorCode = response.code()
      )
    } else {
      throw NetworkException(errorCode = response.code())
    }
  }

  override fun getNewsArticlesWithCategory(category: String): List<NewsArticle> {
    val response = newsApiService.getTopHeadlinesWithCategory(category = category).execute()

    if (response.isSuccessful) {
      return response.body()?.articles?.map { item ->
        item.mapToNewsArticle(category = NewsCategory.valueOf(category))
      } ?: throw NetworkException(
        errorCode = response.code()
      )
    } else {
      throw NetworkException(errorCode = response.code())
    }
  }
}