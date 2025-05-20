package com.aamir.compose.eBooksLibrary.di

import com.aamir.compose.eBooksLibrary.core.presentation.SharedViewModel
import com.aamir.compose.eBooksLibrary.data.repository.HomeRepository
import com.aamir.compose.eBooksLibrary.data.repository.SearchRepository
import com.aamir.compose.eBooksLibrary.presentation.authors.AuthorsViewModel
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.categories.CategoriesViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsViewModel
import com.aamir.compose.eBooksLibrary.presentation.profiile.ProfileViewModel
import com.aamir.compose.eBooksLibrary.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HomeRepository() }
    single { SearchRepository() }
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModel { AuthorsViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { SharedViewModel() }
    viewModel { BookDetailsViewModel() }
    viewModel { NotificationsViewModel() }
}