plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = 32

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    implementation(deps.retrofit.retrofitGson)
    implementation(deps.android.androidx.corektx)
}


