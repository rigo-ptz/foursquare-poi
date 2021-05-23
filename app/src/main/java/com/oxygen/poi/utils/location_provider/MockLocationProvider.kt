package com.oxygen.poi.utils.location_provider

import android.location.Location
import android.location.LocationManager
import androidx.annotation.Keep
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author Yamushev Igor
 * @since  4/21/21
 */
class MockLocationProvider(
  override var lifecycleOwner: LifecycleOwner?
) : LocationProvider {

  private var locationUpdates: Observable<Location>? = null
  private var locationUpdatesDisposable: Disposable? = null

  private val coordinates = listOf(
    60.170187 to 24.930599,
    60.169418 to 24.931618,
    60.169818 to 24.932906,
    60.170005 to 24.935105,
    60.169108 to 24.936210,
    60.168355 to 24.934869,
    60.167560 to 24.932562,
    60.168254 to 24.931532,
    60.169012 to 24.930341,
    60.170085 to 24.929569
  )

  var mockedLocation: Location = Location(LocationManager.GPS_PROVIDER)
    private set

  init {
    lifecycleOwner?.lifecycle?.addObserver(this)
  }

  override fun startLocationUpdates(priorityType: PriorityType, callback: (Location) -> Unit) {
    locationUpdatesDisposable = locationUpdates?.subscribe(callback)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  @Keep
  private fun onCreate() {
    locationUpdates = Observable.interval(0L,10, TimeUnit.SECONDS)
      .map { coordinates[it.toInt() % coordinates.size] }
      .map {
        mockedLocation.apply {
          latitude = it.first
          longitude = it.second
        }
      }
      .observeOn(AndroidSchedulers.mainThread())
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  @Keep
  private fun onStop() {
    locationUpdatesDisposable?.takeUnless { it.isDisposed }?.dispose()
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  @Keep
  private fun onDestroy() {
    lifecycleOwner?.lifecycle?.removeObserver(this)
    lifecycleOwner = null

    locationUpdates = null
    locationUpdatesDisposable = null
  }

}