package com.oxygen.data.venues.mapper

import com.oxygen.data.venues.model.VenuesResponse
import com.oxygen.domain.venues.model.LocationModel
import com.oxygen.domain.venues.model.VenueModel

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
object VenuesMapper {

  fun toDomain(venuesResponse: VenuesResponse): List<VenueModel> =
    venuesResponse.response.venues.map {
      VenueModel(
        it.id,
        it.name,
        LocationModel(
          it.location.address,
          it.location.lat,
          it.location.lon,
          it.location.distance,
        )
      )
    }

}