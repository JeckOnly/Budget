plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    implementation(project(":core:navigation"))

    // core
    implementation("androidx.core:core-ktx:$core_ktx_version")
    implementation("androidx.core:core-splashscreen:$core_splashscreen_version")

    // compose
    addComposeBasic()
    // compose-extension
    implementation("androidx.constraintlayout:constraintlayout-compose:$constraintlayout_compose_version")
    implementation("androidx.navigation:navigation-compose:$navigation_compose_version")// 官方导航库

    // Timber
    implementation("com.jakewharton.timber:timber:$timber_version")

    // date picker
    addComposeDatePicker()

    // test
    addTestImpl()
}