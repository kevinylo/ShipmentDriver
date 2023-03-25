plugins {
    id("com.android.library")
    kotlin("android")
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
}

dependencies {
    implementation(project(":model"))

    implementation(deps.kotlin.stdlib.jdk7)
    implementation(deps.android.androidx.appcompat)
    implementation(deps.android.androidx.corektx)
    implementation(deps.rx.rx)

    // autodispose
    implementation(deps.rx.autodispose)
    implementation(deps.rx.autodisposeLifecycle)
    implementation(deps.rx.autodisposeAndroidKotlin)
    implementation(deps.rx.autodisposeKotlin)
    implementation(deps.rx.autodisposeLifecycleKotlin)

    implementation(deps.util.joda)

    // dagger
    implementation(deps.dagger.core)

}
