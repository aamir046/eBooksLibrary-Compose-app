package com.aamir.compose.eBooksLibrary.presentation.home

import com.aamir.compose.eBooksLibrary.domain.Book

sealed class HomeScreenSectionItem {
    data class UpComingBooks(val upComingBooks: List<Book>) : HomeScreenSectionItem()
    data class RecommendedBooks(val recommendedBooks: List<Book>) : HomeScreenSectionItem()
    data class PopularBooks(val popularBooks: List<Book>) : HomeScreenSectionItem()
    data class TopSearchedBooks(val topSearchedBooks: List<Book>) : HomeScreenSectionItem()
    data class NewReleasedBooks(val newReleasedBooks: List<Book>) : HomeScreenSectionItem()
//    data class TopAuthors(val topAuthors: List<Authors>) : HomeScreenSectionItem() to be added
}