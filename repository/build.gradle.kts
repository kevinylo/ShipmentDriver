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

  defaultConfig {
    minSdk = 21
    targetSdk = 32

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }


  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
  }
  kotlinOptions {
    jvmTarget = "15"
  }
}

dependencies {

  implementation(project(":core-interface"))
  implementation(project(":model"))
  implementation(deps.dagger.core)
  kapt(deps.kapt.daggerCompiler)
  implementation(deps.kotlin.stdlib.jdk7)

  implementation(deps.rx.autodispose)

  implementation(deps.rx.rx)
  implementation(deps.rx.rxKotlin)
  implementation(deps.rx.rxRelay)
  implementation(deps.util.joda)
  implementation(deps.reporting.timber)
  implementation(deps.android.androidx.corektx)
}
