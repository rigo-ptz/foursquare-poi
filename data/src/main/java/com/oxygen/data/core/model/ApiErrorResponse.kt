package com.oxygen.data.core.model

import kotlinx.serialization.Serializable

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
@Serializable
data class ApiErrorResponse(
  val meta: ApiMetaResponse
)
