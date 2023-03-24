package co.example.drivers

import com.example.drivers.DriversPresenter
import com.example.drivers.manager.ShipmentManager
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DriversPresenterTest {
    init {
        MockKAnnotations.init(this)
    }

    lateinit var presenter: DriversPresenter
    @RelaxedMockK
    lateinit var manager: ShipmentManager

    @BeforeEach
    fun setUp() {
        presenter = DriversPresenter(manager)
    }

    @Test
    fun `swipe to refresh results in shipment being refreshed`() {
        // to be added
    }

    @Test
    fun `Initialize(drivers) are called automatically upon entering`() {
        // to be added
    }

    @Test
    fun `driver selected when the assignment is not yet ready `() {
        // to be added
    }

    @Test
    fun `driver selected when the assignment is ready`() {
        // to be added
    }
}