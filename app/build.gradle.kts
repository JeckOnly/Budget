plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.jeckonly.budget"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "v1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val release = getByName("release")
        release.apply {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    // compose basic
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_version
    }
    defaultConfig {

        resConfigs("en", "zh")
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}


dependencies {
    // modules
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    // implementation(project(":feature:example"))
    implementation(project(":feature:home"))
    implementation(project(":feature:chart"))
    implementation(project(":feature:more"))
    implementation(project(":feature:changeTheme"))
    implementation(project(":feature:chooseType"))
    implementation(project(":feature:updateType"))
    implementation(project(":feature:addType"))
    implementation(project(":feature:recordDetail"))
    implementation(project(":core:data"))
    implementation(project(":util"))

    // core
    implementation("androidx.core:core-ktx:$core_ktx_version")
    implementation("androidx.core:core-splashscreen:$core_splashscreen_version")// Android12兼容splash screen

    implementation("androidx.appcompat:appcompat:$androidx_appcompat_version")

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
    implementation("com.google.accompanist:accompanist-pager:$acompanist_version")// compose view pager
    implementation("com.google.accompanist:accompanist-pager-indicators:$acompanist_version")// If using indicators, also depend on
    implementation("com.google.accompanist:accompanist-navigation-animation:$acompanist_navigation_version")

    // test
    addTestImpl()

    // lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    // implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")// livedata to flow extension

    //Dagger - Hilt
    addHiltImpl()
    // Hilt work with WorkManager
    implementation("androidx.hilt:hilt-work:$androidx_hilt_version")
    // When using Kotlin.
    kapt("androidx.hilt:hilt-compiler:$androidx_hilt_version")

    // WorkManager: Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$work_version")

    // Timber
    implementation("com.jakewharton.timber:timber:$timber_version")

    // 内存泄漏检测
    // debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
}