package com.oxygen.data.utils.extensions

import com.oxygen.data.core.model.ApiErrorResponse
import com.oxygen.domain.core.model.api.ApiMetaModel
import com.oxygen.domain.core.model.api.RetrofitException
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

/**
 * @author Yamushev Igor
 * @since  4/19/21
 */
fun <T> Single<Response<T>>.asRetrofitResponse() = this
  .subscribeOn(Schedulers.io())
  .map {
    val urlPath = it.raw().request().url().encodedPath()

    if (!it.isSuccessful) {
      val errorBody = it.errorBody()?.string()
      throw if (!errorBody.isNullOrEmpty()) {
        val errorResponse = Json { ignoreUnknownKeys = true }
          .decodeFromString<ApiErrorResponse>(errorBody)

        RetrofitException.httpErrorWithBody(
          urlPath,
          ApiMetaModel(
            errorResponse.meta.code,
            errorResponse.meta.errorDetail,
            errorResponse.meta.errorType,
            errorResponse.meta.requestId
          )
        )
      } else {
        RetrofitException.httpError(
          urlPath,
          it.message()
        )
      }
    }
    it
  }
  .onErrorResumeNext {
    Timber.tag("asRetrofitResponse()").e(it)
    when (it) {
      is SerializationException -> {
        Single.error(RetrofitException.serializationError(it.message))
      }
      is IOException -> {
        Single.error(RetrofitException.networkError(it))
      }
      is RetrofitException -> {
        Single.error(it)
      }
      else -> {
        Single.error(RetrofitException.unexpectedError(it))
      }
    }
  }

fun <T> Single<Response<T>>.asVoid() = this
  .asRetrofitResponse()

fun <T> Single<Response<T>>.asBody() = this
  .asRetrofitResponse()
  .map { it.body()!! }