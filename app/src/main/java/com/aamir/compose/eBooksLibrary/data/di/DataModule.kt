package com.aamir.compose.eBooksLibrary.data.di

import androidx.room.Room
import com.aamir.compose.eBooksLibrary.data.local.database.AppDatabase
import com.aamir.compose.eBooksLibrary.data.local.datasource.BookLocalDataSource
import com.aamir.compose.eBooksLibrary.data.remote.api.BookApi
import com.aamir.compose.eBooksLibrary.data.remote.datasource.BookRemoteDataSource
import com.aamir.compose.eBooksLibrary.data.repository.BookRepositoryImpl
import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "my_database"
        ).build()
    }

    // --- Local ---
    single { get<AppDatabase>().bookDao() }
    single { BookLocalDataSource(get()) }

    // --- Network ---
    single { BookRemoteDataSource(get()) }
    single { BookApi() }

    // --- Repository ---
    single<IBookRepository> { BookRepositoryImpl(get(), get()) }
}
