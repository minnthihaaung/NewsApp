package com.mta.newsapp.domain.newsarticle.model

import java.time.LocalDateTime

data class NewsArticle(
  val sourceName: String,
  val title: String,
  val author: String?,
  val content: String,
  val description: String,
  val publishedAt: LocalDateTime,
  val url: String,
  val imageUrl: String,
  val category: NewsCategory,
  val isFavourite: Boolean = false,
)