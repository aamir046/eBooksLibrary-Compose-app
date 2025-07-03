package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FavouritesScreenRoot(
    viewModel: FavouritesViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (FavouritesScreenActions) -> Unit = { action ->
        when (action) {
            is FavouritesScreenActions.OnBackClick -> onBackClick()
            else -> viewModel.onAction(action)
        }
    }

    FavouritesScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    uiState: FavouritesScreenState = FavouritesScreenState(),
    actions: (FavouritesScreenActions) -> Unit
) {

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun FavouritesScreenPreview() {
    FavouritesScreen(
        uiState = FavouritesScreenState(),
        modifier = Modifier,
        actions = {}
    )
}