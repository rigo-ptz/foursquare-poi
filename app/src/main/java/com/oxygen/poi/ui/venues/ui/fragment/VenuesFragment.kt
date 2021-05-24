package com.oxygen.poi.ui.venues.ui.fragment

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.oxygen.poi.R
import com.oxygen.poi.core.ui.base.BaseFragment
import com.oxygen.poi.databinding.FragmentVenuesBinding
import com.oxygen.poi.ui.splash.ui.presenter.SplashFragmentPresenter
import com.oxygen.poi.ui.venues.ui.adapter.VenuesAdapter
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel
import com.oxygen.poi.ui.venues.ui.presenter.VenuesFragmentPresenter
import com.oxygen.poi.ui.venues.ui.view.VenuesView
import com.oxygen.poi.utils.location_provider.PriorityType
import com.oxygen.poi.utils.location_provider.RealLocationProvider
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

  private lateinit var locationProvider: RealLocationProvider

  private lateinit var venuesAdapter: VenuesAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    locationProvider = RealLocationProvider(this, context)
  }

  override fun onStart() {
    super.onStart()
    checkOrRequestPermission(Manifest.permission.ACCESS_FINE_LOCATION) {
      initLocationUpdates()
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initRecycler()
  }

  private fun initRecycler() {
    venuesAdapter = VenuesAdapter().apply {
      setHasStableIds(true)
    }
    binding.rvVenues.apply {
      itemAnimator = null
      adapter = venuesAdapter
    }
  }

  override fun showVenues(venues: List<VenueUiModel>) {
    Timber.d("showVenues()")
    showProgress(false)
    venuesAdapter.setData(venues, binding.rvVenues)
  }

  override fun showProgress(show: Boolean) {
    Timber.d("showLoading($show)")
    binding.progressBar.isVisible = show
  }

  override fun showError(message: String) {
    Timber.d("showVenues($message)")
    showProgress(false)
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }

  @RequiresPermission(
    anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"]
  )
  private fun initLocationUpdates() {
    Timber.d("initLocationUpdates()")
    locationProvider.startLocationUpdates(PriorityType.BALANCED_POWER_ACCURACY) {
      Timber.d("LOCATION = $it")
      presenter.loadStations(location = it, query = null)
    }
  }


}