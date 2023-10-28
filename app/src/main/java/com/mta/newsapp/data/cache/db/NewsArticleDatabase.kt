package com.mta.newsapp.data.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mta.newsapp.data.cache.entity.NewsArticleCacheEntity

@Database(entities = [NewsArticleCacheEntity::class], version = 1)
@TypeConverters(LocalDateTimeCacheConverter::class)
abstract class NewsArticleDatabase : RoomDatabase() {

  abstract fun newsArticleDao(): NewsArticleDao

}