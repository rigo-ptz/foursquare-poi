package com.oxygen.domain.venues.model

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
data class VenueModel(
  val id: String,
  val name: String,
  val location: LocationModel,
)

data class LocationModel(
  val address: String,
  val lat: Double,
  val lon: Double,
  val distance: Int
)

