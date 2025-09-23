package com.aamir.compose.eBooksLibrary.domain.interactor.book

data class FavoriteUseCases(
    val getFavoriteBooksUseCase: GetFavoriteBooksUseCase,
    val insertFavouriteBookUseCase: InsertFavouriteBookUseCase,
    val toggleFavoriteUseCase: ToggleFavoriteUseCase
)