package co.example.drivers

import com.example.drivers.Assignment
import com.example.drivers.AssignmentNotReady
import com.example.drivers.Driver
import com.example.drivers.DriversPresenter
import com.example.drivers.DriversRenderer
import com.example.drivers.Initialize
import com.example.drivers.Optional
import com.example.drivers.ShowShipmentAssigned
import com.example.drivers.ShowSwipeRefresh
import com.example.drivers.manager.ShipmentManager
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DriversPresenterTest {
    init {
        MockKAnnotations.init(this)
    }

    lateinit var presenter: DriversPresenter
    @RelaxedMockK
    lateinit var manager: ShipmentManager
    private val mock = mockk<DriversRenderer>(relaxed = true)
    private val testRenderer = DriversRendererStubTestRenderer(mock)
    private val driver1 = Driver(name = "david", vowels = 3, consonants = 2)
    private val driver2 = Driver(name = "Kevin", vowels = 2, consonants = 3)

    @BeforeEach
    fun setUp() {
        val drivers = listOf(driver1, driver2)
        every { manager.getAllDrivers() } returns Observable.just(drivers)
        presenter = DriversPresenter(manager)
    }

    @Test
    fun `swipe to refresh results in shipment being refreshed`() {
        testRenderer.swipeRefreshedSubject.onNext(Unit)
        val states = presenter.states().test()
        presenter.consume(testRenderer)

        assertThat(states.values()[0]).isInstanceOf(ShowSwipeRefresh::class.java)
        assertThat(states.values()[1]).isInstanceOf(Initialize::class.java)
        assertThat(states.values().size).isEqualTo(2)
    }

    @Test
    fun `Initialize(drivers) are called automatically upon entering`() {
        val states = presenter.states().test()
        presenter.consume(testRenderer)

        assertThat(states.values()[0]).isInstanceOf(ShowSwipeRefresh::class.java)
        assertThat(states.values()[1]).isInstanceOf(Initialize::class.java)
        assertThat(states.values().size).isEqualTo(2)
    }

    @Test
    fun `driver selected when the assignment is not yet ready `() {
        every { manager.getAssignmentFor(driver1) } returns Single.just(Optional.absent())

        testRenderer.driverSelectedSubject.onNext(driver1)
        val states = presenter.states().test()
        presenter.consume(testRenderer)

        assertThat(states.values()[0]).isInstanceOf(ShowSwipeRefresh::class.java)
        assertThat(states.values()[1]).isInstanceOf(Initialize::class.java)
        assertThat(states.values()[2]).isInstanceOf(AssignmentNotReady::class.java)
        assertThat(states.values().size).isEqualTo(3)
    }

    @Test
    fun `driver selected when the assignment is ready`() {
        val assignment = mockk<Assignment>()
        every { manager.getAssignmentFor(driver1) } returns Single.just(Optional.of(assignment))

        testRenderer.driverSelectedSubject.onNext(driver1)
        val states = presenter.states().test()
        presenter.consume(testRenderer)

        assertThat(states.values()[0]).isInstanceOf(ShowSwipeRefresh::class.java)
        assertThat(states.values()[1]).isInstanceOf(Initialize::class.java)
        assertThat(states.values()[2]).isInstanceOf(ShowShipmentAssigned::class.java)
        assertThat(states.values().size).isEqualTo(3)
    }
}