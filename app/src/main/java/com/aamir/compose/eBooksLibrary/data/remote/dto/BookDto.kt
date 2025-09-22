package com.aamir.compose.eBooksLibrary.data.remote.dto

data class BookDto(
    val id: Int,
    val author: String,
    val title: String,
    val year: String,
    val description: String,
    val imageUrl: String,
    val type: String
)
