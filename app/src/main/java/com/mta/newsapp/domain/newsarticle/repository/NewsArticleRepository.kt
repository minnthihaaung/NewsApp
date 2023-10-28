package com.mta.newsapp.domain.newsarticle.repository

import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import kotlinx.coroutines.flow.Flow

interface NewsArticleRepository {

  fun getNewsArticles(
  ): List<NewsArticle>

  fun getNewsArticlesAsFlow(): Flow<List<NewsArticle>>

  fun getNewsArticlesWithCategory(
    category: NewsCategory,
  ): List<NewsArticle>

  fun getNewsArticlesWithCategoryAsFlow(category: NewsCategory): Flow<List<NewsArticle>>

  fun updateNewsArticleFavourite(
    url: String,
    isFavourite: Boolean,
  )

  fun isFavNewsArticleAsFlow(url: String): Flow<Boolean>

  fun getAllFavouriteNewsArticlesAsFlow(): Flow<List<NewsArticle>>
}