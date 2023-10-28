package com.mta.newsapp.data.remote.auth

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val newRequest = chain.request().newBuilder()
      .addHeader("Authorization", "614d49ce98764ec99fd2b2f0d49af3b3")

    return chain.proceed(newRequest.build())
  }
}