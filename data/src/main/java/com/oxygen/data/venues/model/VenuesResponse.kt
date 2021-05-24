package com.oxygen.data.venues.model

import com.oxygen.data.core.model.ApiMetaResponse
import kotlinx.serialization.Serializable

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
@Serializable
data class VenuesResponse(
  val meta: ApiMetaResponse,
  val response: VenuesListResponse
)

@Serializable
data class VenuesListResponse(
  val venues: List<VenueResponse>
)

@Serializable
data class VenueResponse(
  val id: String,
  val name: String,
  val location: LocationResponse,
)

@Serializable
data class LocationResponse(
  val address: String? = null,
  val lat: Double,
  val lng: Double,
  val distance: Int
)
