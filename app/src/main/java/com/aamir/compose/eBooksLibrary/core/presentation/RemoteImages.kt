package com.aamir.compose.eBooksLibrary.core.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aamir.compose.eBooksLibrary.R

@Composable
fun LoadRemoteImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.None,
    placeholderRes: Int = R.drawable.cover_image,
    errorRes: Int = R.drawable.cover_image,
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(url)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            placeholder = painterResource(id = placeholderRes),
            modifier = Modifier.fillMaxSize(),
            error = painterResource(id = placeholderRes)
        )
    }
}