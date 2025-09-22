package com.aamir.compose.eBooksLibrary.data.repository

import com.aamir.compose.eBooksLibrary.data.local.datasource.BookLocalDataSource
import com.aamir.compose.eBooksLibrary.data.mapper.toDomain
import com.aamir.compose.eBooksLibrary.data.mapper.toEntity
import com.aamir.compose.eBooksLibrary.data.remote.datasource.BookRemoteDataSource
import com.aamir.compose.eBooksLibrary.domain.model.Book
import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val local: BookLocalDataSource,
    private val remote: BookRemoteDataSource
) : IBookRepository {

    override suspend fun getBooks(): Result<List<Book>> {
        return try {
            val remoteBooks = remote.getBooks()
            Result.success(remoteBooks.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBook(id: Int):  Flow<Book?> =
        local.getBook(id).map { it?.toDomain() }

    override suspend fun toggleFavourite(bookId: Int) =
        local.toggleFavourite(bookId)

    override suspend fun getFavouriteBooks(): Flow<List<Book>> =
        local.getFavouriteBooks().map { it.map { entity -> entity.toDomain() } }
}
