import Config.Application.applicationId
import Versions.Android.appVersionCode
import Versions.Android.appVersionName
import Versions.Android.compileSDKVersion
import Versions.Android.minSdkVersion
import Versions.Android.targetSdkVersion

plugins {
    kotlin("android")
    id("com.android.application")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = applicationId
    compileSdk = compileSDKVersion

    defaultConfig {
        applicationId = Config.Application.applicationId
        minSdk = minSdkVersion
        targetSdk = targetSdkVersion
        versionCode = appVersionCode
        versionName = appVersionName
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            "String",
            name = Config.BuildFurniture.BASE_URL,
            "\"https://api.freecurrencyapi.com/\""
        )
        buildConfigField(
            "String",
            name = Config.BuildFurniture.BASE_URL,
            "\"fca_live_jj7Ad7xnxW49KWVHwKUUFWuHYEaAkihFqbnI14jF\""
        )
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = false
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    //==================== Support Library ============
    implementation(Libs.Android.ktxCore)
    implementation(Libs.Android.supportAppcompat)
    implementation(Libs.Android.androidMaterial)
    implementation(Libs.Android.dataBinding)
    implementation(Libs.Android.navigationFragment)
    implementation(Libs.Android.navigation)

    //Lifecycle
    implementation(Libs.Android.livedata)
    implementation(Libs.Android.ktxLifecycleRuntime)
    implementation(Libs.Android.viewModel)
    //Coroutines
    implementation(Libs.Android.courotineCore)
    implementation(Libs.Android.courotineAndroid)

    implementation(Libs.Android.fragmentKtx)
    implementation(Libs.Android.multiDex)
    implementation(Libs.Android.workRuntime)
    implementation(Libs.Android.workMultiprocess)
    //==================== Caching ============
    //room
    implementation(Libs.CACHING.roomRuntime)
    annotationProcessor(Libs.CACHING.roomCompiler)
    implementation(Libs.CACHING.roomKtx)
    //dataStore
    implementation(Libs.CACHING.prefDataStore)
    // Proto Data Store
    //implementation(Libs.CACHING.protoDataStore)
    //implementation(Libs.CACHING.protobuf)


    //==================== DI ============
    implementation(Libs.DI.javax)
    implementation(Libs.DI.hiltAndroid)
    implementation(Libs.DI.hiltWork)
    kapt(Libs.DI.googleHiltCompiler)
    kapt(Libs.DI.androidXHiltCompiler)

    //==================== Networking ============
    implementation(Libs.Networking.retrofite)
    implementation(Libs.Networking.retrofiteCoventer)
    implementation(Libs.Networking.googleGson)
    implementation(Libs.Networking.okHttp)
    implementation(Libs.Networking.interceptor)


    //==================== Testing Library ============
    testImplementation(Libs.TestLibraries.jUnit)
    androidTestImplementation(Libs.TestLibraries.androidXJunit)
    androidTestImplementation(Libs.TestLibraries.espressoCore)

    //==================== Ui Library ============
    implementation(Libs.UI.constraintlayout)
    implementation(Libs.UI.swiperefreshlayout)
    implementation(Libs.UI.coilImageLoading)


}