package com.oxygen.data.venues.remote

import com.oxygen.data.venues.model.VenuesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
interface VenuesApi {

  @GET("venues/search")
  fun searchVenues(
    @Query("ll") latLong: String,
    @Query("query") query: String?,
    @Query("client_id") clientId: String,
    @Query("client_secret") clientSecret: String,
    @Query("v") version: String,
  ): Single<Response<VenuesResponse>>

}