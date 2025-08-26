package com.aamir.compose.eBooksLibrary.presentation.home

import com.aamir.compose.eBooksLibrary.domain.model.Book

sealed interface HomeScreenActions {
    data class OnBookClick(val book: Book) : HomeScreenActions
    data object OnSearchClick : HomeScreenActions
    data object OnNotificationsClick : HomeScreenActions
}