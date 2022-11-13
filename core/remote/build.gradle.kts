plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 23
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    // modules
    implementation(project(":core:model"))

    // core
    implementation("androidx.core:core-ktx:$core_ktx_version")

    // test
    addTestImpl()

    //Dagger - Hilt
    addHiltImpl()

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit2_version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    // implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"

    // gson
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2_version")
    
    // sandwich
    api("com.github.skydoves:sandwich:$sandwich_version")
}