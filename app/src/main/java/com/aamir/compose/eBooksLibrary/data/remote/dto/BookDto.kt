// data/remote/dto/BookDto.kt
package com.aamir.compose.eBooksLibrary.data.remote.dto

data class BookDto(
    val id: String,
    val author: String,
    val title: String,
    val year: String,
    val description: String,
    val imageUrl: String,
    val type: String
)
