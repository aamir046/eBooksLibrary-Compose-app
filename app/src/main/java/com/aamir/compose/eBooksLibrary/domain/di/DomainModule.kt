package com.aamir.compose.eBooksLibrary.domain.di

import com.aamir.compose.eBooksLibrary.domain.interactor.book.DeleteFavouriteBookUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.FavoriteUseCases
import com.aamir.compose.eBooksLibrary.domain.interactor.book.GetBooksUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.GetFavoriteBooksUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.InsertFavouriteBookUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.IsFavoriteBookUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.book.ToggleFavoriteUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.userinfo.GetUserInfoUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.userinfo.InsertUserInfoUseCase
import com.aamir.compose.eBooksLibrary.domain.interactor.userinfo.UserInfoUseCases
import org.koin.dsl.module

val domainModule = module {
    //Books use cases
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

    //user info use cases
    factory { GetUserInfoUseCase(get()) }
    factory { InsertUserInfoUseCase(get()) }
    factory {
        UserInfoUseCases(
            getUserInfoUseCase = get(),
            insertUserInfoUseCase = get(),
        )
    }

}

