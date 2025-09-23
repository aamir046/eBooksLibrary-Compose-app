package com.aamir.compose.eBooksLibrary.data.local.dao

import androidx.room.*
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books WHERE id = :id LIMIT 1")
    fun getBook(id: Int):Flow<BookEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteBook(book: BookEntity)

    @Update
    suspend fun updateBook(book: BookEntity)

    @Query("UPDATE books SET isFavourite = NOT isFavourite WHERE id = :id")
    suspend fun toggleFavourite(id: Int)

    @Query("SELECT * FROM books WHERE isFavourite = 1")
    fun getFavouriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT isFavourite FROM books WHERE id = :bookId LIMIT 1")
    fun isFavorite(bookId: Int): Flow<Boolean>
}
