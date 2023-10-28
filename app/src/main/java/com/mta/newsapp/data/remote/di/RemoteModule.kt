package com.mta.newsapp.data.remote.di

import android.content.Context
import com.mta.newsapp.data.common.newsarticle.NewsArticleRemoteSource
import com.mta.newsapp.data.remote.api.NewsApiService
import com.mta.newsapp.data.remote.provider.RetrofitProvider
import com.mta.newsapp.data.remote.source.NewsArticleRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

  companion object {
    @Provides
    @Singleton
    fun provideNewsApiService(@ApplicationContext context: Context): NewsApiService {
      return RetrofitProvider.createRetrofit(context = context).create(NewsApiService::class.java)
    }
  }

  @Binds
  abstract fun newsArticleRemoteSource(newsArticleRemoteSource: NewsArticleRemoteSourceImpl): NewsArticleRemoteSource
}