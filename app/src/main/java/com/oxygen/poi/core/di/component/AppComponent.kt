package com.oxygen.poi.core.di.component

import com.oxygen.poi.core.App
import com.oxygen.poi.core.di.module.AppModule
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
])
interface AppComponent : AndroidInjector<App>