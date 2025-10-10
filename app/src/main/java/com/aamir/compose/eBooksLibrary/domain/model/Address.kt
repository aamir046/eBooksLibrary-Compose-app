package com.aamir.compose.eBooksLibrary.domain.model

data class Address(
    val id: Long = 0L,
    val title: String = "",
    val fullAddress: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val addressTag: String = ""
)