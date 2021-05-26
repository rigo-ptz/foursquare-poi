package com.oxygen.poi.ui.splash.ui.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.oxygen.poi.R
import com.oxygen.poi.core.BaseRobot

/**
 * @author Yamushev Igor
 * @since  5/25/21
 */
class SplashRobot : BaseRobot() {

  private lateinit var splashScenario: FragmentScenario<SplashFragment>

  fun open() {
    splashScenario = launchFragmentInContainer()

    splashScenario.onFragment { fragment ->
      // Set the graph on the TestNavHostController
      navController.setGraph(R.navigation.nav_graph_main)

      // Make the NavController available via the findNavController() APIs
      Navigation.setViewNavController(fragment.requireView(), navController)
    }
  }

  fun waitSplash(delay: Long) {
    onView(isRoot()).perform(waitFor(delay))
  }

}

fun splash(func: SplashRobot.() -> Unit) = SplashRobot().apply { func() }