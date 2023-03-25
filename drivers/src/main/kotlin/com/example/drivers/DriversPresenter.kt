package com.example.drivers

import android.util.Log
import co.example.news.core.mrp.AbstractPresenter
import com.example.drivers.manager.ShipmentManager
import com.uber.autodispose.autoDispose
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * MVI style presenter that be more easily tested based on states
 * Presenter reacts to renderer (view) and then doing one directional flow from presenter to renderer (view) for updates
 */
class DriversPresenter @Inject constructor(
    private val manager: ShipmentManager,
): AbstractPresenter<DriversRenderer, DriversState>(initial = Start) {

    override fun consume(renderer: DriversRenderer) {
        super.consume(renderer)

        // view triggered action
        renderer.swipeRefreshed
            .flatMapCompletable {
                manager.refresh()
                    .subscribeOn(Schedulers.io())
            }
            .autoDispose(renderer)
            .subscribe({}, {
                Log.e("swipe refresh", it.toString())
            })

        // Observing drivers data from shipment manager so this data will be updated
        // with updated input data (assignment might not be ready right away)
        manager
            .getAllDrivers()
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(renderer)
            .subscribe({ drivers ->
                // in the event this update is triggered from pull to refresh
                emit(ShowSwipeRefresh(false))
                // populate the adapter
                emit(Initialize(drivers))
            }, {
                Log.e("get all drivers", it.toString())
            })

        // view triggered action
        renderer
            .driverSelected
            .flatMapSingle { driver ->
                manager.getAssignmentFor(driver)
            }
            .autoDispose(renderer)
            .subscribe({ maybeAssignment ->
                if (maybeAssignment.isPresent) {
                    emit(ShowShipmentAssigned(maybeAssignment.get()))
                } else {
                    emit(AssignmentNotReady)
                }
            }, {
                Log.e("driver selected", it.toString())
            })
    }

}