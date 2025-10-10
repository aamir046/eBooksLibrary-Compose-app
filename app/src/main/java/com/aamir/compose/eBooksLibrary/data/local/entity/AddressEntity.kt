package com.aamir.compose.eBooksLibrary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val fullAddress: String,
    val latitude: Double,
    val longitude: Double,
    val addressTag: String
)