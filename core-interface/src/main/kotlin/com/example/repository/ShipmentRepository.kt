package com.example.repository

import com.example.drivers.Optional
import com.example.drivers.ShipmentInputData
import io.reactivex.Completable
import io.reactivex.Observable

interface ShipmentRepository {
    fun fetchShipments(): Completable
    fun streamShipments(): Observable<Optional<ShipmentInputData>>
    fun clear(): Completable
}