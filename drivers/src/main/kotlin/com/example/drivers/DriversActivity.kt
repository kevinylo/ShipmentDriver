package com.example.drivers

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.example.drivers.databinding.ActivityDriversBinding
import com.example.BaseActivity
import com.example.news.dagger.module.Injector
import io.reactivex.Observable
import javax.inject.Inject

class DriversActivity : BaseActivity(), DriversRenderer {

    @Inject
    lateinit var presenter: DriversPresenter

    @Inject
    lateinit var adapter: DriversAdapter

    private lateinit var binding: ActivityDriversBinding

    override val driverSelected: Observable<Driver> by lazy { adapter.driverSelected() }

    override val swipeRefreshed: Observable<Unit> = Observable.create { emitter ->
        binding.swipeRefresh.setOnRefreshListener { emitter.onNext(Unit) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDriversComponent.factory()
            .create(
                mainComponent = Injector,
                activity = this,
            )
            .inject(this)

        binding = ActivityDriversBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        setContentView(binding.root)
        presenter.consume(this)
    }

    override fun render(state: DriversState) {
        when (state) {
            is Initialize -> {
                adapter.setDrivers(state.rows)
                binding.recyclerView.adapter = adapter
            }
            is ShowShipmentAssigned -> Toast.makeText(this,
                state.assignment.shipment.fullAddress + " " + state.assignment.suitabilityScore
                , Toast.LENGTH_SHORT).show()
            is AssignmentNotReady -> Toast.makeText(this, "Assignment not ready"
                , Toast.LENGTH_SHORT).show()
            is Loading -> binding.progressBar.isVisible = state.showLoading
            is Start -> {}
            is ShowSwipeRefresh -> binding.swipeRefresh.isRefreshing = state.show
        }
    }

}