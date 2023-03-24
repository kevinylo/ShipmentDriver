package co.example.driver

import com.example.drivers.Optional
import com.example.drivers.ShipmentInputData
import com.example.repository.ShipmentRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ShipmentRepositoryImpl @Inject constructor(
    private val shipmentInputData: ShipmentInputData
): ShipmentRepository {

    private val shipmentInputSubject = BehaviorSubject.create<Optional<ShipmentInputData>>()

    init {
        shipmentInputSubject.onNext(Optional.of(shipmentInputData))
    }

    override fun fetchShipments(): Completable {
        // for future where we are fetching latest shipment from somewhere
        return Completable.complete()
    }

    override fun streamShipments(): Observable<Optional<ShipmentInputData>> {
        return shipmentInputSubject
    }

    override fun clear(): Completable {
        shipmentInputSubject.onNext(Optional.absent())
        return Completable.complete()
    }
}