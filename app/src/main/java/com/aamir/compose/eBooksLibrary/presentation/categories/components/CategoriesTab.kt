package com.aamir.compose.eBooksLibrary.presentation.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aamir.compose.eBooksLibrary.presentation.theme.Purple

@Composable
fun CategoriesTab(
    modifier: Modifier = Modifier,
    name: String = "",
    onItemClick: (String) -> Unit = {},
    isSelected:Boolean = false,
){

    Column(
        modifier = modifier
            .background(Color.White)
            .padding(start = 16.dp)
            .clickable {
                onItemClick(name)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
            color = if(isSelected) Color.Black else Color.LightGray,
            fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Light,
            modifier = Modifier
        )
    }

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun CategoriesTabPreview() {
    CategoriesTab(
        modifier = Modifier,
        name = "All",
        isSelected = true,
    )
}