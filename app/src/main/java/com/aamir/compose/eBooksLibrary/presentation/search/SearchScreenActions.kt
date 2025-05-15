package com.aamir.compose.eBooksLibrary.presentation.search

import com.aamir.compose.eBooksLibrary.domain.Book

sealed interface SearchScreenActions {
    data object OnBackClick : SearchScreenActions
    data class OnSearchResultSelected(val book: Book) : SearchScreenActions
    data class OnSearchQuery(val searchQuery:String) : SearchScreenActions
    data class OnRecentSearchSelected(val searchText: String) : SearchScreenActions
}