import com.oxygen.dependencies.*

plugins {
    id("dependencies")
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")

}

android {

    compileSdkVersion(Android.compileSdk)
    buildToolsVersion(Android.buildTools)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = Android.applicationId
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true

        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("Boolean", "isDebug", "true")
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    implementation(project(":data"))
    kotlinDeps()
    tests()
    mvp()
    uiLayer()
    androidTests()
    jetpackLibs()
    navigation()
    material()
    googlePlay()
    dagger()
    daggerAndroid()
    rxJava()
    loggers()
    other()
}