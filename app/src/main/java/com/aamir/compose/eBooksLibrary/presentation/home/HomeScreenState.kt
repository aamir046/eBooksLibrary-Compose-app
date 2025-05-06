package com.aamir.compose.eBooksLibrary.presentation.home



data class HomeScreenState (
    val screenSectionItems: List<HomeScreenSectionItem> = emptyList(),
    val titleRecommendedBooks: String = "",
    val titlePopularBooks: String = "",
    val titleTopSearchedBooks: String = "",
    val tileNewReleasedBooks: String = "",
)