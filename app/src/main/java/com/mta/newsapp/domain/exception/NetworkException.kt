package com.mta.newsapp.domain.exception

data class NetworkException constructor(
  val errorBody: String? = null,
  var errorCode: Int = 0,
) : Exception()