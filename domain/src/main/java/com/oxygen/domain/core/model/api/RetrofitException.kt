package com.oxygen.domain.core.model.api

import java.io.IOException

class RetrofitException(
  val type: Type,
  val url: String?,
  val apiMetaModel: ApiMetaModel?,
  message: String?,
  exception: Throwable?,
) : RuntimeException(message, exception) {

  companion object {
    fun httpErrorWithBody(url: String, apiMetaModel: ApiMetaModel): RetrofitException =
      RetrofitException(Type.HTTP, url, apiMetaModel, apiMetaModel.errorDetail, null)

    fun httpError(url: String, message: String): RetrofitException =
      RetrofitException(Type.HTTP, url, null, message, null)

    fun networkError(exception: IOException): RetrofitException =
      RetrofitException(Type.NETWORK, null, null, exception.message, exception)

    fun serializationError(message: String?): RetrofitException =
      RetrofitException(Type.SERIALIZATION, null, null, message, null)

    fun unexpectedError(exception: Throwable): RetrofitException =
      RetrofitException(Type.UNEXPECTED, null, null, exception.message, exception)
  }

  enum class Type {
    NETWORK,
    HTTP,
    SERIALIZATION,
    UNEXPECTED
  }
}
