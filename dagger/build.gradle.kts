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
    compileSdk = deps.android.build.compileSdkVersion

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
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
}
