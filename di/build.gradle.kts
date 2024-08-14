plugins {
    id("com.android.library")
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Retrofit with Kotlin serialization Converter
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.okhttp)

    // KotlinX Serialization
    implementation(libs.kotlinx.serialization.json)

    //Retrofit
    implementation(libs.retrofit)
}