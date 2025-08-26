package com.aamir.compose.eBooksLibrary.domain.model

data class BookInfoNotification(
    val id: String="",
    val status:String = "",
    val dateTime :String = "05/12/2025: 10:10 PM",
    val book: Book? = null,
)
