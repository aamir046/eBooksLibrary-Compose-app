// data/local/datasource/BookLocalDataSource.kt
package com.aamir.compose.eBooksLibrary.data.local.datasource

import com.aamir.compose.eBooksLibrary.data.local.dao.BookDao
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity

class BookLocalDataSource(private val dao: BookDao) {
    suspend fun getBooks() = dao.getBooks()
    suspend fun getBook(id: String) = dao.getBook(id)
    suspend fun insertBook(book: BookEntity) = dao.insertBook(book)
    suspend fun toggleFavourite(id: String) = dao.toggleFavourite(id)
    suspend fun getFavouriteBooks() = dao.getFavouriteBooks()
}
