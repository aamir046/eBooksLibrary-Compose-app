package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.model.Book
import com.aamir.compose.eBooksLibrary.presentation.home.components.HorizontalBooksListing
import com.aamir.compose.eBooksLibrary.presentation.home.components.SectionItemUpcomingBooks

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel,
    onBookClick: (Book) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (HomeScreenActions) -> Unit = { action ->
        when (action) {
            is HomeScreenActions.OnBookClick -> onBookClick(action.book)
            is HomeScreenActions.OnSearchClick -> onSearchClick()
            is HomeScreenActions.OnNotificationsClick -> onNotificationsClick()
        }
    }

    HomeScreen(
        modifier = Modifier,
        uiState = uiState,
        onActions = onActions
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenState = HomeScreenState(),
    onActions: (HomeScreenActions) -> Unit,
) {
    val scrollState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(rememberNestedScrollInteropConnection()),
            state = scrollState,
        ) {
            items(uiState.screenSectionItems) { item ->
                when (item) {
                    is HomeScreenSectionItem.UpComingBooks -> SectionItemUpcomingBooks(
                        books = item.upComingBooks
                    )

                    is HomeScreenSectionItem.RecommendedBooks -> HorizontalBooksListing(
                        rowTitle = uiState.titleRecommendedBooks,
                        books = item.recommendedBooks,
                        onBookClick = { onActions.invoke(HomeScreenActions.OnBookClick(it)) }
                    )

                    is HomeScreenSectionItem.PopularBooks -> HorizontalBooksListing(
                        rowTitle = uiState.titlePopularBooks,
                        books = item.popularBooks,
                        onBookClick = { onActions.invoke(HomeScreenActions.OnBookClick(it)) }
                    )

                    is HomeScreenSectionItem.TopSearchedBooks -> HorizontalBooksListing(
                        rowTitle = uiState.titleTopSearchedBooks,
                        books = item.topSearchedBooks,
                        onBookClick = { onActions.invoke(HomeScreenActions.OnBookClick(it)) }
                    )

                    is HomeScreenSectionItem.NewReleasedBooks -> HorizontalBooksListing(
                        rowTitle = uiState.tileNewReleasedBooks,
                        books = item.newReleasedBooks,
                        onBookClick = { onActions.invoke(HomeScreenActions.OnBookClick(it)) }
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeScreenState(),
        modifier = Modifier,
        onActions = {}
    )
}