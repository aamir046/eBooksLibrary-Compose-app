package com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting

import com.aamir.compose.eBooksLibrary.domain.Author

sealed interface AuthorsScreenActions {
    data class OnAuthorsCategory(val selectedCategory: String) : AuthorsScreenActions
    data class OnAuthorSelected(val selectedAuthor: Author) : AuthorsScreenActions
}