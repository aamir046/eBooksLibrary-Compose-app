package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails.components

import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.domain.Author
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.components.RatingBar

@Composable
fun AboutAuthor(
    modifier: Modifier,
    author: Author? = null,
){
    LoadRemoteImage(
        url = author?.imageUrl ?: "",
        contentDescription = "Profile Picture",
        modifier = Modifier
            .shadow(elevation = 8.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .width(150.dp)
            .height(150.dp)
            .border(1.dp, Color.LightGray, CircleShape),
        contentScale = ContentScale.Crop,
        placeholderRes = R.drawable.ic_pofile_deafult
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = author?.name ?: "",
            style = MaterialTheme.typography.titleMedium,
            color = Color.DarkGray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

    Text(
        text = author?.category ?: "",
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )

    Spacer(modifier = Modifier.height(12.dp))

    RatingBar(rating = author?.rating?.toFloat() ?: 4.5f)

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = "About",
        style = MaterialTheme.typography.titleMedium,
        color = Color.DarkGray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.fillMaxWidth()
    )

    Text(
        text = author?.about ?: "",
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    )
}
