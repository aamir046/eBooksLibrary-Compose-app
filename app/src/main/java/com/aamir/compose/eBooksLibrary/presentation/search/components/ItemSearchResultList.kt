package com.aamir.compose.eBooksLibrary.presentation.search.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.domain.model.Book
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage

@Composable
fun ItemSearchResultList(
    book: Book,
    onSearchResultSelected: (Book) -> Unit
){
    Column(
        modifier= Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable(enabled = true) { onSearchResultSelected.invoke(book) }
    ) {
        LoadRemoteImage(
            url = book.imageUrl,
            contentDescription = "Book Cover Picture",
            modifier = Modifier
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
                .clipToBounds()
                .fillMaxWidth()
                .height(200.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds,
        )

        Text(
            text = book.title,
            style = MaterialTheme.typography.labelLarge,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top=8.dp, start = 8.dp, end = 8.dp).fillMaxWidth()
        )

        Text(
            text = book.author,
            style = MaterialTheme.typography.labelMedium,
            color = Color.DarkGray,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        )
    }
}