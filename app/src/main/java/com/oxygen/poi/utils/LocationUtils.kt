package com.oxygen.poi.utils

import android.location.Location
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * @author Yamushev Igor
 * @since  4/21/21
 */
object LocationUtils {

  private const val EARTH_RADIUS_M = 6371009.0

  fun calculateDistanceBetween(location1: Location, location2: Location): Double {
    val dLat = Math.toRadians(location2.latitude - location1.latitude)
    val dLon = Math.toRadians(location2.longitude - location1.longitude)
    val a = sin(dLat / 2) * sin(dLat / 2) +
          cos(Math.toRadians(location1.latitude)) * cos(Math.toRadians(location2.latitude)) *
          sin(dLon / 2) * sin(dLon / 2)

    val c = 2 * atan2(sqrt(a), sqrt(1-a))
    return EARTH_RADIUS_M * c
  }

}