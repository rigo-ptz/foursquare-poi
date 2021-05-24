package com.oxygen.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.oxygen.data.core.HttpConstants
import com.oxygen.data.utils.libs.okhttp.OkHttpClientFactory
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun retrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl(HttpConstants.baseUrl + HttpConstants.apiVersion)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
      .addConverterFactory(Json { ignoreUnknownKeys = true } .asConverterFactory(MediaType.get("application/json")))
      .client(okHttpClient)
      .build()

  @Provides
  @Singleton
  fun okHttp(): OkHttpClient =
    OkHttpClientFactory.buildOkHttpClient()

}