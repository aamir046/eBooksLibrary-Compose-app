package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails

sealed interface AuthorDetailsScreenActions {
    data object OnBackClick : AuthorDetailsScreenActions
}