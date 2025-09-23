package com.aamir.compose.eBooksLibrary.domain.di

import com.aamir.compose.eBooksLibrary.domain.interactor.book.FavoriteUseCases
import com.aamir.compose.eBooksLibrary.domain.interactor.book.GetBooksUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.GetFavoriteBooksUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.InsertFavouriteBookUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.IsFavoriteBookUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetBooksUseCase(get()) }
    factory { InsertFavouriteBookUseCase(get()) }
    factory { GetFavoriteBooksUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
    factory { IsFavoriteBookUseCase(get()) }

    factory {
        FavoriteUseCases(
            getFavoriteBooksUseCase = get(),
            insertFavouriteBookUseCase = get(),
            toggleFavoriteUseCase = get()
        )
    }
}

