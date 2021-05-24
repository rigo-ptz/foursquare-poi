import com.oxygen.dependencies.*

plugins {
    id("dependencies")
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    kotlinDeps()
    tests()
    dagger()
    rxJava()
    loggers()
}