package com.oxygen.poi.utils.libs.timber

import timber.log.Timber

/**
 * @author Yamushev Igor
 * @since  4/18/21
 */
class DebugTimberLogger : Timber.DebugTree() {

  override fun createStackElementTag(element: StackTraceElement): String? =
    String.format(
      "%s [%s() L:%s]",
      super.createStackElementTag(element),
      element.methodName,
      element.lineNumber
    )

}