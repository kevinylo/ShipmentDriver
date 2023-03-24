plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

apply {
    from(rootProject.file("gradle/config-kotlin-sources.gradle"))
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdkVersion(deps.android.build.compileSdkVersion)

    defaultConfig {
        minSdkVersion(deps.android.build.minSdkVersion)
        targetSdkVersion(deps.android.build.targetSdkVersion)
    }

    kotlinOptions {
        jvmTarget = "15"
    }
}

dependencies {
    implementation(project(":core-interface"))
    implementation(project(":shipment-manager"))
    implementation(project(":model"))
    implementation(project(":repository"))

    implementation(deps.kotlin.stdlib.jdk7)
    implementation(deps.android.androidx.appcompat)

    // dagger
    implementation(deps.dagger.core)
    implementation(deps.android.androidx.corektx)
    kapt(deps.kapt.daggerCompiler)

    // json serialization/deserialization
    implementation(deps.util.gson)

    // joda
    implementation(deps.util.joda)

    // networking
    implementation(deps.okhttp.core)
    implementation(deps.retrofit.core)
    implementation(deps.retrofit.retrofitGson)
    implementation(deps.retrofit.retrofitRx2)
}
