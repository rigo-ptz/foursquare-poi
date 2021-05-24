import com.oxygen.dependencies.*

plugins {
    id("dependencies")
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.4.30"
}

apply {
    from("$rootDir/poi.gradle.kts")
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

    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true

        multiDexEnabled = true

        val clientId: String by project.extra
        val clientSecret: String by project.extra

        buildConfigField("String", "clientId", clientId)
        buildConfigField("String", "clientSecret", clientSecret)
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("Boolean", "isDebug", "true")
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    kotlinDeps()
    implementation(project(":domain"))
    tests()
    dataLayer()
    dagger()
    rxJava()
    loggers()
}