package com.aamir.compose.eBooksLibrary.di

import com.aamir.compose.eBooksLibrary.core.presentation.SharedViewModel
import com.aamir.compose.eBooksLibrary.data.repository.BookRepository
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { BookRepository() }
    viewModelOf(::HomeViewModel)
    viewModel { SharedViewModel() }
    viewModel { BookDetailsViewModel() }
    viewModel { SearchViewModel() }
}