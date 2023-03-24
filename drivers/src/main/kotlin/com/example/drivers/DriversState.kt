package com.example.drivers


sealed interface DriversState
object Start: DriversState
data class Initialize(val rows: List<Driver>): DriversState
class Loading(val showLoading: Boolean): DriversState
data class ShowShipmentAssigned(val assignment: Assignment): DriversState
object AssignmentNotReady: DriversState
class ShowSwipeRefresh(val show: Boolean): DriversState