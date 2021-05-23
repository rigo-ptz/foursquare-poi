package com.oxygen.poi.ui.splash.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.oxygen.poi.R
import com.oxygen.poi.core.ui.base.BaseFragment
import com.oxygen.poi.databinding.FragmentSplashBinding
import com.oxygen.poi.ui.splash.ui.presenter.SplashFragmentPresenter
import com.oxygen.poi.ui.splash.ui.view.SplashView
import dagger.Component
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import javax.inject.Inject

class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashView {

  @Inject
  @InjectPresenter
  lateinit var presenter: SplashFragmentPresenter

  @ProvidePresenter
  fun providePresenter() = presenter

  override val layoutRes: Int = R.layout.fragment_splash

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observeLoginState()
  }

  private fun observeLoginState() {
    /*observe(viewModel.isLoginRequired) {
      it.consume {
        Timber.d("$this")
        if (this)
          navigateToLogin()
        else
          navigateToStationsList()
      }
    }*/
  }

  private fun navigateToLogin() {
    Timber.d("navigateToLogin()")
    //findNavController().navigate(R.id.actionToLogin)
  }

  private fun navigateToStationsList() {
    Timber.d("navigateToStationsList()")
    //findNavController().navigate(R.id.actionToStations)
  }

  override fun goToLogin() {

  }

  override fun goToPlaces() {
    TODO("Not yet implemented")
  }

}