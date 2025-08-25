package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites

import com.aamir.compose.eBooksLibrary.domain.Book

data class FavouritesScreenState(
    val favouriteBooks: List<Book> = listOf(
        Book(
            author = "Harper Lee",
            title = "To Kill a Mockingbird",
            year = "1960",
            description = "A story of racial injustice and childhood innocence in the Deep South.",
            imageUrl = "https://images.gr-assets.com/books/1553383690l/2657.jpg"
        ),
        Book(
            author = "George Orwell",
            title = "1984",
            year = "1949",
            description = "A chilling vision of a dystopian world under total surveillance.",
            imageUrl = "https://images.penguinrandomhouse.com/cover/9780679417392"
        )
    )
)