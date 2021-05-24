package com.oxygen.poi.core.di.component

import com.oxygen.data.di.DataModule
import com.oxygen.poi.core.App
import com.oxygen.poi.core.di.module.AppModule
import com.oxygen.poi.ui.main.di.MainModule
import com.oxygen.poi.ui.splash.di.SplashModule
import com.oxygen.poi.ui.venues.di.VenuesModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * @author Yamushev Igor
 * @since  4/18/21
 */
@Singleton
@Component(modules = [
  AndroidInjectionModule::class,
  AppModule::class,
  MainModule::class,
  SplashModule::class,
  VenuesModule::class,
  DataModule::class
])
interface AppComponent : AndroidInjector<App>