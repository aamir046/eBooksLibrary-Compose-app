package com.aamir.compose.eBooksLibrary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val author: String,
    val title: String,
    val year: String,
    val description: String,
    val imageUrl: String,
    val type: String,
    val isFavourite: Boolean = false
)