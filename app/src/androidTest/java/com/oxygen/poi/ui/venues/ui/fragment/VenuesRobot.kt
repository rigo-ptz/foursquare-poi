package com.oxygen.poi.ui.venues.ui.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.oxygen.poi.R
import com.oxygen.poi.core.BaseRobot
import com.oxygen.poi.core.RecyclerItemCountAssertion
import org.hamcrest.Matchers.greaterThan

/**
 * @author Yamushev Igor
 * @since  5/25/21
 */
class VenuesRobot : BaseRobot() {

  private lateinit var venuesScenario: FragmentScenario<VenuesFragment>

  fun open() {
    venuesScenario = launchFragmentInContainer(themeResId = R.style.Theme_App)

    venuesScenario.onFragment { fragment ->
      // Set the graph on the TestNavHostController
      navController.setGraph(R.navigation.nav_graph_main)

      // Make the NavController available via the findNavController() APIs
      Navigation.setViewNavController(fragment.requireView(), navController)
    }
  }

  fun await(delay: Long) {
    Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(delay))
  }

  fun checkRecyclerContainsData() {
    view(R.id.rvVenues).check(RecyclerItemCountAssertion.withItemCount(greaterThan(1)))
  }

}

fun venues(func: VenuesRobot.() -> Unit) = VenuesRobot().apply { func() }