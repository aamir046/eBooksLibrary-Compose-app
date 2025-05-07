package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import androidx.compose.foundation.background
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.components.BookDetailsAppBar
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.components.RatingBar
import com.aamir.compose.eBooksLibrary.presentation.home.components.LoadBookCoverImage
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
            BookDetailsAppBar(
                onBackClick = {  }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color.White)){
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            ) {
                LoadBookCoverImage(
                    url = uiState.book?.imageUrl ?: "",
                    contentDescription = "Book Cover Picture",
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(25.dp))
                        .clipToBounds()
                        .fillMaxWidth(0.61f)
                        .fillMaxHeight(0.5f)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.FillBounds,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = modifier.fillMaxWidth().wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = uiState.book?.title ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .height(34.dp)
                            .width(34.dp),
                        onClick = {  }
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
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column {
                    Text(
                        text = "Review",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    RatingBar(rating = 4.5f)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Synopsis",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.fillMaxWidth()
                )

                Text(
                    text = uiState.book?.description ?: "",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    modifier = modifier.fillMaxWidth()
                )
            }
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
    )
}