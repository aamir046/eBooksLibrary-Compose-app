package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails

import com.aamir.compose.eBooksLibrary.domain.Book

sealed interface AuthorDetailsScreenActions {
    data object OnBackClick : AuthorDetailsScreenActions
    data class OnBookSelected(val book: Book) : AuthorDetailsScreenActions
}