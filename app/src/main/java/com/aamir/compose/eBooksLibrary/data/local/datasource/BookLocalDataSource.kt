package com.aamir.compose.eBooksLibrary.data.local.datasource

import com.aamir.compose.eBooksLibrary.data.local.dao.BookDao
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity

class BookLocalDataSource(private val dao: BookDao) {
    suspend fun getBook(id: Int) = dao.getBook(id)
    suspend fun insertBook(book: BookEntity) = dao.insertBook(book)
    suspend fun toggleFavourite(id: Int) = dao.toggleFavourite(id)
    suspend fun getFavouriteBooks() = dao.getFavouriteBooks()
}
