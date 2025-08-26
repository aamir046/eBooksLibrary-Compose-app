package com.aamir.compose.eBooksLibrary.presentation.notifications.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.model.GeneralNotification

@Composable
fun ItemGeneralNotifications(
    modifier: Modifier = Modifier,
    notification: GeneralNotification?=null,
    onNotificationClick: (GeneralNotification) -> Unit = {}
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .wrapContentSize(),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Unspecified,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified,
        ),
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(2.dp)

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Text(
                text = notification?.title ?: "Promotional",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = notification?.description ?: "this is more than the description i have written for all",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal,
                modifier = Modifier,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = notification?.dateTime ?: "05/12/2025: 10:10 PM",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.align(Alignment.End),
                color = Color.Gray
            )
        }
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun ItemGeneralNotificationsPreview() {
    Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        ItemGeneralNotifications()
    }
}