package com.example.drivers

import co.example.news.core.mrp.Renderer
import io.reactivex.Observable

interface DriversRenderer: Renderer<DriversState> {
    val driverSelected: Observable<Driver>
    val swipeRefreshed: Observable<Unit>
}