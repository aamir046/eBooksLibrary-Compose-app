package com.aamir.compose.eBooksLibrary.core.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R

@Composable
fun EmptyListComposable(
    modifier: Modifier = Modifier,
    painterResource: Painter,
    title: String,
    subTitle: String,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(200.dp),
            painter = painterResource,
            contentDescription = "Empty List Icon",
            tint = Color.Unspecified
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold ,
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray,
        )
    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun EmptyListComposablePreview() {
    EmptyListComposable(
        modifier = Modifier,
        painterResource = painterResource(id = R.drawable.ic_favourites_empty),
        title = "No favorites",
        subTitle = "You haven't marked any favorite"
    )
}
