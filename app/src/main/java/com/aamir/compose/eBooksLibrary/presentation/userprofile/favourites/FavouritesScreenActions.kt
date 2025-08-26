package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites

import com.aamir.compose.eBooksLibrary.domain.model.Book

sealed interface FavouritesScreenActions {
    data object OnBackClick : FavouritesScreenActions
    data class OnBookClick(val book: Book) : FavouritesScreenActions
    data class OnFavouritesIconClick(val book: Book) : FavouritesScreenActions
}