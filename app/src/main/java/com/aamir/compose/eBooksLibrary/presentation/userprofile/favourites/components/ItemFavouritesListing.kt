package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.domain.Book

@Composable
fun ItemFavouritesListing(
    modifier: Modifier = Modifier,
    book: Book,
    onBookClick: (Book) -> Unit,
    onFavouritesIconClick: (Book) -> Unit = { }
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = true) {
                    onBookClick(book)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadRemoteImage(
                url = book.imageUrl,
                contentDescription = "Book Cover Picture",
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
                    .clipToBounds()
                    .width(80.dp)
                    .height(80.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds,
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f,true),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = book.author,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()

                )
            }

            IconButton(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp),
                onClick = { onFavouritesIconClick(book) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fav_checked),
                    contentDescription = "Search"
                )
            }

        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(1.dp)
                .background(Color.LightGray)
                .fillMaxWidth()
        )
    }

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun ItemFavouritesListingPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            ItemFavouritesListing(
                modifier = Modifier,
                book = Book(
                    author = "Harper Lee",
                    title = "To Kill a Mockingbird",
                    year = "1960",
                    description = "A story of racial injustice and childhood innocence in the Deep South.",
                    imageUrl = "https://images.gr-assets.com/books/1553383690l/2657.jpg"
                ),
                onBookClick = {}
            )
        }
    }
}