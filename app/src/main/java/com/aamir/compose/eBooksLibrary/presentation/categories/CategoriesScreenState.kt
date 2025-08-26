package com.aamir.compose.eBooksLibrary.presentation.categories

import com.aamir.compose.eBooksLibrary.domain.model.Book

data class CategoriesScreenState(
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "",
    val books: List<Book> = emptyList()
)