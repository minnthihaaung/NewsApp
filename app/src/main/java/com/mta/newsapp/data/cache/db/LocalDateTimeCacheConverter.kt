package com.mta.newsapp.data.cache.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ProvidedTypeConverter
class LocalDateTimeCacheConverter {

  companion object {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
  }

  @TypeConverter
  fun toJson(value: LocalDateTime): String {
    return value.format(formatter)
  }

  @TypeConverter
  fun fromJson(value: String): LocalDateTime {
    return LocalDateTime.parse(value, formatter)
  }
}