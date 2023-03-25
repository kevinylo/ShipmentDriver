package co.example.drivers

import androidx.annotation.VisibleForTesting
import com.example.drivers.Assignment
import com.example.drivers.Driver
import com.example.drivers.Optional
import com.example.drivers.Shipment
import com.example.drivers.ShipmentInputData
import com.example.drivers.destinationNameEven
import com.example.drivers.hasCommonFactorsWith
import com.example.drivers.manager.ShipmentManager
import com.example.repository.ShipmentRepository
import com.uber.autodispose.ScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.PriorityQueue

class ShipmentManagerImpl(
    private val repo: ShipmentRepository
) : ShipmentManager {

    companion object {
        private const val VOWELS_MULTIPLIER = 1.5f
        private const val CONSONANTS_MULTIPLIER = 1.0f
        private const val COMMON_FACTORS_BUMP = 1.5f
    }

    private lateinit var queue: PriorityQueue<Assignment>

    private val assignments = mutableMapOf<Driver, Assignment>()

    private val assignedDrivers = mutableSetOf<Driver>()

    private val assignedShipments = mutableSetOf<Shipment>()

    @VisibleForTesting
    val driversSubject = BehaviorSubject.create<List<Driver>>()

    @VisibleForTesting
    val shipmentsSubject = BehaviorSubject.create<List<Shipment>>()

    init {
        refresh()
            .autoDispose(ScopeProvider.UNBOUND)
            .subscribe({}, {
                Timber.e("shipment manager init()")
            })
    }

    override fun refresh(): Completable {
        return repo.fetchShipments()
            .andThen(repo.streamShipments())
            .filter { it.isPresent }
            .map { it.get() }
            .doOnNext { input ->
                // clear previous data once new input is available
                // Assuming the input data is not cumulative and no previous state needs to be maintained (big assumption here)
                resetAssignments()
                input.processDriverData()
                input.processShipmentData()
                queue = PriorityQueue(input.shipments.size * input.shipments.size, compareByDescending { it.suitabilityScore })
                val initialCapacity = input.shipments.size * input.shipments.size // assuming there is one shipment per driver
                determineOptimalAssignments(PriorityQueue(initialCapacity, compareByDescending { it.suitabilityScore }))
            }
            .ignoreElements()
    }

    override fun getAssignmentFor(driver: Driver): Single<Optional<Assignment>> {
        // in the event when processing takes long or scheduling has been updated
        return if (!assignments.contains(driver)) {
            Single.just(Optional.absent())
        } else {
            Single.just(Optional.of(assignments[driver]!!))
        }
    }

    override fun getAllDrivers(): Observable<List<Driver>> {
        return driversSubject.hide()
    }

    override fun getAllShipments(): Observable<List<Shipment>> {
        return shipmentsSubject.hide()
    }

    private fun ShipmentInputData.processDriverData(): List<Driver> {
        return drivers.map { fullName ->
            val numOfVowels = fullName.count { letter -> letter.isVowel() }

            Driver(
                name = fullName,
                vowels = numOfVowels,
                consonants = fullName.count { letter -> !letter.isVowel() && letter != ' ' }
            )
        }.also {
            driversSubject.onNext(it)
        }
    }

    private fun ShipmentInputData.processShipmentData(): List<Shipment> {
        return shipments.map { address ->
            val sanitizedAddress = address.trimStart()  // remove any starting spaces
            Shipment(
                fullAddress = sanitizedAddress,
                streetNameLength = sanitizedAddress.substringAfter(delimiter = " ")  // assuming anything following the first space is the beginning of the street name
                    .substringBefore(delimiter = "Apt")
                    .substringBefore(delimiter = "Suite")
                    .trim() // remove trailing spaces
                    .length
            )
        }.also {
            shipmentsSubject.onNext(it)
        }
    }

    private fun determineOptimalAssignments(queue: PriorityQueue<Assignment>) {
        // seed the queue with every possible combination of driver/delivery/ss
        driversSubject.value?.forEach { driver ->
            shipmentsSubject.value?.forEach { shipment ->
                queue.offer(Assignment(driver, shipment, suitabilityScore(driver, shipment)))
            }
        }
        while (queue.isNotEmpty()) {
            val assignment = queue.poll()!!
            // verify that the next highest suitability score is not from already assigned driver or delivery
            if (!assignedDrivers.contains(assignment.driver) && !assignedShipments.contains(assignment.shipment)) {
                assignments[assignment.driver] = assignment
                assignedDrivers.add(assignment.driver)
                assignedShipments.add(assignment.shipment)
            }
        }
    }

    private fun resetAssignments() {
        assignedDrivers.clear()
        assignedShipments.clear()
        assignments.clear()
    }

    private fun suitabilityScore(driver: Driver, shipment: Shipment): Float {
        return when (shipment.destinationNameEven()) {
            true -> driver.vowels * VOWELS_MULTIPLIER
            false -> driver.consonants * CONSONANTS_MULTIPLIER
        } * if (shipment.hasCommonFactorsWith(driver.name.length)) COMMON_FACTORS_BUMP else 1.0f
    }

    private fun Char.isVowel(): Boolean {
        return this == 'a' || this == 'e' || this == 'i' || this == 'o' || this == 'u' ||
                this == 'A' || this == 'E' || this == 'I' || this == 'O' || this == 'U'
    }
}