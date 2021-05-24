package com.oxygen.data.utils.libs.okhttp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author Yamushev Igor
 * @since  4/19/21
 */
object OkHttpClientFactory {

  fun buildOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder().run {
      connectTimeout(5, TimeUnit.SECONDS)
      readTimeout(5, TimeUnit.SECONDS)
      writeTimeout(5, TimeUnit.SECONDS)
    }

    // add logs
    val logger = HttpLoggingInterceptor { message ->
      Timber.tag("OkHttp").d(message)
    }.apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
    builder.addInterceptor(logger)

    return builder.build()
  }

}