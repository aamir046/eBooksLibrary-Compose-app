package com.aamir.compose.eBooksLibrary.domain.repository

import com.aamir.compose.eBooksLibrary.domain.model.Book

interface IBookRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBook(id: String): Book?
    suspend fun toggleFavourite(bookId: String)
    suspend fun getFavouriteBooks(): List<Book>
}