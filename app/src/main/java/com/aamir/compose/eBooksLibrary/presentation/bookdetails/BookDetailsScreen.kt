package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.aamir.compose.eBooksLibrary.presentation.home.components.HomeAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookDetailsScreenRoot(
    viewModel: BookDetailsViewModel = koinViewModel()
) {
    BookDetailsScreen(
        uiState = BookDetailsScreenState(),
        modifier = Modifier
    )
}

@Composable
fun BookDetailsScreen(
    uiState: BookDetailsScreenState = BookDetailsScreenState(),
    modifier: Modifier = Modifier
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

        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun BookDetailsScreenPreview() {
    BookDetailsScreen()
}