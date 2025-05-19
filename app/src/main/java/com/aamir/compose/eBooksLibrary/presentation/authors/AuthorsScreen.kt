package com.aamir.compose.eBooksLibrary.presentation.authors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AuthorsScreenRoot(
    viewModel: AuthorsViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (AuthorsScreenActions) -> Unit = { action -> }

    AuthorsScreen(
        uiState = uiState,
        modifier = Modifier,
        onActions = onActions
    )
}

@Composable
fun AuthorsScreen(
    uiState: AuthorsScreenState,
    modifier: Modifier = Modifier,
    onActions: (AuthorsScreenActions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {}
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun AuthorsScreenPreview() {
    AuthorsScreen(
        uiState = AuthorsScreenState(),
        modifier = Modifier,
        onActions = {}
    )
}
