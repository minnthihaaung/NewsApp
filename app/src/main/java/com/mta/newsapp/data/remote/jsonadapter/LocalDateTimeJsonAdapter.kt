package com.mta.newsapp.data.remote.jsonadapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeJsonAdapter {

  companion object {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  }

  @ToJson
  fun toJson(value: LocalDateTime): String {
    return value.format(formatter)
  }

  @FromJson
  fun fromJson(value: String): LocalDateTime {
    return LocalDateTime.parse(value, formatter)
  }
}