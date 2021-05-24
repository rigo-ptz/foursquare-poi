package com.oxygen.poi.ui.splash.ui.presenter

import com.oxygen.poi.core.PresenterTest
import com.oxygen.poi.ui.splash.ui.view.`SplashView$$State`
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

/**
 * @author Yamushev Igor
 * @since 5/24/21
 */
class SplashFragmentPresenterTest : PresenterTest() {

  @MockK
  lateinit var viewState: `SplashView$$State`

  private lateinit var presenter: SplashFragmentPresenter


  @Before
  fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
    presenter = SplashFragmentPresenter()
    presenter.setViewState(viewState)
  }

  @Test
  fun `init called`() {
    //given

    // when
    presenter.init()

    // then
    verify(exactly = 0) { viewState.navigateToVenues() }
    testSchedulerRule.testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
    verify(exactly = 1) { viewState.navigateToVenues() }
  }

}

