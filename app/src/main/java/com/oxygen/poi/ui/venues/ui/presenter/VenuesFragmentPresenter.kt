package com.oxygen.poi.ui.venues.ui.presenter

import android.location.Location
import androidx.preference.PreferenceManager
import com.oxygen.domain.venues.model.VenueModel
import com.oxygen.domain.venues.usecase.SearchVenuesUseCase
import com.oxygen.poi.core.ui.base.BasePresenter
import com.oxygen.poi.ui.venues.di.VenuesScoped
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel
import com.oxygen.poi.ui.venues.ui.view.VenuesView
import com.oxygen.poi.utils.libs.espresso.EspressoIdlingResource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
@VenuesScoped
class VenuesFragmentPresenter @Inject constructor(
  private val searchVenuesUseCase: SearchVenuesUseCase
) : BasePresenter<VenuesView>() {

  private val searchSubject: PublishSubject<Pair<Location, String?>> = PublishSubject.create()

  init {
    initSearch()
  }

  private fun initSearch() {
    searchSubject
      .doOnNext { viewState.showProgress(true) }
      .switchMap { (location, query) ->
        EspressoIdlingResource.increment()
        searchVenuesUseCase
          .loadVenues(location.latitude, location.longitude, query)
          .toObservable()
      }
      .map { list ->
        list.map { VenueUiModel.from(it) }
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        {
          EspressoIdlingResource.decrement()
          viewState.showVenues(it)
        },
        {
          Timber.e(it)
          viewState.showError(it.message.orEmpty())
        }
      )
      .addTo(compositeDisposable)
  }

  fun loadStations(location: Location, query: String?) {
    searchSubject.onNext(location to query)
  }

}