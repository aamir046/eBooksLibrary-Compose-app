package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.home.components.HomeAppBar
import com.aamir.compose.eBooksLibrary.presentation.home.components.HorizontalBooksListing
import com.aamir.compose.eBooksLibrary.presentation.home.components.HorizontalBooksListingPreview
import com.aamir.compose.eBooksLibrary.presentation.home.components.SectionItemUpcomingBooks
import com.aamir.compose.eBooksLibrary.presentation.home.components.UpComingBooksLaunchCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel(),
    onBookClick: (Book) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        modifier = Modifier,
        onBookClick = onBookClick
    )
}

@Composable
fun HomeScreen(
    uiState: HomeScreenState = HomeScreenState(),
    modifier: Modifier = Modifier,
    onBookClick: (Book) -> Unit = {}
) {
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.screenSectionItems) { item ->
                    when (item) {
                        is HomeScreenSectionItem.UpComingBooks -> SectionItemUpcomingBooks(
                           books = item.upComingBooks
                        )
                        is HomeScreenSectionItem.RecommendedBooks -> HorizontalBooksListing(
                            rowTitle = uiState.titleRecommendedBooks,
                            books = item.recommendedBooks,
                            onBookClick = onBookClick
                        )

                        is HomeScreenSectionItem.PopularBooks -> HorizontalBooksListing(
                            rowTitle = uiState.titlePopularBooks,
                            books = item.popularBooks,
                            onBookClick = onBookClick
                        )

                        is HomeScreenSectionItem.TopSearchedBooks -> HorizontalBooksListing(
                            rowTitle = uiState.titleTopSearchedBooks,
                            books = item.topSearchedBooks,
                            onBookClick = onBookClick
                        )

                        is HomeScreenSectionItem.NewReleasedBooks -> HorizontalBooksListing(
                            rowTitle = uiState.tileNewReleasedBooks,
                            books = item.newReleasedBooks,
                            onBookClick = onBookClick
                        )
                    }
                }
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}