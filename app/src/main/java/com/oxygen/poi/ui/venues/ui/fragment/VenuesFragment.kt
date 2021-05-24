package com.oxygen.poi.ui.venues.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.oxygen.poi.R
import com.oxygen.poi.core.ui.base.BaseFragment
import com.oxygen.poi.databinding.FragmentVenuesBinding
import com.oxygen.poi.ui.splash.ui.presenter.SplashFragmentPresenter
import com.oxygen.poi.ui.venues.ui.presenter.VenuesFragmentPresenter
import com.oxygen.poi.ui.venues.ui.view.VenuesView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
class VenuesFragment : BaseFragment<FragmentVenuesBinding>(), VenuesView {

  @Inject
  @InjectPresenter
  lateinit var presenter: VenuesFragmentPresenter

  @ProvidePresenter
  fun providePresenter() = presenter

  override val layoutRes: Int = R.layout.fragment_venues

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun showVenues() {

  }


}