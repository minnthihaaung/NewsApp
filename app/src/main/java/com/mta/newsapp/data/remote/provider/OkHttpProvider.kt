package com.mta.newsapp.data.remote.provider

import android.content.Context
import com.mta.newsapp.data.remote.auth.AuthInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

object OkHttpProvider {

  private var okHttpClient: OkHttpClient? = null

  fun createOkHttpClient(context: Context): OkHttpClient {

    if (okHttpClient == null) {

      val okHttpClientBuilder = OkHttpClient.Builder()

      okHttpClientBuilder.addNetworkInterceptor(AuthInterceptor())

      val cache = Cache(
        File(context.cacheDir, "http_cache"),
        50L * 1024L * 1024L
      )

      return okHttpClientBuilder
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .cache(cache)
        .build()
    }

    return okHttpClient!!
  }
}