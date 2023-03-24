package com.example.drivers.manager

import com.example.drivers.Assignment
import com.example.drivers.Driver
import com.example.drivers.Optional
import com.example.drivers.Shipment
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ShipmentManager {
    fun refresh(): Completable
    fun getAllDrivers(): Observable<List<Driver>>
    fun getAllShipments(): Observable<List<Shipment>>
    fun getAssignmentFor(driver: Driver): Single<Optional<Assignment>>
}