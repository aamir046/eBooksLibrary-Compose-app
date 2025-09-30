package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import com.aamir.compose.eBooksLibrary.domain.model.Book

sealed interface BookDetailsScreenActions {
    data object OnBackClick : BookDetailsScreenActions
    data class OnToggleFavorite(val book: Book) : BookDetailsScreenActions
}