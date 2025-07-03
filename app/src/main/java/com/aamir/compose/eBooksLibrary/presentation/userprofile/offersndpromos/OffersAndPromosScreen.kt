package com.aamir.compose.eBooksLibrary.presentation.userprofile.offersndpromos


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun OffersAndPromosScreenRoot(
    viewModel: OffersAndPromosViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (OffersAndPromosScreenActions) -> Unit = { action ->
        when (action) {
            is OffersAndPromosScreenActions.OnBackClick -> onBackClick()
            else -> viewModel.onAction(action)
        }
    }

    OffersAndPromosScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun OffersAndPromosScreen(
    modifier: Modifier = Modifier,
    uiState: OffersAndPromosScreenState = OffersAndPromosScreenState(),
    actions: (OffersAndPromosScreenActions) -> Unit
) {

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun OffersAndPromosScreenPreview() {
    OffersAndPromosScreen(
        uiState = OffersAndPromosScreenState(),
        modifier = Modifier,
        actions = {}
    )
}