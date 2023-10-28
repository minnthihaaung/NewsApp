package com.mta.newsapp.data.remote.api.response.topheadline

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleSourceResponse(
  @Json(name = "id") val id: String?,
  @Json(name = "name") val name: String,
)