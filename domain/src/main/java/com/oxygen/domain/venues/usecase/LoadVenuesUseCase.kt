package com.oxygen.domain.venues.usecase

import com.oxygen.domain.venues.model.VenueModel
import com.oxygen.domain.venues.repo.VenuesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
class LoadVenuesUseCase @Inject constructor(
  private val venuesRepository: VenuesRepository
) {

  fun loadVenues(
    lat: Double,
    lon: Double,
    query: String? = null
  ): Single<List<VenueModel>> = venuesRepository.searchVenues(lat, lon, query)

}