object Libs {

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"

    object BuildPlugins {
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidGradlePluginVersion}"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object Android {
        const val supportAnnotations = "androidx.annotation:annotation:${Versions.androidSupportVersion}"
        const val supportAppcompat = "androidx.appcompat:appcompat:${Versions.androidSupportVersion}"
        const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
        const val ktxCore = "androidx.core:core-ktx:${Versions.androidSupportVersion}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
        const val ktxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle_runtime_ktx}"
        const val courotineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.courotineCore}"
        const val courotineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.courotineCore}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle_runtime_ktx}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle_runtime_ktx}"
        const val navigation = "androidx.navigation:navigation-runtime-ktx:${Versions.navigation}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val dataBinding = "androidx.databinding:viewbinding:${Versions.dataBinding}"
        const val multiDex = "androidx.multidex:multidex:${Versions.multiDexVersion}"
        const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.work_version}"
        const val workMultiprocess = "androidx.work:work-multiprocess:${Versions.work_version}"

    }
    object CACHING {
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val prefDataStore = "androidx.datastore:datastore-preferences:${Versions.prefDataStoreVersion}"
        const val protoDataStore = "androidx.datastore:datastore-core:${Versions.protoDataStoreVersion}"
        const val protobuf = "com.google.protobuf:protobuf-javalite:${Versions.protobuf}"
    }
    object DI {
        const val javax = "javax.inject:javax.inject:${Versions.javaxDI}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val hiltWork = "androidx.hilt:hilt-work:${Versions.hiltWorkVersion}"
        const val googleHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
        const val androidXHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompilerVersion}"

    }
    object UI {
        const val coilImageLoading = "io.coil-kt:coil:${Versions.coil}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
        const val mpAndroidChart = "com.github.PhilJay:MPAndroidChart:${Versions.mpAndroidChart}"
    }
    object Networking {
        //Networking libraries
        const val retrofite = "com.squareup.retrofit2:retrofit:${Versions.retrofite}"
        const val retrofiteCoventer = "com.squareup.retrofit2:converter-gson:${Versions.retrofite}"
        const val googleGson = "com.google.code.gson:gson:${Versions.retrofite}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object TestLibraries {
        const val jUnit = "junit:junit:${Versions.junitVersion}"
        const val androidXJunit = "androidx.test.ext:junit:${Versions.jUnitAndroidX}"
        const val jUnitRunner = "androidx.test:runner:${Versions.junitRunnerVersion}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
        const val hiltTesting = "com.google.dagger:hilt-android-testing:2.41"
        const val mockito = "org.mockito:mockito-android:2.21.0"
        const val androidxCoreTesting = "androidx.arch.core:core-testing:2.2.0"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"

    }

}