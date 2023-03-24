package com.example.news.dagger.module

import android.app.Application
import com.example.drivers.manager.ShipmentManager

object Injector : MainComponent {

  private lateinit var component: MainComponent

  fun init(app: Application) {
    component = DaggerMainComponent
      .builder()
      .appModule(AppModule(app))
      .shipmentModule(ShipmentModule((app)))
      .build()
  }

  override fun shipmentManager(): ShipmentManager = component.shipmentManager()
}