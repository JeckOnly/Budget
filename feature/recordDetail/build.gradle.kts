plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    // compose basic
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
}

dependencies {

    // modules
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:navigation"))
    implementation(project(":util"))

    // core
    implementation("androidx.core:core-ktx:$core_ktx_version")

    // compose
    addComposeBasic()
    // compose-extension
    implementation("androidx.activity:activity-compose:$activity_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintlayout_compose_version")
    implementation("androidx.navigation:navigation-compose:$navigation_compose_version")// 官方导航库
    implementation("androidx.hilt:hilt-navigation-compose:$androidx_hilt_version")
    implementation("io.coil-kt:coil-compose:$coil_compose_version")
    // compose-accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$acompanist_version")
    implementation("com.google.accompanist:accompanist-navigation-animation:$acompanist_navigation_version")

    // compose-third
    implementation("com.airbnb.android:lottie-compose:$lottie_version")

    // test
    addTestImpl()

    //Dagger - Hilt
    addHiltImpl()

    // Timber
    implementation("com.jakewharton.timber:timber:$timber_version")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$desugar_jdk_libs_version")
}