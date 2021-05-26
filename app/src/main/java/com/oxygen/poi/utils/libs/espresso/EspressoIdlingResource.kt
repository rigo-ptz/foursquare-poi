package com.oxygen.poi.utils.libs.espresso

import androidx.test.espresso.idling.CountingIdlingResource




/**
 * @author Yamushev Igor
 * @since  5/25/21
 */
object EspressoIdlingResource {

  private val RESOURCE = "GLOBAL"

  val countingIdlingResource = CountingIdlingResource(RESOURCE)

  fun increment() {
    countingIdlingResource.increment()
  }

  fun decrement() {
    countingIdlingResource.decrement()
  }

}