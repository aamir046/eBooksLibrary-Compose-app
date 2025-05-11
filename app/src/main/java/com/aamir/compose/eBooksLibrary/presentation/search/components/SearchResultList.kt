package com.aamir.compose.eBooksLibrary.presentation.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.Book

@Composable
fun SearchResultList(
    modifier: Modifier = Modifier,
    searchResult: List<Book> = emptyList(),
    onSearchResultSelected: (book: Book) -> Unit = {}
){
    Column(
        modifier = modifier.padding(horizontal =  8.dp)
    ) {
        Text(
            text = "Search Result",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal =  8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(searchResult){book->
                ItemSearchResultList(
                    book = book,
                    onSearchResultSelected = onSearchResultSelected
                )
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun SearchResultListPreview() {
    SearchResultList(
        modifier = Modifier
            .fillMaxSize(),
        searchResult = listOf(
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
            ),
            Book(
                author = "J.K. Rowling",
                title = "Harry Potter and the Sorcerer's Stone",
                year = "1997",
                description = "The beginning of Harry Potter’s magical journey at Hogwarts.",
                imageUrl = "https://images.gr-assets.com/books/1474154022l/3.jpg"
            ),
            Book(
                author = "J.R.R. Tolkien",
                title = "The Lord of the Rings",
                year = "1954",
                description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
                imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg"
            )
        ),
        onSearchResultSelected = {}
    )
}