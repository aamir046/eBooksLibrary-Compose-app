package com.aamir.compose.eBooksLibrary.data.repository

import com.aamir.compose.eBooksLibrary.domain.Book

class BookRepository {
    fun fetchBooks(): List<Book> = listOf(
        Book(
            "J.R.R. Tolkien", "AlgoLogics", "2022", "An Amazing Book for Clean Code Programming"
        )
    )

}