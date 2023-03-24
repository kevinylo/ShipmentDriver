package com.example.drivers

import java.util.UUID

data class Driver(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val vowels: Int,
    val consonants: Int,
)
