package com.example.drivers

import android.app.Activity
import com.example.news.dagger.module.MainComponent
import com.example.news.dagger.module.scopes.PerActivity
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = [MainComponent::class])
interface DriversComponent {
    @Component.Factory
    interface Factory {
        fun create(
            mainComponent: MainComponent,
            @BindsInstance activity: Activity,
        ): DriversComponent
    }
    fun inject(activity: DriversActivity)
}
