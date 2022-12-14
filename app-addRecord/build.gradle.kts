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
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    // implementation(project(":feature:example"))
    implementation(project(":feature:chooseType"))
    implementation(project(":feature:updateType"))
    implementation(project(":feature:addType"))
    implementation(project(":core:data"))

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
    // compose-accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:$acompanist_version")
    implementation("com.google.accompanist:accompanist-navigation-animation:$acompanist_navigation_version")

    // test
    addTestImpl()

    //Dagger - Hilt
    addHiltImpl()

    // Timber
    implementation("com.jakewharton.timber:timber:$timber_version")
}