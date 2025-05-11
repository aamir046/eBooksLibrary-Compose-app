package com.aamir.compose.eBooksLibrary.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecentSearches(
    modifier: Modifier = Modifier,
    recentSearches: List<String> = emptyList(),
    onRecentSearchSelected:(String)-> Unit = {}
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Recent Searches",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(recentSearches){ recentSearch: String ->
                ItemRecentSearches(
                    searchText = recentSearch,
                    onRecentSearchSelected = onRecentSearchSelected
                )
            }
        }
    }
}

@Composable
fun ItemRecentSearches(
    modifier: Modifier = Modifier,
    searchText: String,
    onRecentSearchSelected: (String) -> Unit = {}
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onRecentSearchSelected.invoke(searchText)
            }
    ) {
        Text(
            text = searchText,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Spacer(
            modifier = Modifier.height(1.dp).background(Color.LightGray).fillMaxWidth()
        )

    }
}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun RecentSearchesPreview() {
    RecentSearches(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp),
        recentSearches = listOf(
            "The Good Sister",
            "Kite Runner",
            "History",
            "Adventure",
            "The hunger Games",
        )
    )
}