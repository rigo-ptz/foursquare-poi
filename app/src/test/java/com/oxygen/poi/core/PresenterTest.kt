package com.oxygen.poi.core

import org.junit.Rule

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
abstract class PresenterTest {

  @get:Rule
  val testSchedulerRule = TestSchedulerRule()

}