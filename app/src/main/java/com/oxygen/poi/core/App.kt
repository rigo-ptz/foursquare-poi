package com.oxygen.poi.core

import android.content.Context
import androidx.multidex.MultiDex
import com.oxygen.poi.core.di.component.DaggerAppComponent
import com.oxygen.poi.core.di.module.AppModule
import com.oxygen.poi.utils.libs.timber.DebugTimberLogger
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * @author Yamushev Igor
 * @since  5/23/21
 */
class App : DaggerApplication() {

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  override fun onCreate() {
    super.onCreate()
    initLogging()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerAppComponent.builder()
      .appModule(AppModule(this))
      .build()

  private fun initLogging() {
    Timber.plant(DebugTimberLogger())
  }

}