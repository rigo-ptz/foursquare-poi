package com.oxygen.poi.ui.venues.ui.fragment

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.view.isVisible
import com.jakewharton.rxbinding4.widget.textChanges
import com.oxygen.poi.R
import com.oxygen.poi.core.ui.base.BaseFragment
import com.oxygen.poi.databinding.FragmentVenuesBinding
import com.oxygen.poi.ui.venues.ui.adapter.VenuesAdapter
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel
import com.oxygen.poi.ui.venues.ui.presenter.VenuesFragmentPresenter
import com.oxygen.poi.ui.venues.ui.view.VenuesView
import com.oxygen.poi.utils.location_provider.PriorityType
import com.oxygen.poi.utils.location_provider.RealLocationProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import timber.log.Timber
import java.util.concurrent.TimeUnit
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

  private var scrollListener: ViewTreeObserver.OnScrollChangedListener? = null

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
    initSearchElevation()
    initSearchInput()
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

  private fun initSearchElevation() {
    Timber.d("initToolbarElevation()")
    scrollListener = object : ViewTreeObserver.OnScrollChangedListener {
      private val minElevation =
          binding.rvVenues.context.resources.getDimensionPixelOffset(R.dimen.default_search_elevation)

      private val maxElevation =
        binding.rvVenues.context.resources.getDimensionPixelOffset(R.dimen.search_elevation)

      override fun onScrollChanged() {
        if (binding.rvVenues.canScrollVertically(-1)) {
          binding.search.elevation = maxElevation.toFloat()
        } else {
          binding.search.elevation = minElevation.toFloat()
        }
      }
    }

    binding.rvVenues.viewTreeObserver.addOnScrollChangedListener(scrollListener)
  }

  private fun initSearchInput() {
    binding.input.textChanges()
      .filter { it.length >= 3 }
      .debounce(800, TimeUnit.MILLISECONDS)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        {
          val location = locationProvider.lastKnownLocation ?: return@subscribe
          presenter.loadStations(location = location, query = it.toString())
        },
        {
          Timber.e(it)
        }
      )
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