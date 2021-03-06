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
  @ContributesAndroidInjector
  abstract fun splashFragment(): SplashFragment

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