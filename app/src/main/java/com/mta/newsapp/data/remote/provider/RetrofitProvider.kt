package com.mta.newsapp.data.remote.provider

import android.content.Context
import com.mta.newsapp.data.remote.jsonadapter.LocalDateTimeJsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {

  private var retrofit: Retrofit? = null

  fun createRetrofit(context: Context): Retrofit {

    if (retrofit == null) {

      val moshi = Moshi.Builder()
        .add(LocalDateTimeJsonAdapter())
        .build()

      retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(OkHttpProvider.createOkHttpClient(context))
        .build()
    }

    return retrofit!!
  }
}