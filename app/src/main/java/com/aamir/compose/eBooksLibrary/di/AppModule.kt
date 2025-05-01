package com.aamir.compose.eBooksLibrary.di

import com.aamir.compose.eBooksLibrary.data.repository.BookRepository
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { BookRepository() }
    viewModel { HomeViewModel(get()) }
}