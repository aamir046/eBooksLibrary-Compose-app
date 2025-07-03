package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites

sealed interface FavouritesScreenActions {
    data object OnBackClick : FavouritesScreenActions
}