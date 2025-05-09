package com.aamir.compose.eBooksLibrary.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.presentation.search.components.RecentSearches
import com.aamir.compose.eBooksLibrary.presentation.search.components.SearchAppBar
import com.aamir.compose.eBooksLibrary.presentation.search.components.SearchBar

@Composable
fun SearchScreenRoot(
    viewModel: SearchViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState,
        modifier = Modifier
    )
}

@Composable
fun SearchScreen(
    uiState: SearchScreenState,
    modifier: Modifier= Modifier
) {
    Scaffold(
        topBar = {
            SearchAppBar(
                onBackClick = { }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                SearchBar(
                    modifier = Modifier.padding(16.dp),
                    onSearch = {}
                )
                Spacer(modifier = Modifier.height(10.dp))
                RecentSearches(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    recentSearches = listOf(
                        "The Good Sister",
                        "Kite Runner",
                        "History",
                        "Adventure",
                        "The hunger Games",
                    )
                )
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        uiState = SearchScreenState(),
        modifier = Modifier
    )
}
