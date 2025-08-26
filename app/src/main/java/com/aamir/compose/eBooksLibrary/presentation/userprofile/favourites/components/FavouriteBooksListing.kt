package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.model.Book

@Composable
fun FavouriteBooksListing(
    modifier: Modifier = Modifier,
    books:List<Book> = emptyList(),
    onBookClick: (Book) -> Unit = {},
    onFavouritesIconClick: (Book) -> Unit = {}
){
        LazyColumn (modifier = modifier.fillMaxSize()) {
            items(books, key = { it.title }) { book ->
                ItemFavouritesListing(
                    modifier =  Modifier.padding(horizontal = 16.dp),
                    book = book,
                    onBookClick = onBookClick,
                    onFavouritesIconClick = onFavouritesIconClick
                )
            }
        }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun FavouriteBooksListingPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            FavouriteBooksListing(
                modifier = Modifier,
                books = listOf(
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
        }
    }
}