package com.aamir.compose.eBooksLibrary.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.Book

@Composable
fun HorizontalBooksListing(
    modifier: Modifier = Modifier,
    rowTitle:String = "Title",
    books:List<Book> = emptyList(),
    onBookClick: (Book) -> Unit = {}
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = rowTitle,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )

        LazyRow(modifier = modifier.fillMaxSize()) {
            items(books, key = { it.title }) { book ->
                ItemBooksListingHorizontal(
                    modifier =  if(book == books.last())
                        Modifier.padding(horizontal = 12.dp)
                    else
                        Modifier.padding(start = 12.dp),
                    book = book,
                    onBookClick = onBookClick
                )
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun HorizontalBooksListingPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalBooksListing(
                modifier = Modifier,
                rowTitle = "Recommended Books",
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