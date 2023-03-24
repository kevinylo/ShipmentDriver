package com.example.drivers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.example.drivers.R
import co.example.drivers.databinding.ViewDriverBinding
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class DriversAdapter @Inject constructor(): RecyclerView.Adapter<DriverViewHolder>() {
    val driverClickSubject = PublishSubject.create<Driver>()

    private lateinit var rows: MutableList<Driver>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        return DriverViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_driver, parent, false),
            this
        )
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.bind(rows[position])
    }

    override fun getItemCount(): Int = rows.size

    fun setDrivers(drivers: List<Driver>) {
        rows = drivers.toMutableList()
    }

    fun driverSelected(): Observable<Driver> = driverClickSubject.hide()
}

class DriverViewHolder(
    private val view: View,
    articlesAdapter: DriversAdapter
): RecyclerView.ViewHolder(view) {
    private val binding = ViewDriverBinding.bind(view)
    lateinit var currentDriver: Driver

    init {
        view.setOnClickListener {
            articlesAdapter.driverClickSubject.onNext(currentDriver)
        }
    }

    fun bind(driver: Driver) {
        currentDriver = driver
        binding.name.text = driver.name
    }
}
