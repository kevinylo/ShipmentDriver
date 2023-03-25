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
        sourceCompatibility = JavaVersion.VERSION_15
        targetCompatibility = JavaVersion.VERSION_15
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_15.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

dependencies {

    implementation(deps.retrofit.retrofitGson)
    implementation(deps.android.androidx.corektx)
}


