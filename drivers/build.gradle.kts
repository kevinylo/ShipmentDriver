plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
repositories {
    google()
    mavenCentral()
}
android {

    compileSdk = deps.android.build.compileSdkVersion

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
    implementation(project(":model"))
    implementation(project(":core-interface"))
    implementation(project(":dagger"))
    implementation(project(":core-basemrp"))

    implementation(deps.rx.autodispose)
    implementation(deps.rx.autodisposeLifecycle)
    implementation(deps.rx.autodisposeAndroidKotlin)
    implementation(deps.rx.autodisposeKotlin)
    implementation(deps.rx.autodisposeLifecycleKotlin)
    implementation(deps.rx.autodisposeAndroid)

    implementation(deps.android.androidx.appcompat)
    implementation(deps.android.androidx.corektx)
    implementation(deps.android.androidx.cardView)
    implementation(deps.android.androidx.swipeRefresh)
    implementation(deps.android.androidx.constraintLayout)
    implementation(deps.ui.materialProgressBar)
    implementation(deps.reporting.timber)

    // dagger
    implementation(deps.dagger.core)
    kapt(deps.kapt.daggerCompiler)

    implementation(deps.android.androidx.recyclerView)

    testImplementation(deps.test.junit5)
    testImplementation(deps.test.assertj)
    testImplementation(deps.test.mockK)
}