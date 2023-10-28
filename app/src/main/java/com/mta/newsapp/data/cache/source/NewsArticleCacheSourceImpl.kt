package com.mta.newsapp.data.cache.source

import com.mta.newsapp.data.cache.db.NewsArticleDao
import com.mta.newsapp.data.cache.entity.NewsArticleCacheEntity
import com.mta.newsapp.data.common.newsarticle.NewsArticleCacheSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsArticleCacheSourceImpl @Inject constructor(private val newsArticleDao: NewsArticleDao) :
  NewsArticleCacheSource {

  override fun insertNewsArticles(newsArticleCacheEntities: List<NewsArticleCacheEntity>) {
    newsArticleDao.insertNewsArticles(newsArticleCacheEntities = newsArticleCacheEntities)
  }

  override fun insertNewsArticle(newsArticleCacheEntity: NewsArticleCacheEntity) {
    newsArticleDao.insertNewsArticle(newsArticleCacheEntity = newsArticleCacheEntity)
  }

  override fun getCacheNewsArticlesWithCategory(category: String): List<NewsArticleCacheEntity> {
    return newsArticleDao.getNewsArticlesWithCategory(category = category)
  }

  override fun getCacheNewsArticlesWithCategoryAsFlow(category: String): Flow<List<NewsArticleCacheEntity>> {
    return newsArticleDao.getNewsArticlesWithCategoryAsFlow(category = category)
  }

  override fun isFavNewsArticle(url: String): Boolean {
    return newsArticleDao.getFavNewsArticleWithUrl(url = url)?.isFavourite ?: false
  }

  override fun isFavNewsArticleAsFlow(url: String): Flow<Boolean> {
    return newsArticleDao.getFavNewsArticleWithUrlAsFlow(url = url)
      .map { item -> item?.isFavourite ?: false }
  }

  override fun updateNewsArticleFavourite(
    url: String,
    isFavourite: Boolean,
  ) {
    newsArticleDao.updateNewsArticleFavourite(url = url, isFavourite = isFavourite)
  }

  override fun getAllFavouriteNewsArticlesAsFlow(): Flow<List<NewsArticleCacheEntity>> {
    return newsArticleDao.getAllFavouriteNewsArticlesAsFlow()
  }
}