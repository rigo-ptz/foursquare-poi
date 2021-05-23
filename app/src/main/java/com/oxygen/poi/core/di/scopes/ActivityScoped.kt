package com.oxygen.poi.core.di.scopes

import javax.inject.Scope

/**
 * @author Yamushev Igor
 * @since  4/18/21
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(
  AnnotationTarget.ANNOTATION_CLASS,
  AnnotationTarget.CLASS,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
annotation class ActivityScoped
