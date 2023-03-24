package com.example.news

import android.app.Application
import com.example.news.dagger.module.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}