package com.aamir.compose.eBooksLibrary.di

import com.aamir.compose.eBooksLibrary.core.presentation.SharedViewModel
import com.aamir.compose.eBooksLibrary.data.repository.AuthorsRepository
import com.aamir.compose.eBooksLibrary.data.repository.HomeRepository
import com.aamir.compose.eBooksLibrary.data.repository.SearchRepository
import com.aamir.compose.eBooksLibrary.presentation.authors.authordetails.AuthorDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting.AuthorsViewModel
import com.aamir.compose.eBooksLibrary.presentation.bookdetails.BookDetailsViewModel
import com.aamir.compose.eBooksLibrary.presentation.categories.CategoriesViewModel
import com.aamir.compose.eBooksLibrary.presentation.home.HomeViewModel
import com.aamir.compose.eBooksLibrary.presentation.notifications.NotificationsViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile.ProfileViewModel
import com.aamir.compose.eBooksLibrary.presentation.search.SearchViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.address.AddressViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites.FavouritesViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter.HelpCenterViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount.MyAccountViewModel
import com.aamir.compose.eBooksLibrary.presentation.userprofile.offersndpromos.OffersAndPromosViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HomeRepository() }
    single { SearchRepository() }
    single { AuthorsRepository() }
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::AuthorsViewModel)
    viewModelOf(::BookDetailsViewModel)
    viewModelOf(::FavouritesViewModel)
    viewModelOf(::MyAccountViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::AddressViewModel)
    viewModel { SharedViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { AuthorDetailsViewModel() }
    viewModel { OffersAndPromosViewModel() }
    viewModel { HelpCenterViewModel() }
}