package com.aamir.compose.eBooksLibrary.domain.interactor.book

import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository

class ToggleFavoriteUseCase(private val repo: IBookRepository) {
    suspend operator fun invoke(bookId: Int) = repo.toggleFavourite(bookId)
}