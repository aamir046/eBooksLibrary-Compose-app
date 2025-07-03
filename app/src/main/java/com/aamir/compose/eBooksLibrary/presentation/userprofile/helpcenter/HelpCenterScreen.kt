package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HelpCenterScreenRoot(
    viewModel: HelpCenterViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (HelpCenterScreenActions) -> Unit = { action ->
        when (action) {
            is HelpCenterScreenActions.OnBackClick -> onBackClick()
            else -> viewModel.onAction(action)
        }
    }

    HelpCenterScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun HelpCenterScreen(
    modifier: Modifier = Modifier,
    uiState: HelpCenterScreenState = HelpCenterScreenState(),
    actions: (HelpCenterScreenActions) -> Unit
) {

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun HelpCenterScreenPreview() {
    HelpCenterScreen(
        uiState = HelpCenterScreenState(),
        modifier = Modifier,
        actions = {}
    )
}