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
