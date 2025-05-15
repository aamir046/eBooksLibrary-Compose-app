package com.aamir.compose.eBooksLibrary.presentation.bookdetails

sealed interface BookDetailsScreenActions {
    data object OnBackClick : BookDetailsScreenActions
}