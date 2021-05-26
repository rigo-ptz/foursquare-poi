package com.oxygen.poi.ui.venues.ui.fragment

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.oxygen.poi.R
import com.oxygen.poi.utils.libs.espresso.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Yamushev Igor
 * @since 5/25/21
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class VenuesFragmentTest {

  @Before
  fun setup() {
    IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
  }

  @After
  fun tearDown() {
    IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
  }

  @Test
  fun inputIsVisible() {
    venues {
      open()
      checkViewDisplayed(R.id.search)
    }
  }

  // TODO inject MockLocationProvider into the fragment
  /*@Test
  fun inputShowProgress() {
    venues {
      open()
      typeText(R.id.input, "hes")
      await(1000)
      checkViewDisplayed(R.id.progressBar)
      await(5000)
      checkRecyclerContainsData()
    }
  }*/

}