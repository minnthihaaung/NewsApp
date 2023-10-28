package com.mta.newsapp.data.remote.api.response.topheadline

import com.mta.newsapp.domain.newsarticle.model.NewsArticle
import com.mta.newsapp.domain.newsarticle.model.NewsCategory
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class ArticleResponse(
  @Json(name = "author") val author: String?,
  @Json(name = "content") val content: String?,
  @Json(name = "description") val description: String?,
  @Json(name = "publishedAt") val publishedAt: LocalDateTime,
  @Json(name = "source") val source: ArticleSourceResponse,
  @Json(name = "title") val title: String,
  @Json(name = "url") val url: String,
  @Json(name = "urlToImage") val imageUrl: String?,
) {

  fun mapToNewsArticle(category: NewsCategory) = NewsArticle(
    sourceName = source.name,
    title = title,
    author = author,
    content = content ?: "Default Content",
    description = description ?: "Default Description",
    publishedAt = publishedAt,
    url = url,
    imageUrl = imageUrl
      ?: "https://www.niddk.nih.gov/-/media/Images/Components/Default-Social-Media-Images/News-Card.png",
    category = category
  )
}