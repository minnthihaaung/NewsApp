package com.mta.newsapp.data.remote.api.response.topheadline

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopHeadlinesResponse(
  @Json(name = "status") val status: String,
  @Json(name = "totalResults") val totalResults: Int,
  @Json(name = "articles") val articles: List<ArticleResponse>,
)