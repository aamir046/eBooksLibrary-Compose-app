package com.aamir.compose.eBooksLibrary.domain.model

data class GeneralNotification(
    val id: String="",
    val title: String="",
    val description: String="",
    val imageUrl: String="",
    val dateTime :String = "",
    val type :String = ""
)
