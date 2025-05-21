package com.aamir.compose.eBooksLibrary.domain

data class Author(
    val name: String,
    val category: String,
    val about: String,
    val imageUrl: String,
    val rating: Double
)
