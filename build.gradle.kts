import org.gradle.kotlin.dsl.repositories

buildscript {

    repositories {
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.co") }
        google()
        mavenCentral()

    }
    dependencies {
        classpath(Libs.BuildPlugins.androidPlugin)
        classpath(Libs.BuildPlugins.kotlinPlugin)
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
        classpath("com.android.tools.build:gradle:8.0.2")
        val nav_version = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("com.google.protobuf:protobuf-gradle-plugin:0.9.1")

    }


}


plugins {
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.protobuf") version "0.8.15" apply false
}

allprojects {
    repositories {
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.google.co") }
        google()
        mavenCentral()
    }
}







