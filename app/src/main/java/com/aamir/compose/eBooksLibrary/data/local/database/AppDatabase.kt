package com.aamir.compose.eBooksLibrary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aamir.compose.eBooksLibrary.data.local.dao.BookDao
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
