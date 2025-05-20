package com.aamir.compose.eBooksLibrary.presentation.categories

sealed interface CategoriesScreenActions {
    class OnCategoryClick(val selectedCategory: String) : CategoriesScreenActions

}