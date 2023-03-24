package com.example.drivers

import java.util.UUID

data class Shipment(
    val id: String = UUID.randomUUID().toString(),
    val fullAddress: String,
    val streetNameLength: Int,
)

fun Shipment.destinationNameEven(): Boolean {
    return streetNameLength % 2 == 0
}

fun Shipment.hasCommonFactorsWith(target: Int): Boolean {
    val smallerLen = streetNameLength.coerceAtMost(target)
    for (i in 2..smallerLen) {
        if (streetNameLength % i == 0 && target % i == 0) {
            return true
        }
    }
    return false
}