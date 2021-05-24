package com.oxygen.poi.ui.venues.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
interface VenuesView : MvpView {

  @AddToEndSingle
  fun showVenues()

}