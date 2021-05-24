package com.oxygen.data.venues.repo

import com.oxygen.data.BuildConfig
import com.oxygen.data.core.BaseRepository
import com.oxygen.data.utils.extensions.asBody
import com.oxygen.data.venues.mapper.VenuesMapper
import com.oxygen.data.venues.remote.VenuesApi
import com.oxygen.domain.venues.model.VenueModel
import com.oxygen.domain.venues.repo.VenuesRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
class VenuesRepositoryImpl @Inject constructor(
  retrofit: Retrofit
) : BaseRepository<VenuesApi>(
  retrofit,
  VenuesApi::class.java
), VenuesRepository {

  override fun searchVenues(
    lat: Double,
    lon: Double,
    query: String?
  ): Single<List<VenueModel>> =
    api.searchVenues(
      "$lat,$lon",
      query,
      BuildConfig.clientId,
      BuildConfig.clientSecret,
      getVersion(Calendar.getInstance())
    )
        .asBody()
        .map { VenuesMapper.toDomain(it) }

  private fun getVersion(calendar: Calendar): String =
    SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).format(calendar.time)

}