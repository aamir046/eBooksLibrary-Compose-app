package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.Author
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.authors.authordetails.components.AboutAuthor
import com.aamir.compose.eBooksLibrary.presentation.home.components.ItemBooksListingHorizontal

@Composable
fun AuthorDetailsScreenRoot(
    viewModel: AuthorDetailsViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (AuthorDetailsScreenActions) -> Unit = { action ->
        when (action) {
            is AuthorDetailsScreenActions.OnBackClick -> onBackClick()
        }
    }

    AuthorDetailsScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun AuthorDetailsScreen(
    uiState: AuthorDetailsScreenState = AuthorDetailsScreenState(),
    modifier: Modifier = Modifier,
    actions: (AuthorDetailsScreenActions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AboutAuthor(
                modifier = Modifier.padding(horizontal = 16.dp),
                author = uiState.author
            )

            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Products",
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(Modifier.fillMaxWidth()){
                items(uiState.books){
                    ItemBooksListingHorizontal(
                        modifier = if(it == uiState.books.last())
                            Modifier.padding(horizontal = 12.dp)
                        else
                            Modifier.padding(start = 12.dp),
                        book = it,
                        onBookClick = {}
                    )
                }
            }

        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun AuthorDetailsScreenPreview() {
    AuthorDetailsScreen(
        uiState = AuthorDetailsScreenState(
            author = Author(
                name = "Jane Austen",
                category = "Novelist",
                about = "Renowned for her keen observations of 18th-century English society and themes of love and marriage.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/cd/Jane_Austen_coloured_version.jpg",
                rating = 4.5
            ),
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
                ),
                Book(
                    author = "J.K. Rowling",
                    title = "Harry Potter and the Sorcerer's Stone",
                    year = "1997",
                    description = "The beginning of Harry Potter’s magical journey at Hogwarts.",
                    imageUrl = "https://images.gr-assets.com/books/1474154022l/3.jpg"
                )
            )
        ),
        modifier = Modifier,
        actions = {}
    )
}