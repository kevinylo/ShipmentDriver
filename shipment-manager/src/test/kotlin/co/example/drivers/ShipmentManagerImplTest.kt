package co.example.drivers

import com.example.drivers.Optional
import com.example.drivers.ShipmentInputData
import com.example.repository.ShipmentRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Completable
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ShipmentManagerImplTest {

    lateinit var manager: ShipmentManagerImpl

    @RelaxedMockK
    private lateinit var repo: ShipmentRepository

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    fun setUp() {
        every { repo.fetchShipments() } returns Completable.complete()
        manager = ShipmentManagerImpl(repo)
    }

    @Test
    fun `getAllShipments return correct street name len`() {
        // assert
        val volkman = "63187 Volkman Garden Suite 447"
        val adolf = "1797 Adolf Island Apt. 744"
        val dessie = "75855 Dessie Lights"
        val volkmanLen = "Volkman Garden".length
        val adolfLen = "Adolf Island".length
        val dessieLen = "Dessie Lights".length

        every { repo.streamShipments() } returns Observable.just(
            Optional.of(ShipmentInputData(
            shipments = listOf(
                volkman,
                adolf,
                dessie
            ),
            drivers = listOf("driver1", "driver2", "driver3")
        )))

        manager.refresh().test()

        // verify
        assertThat(manager.shipmentsSubject.value!![0].streetNameLength).isEqualTo(volkmanLen)
        assertThat(manager.shipmentsSubject.value!![1].streetNameLength).isEqualTo(adolfLen)
        assertThat(manager.shipmentsSubject.value!![2].streetNameLength).isEqualTo(dessieLen)
    }

}