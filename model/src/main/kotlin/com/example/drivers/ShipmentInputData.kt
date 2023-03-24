package com.example.drivers

import com.google.gson.annotations.SerializedName

data class ShipmentInputData(
    @SerializedName("shipments")
    val shipments: List<String>,

    @SerializedName("drivers")
    val drivers: List<String>,
)
