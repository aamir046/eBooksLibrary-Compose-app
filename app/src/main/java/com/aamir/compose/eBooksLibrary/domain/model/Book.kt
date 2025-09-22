package com.aamir.compose.eBooksLibrary.domain.model

data class Book(
    val id: Int = -1,
    val author: String,
    val title: String,
    val year: String,
    val description: String,
    val imageUrl: String,
    val type: String = "",
    val isFavourite: Boolean = false
)