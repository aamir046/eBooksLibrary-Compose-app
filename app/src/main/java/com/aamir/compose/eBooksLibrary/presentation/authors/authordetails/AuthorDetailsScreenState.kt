package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails

import com.aamir.compose.eBooksLibrary.domain.model.Author
import com.aamir.compose.eBooksLibrary.domain.model.Book

data class AuthorDetailsScreenState(
    val author: Author?=null,
    val books: List<Book> = emptyList()
)