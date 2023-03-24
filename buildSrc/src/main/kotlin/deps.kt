@file:Suppress("unused")

object deps {
  object versions {
    const val kotlin = "1.7.22"
    const val dagger = "2.43.2"
    const val androidxVersion = "1.2.0"
    const val rxBindingsVersion = "3.0.0"
    const val gradle = "7.1.2"
    const val retrofit = "2.7.1"
    const val glide = "4.13.0"
  }

  object android {
    object androidx {
      const val annotations = "androidx.annotation:annotation:${versions.androidxVersion}"
      const val appcompat = "androidx.appcompat:appcompat:${versions.androidxVersion}"
      const val lifecycleExtentions = "androidx.lifecycle:lifecycle-extensions:2.1.0"
      const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:2.1.0"
      const val cardView = "androidx.cardview:cardview:1.0.0"
      const val collection = "androidx.collection:collection:${versions.androidxVersion}"
      const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
      const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0-alpha05"
      const val corektx = "androidx.core:core-ktx:1.2.0"
      const val core = "androidx.core:core:1.2.0"
      const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"

      const val paging = "androidx.paging:paging-rxjava2:2.1.0"
      const val pagingRuntime = "androidx.paging:paging-runtime:2.1.0"
      const val recyclerView = "androidx.recyclerview:recyclerview:${versions.androidxVersion}"
      const val v13 = "androidx.legacy:legacy-support-v13:${versions.androidxVersion}"
      const val v4 = "androidx.legacy:legacy-support-v4:${versions.androidxVersion}"

      object test {
        const val androidTestCore = "androidx.test:core:1.1.0"
        const val androidTestRules = "androidx.test:rules:1.1.0"
        const val androidTestExt = "androidx.test.ext:junit:1.1.1"
      }
    }

    object build {
      const val buildToolsVersion = "28.0.3"
      const val compileSdkVersion = 29
      const val minSdkVersion = 21
      const val targetSdkVersion = 29
    }
  }

  object kotlin {
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${versions.kotlin}"

    object stdlib {
      const val core = "org.jetbrains.kotlin:kotlin-stdlib:${versions.kotlin}"
      const val jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${versions.kotlin}"
    }
  }

  object gradlePlugins {
    const val googlePlay = "com.google.gms:google-services:4.3.8"
    const val gradle = "com.android.tools.build:gradle:${versions.gradle}"
    const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.6.0.0"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
    const val apollo = "com.apollographql.apollo:apollo-gradle-plugin:2.0.3"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
  }

  object dagger {
    const val core = "com.google.dagger:dagger:${versions.dagger}"
  }

  object kapt {
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${versions.dagger}"
  }

  object rx {
    const val autodispose = "com.uber.autodispose:autodispose:1.4.0"
    const val autodisposeAndroidKotlin = "com.uber.autodispose:autodispose-android-ktx:1.2.0"
    const val autodisposeKotlin = "com.uber.autodispose:autodispose-ktx:1.2.0"
    const val autodisposeLifecycleKotlin = "com.uber.autodispose:autodispose-lifecycle-ktx:1.2.0"
    const val autodisposeLifecycle = "com.uber.autodispose:autodispose-lifecycle:1.4.0"
    const val autodisposeAndroidArc = "com.uber.autodispose:autodispose-android-archcomponents-ktx:1.2.0"
    const val autodisposeAndroid = "com.uber.autodispose:autodispose-android:1.4.0"

    const val rx = "io.reactivex.rxjava2:rxjava:2.2.18"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.0"
    const val rxBinding = "com.jakewharton.rxbinding2:rxbinding:${versions.rxBindingsVersion}"
    const val rxBindingKotlin = "com.jakewharton.rxbinding2:rxbinding-kotlin:${versions.rxBindingsVersion}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
    const val rxRelay = "com.jakewharton.rxrelay2:rxrelay:2.1.0"
    const val location = "pl.charmas.android:android-reactive-location2:2.1@aar"
  }

  object ui {
    const val material = "com.google.android.material:material:1.1.0-alpha07"
    const val materialDialog = "com.afollestad.material-dialogs:core:3.3.0"
    const val materialDialogCommon = "com.afollestad.material-dialogs:commons:0.9.6.0"
    const val materialEditText = "com.rengwuxian.materialedittext:library:2.1.4"
    const val materialProgressBar = "me.zhanghai.android.materialprogressbar:library:1.4.2"
    const val progressBar = "com.github.castorflex.smoothprogressbar:library-circular:1.1.0"
    const val snackbar = "com.androidmads.amsnackbar:library:1.0.0"
    const val materialSearchBar =  "com.github.mancj:MaterialSearchBar:0.8.5"
    const val snacky = "com.github.matecode:Snacky:1.1.5"
    const val androidFlowLayout = "org.apmem.tools:layouts:1.10@aar"
    const val glide = "com.github.bumptech.glide:glide:${versions.glide}"
  }

  object reporting {
    const val timber = "com.jakewharton.timber:timber:4.7.1"
  }

  object util {
    const val gson = "com.google.code.gson:gson:2.8.5"
    const val moshi = "com.squareup.moshi:moshi-kotlin:1.8.0"
    const val joda = "net.danlew:android.joda:2.10.3"
  }

  object okhttp {
    const val core = "com.squareup.okhttp3:okhttp:4.6.0"

    object debug {
      const val logging = "com.squareup.okhttp3:logging-interceptor:4.6.0"
    }
  }

  object retrofit {
    const val core = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
    const val rx2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${versions.retrofit}"
    const val retrofitRx2 = "com.squareup.retrofit2:adapter-rxjava2:${versions.retrofit}"
  }

  object test {
    const val junit5 = "org.junit.jupiter:junit-jupiter:5.6.0"
    const val assertj = "org.assertj:assertj-core:2.4.1"
    const val mockK = "io.mockk:mockk:1.13.4"
  }
}
