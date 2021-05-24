package com.oxygen.data.core

import retrofit2.Retrofit

abstract class BaseRepository<T>(
  retrofit: Retrofit,
  apiClass: Class<T>
) {
  protected val api: T = retrofit.create(apiClass)
}