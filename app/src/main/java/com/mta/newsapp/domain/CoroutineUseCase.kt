package com.mta.newsapp.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<I, O> {

  suspend fun execute(params: I): O {
    return withContext(Dispatchers.IO) {
      provide(params)
    }
  }

  protected abstract suspend fun provide(params: I): O
}