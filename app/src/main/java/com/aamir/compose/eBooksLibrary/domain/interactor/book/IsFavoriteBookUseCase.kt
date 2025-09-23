package com.aamir.compose.eBooksLibrary.domain.interactor.book

import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository

class IsFavoriteBookUseCase(private val repo: IBookRepository) {
    suspend operator fun invoke(bookId: Int) = repo.isFavouriteBook(bookId)
}