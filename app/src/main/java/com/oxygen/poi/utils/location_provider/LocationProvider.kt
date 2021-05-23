package com.oxygen.poi.utils.location_provider

import android.location.Location
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @author Yamushev Igor
 * @since  4/21/21
 */
enum class PriorityType {
  HIGH_ACCURACY,
  BALANCED_POWER_ACCURACY
}

interface LocationProvider : LifecycleObserver {
  var lifecycleOwner: LifecycleOwner?
  fun startLocationUpdates(priorityType: PriorityType = PriorityType.HIGH_ACCURACY, callback: (Location) -> Unit)
}