package com.aamir.compose.eBooksLibrary.presentation.notifications.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsAppBar(
    modifier: Modifier = Modifier,
    onBackClick: (Boolean) -> Unit = {}
){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(40.dp)
                    .width(40.dp),
                onClick = { onBackClick.invoke(true) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        )
    )

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun NotificationsAppBarPreview() {
    NotificationsAppBar()
}