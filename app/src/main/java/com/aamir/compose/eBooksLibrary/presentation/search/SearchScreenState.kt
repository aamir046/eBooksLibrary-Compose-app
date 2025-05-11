package com.aamir.compose.eBooksLibrary.presentation.search

import com.aamir.compose.eBooksLibrary.domain.Book

data class SearchScreenState(
    val searchQuery: String = "",
    val searchResult: List<Book> = emptyList(),
    val recentSearches: List<String> = emptyList(),

)