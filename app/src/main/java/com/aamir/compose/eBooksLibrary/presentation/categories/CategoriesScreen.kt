package com.aamir.compose.eBooksLibrary.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.core.presentation.MainAppBar

@Composable
fun CategoriesScreenRoot(
    viewModel: CategoriesViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (CategoriesScreenActions) -> Unit = { action -> }

    CategoriesScreen(
        uiState = uiState,
        modifier = Modifier,
        onActions = onActions
    )
}

@Composable
fun CategoriesScreen(
    uiState: CategoriesScreenState,
    modifier: Modifier= Modifier,
    onActions: (CategoriesScreenActions) -> Unit
) {
    Scaffold(
        topBar = {
           MainAppBar(
               title = "Categories",
               onNotificationsClick = {  },
               onSearchClick = {}
           )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {}
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun CategoriesScreenPreview() {
    CategoriesScreen(
        uiState = CategoriesScreenState(),
        modifier = Modifier,
        onActions = {}
    )
}
