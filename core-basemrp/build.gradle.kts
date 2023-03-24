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
  compileSdk = 32

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
  }
  kotlinOptions {
    jvmTarget = "15"
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




