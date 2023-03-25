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
}

dependencies {
  api(project(":core-interface"))

  implementation(deps.rx.autodispose)
  implementation(deps.rx.rx)
  implementation(deps.rx.rxAndroid)
  implementation(deps.reporting.timber)
  implementation(deps.android.androidx.annotations)
  implementation(deps.android.androidx.appcompat)
  implementation(deps.android.androidx.constraintLayout)

  implementation(deps.dagger.core)
  kapt(deps.kapt.daggerCompiler)
}




