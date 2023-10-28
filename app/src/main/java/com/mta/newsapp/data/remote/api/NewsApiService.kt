package com.mta.newsapp.data.remote.api

import com.mta.newsapp.data.remote.api.response.topheadline.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

  @GET("v2/top-headlines")
  fun getTopHeadlines(
    @Query("country") country: String = "us"
  ): Call<TopHeadlinesResponse>

  @GET("v2/top-headlines")
  fun getTopHeadlinesWithCategory(
    @Query("country") country: String = "us",
    @Query("category") category: String
  ): Call<TopHeadlinesResponse>

}