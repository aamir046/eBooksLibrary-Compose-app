package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites

import com.aamir.compose.eBooksLibrary.domain.model.Book

data class FavouritesScreenState(
    val favouriteBooks: List<Book> = listOf()
)