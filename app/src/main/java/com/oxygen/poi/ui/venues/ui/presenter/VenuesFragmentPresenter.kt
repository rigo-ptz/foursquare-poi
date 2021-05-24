package com.oxygen.poi.ui.venues.ui.presenter

import android.location.Location
import androidx.preference.PreferenceManager
import com.oxygen.domain.venues.model.VenueModel
import com.oxygen.domain.venues.usecase.SearchVenuesUseCase
import com.oxygen.poi.core.ui.base.BasePresenter
import com.oxygen.poi.ui.venues.di.VenuesScoped
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel
import com.oxygen.poi.ui.venues.ui.view.VenuesView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
@VenuesScoped
class VenuesFragmentPresenter @Inject constructor(
  private val searchVenuesUseCase: SearchVenuesUseCase
) : BasePresenter<VenuesView>() {

  fun loadStations(location: Location, query: String?) {
    searchVenuesUseCase
      .loadVenues(location.latitude, location.longitude, query)
      .doOnSubscribe {
        viewState.showProgress(true)
      }
      .map { list ->
        list.map { VenueUiModel.from(it) }
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        {
          viewState.showVenues(it)
        },
        {
          Timber.e(it)
          viewState.showError(it.message.orEmpty())
        }
      )
      .addTo(compositeDisposable)
  }

}