package com.aamir.compose.eBooksLibrary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aamir.compose.eBooksLibrary.data.local.dao.AddressDao
import com.aamir.compose.eBooksLibrary.data.local.dao.BookDao
import com.aamir.compose.eBooksLibrary.data.local.dao.UserDao
import com.aamir.compose.eBooksLibrary.data.local.entity.AddressEntity
import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity
import com.aamir.compose.eBooksLibrary.data.local.entity.UserInfoEntity

@Database(
    entities = [BookEntity::class, UserInfoEntity::class, AddressEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao
    abstract fun addressDao(): AddressDao
}
