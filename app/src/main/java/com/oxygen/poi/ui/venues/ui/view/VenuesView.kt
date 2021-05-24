package com.oxygen.poi.ui.venues.ui.view

import com.oxygen.poi.ui.venues.ui.model.VenueUiModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
interface VenuesView : MvpView {

  @AddToEndSingle
  fun showVenues(venues: List<VenueUiModel>)

  @AddToEndSingle
  fun showProgress(show: Boolean)

  @OneExecution
  fun showError(message: String)

}