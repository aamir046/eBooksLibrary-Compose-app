package com.aamir.compose.eBooksLibrary.presentation.profiile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.presentation.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileListingCard(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {},
    iconRes: Int = R.drawable.ic_my_account,
    title: String = "My Account"
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadRemoteImage(
                url = "",
                contentDescription = "listing icon",
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .width(50.dp)
                    .height(50.dp),
                contentScale = ContentScale.Crop,
                placeholderRes = iconRes
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(0.1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_listing),
                contentDescription = "next",
                tint = Color.LightGray,
            )
        }
    }

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun ProfileListingCardPreview() {
    ProfileListingCard(
        modifier = Modifier,
    )
}