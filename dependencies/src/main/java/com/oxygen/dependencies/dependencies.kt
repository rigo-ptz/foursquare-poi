package com.oxygen.dependencies

/**
 * @author Yamushev Igor
 * @since  5/19/21
 */

object Android {
  const val compileSdk = 30
  const val buildTools = "30.0.2"
  const val applicationId = "com.oxygen.poi_chargers"
  const val minSdkVersion = 21
  const val targetSdkVersion = 30
  const val versionCode = 1
  const val versionName = "1.0"
}

object Versions {
  const val kotlin = "1.4.32"
  const val dagger = "2.34.1"
  const val okHttp = "3.14.9"
  const val navigation = "2.3.5"
  const val dataStore = "1.0.0-alpha08"
  const val moxy = "2.2.2"
}

object Dependencies {
  // Tests
  const val jUnit = "junit:junit:4.13"
  const val androidJUnit = "androidx.test.ext:junit:1.1.2"
  const val androidEspresso = "androidx.test.espresso:espresso-core:3.3.0"
  const val mockK = "io.mockk:mockk:1.10.6"

  // MVP
  const val moxy = "com.github.moxy-community:moxy:${Versions.moxy}"
  const val moxyAndroidX = "com.github.moxy-community:moxy-androidx:${Versions.moxy}"
  const val moxyProcessor = "com.github.moxy-community:moxy-compiler:${Versions.moxy}"

  // Jetpack libs
  const val appCompat = "androidx.appcompat:appcompat:1.2.0"
  const val multiDex = "androidx.multidex:multidex:2.0.1"
  const val core = "androidx.core:core-ktx:1.3.2"
  const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"
  const val cardView = "androidx.cardview:cardview:1.0.0"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
  const val activity = "androidx.activity:activity-ktx:1.2.2"
  const val fragment = "androidx.fragment:fragment-ktx:1.3.2"
  const val preferences = "androidx.preference:preference-ktx:1.1.1"

  // Navigation
  const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
  const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

  // Material
  const val material = "com.google.android.material:material:1.3.0"

  // Google play
  const val googlePlayLocation = "com.google.android.gms:play-services-location:18.0.0"

  // Dagger
  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
  const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
  const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
  const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
  const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

  // Network
  const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
  const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava3:2.9.0"
  const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"
  const val jsonConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
  const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
  const val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

  // RxJava
  const val rxJava = "io.reactivex.rxjava3:rxjava:3.0.12"
  const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:3.0.1"
  const val rxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.0"

  // Loggers
  const val timber = "com.jakewharton.timber:timber:4.7.1"

  // Storage
  const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
  const val dataStoreRx = "androidx.datastore:datastore-preferences-rxjava3:${Versions.dataStore}"

  // other
  const val optional = "com.github.memoizr:retro-optional:0.2.0"

  // Field Validation (my library)
  const val validato = "com.github.rigo-ptz:Validato:1.1.1"

}