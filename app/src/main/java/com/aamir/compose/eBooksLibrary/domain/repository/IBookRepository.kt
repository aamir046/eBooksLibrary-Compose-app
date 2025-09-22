package com.aamir.compose.eBooksLibrary.domain.repository

import com.aamir.compose.eBooksLibrary.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface IBookRepository {
    suspend fun getBooks(): Result<List<Book>>
    suspend fun getBook(id: Int): Flow<Book?>
    suspend fun toggleFavourite(bookId: Int)
    suspend fun getFavouriteBooks(): Flow<List<Book>>
}