package com.aamir.compose.eBooksLibrary.domain.interactor.book

import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository

class GetFavoriteBooksUseCase(private val repo: IBookRepository) {
    suspend operator fun invoke() = repo.getFavouriteBooks()
}