package com.aamir.compose.eBooksLibrary.presentation.categories

import com.aamir.compose.eBooksLibrary.domain.Book

sealed interface CategoriesScreenActions {
    data class OnCategoryClick(val selectedCategory: String) : CategoriesScreenActions
    data class OnBookClick(val book: Book) : CategoriesScreenActions
}