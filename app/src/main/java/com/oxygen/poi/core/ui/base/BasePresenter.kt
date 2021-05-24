package com.oxygen.poi.core.ui.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>() {

  protected val compositeDisposable = CompositeDisposable()

  override fun onDestroy() {
    compositeDisposable.clear()
    super.onDestroy()
  }

}