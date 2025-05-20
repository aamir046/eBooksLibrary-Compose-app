package com.aamir.compose.eBooksLibrary.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.categories.components.CategoriesTab
import com.aamir.compose.eBooksLibrary.presentation.categories.components.ItemBooksListing

@Composable
fun CategoriesScreenRoot(
    viewModel: CategoriesViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (CategoriesScreenActions) -> Unit = { action ->
        viewModel.onActions(action)
    }

    CategoriesScreen(
        uiState = uiState,
        modifier = Modifier,
        onActions = onActions
    )
}

@Composable
fun CategoriesScreen(
    uiState: CategoriesScreenState,
    modifier: Modifier = Modifier,
    onActions: (CategoriesScreenActions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyRow {
            items(uiState.categories){
                CategoriesTab(
                    modifier = Modifier,
                    name = it,
                    isSelected = uiState.selectedCategory == it,
                    onItemClick = {selectedCategory->
                        onActions(CategoriesScreenActions.OnCategoryClick(selectedCategory))
                    }
                )
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
        ) {
            items(uiState.books.size) { book ->
                ItemBooksListing(
                    book = uiState.books[book],
                    onBookClick = { }
                )
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen(
        uiState = CategoriesScreenState(
            categories = listOf(
                "All",
                "Novels",
                "History",
                "Science",
                "Self Love",
                "Adventure",
                "Romantic",
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
                ),
                Book(
                    author = "J.R.R. Tolkien",
                    title = "The Lord of the Rings",
                    year = "1954",
                    description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
                    imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg"
                )
            )
        ),
        modifier = Modifier,
        onActions = {}
    )
}
