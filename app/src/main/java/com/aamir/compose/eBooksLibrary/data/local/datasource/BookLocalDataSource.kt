package com.aamir.compose.eBooksLibrary.data.local.datasource

import com.aamir.compose.eBooksLibrary.data.local.dao.BookDao
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity

class BookLocalDataSource(private val dao: BookDao) {
    fun getBook(id: Int) = dao.getBook(id)
    suspend fun insertFavoriteBook(book: BookEntity) = dao.insertFavoriteBook(book)
    suspend fun deleteFavoriteBook(book: BookEntity) = dao.deleteFavoriteBook(book)
    suspend fun toggleFavourite(id: Int) = dao.toggleFavourite(id)
    fun getFavouriteBooks() = dao.getFavouriteBooks()
    suspend fun isFavouriteBook(id: Int) = dao.isFavorite(id)
}
