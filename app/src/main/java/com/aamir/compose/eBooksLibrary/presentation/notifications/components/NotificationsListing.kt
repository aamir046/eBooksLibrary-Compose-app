package com.aamir.compose.eBooksLibrary.presentation.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.BookInfoNotification
import com.aamir.compose.eBooksLibrary.domain.GeneralNotification
import com.aamir.compose.eBooksLibrary.presentation.notifications.Notifications

@Composable
fun NotificationsListing(
    modifier: Modifier = Modifier,
    title:String="Current",
    notifications: List<Notifications> = emptyList()
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            notifications.forEach{
                when(it){
                    is Notifications.General -> ItemGeneralNotifications(
                        notification = it.generalNotification
                    )
                    is Notifications.BookInfo -> ItemBookInfoNotifications(
                        notification = it.bookInfoNotification
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun NotificationsListingPreview() {
    Surface(modifier = Modifier.background(Color.White)) {
        NotificationsListing(
            modifier = Modifier.padding(16.dp),
            title = "Current",
            notifications = listOf(
                Notifications.General(
                    generalNotification = GeneralNotification(
                        title = "General",
                        description = "this is more than the description i have written for all",
                        dateTime = "05/12/2025: 10:10 PM"
                    )
                ),
                Notifications.General(
                    generalNotification = GeneralNotification(
                        title = "Promotional",
                        description = "this is more than the description i have written for all",
                        dateTime = "05/12/2025: 10:10 PM"
                    )
                ),
                Notifications.BookInfo(
                    bookInfoNotification = BookInfoNotification()
                )
            )
        )
    }
}