package com.mta.newsapp.data.common.newsarticle

import com.mta.newsapp.data.cache.entity.NewsArticleCacheEntity
import kotlinx.coroutines.flow.Flow

interface NewsArticleCacheSource {

  fun insertNewsArticles(newsArticleCacheEntities: List<NewsArticleCacheEntity>)

  fun insertNewsArticle(newsArticleCacheEntity: NewsArticleCacheEntity)

  fun getCacheNewsArticlesWithCategory(category: String): List<NewsArticleCacheEntity>

  fun getCacheNewsArticlesWithCategoryAsFlow(category: String): Flow<List<NewsArticleCacheEntity>>

  fun isFavNewsArticle(url: String): Boolean

  fun isFavNewsArticleAsFlow(url: String): Flow<Boolean>

  fun updateNewsArticleFavourite(
    url: String,
    isFavourite: Boolean
  )

  fun getAllFavouriteNewsArticlesAsFlow(): Flow<List<NewsArticleCacheEntity>>
}