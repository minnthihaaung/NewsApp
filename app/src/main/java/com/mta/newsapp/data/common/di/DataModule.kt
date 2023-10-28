package com.mta.newsapp.data.common.di

import com.mta.newsapp.data.common.newsarticle.NewsArticleRepositoryImpl
import com.mta.newsapp.domain.newsarticle.repository.NewsArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

  @Binds
  abstract fun newsArticleRepository(newsArticleRepository: NewsArticleRepositoryImpl): NewsArticleRepository
}