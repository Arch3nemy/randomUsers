plugins {
    id("com.android.library")
    id ("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Dependencies.android.compileSdk
    buildToolsVersion = Dependencies.android.buildTools

    defaultConfig {
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
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
    implementation(project(":domain"))
    implementation("androidx.core:core-ktx:1.9.0")
    retrofit()
    klaxon()
    moshi()
    room()
}

fun DependencyHandlerScope.retrofit() {
    implementation(Dependencies.retrofit.retrofit)
    implementation(Dependencies.retrofit.gson)
    implementation(Dependencies.retrofit.gsonConverter)
}

fun DependencyHandlerScope.room() {
    implementation(Dependencies.room.runtime)
    kapt(Dependencies.room.compiler)
    implementation(Dependencies.room.ktx)
}

fun DependencyHandlerScope.klaxon() {
    implementation(Dependencies.other.klaxon)
    implementation(Dependencies.other.timber)
}

fun DependencyHandlerScope.moshi() {
    implementation(Dependencies.other.moshi)
}

