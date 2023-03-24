plugins {
    id("com.android.application")
    kotlin("android")
}
repositories {
    google()
    mavenCentral()
}
android {
    namespace = "com.example.news"

    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.news"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "15"
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

    implementation(deps.android.androidx.recyclerView)
}