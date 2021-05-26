package com.oxygen.domain.venues.usecase

import com.oxygen.domain.venues.model.LocationModel
import com.oxygen.domain.venues.model.VenueModel
import com.oxygen.domain.venues.repo.VenuesRepository
import com.oxygen.poi.core.TestSchedulerRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Yamushev Igor
 * @since 5/26/21
 */
class SearchVenuesUseCaseTest {

  @get:Rule
  val testSchedulerRule = TestSchedulerRule()

  @MockK
  private lateinit var venuesRepository: VenuesRepository

  private lateinit var useCase: SearchVenuesUseCase

  private val mockedVenueModelList by lazy { mockVenueModelsList() }

  @Before
  fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    useCase = SearchVenuesUseCase(venuesRepository)
  }

  @Test
  fun testLoadVenues() {
    // given
    every { venuesRepository.searchVenues(0.0, 0.0, null) } returns Single.just(mockedVenueModelList)

    // when
    val result = useCase.loadVenues(0.0, 0.0, null)

    // then
    result.test().assertValue(mockedVenueModelList)
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
}