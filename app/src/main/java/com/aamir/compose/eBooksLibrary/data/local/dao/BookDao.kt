// data/local/dao/BookDao.kt
package com.aamir.compose.eBooksLibrary.data.local.dao

import androidx.room.*
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE id = :id LIMIT 1")
    suspend fun getBook(id: String): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("UPDATE books SET isFavourite = NOT isFavourite WHERE id = :id")
    suspend fun toggleFavourite(id: String)

    @Query("SELECT * FROM books WHERE isFavourite = 1")
    suspend fun getFavouriteBooks(): List<BookEntity>
}
