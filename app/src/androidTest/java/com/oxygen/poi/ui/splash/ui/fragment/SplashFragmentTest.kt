package com.oxygen.poi.ui.splash.ui.fragment

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.oxygen.poi.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Yamushev Igor
 * @since 5/25/21
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SplashFragmentTest {

  @Test
  fun testNavigateToVenues() {
    splash {
      open()
      waitSplash(1500)
      assertCurrentDestination(R.id.venuesFragment)
    }
  }

}

