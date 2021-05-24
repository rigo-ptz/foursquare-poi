package com.oxygen.poi.ui.venues.di

import com.oxygen.poi.ui.venues.ui.fragment.VenuesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
@Module
abstract class VenuesModule {

  @VenuesScoped
  @ContributesAndroidInjector
  abstract fun splashFragment(): VenuesFragment

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
annotation class VenuesScoped