package com.oxygen.domain.venues.repo

import com.oxygen.domain.venues.model.VenueModel
import io.reactivex.rxjava3.core.Single

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
interface VenuesRepository {
  fun searchVenues(lat: Double, lon: Double, query: String?): Single<List<VenueModel>>
}