package com.oxygen.poi.ui.splash.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle




/**
 * @author Yamushev Igor
 * @since  5/23/21
 */
interface SplashView : MvpView {

  @AddToEndSingle
  fun goToLogin()

  @AddToEndSingle
  fun goToPlaces()

}