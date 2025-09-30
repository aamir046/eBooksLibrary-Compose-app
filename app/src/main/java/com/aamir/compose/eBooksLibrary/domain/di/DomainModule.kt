package com.aamir.compose.eBooksLibrary.domain.di

import com.aamir.compose.eBooksLibrary.domain.interactor.book.DeleteFavouriteBookUseCase
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
    factory { DeleteFavouriteBookUseCase(get()) }

    factory {
        FavoriteUseCases(
            getFavoriteBooksUseCase = get(),
            insertFavouriteBookUseCase = get(),
            deleteFavouriteBookUseCase = get(),
            toggleFavoriteUseCase = get(),
            isFavoriteBookUseCase = get()
        )
    }
}

