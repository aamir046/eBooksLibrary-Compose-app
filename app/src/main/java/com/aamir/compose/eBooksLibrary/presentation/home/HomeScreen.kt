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
import com.aamir.compose.eBooksLibrary.presentation.home.components.HomeAppBar
import com.aamir.compose.eBooksLibrary.presentation.home.components.SectionItemUpcomingBooks
import com.aamir.compose.eBooksLibrary.presentation.home.components.UpComingBooksLaunchCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        modifier = Modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeScreenState = HomeScreenState(),
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            HomeAppBar()
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        Box(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color.White)) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(uiState.screenSectionItems){item->
                    when (item) {
                        is HomeScreenSectionItem.UpComingBooks -> SectionItemUpcomingBooks(item.upComingBooks)
//                        is HomeScreenSectionItem.RecommendedBooks -> RecommendedBannerPager(item.books)
//                        is HomeScreenSectionItem.PopularBooks -> HorizontalBookSection(item.title, item.books)
//                        is HomeScreenSectionItem.TopSearchedBooks -> HorizontalBookSection(item.title, item.books)
//                        is HomeScreenSectionItem.NewReleasedBooks -> HorizontalBookSection(item.title, item.books)
                        else -> {

                        }
                    }
                }
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun GreetingPreview() {
    HomeScreen()
}