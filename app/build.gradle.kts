plugins {
    id("com.android.application")
    kotlin("android")
}
repositories {
    google()
    mavenCentral()
}
android {
    namespace = "com.example.shipmentdriver"

    compileSdk = deps.android.build.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.shipmentdriver"
        minSdk = deps.android.build.minSdkVersion
        targetSdk = deps.android.build.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":dagger"))
    implementation(project(":drivers"))

    implementation(deps.android.androidx.corektx)
    // dagger
    implementation(deps.dagger.core)
}