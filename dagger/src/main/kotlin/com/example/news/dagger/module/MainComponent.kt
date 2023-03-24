package com.example.news.dagger.module

import com.example.drivers.manager.ShipmentManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
  (AppModule::class),
  (ShipmentModule::class)
])

interface MainComponent {
  // Only has one manager for now but could have a lot more
  fun shipmentManager(): ShipmentManager
}