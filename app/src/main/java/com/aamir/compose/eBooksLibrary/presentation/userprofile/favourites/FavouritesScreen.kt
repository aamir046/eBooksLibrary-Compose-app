package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites.components.FavouriteBooksListing

@Composable
fun FavouritesScreenRoot(
    viewModel: FavouritesViewModel,
    onBackClick: () -> Unit = {},
    onBookClick: (Book) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (FavouritesScreenActions) -> Unit = { action ->
        when (action) {
            is FavouritesScreenActions.OnBackClick -> onBackClick()
            is FavouritesScreenActions.OnBookClick -> onBookClick(action.book)
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
    FavouriteBooksListing(
        modifier = modifier,
        books = uiState.favouriteBooks,
        onBookClick = {
            actions(FavouritesScreenActions.OnBookClick(it))
        },
        onFavouritesIconClick = {
            actions(FavouritesScreenActions.OnFavouritesIconClick(it))
        }
    )
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