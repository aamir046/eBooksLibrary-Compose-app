package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.components.RatingBar
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage

@Composable
fun BookDetailsScreenRoot(
    viewModel: BookDetailsViewModel,
    onBackClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val actions: (BookDetailsScreenActions) -> Unit = { action ->
        when (action) {
            is BookDetailsScreenActions.OnBackClick -> onBackClick()
        }
    }

    BookDetailsScreen(
        uiState = uiState,
        modifier = Modifier,
        actions = actions
    )
}

@Composable
fun BookDetailsScreen(
    uiState: BookDetailsScreenState = BookDetailsScreenState(),
    modifier: Modifier = Modifier,
    actions: (BookDetailsScreenActions) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            LoadRemoteImage(
                url = uiState.book?.imageUrl ?: "",
                contentDescription = "Book Cover Picture",
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp))
                    .fillMaxWidth(0.61f)
                    .fillMaxHeight(0.47f)
                    .align(Alignment.CenterHorizontally)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = uiState.book?.title ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(34.dp)
                        .width(34.dp),
                    onClick = { }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fav_unchecked),
                        contentDescription = "Search",
                        tint = Color.Unspecified
                    )
                }
            }

            Text(
                text = uiState.book?.author ?: "",
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column {
                Text(
                    text = "Review",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                RatingBar(rating = 4.5f)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleMedium,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = uiState.book?.description ?: "",
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, name = "Empty View", device = Devices.PIXEL)
@Composable
fun BookDetailsScreenPreview() {
    BookDetailsScreen(
        uiState = BookDetailsScreenState(
            book = Book(
                author = "J.R.R. Tolkien",
                title = "The Lord of the Rings",
                year = "1954",
                description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
                imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg"
            )
        ),
        modifier = Modifier,
        actions = {}
    )
}