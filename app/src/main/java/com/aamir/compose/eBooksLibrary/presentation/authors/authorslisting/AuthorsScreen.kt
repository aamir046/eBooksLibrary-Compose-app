package com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.domain.Author
import com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting.components.AuthorCard
import com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting.components.AuthorsTab

@Composable
fun AuthorsScreenRoot(
    viewModel: AuthorsViewModel,
    onAuthorSelected: (Author) -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val onActions: (AuthorsScreenActions) -> Unit = { action ->
        when (action) {
            is AuthorsScreenActions.OnAuthorSelected -> onAuthorSelected(action.selectedAuthor)
            else -> {
                viewModel.onActions(action)
            }
        }

    }

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyRow(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(uiState.authorsCategoriesList) {
                AuthorsTab(
                    modifier = Modifier,
                    name = it,
                    isSelected = uiState.authorsCategory == it,
                    onItemClick = { authorsCategory ->
                        onActions(AuthorsScreenActions.OnAuthorsCategory(authorsCategory))
                    }
                )
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            items(uiState.authors) { author->
                AuthorCard(
                    authorName = author.name,
                    aboutAuthor = author.about,
                    imageURL = author.imageUrl,
                    onAuthorClick = {
                        onActions(AuthorsScreenActions.OnAuthorSelected(author))
                    }
                )
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun AuthorsScreenPreview() {
    AuthorsScreen(
        uiState = AuthorsScreenState(
            authorsCategoriesList = listOf(
                "All",
                "Poets",
                "Playwrights",
                "Novelists",
                "Journalists",
                "Historians",
                "Columnists",
                "Biographers",
            ),
        ),
        modifier = Modifier,
        onActions = {}
    )
}
