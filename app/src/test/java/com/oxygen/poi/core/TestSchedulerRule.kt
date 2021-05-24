package com.oxygen.poi.core

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * @author Yamushev Igor
 * @since  5/24/21
 */
class TestSchedulerRule : TestRule {

  val testScheduler by lazy { TestScheduler() }
  private val trampolineScheduler by lazy { Schedulers.trampoline() }

  override fun apply(base: Statement?, description: Description?): Statement =
    object : Statement() {
      override fun evaluate() {
        try {
          RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
          RxJavaPlugins.setIoSchedulerHandler { testScheduler }
          RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
          RxJavaPlugins.setSingleSchedulerHandler { testScheduler }
          RxAndroidPlugins.setInitMainThreadSchedulerHandler { trampolineScheduler }
          base?.evaluate()
        } finally {
          RxJavaPlugins.reset()
          RxAndroidPlugins.reset()
        }
      }
    }
}
