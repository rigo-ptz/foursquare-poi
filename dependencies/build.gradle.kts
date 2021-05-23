plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.oxygen.dependencies"
version = "SNAPSHOT"

repositories {
    google()
    jcenter()
    gradlePluginPortal()
}

gradlePlugin {
    plugins.register("App dependencies") {
        id = "dependencies"
        implementationClass = "com.oxygen.dependencies.DependenciesPlugin"
    }
}