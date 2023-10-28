package com.mta.newsapp.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import java.time.LocalDateTime

@Entity
data class NewsArticleCacheEntity(
  val sourceName: String,
  val title: String,
  val author: String?,
  val content: String,
  val description: String,
  val publishedAt: LocalDateTime,
  @PrimaryKey
  val url: String,
  val imageUrl: String,
  val category: String,
  val isFavourite: Boolean
) {
  fun mapToNewsArticle() = NewsArticle(
    sourceName = sourceName,
    title = title,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    url = url,
    imageUrl = imageUrl,
    category = NewsCategory.valueOf(category),
    isFavourite = isFavourite
  )
}