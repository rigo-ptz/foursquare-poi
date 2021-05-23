package com.oxygen.poi.ui.splash.di

import com.oxygen.poi.ui.splash.ui.fragment.SplashFragment
import com.oxygen.poi.ui.splash.ui.presenter.SplashFragmentPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

/**
 * @author Yamushev Igor
 * @since  4/18/21
 */
@Module
abstract class SplashModule {

  @SplashScoped
  @ContributesAndroidInjector(modules = [SplashSubComponentModule::class])
  abstract fun splashFragment(): SplashFragment

}

@Module
abstract class SplashSubComponentModule {

  /*@SplashScoped
  @Binds
  abstract fun bindSplashPresenter(presenter: SplashFragmentPresenter): SplashFragmentPresenter*/

}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(
  AnnotationTarget.ANNOTATION_CLASS,
  AnnotationTarget.CLASS,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
annotation class SplashScoped