package com.example.news.dagger.module

import android.content.Context
import co.example.driver.ShipmentRepositoryImpl
import co.example.drivers.ShipmentManagerImpl
import com.example.drivers.ShipmentInputData
import com.example.drivers.manager.ShipmentManager
import com.example.repository.ShipmentRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Singleton

@Module(includes = [
    (AppModule::class)
])

class ShipmentModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideInputData(
        gson: Gson
    ): ShipmentInputData {
        val inputStreamReader = InputStreamReader(context.resources.assets.open("data.json"))
        val bufferedReader = BufferedReader(inputStreamReader)
        val sb = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            sb.append(line)
        }
        inputStreamReader.close()
        return gson.fromJson(sb.toString(), ShipmentInputData::class.java)
    }

    @Provides
    @Singleton
    fun provideShipmentManager(
        shipmentRepository: ShipmentRepository
    ): ShipmentManager {
        return ShipmentManagerImpl(shipmentRepository)
    }

    @Provides
    @Singleton
    fun provideShipmentRepository(
        shipmentInputData: ShipmentInputData
    ): ShipmentRepository {
        return ShipmentRepositoryImpl(shipmentInputData)
    }
}