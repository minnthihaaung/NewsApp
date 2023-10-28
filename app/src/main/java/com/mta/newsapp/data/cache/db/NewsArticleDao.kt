package com.mta.newsapp.data.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mta.newsapp.data.cache.entity.NewsArticleCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertNewsArticles(newsArticleCacheEntities: List<NewsArticleCacheEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertNewsArticle(newsArticleCacheEntity: NewsArticleCacheEntity)

  @Query("SELECT * FROM newsarticlecacheentity WHERE category=:category")
  fun getNewsArticlesWithCategory(category: String): List<NewsArticleCacheEntity>

  @Query("SELECT * FROM newsarticlecacheentity WHERE category=:category")
  fun getNewsArticlesWithCategoryAsFlow(category: String): Flow<List<NewsArticleCacheEntity>>

  @Query("SELECT * FROM newsarticlecacheentity WHERE url=:url")
  fun getFavNewsArticleWithUrl(url: String): NewsArticleCacheEntity?

  @Query("SELECT * FROM newsarticlecacheentity WHERE url=:url")
  fun getFavNewsArticleWithUrlAsFlow(url: String): Flow<NewsArticleCacheEntity?>

  @Query("UPDATE newsarticlecacheentity SET isFavourite=:isFavourite WHERE url=:url")
  fun updateNewsArticleFavourite(
    url: String,
    isFavourite: Boolean,
  )

  @Query("SELECT * FROM newsarticlecacheentity WHERE isFavourite=1")
  fun getAllFavouriteNewsArticlesAsFlow(): Flow<List<NewsArticleCacheEntity>>
}