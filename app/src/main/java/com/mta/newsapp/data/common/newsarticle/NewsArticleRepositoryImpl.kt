package com.mta.newsapp.data.common.newsarticle

import com.mta.newsapp.data.common.mapper.NewsArticleToNewsArticleEntityMapper
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewsArticleRepositoryImpl @Inject constructor(
  private val newsArticleRemoteSource: NewsArticleRemoteSource,
  private val newsArticleCacheSource: NewsArticleCacheSource,
) :
  NewsArticleRepository {

  override fun getNewsArticles(): List<NewsArticle> {

    val remoteNewsArticles = newsArticleRemoteSource.getNewsArticles()

    val newsArticles = remoteNewsArticles.map { item ->
      val isFav = newsArticleCacheSource.isFavNewsArticle(url = item.url)

      item.copy(isFavourite = isFav)
    }

    newsArticleCacheSource.insertNewsArticles(
      newsArticleCacheEntities = newsArticles.map(
        NewsArticleToNewsArticleEntityMapper::mapToNewsArticleCacheEntity
      )
    )

    return newsArticleCacheSource.getCacheNewsArticlesWithCategory(category = NewsCategory.us.name)
      .map { item -> item.mapToNewsArticle() }.sortedByDescending { it.publishedAt }
  }

  override fun getNewsArticlesAsFlow(): Flow<List<NewsArticle>> {
    return channelFlow {

      newsArticleCacheSource.getCacheNewsArticlesWithCategoryAsFlow(
        category = NewsCategory.us.name
      ).collect {
        send(it.map { item -> item.mapToNewsArticle() }.sortedByDescending { it.publishedAt })
      }

    }.onStart {
      getNewsArticles()
    }
  }

  override fun getNewsArticlesWithCategory(category: NewsCategory): List<NewsArticle> {

    val remoteNewsArticles =
      newsArticleRemoteSource.getNewsArticlesWithCategory(category = category.name)

    val newsArticles = remoteNewsArticles.map { item ->
      val isFav = newsArticleCacheSource.isFavNewsArticle(url = item.url)

      item.copy(isFavourite = isFav)
    }

    newsArticleCacheSource.insertNewsArticles(
      newsArticleCacheEntities = newsArticles.map(
        NewsArticleToNewsArticleEntityMapper::mapToNewsArticleCacheEntity
      )
    )

    return newsArticleCacheSource.getCacheNewsArticlesWithCategory(category = category.name)
      .map { item -> item.mapToNewsArticle() }.sortedByDescending { it.publishedAt }
  }

  override fun getNewsArticlesWithCategoryAsFlow(category: NewsCategory): Flow<List<NewsArticle>> {
    return channelFlow {

      newsArticleCacheSource.getCacheNewsArticlesWithCategoryAsFlow(
        category = category.name
      ).collect {
        send(it.map { item -> item.mapToNewsArticle() }.sortedByDescending { it.publishedAt })
      }

    }.onStart {
      getNewsArticlesWithCategory(category = category)
    }
  }

  override fun updateNewsArticleFavourite(
    url: String,
    isFavourite: Boolean,
  ) {
    newsArticleCacheSource.updateNewsArticleFavourite(url = url, isFavourite = isFavourite)
  }

  override fun isFavNewsArticleAsFlow(url: String): Flow<Boolean> {
    return newsArticleCacheSource.isFavNewsArticleAsFlow(url = url)
  }

  override fun getAllFavouriteNewsArticlesAsFlow(): Flow<List<NewsArticle>> {
    return channelFlow {
      newsArticleCacheSource.getAllFavouriteNewsArticlesAsFlow().collect {
        send(it.map { item -> item.mapToNewsArticle() }.sortedByDescending { it.publishedAt })
      }
    }
  }
}