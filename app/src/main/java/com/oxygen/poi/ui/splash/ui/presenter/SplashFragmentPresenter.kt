package com.oxygen.poi.ui.splash.ui.presenter

import android.content.Context
import com.oxygen.poi.ui.splash.ui.view.SplashView
import moxy.MvpPresenter
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  4/19/21
 */
class SplashFragmentPresenter @Inject constructor(
  //private val checkLoginRequiredUseCase: CheckLoginRequiredUseCase,
  private val context: Context
) : MvpPresenter<SplashView>() {

 /* private val _isLoginRequired = MutableLiveData<Consumable<Boolean>>()
  val isLoginRequired: LiveData<Consumable<Boolean>> = _isLoginRequired

  init {
    Single.timer(1, TimeUnit.SECONDS)
      .subscribeBy {
        _isLoginRequired.postValue(checkLoginRequiredUseCase.isLoginRequired().toConsumable())
      }
  }*/

}