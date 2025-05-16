package com.aamir.compose.eBooksLibrary.presentation.notifications.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.BookInfoNotification
import com.aamir.compose.eBooksLibrary.presentation.home.components.LoadBookCoverImage
import com.aamir.compose.eBooksLibrary.presentation.theme.PurpleMedium

@Composable
fun ItemBookInfoNotifications(
    modifier: Modifier = Modifier,
    notification: BookInfoNotification?=null,
    onNotificationClick: (BookInfoNotification) -> Unit = {}
) {

    Card(
        modifier = modifier
            .wrapContentHeight()
            .padding(bottom = 8.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
        ),
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
            ) {

                LoadBookCoverImage(
                    url = notification?.book?.imageUrl ?: "",
                    contentDescription = "Book Cover Picture",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(height = 110.dp, width = 90.dp)
                        .align(Alignment.CenterVertically)
                )

                Column(modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = notification?.status?:"New Published",
                        style = MaterialTheme.typography.labelMedium,
                        color = PurpleMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.End).padding(bottom = 16.dp)
                    )

                    Text(
                        text = notification?.book?.title?:"Title",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.padding(2.dp))

                    Text(
                        text = "Author: ${notification?.book?.author}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                    )

                    Text(
                        text = notification?.dateTime ?: "05/12/2025: 10:10 PM",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.align(Alignment.End).padding(top = 16.dp),
                        color = Color.Gray,
                    )
                }
            }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun ItemBookInfoNotificationsPreview() {
    Surface( Modifier.fillMaxSize().padding(16.dp)) {
        ItemBookInfoNotifications()
    }
}