package com.aamir.compose.eBooksLibrary.presentation.profiile

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
fun ProfileScreenRoot(
    viewModel: ProfileViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (ProfileScreenActions) -> Unit = { action -> }

    ProfileScreen(
        uiState = uiState,
        modifier = Modifier,
        onActions = onActions
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileScreenState,
    modifier: Modifier = Modifier,
    onActions: (ProfileScreenActions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileScreenState(),
        modifier = Modifier,
        onActions = {}
    )
}
