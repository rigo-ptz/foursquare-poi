package com.oxygen.poi.ui.splash.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.oxygen.poi.R
import com.oxygen.poi.core.ui.base.BaseFragment
import com.oxygen.poi.databinding.FragmentSplashBinding
import com.oxygen.poi.ui.splash.ui.presenter.SplashFragmentPresenter
import com.oxygen.poi.ui.splash.ui.view.SplashView
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
    presenter.init()
  }

  override fun navigateToVenues() {
    Timber.d("navigateToVenues()")
    findNavController().navigate(R.id.actionToVenues)
  }

}