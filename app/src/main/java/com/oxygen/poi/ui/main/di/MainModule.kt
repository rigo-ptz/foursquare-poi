package com.oxygen.poi.ui.main.di

import com.oxygen.poi.core.di.scopes.ActivityScoped
import com.oxygen.poi.ui.main.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

  @ActivityScoped
  @ContributesAndroidInjector
  abstract fun mainActivity(): MainActivity

}