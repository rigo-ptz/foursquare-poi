package com.oxygen.poi.core

import android.view.View
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.Matcher
import org.junit.Assert


/**
 * @author Yamushev Igor
 * @since  5/25/21
 */
open class BaseRobot {

  protected val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

  fun view(resId: Int): ViewInteraction = onView(withId(resId))

  fun typeText(resId: Int, text: String): ViewInteraction =
    view(resId).perform(ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())

  fun clickView(resId: Int): ViewInteraction = view(resId).perform(ViewActions.click())

  fun clickListItem(listRes: Int, position: Int) {
    onData(anything())
      .inAdapterView(allOf(withId(listRes)))
      .atPosition(position).perform(ViewActions.click())
  }

  fun checkViewDisplayed(resId: Int) = view(resId).check(matches(isDisplayed()))

  fun checkText(resId: Int, text: String): ViewInteraction =
    view(resId).check(matches(ViewMatchers.withText(text)))

  protected fun waitFor(delay: Long): ViewAction =
    object : ViewAction {
      override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()

      override fun getDescription() = "wait for $delay milliseconds"

      override fun perform(uiController: UiController, view: View?) {
        uiController.loopMainThreadForAtLeast(delay)
      }
    }

  fun assertCurrentDestination(id: Int) {
    Assert.assertEquals(navController.currentDestination?.id, id)
  }

}