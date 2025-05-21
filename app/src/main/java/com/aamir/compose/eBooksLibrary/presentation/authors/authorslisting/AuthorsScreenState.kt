package com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting

import com.aamir.compose.eBooksLibrary.domain.Author

data class AuthorsScreenState(
    val authorsCategoriesList: List<String> = emptyList(),
    val authorsCategory: String = "",
    val authors:List<Author> = emptyList()
)