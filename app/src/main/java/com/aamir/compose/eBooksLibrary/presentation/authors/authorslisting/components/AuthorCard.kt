package com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage

@Composable
fun AuthorCard(
    modifier: Modifier = Modifier,
    authorName: String = "",
    aboutAuthor:String = "",
    imageURL: String = "",
    onAuthorClick: () -> Unit = {}
){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onAuthorClick.invoke() },
            verticalAlignment = Alignment.Top
        ) {
            LoadRemoteImage(
                url = imageURL,
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
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = authorName,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = aboutAuthor,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.LightGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun AuthorCardPreview() {
    AuthorCard(
        modifier = Modifier,
        authorName = "Muhammad Aamir",
        aboutAuthor = "sa sadas dasd asd sadksad asd sad sa da sd sa dasda dsa dsa das das ds da dsadsadsadas d"
    )
}
