package com.mta.newsapp.data.cache.di

import android.content.Context
import androidx.room.Room
import com.mta.newsapp.data.cache.db.LocalDateTimeCacheConverter
import com.mta.newsapp.data.cache.db.NewsArticleDao
import com.mta.newsapp.data.cache.db.NewsArticleDatabase
import com.mta.newsapp.data.cache.source.NewsArticleCacheSourceImpl
import com.mta.newsapp.data.common.newsarticle.NewsArticleCacheSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

  companion object {

    @Provides
    @Singleton
    fun provideNewsArticleDb(@ApplicationContext context: Context): NewsArticleDatabase {
      return Room.databaseBuilder(context, NewsArticleDatabase::class.java, "news_article_db")
        .addTypeConverter(LocalDateTimeCacheConverter())
        .build()
    }

    @Provides
    @Singleton
    fun provideNewsArticleDao(newsArticleDatabase: NewsArticleDatabase): NewsArticleDao {
      return newsArticleDatabase.newsArticleDao()
    }
  }

  @Binds
  abstract fun newsArticleCacheSource(newsArticleCacheSource: NewsArticleCacheSourceImpl): NewsArticleCacheSource
}