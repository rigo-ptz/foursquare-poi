package com.oxygen.poi.ui.venues.ui.presenter

import android.location.Location
import com.oxygen.domain.core.model.api.RetrofitException
import com.oxygen.domain.venues.model.LocationModel
import com.oxygen.domain.venues.model.VenueModel
import com.oxygen.domain.venues.usecase.SearchVenuesUseCase
import com.oxygen.poi.core.PresenterTest
import com.oxygen.poi.ui.venues.ui.model.VenueUiModel
import com.oxygen.poi.ui.venues.ui.view.`VenuesView$$State`
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

/**
 * @author Yamushev Igor
 * @since 5/25/21
 */
class VenuesFragmentPresenterTest : PresenterTest() {

  @MockK
  lateinit var viewState: `VenuesView$$State`

  @MockK
  lateinit var venuesRepository: SearchVenuesUseCase

  @MockK
  lateinit var location: Location

  private lateinit var presenter: VenuesFragmentPresenter

  @Before
  fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    presenter = VenuesFragmentPresenter(venuesRepository)
    presenter.setViewState(viewState)
  }

  @Test
  fun `load stations - success flow`() {
    // given
    every { venuesRepository.loadVenues(0.0, 0.0, null) } returns Single.just(mockVenueModelsList())
    every { location.longitude } returns 0.0
    every { location.latitude } returns 0.0

    // when
    presenter.loadStations(location, null)

    // then
    verify(exactly = 1) { viewState.showProgress(true) }
    verify(exactly = 1) { venuesRepository.loadVenues(0.0, 0.0, null) }
    verify(exactly = 1) { viewState.showVenues(mockVenueUiModelsList()) }
  }

  @Test
  fun `load stations - error flow`() {
    // given
    every { venuesRepository.loadVenues(0.0, 0.0, null) } returns Single.error(
      RetrofitException(RetrofitException.Type.NETWORK, null, null, "error", Exception())
    )
    every { location.longitude } returns 0.0
    every { location.latitude } returns 0.0

    // when
    presenter.loadStations(location, null)

    // then
    verify(exactly = 1) { viewState.showProgress(true) }
    verify(exactly = 1) { venuesRepository.loadVenues(0.0, 0.0, null) }
    verify(exactly = 1) { viewState.showError("error") }
  }

  private fun mockVenueModelsList(): List<VenueModel> =
    (0..2).map {
      VenueModel(
        it.toString(),
        "name$it",
        LocationModel(
          "address$it",
          0.0,
          0.0,
          1
        )
      )
    }

  private fun mockVenueUiModelsList(): List<VenueUiModel> =
    (0..2).map {
      VenueUiModel(
        it.toString(),
        "name$it",
        "address$it",
      )
    }

}