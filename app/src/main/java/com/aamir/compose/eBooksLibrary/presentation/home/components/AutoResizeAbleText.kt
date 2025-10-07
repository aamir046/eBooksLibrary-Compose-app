package com.aamir.compose.eBooksLibrary.presentation.home.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AutoResizeAbleText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    maxLines: Int = 2,
    fontWeight: FontWeight = FontWeight.Bold,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    minTextSize: TextUnit = 12.sp,
    maxTextSize: TextUnit = style.fontSize,
) {
    var textSize by remember { mutableStateOf(maxTextSize) }

    Text(
        text = text,
        modifier = modifier,
        style = style.copy(fontSize = textSize),
        maxLines = maxLines,
        fontWeight = fontWeight,
        overflow = overflow,
        onTextLayout = { result ->
            if (result.hasVisualOverflow && textSize > minTextSize) {
                textSize *= 0.9f
            }
        }
    )
}