package com.oxygen.dependencies

import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo
import org.gradle.kotlin.dsl.kotlin

/**
 * @author Yamushev Igor
 * @since  5/19/21
 */
fun DependencyHandler.kotlinDeps() {
  implementation(kotlin("stdlib-jdk7", Versions.kotlin))
}

fun DependencyHandler.tests() {
  testImplementation(Dependencies.jUnit)
  testImplementation(Dependencies.mockK)
}

fun DependencyHandler.androidTests() {
  androidTestImplementation(Dependencies.androidJUnit)
  androidTestImplementation(Dependencies.androidEspresso)
}

fun DependencyHandler.mvp() {
  implementation(Dependencies.moxy)
  implementation(Dependencies.moxyAndroidX)
  kapt(Dependencies.moxyProcessor)
}

fun DependencyHandler.jetpackLibs() {
  implementation(Dependencies.appCompat)
  implementation(Dependencies.multiDex)
  implementation(Dependencies.core)
  implementation(Dependencies.recyclerView)
  implementation(Dependencies.cardView)
  implementation(Dependencies.constraintLayout)
  implementation(Dependencies.activity)
  implementation(Dependencies.fragment)
  implementation(Dependencies.preferences)
}

fun DependencyHandler.navigation() {
  implementation(Dependencies.navigationFragment)
  implementation(Dependencies.navigationUi)
}

fun DependencyHandler.material() {
  implementation(Dependencies.material)
}

fun DependencyHandler.googlePlay() {
  implementation(Dependencies.googlePlayLocation)
}

fun DependencyHandler.dagger() {
  implementation(Dependencies.dagger)
  kapt(Dependencies.daggerCompiler)
}

fun DependencyHandler.daggerAndroid() {
  implementation(Dependencies.daggerAndroid)
  implementation(Dependencies.daggerAndroidSupport)
  kapt(Dependencies.daggerAndroidProcessor)
}

fun DependencyHandler.rxJava() {
  implementation(Dependencies.rxJava)
  implementation(Dependencies.rxKotlin)
  implementation(Dependencies.rxAndroid)
  implementation(Dependencies.rxJavaAdapter)
}

fun DependencyHandler.uiLayer() {
  implementation(Dependencies.validato)
}

fun DependencyHandler.other() {
  implementation(Dependencies.optional)
}

fun DependencyHandler.loggers() {
  implementation(Dependencies.timber)
}

fun DependencyHandler.dataLayer() {
  implementation(Dependencies.dataStore)
  implementation(Dependencies.dataStoreRx)
  network()
}

private fun DependencyHandler.network() {
  implementation(Dependencies.retrofit)
  implementation(Dependencies.kotlinSerializationJson)
  implementation(Dependencies.jsonConverter)
  implementation(Dependencies.okHttp)
  implementation(Dependencies.okHttpLogger)
}

private fun DependencyHandler.testImplementation(name: Any) {
  add("testImplementation", name)
}

private fun DependencyHandler.androidTestImplementation(name: Any) {
  add("androidTestImplementation", name)
}

fun DependencyHandler.implementation(
  dependencyNotation: String,
  dependencyConfiguration: Action<ExternalModuleDependency>
): ExternalModuleDependency = addDependencyTo(
  this, "implementation", dependencyNotation, dependencyConfiguration
)

fun DependencyHandler.implementation(name: Any) {
  add("implementation", name)
}

private fun DependencyHandler.api(name: Any) {
  add("api", name)
}

private fun DependencyHandler.compileOnly(name: Any) {
  add("compileOnly", name)
}

private fun DependencyHandler.kapt(name: Any) {
  add("kapt", name)
}