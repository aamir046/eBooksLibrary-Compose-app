package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails

import com.aamir.compose.eBooksLibrary.domain.Author
import com.aamir.compose.eBooksLibrary.domain.Book

data class AuthorDetailsScreenState(
    val author: Author?=null,
    val books: List<Book> = emptyList()
)