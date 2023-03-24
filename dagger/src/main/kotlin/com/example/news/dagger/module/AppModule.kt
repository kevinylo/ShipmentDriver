package com.example.news.dagger.module

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

  @Provides
  @Singleton
  fun provideApplication(): Application {
    return app
  }

  @Provides
  @Singleton
  fun provideApplicationContext(): Context {
    return app.applicationContext
  }

  @Provides
  fun provideMainHandler(): Handler {
    return Handler(Looper.getMainLooper())
  }

  @Provides
  @Singleton
  fun provideGson(): Gson {
    return GsonBuilder()
      .enableComplexMapKeySerialization()
      .serializeNulls()
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .setPrettyPrinting()
      .setLenient()
      .create()
  }
}