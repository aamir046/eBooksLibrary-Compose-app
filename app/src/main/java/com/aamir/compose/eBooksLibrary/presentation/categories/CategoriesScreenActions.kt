package com.aamir.compose.eBooksLibrary.presentation.categories

import com.aamir.compose.eBooksLibrary.domain.model.Book

sealed interface CategoriesScreenActions {
    data class OnCategoryClick(val selectedCategory: String) : CategoriesScreenActions
    data class OnBookClick(val book: Book) : CategoriesScreenActions
}