package com.oxygen.poi.ui.splash.ui.presenter

import com.oxygen.poi.core.ui.base.BasePresenter
import com.oxygen.poi.ui.splash.di.SplashScoped
import com.oxygen.poi.ui.splash.ui.view.SplashView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  4/19/21
 */
@SplashScoped
class SplashFragmentPresenter @Inject constructor() : BasePresenter<SplashView>() {

  fun init() {
    Single.timer(1, TimeUnit.SECONDS)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeBy {
        viewState.navigateToVenues()
      }
      .addTo(compositeDisposable)
  }

}

