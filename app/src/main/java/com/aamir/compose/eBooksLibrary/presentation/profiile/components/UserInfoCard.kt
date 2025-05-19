package com.aamir.compose.eBooksLibrary.presentation.profiile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.presentation.theme.Gray

@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    userName: String = "",
    userEmail:String = ""

){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray)
    ) {
        Spacer(modifier = Modifier.height(1.dp).background(Color.LightGray).fillMaxWidth())
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadRemoteImage(
                url = "",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .shadow(elevation = 8.dp, shape = CircleShape)
                    .clip(shape = CircleShape)
                    .width(70.dp)
                    .height(70.dp)
                    .border(1.dp, Color.LightGray, CircleShape),
                contentScale = ContentScale.Crop,
                placeholderRes = R.drawable.ic_pofile_deafult
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = userEmail,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.height(1.dp).background(Color.LightGray).fillMaxWidth())
    }

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun UserInfoCardPreview() {
    UserInfoCard(
        modifier = Modifier,
        userName = "Muhammad Aamir",
        userEmail = "mhd.aamir046@gmail.com"
    )
}