package com.oxygen.poi.utils.location_provider

import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.annotation.Keep
import androidx.annotation.RequiresPermission
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.oxygen.poi.utils.LocationUtils
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject

/**
 * @author Yamushev Igor
 * @since  4/21/21
 */
private const val UPDATE_INTERVAL_MS = 15_000L
private const val FASTEST_UPDATE_INTERVAL_MS = UPDATE_INTERVAL_MS / 2L


class RealLocationProvider(
  override var lifecycleOwner: LifecycleOwner?,
  context: Context
) : LocationProvider {

  private val highAccuracyRequest: LocationRequest by lazy {
    LocationRequest.create()
      .setInterval(UPDATE_INTERVAL_MS)
      .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS)
      .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
  }

  private val balancedRequest: LocationRequest by lazy {
    LocationRequest.create()
      .setInterval(UPDATE_INTERVAL_MS)
      .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS)
      .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
  }

  private val locationSubject = BehaviorSubject.create<Location>()
  private var locationDisposable: Disposable? = null

  private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
  private var locationCallback: LocationCallback? = null

  val lastKnownLocation: Location?
    get() = locationSubject.value

  @RequiresPermission(
    anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"]
  )
  override fun startLocationUpdates(priorityType: PriorityType, callback: (Location) -> Unit) {
    if (locationCallback == null) {
      val locationRequest = when (priorityType) {
        PriorityType.HIGH_ACCURACY -> highAccuracyRequest
        PriorityType.BALANCED_POWER_ACCURACY -> balancedRequest
      }

      locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
          locationResult ?: return
          locationSubject.onNext(locationResult.lastLocation)
        }
      }.also {
        fusedLocationClient.requestLocationUpdates(
          locationRequest,
          it,
          Looper.getMainLooper()
        )
      }
    }

    locationDisposable = locationSubject
      .distinctUntilChanged { location1, location2 ->
        // if distance is less than 500m then don't update
        LocationUtils.calculateDistanceBetween(location1, location2) < 500
      }
      .subscribe(callback)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  @Keep
  private fun onCreate() {
    // no-op
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  @Keep
  private fun onStop() {
    locationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
    locationDisposable?.takeUnless { it.isDisposed }?.dispose()
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  @Keep
  private fun onDestroy() {
    lifecycleOwner?.lifecycle?.removeObserver(this)
    lifecycleOwner = null
    locationCallback = null
    locationDisposable = null
  }

}