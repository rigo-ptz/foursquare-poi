package com.oxygen.domain.core.model.api

data class ApiMetaModel(
  val code: Int,
  val errorDetail: String?,
  val errorType: String?,
  val requestId: String?
)