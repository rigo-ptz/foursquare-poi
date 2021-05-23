package com.oxygen.poi.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings


/**
 * @author Yamushev Igor
 * @since  4/21/21
 */
object AppUtils {

  fun showAppSettings(context: Context) {
    Intent().apply {
      action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
      addCategory(Intent.CATEGORY_DEFAULT)
      data = Uri.parse("package:" + context.packageName)
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
      addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
    }.also {
      context.startActivity(it)
    }
  }

}