package com.oxygen.poi.core.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yamushev Igor
 * @since  4/18/21
 */
@Module
class AppModule(private val application: Application) {

  @Provides
  @Singleton
  fun applicationContext(): Context = application

}