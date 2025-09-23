package com.aamir.compose.eBooksLibrary.domain.interactor.book

import com.aamir.compose.eBooksLibrary.domain.model.Book
import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository

class InsertFavouriteBookUseCase(private val repo: IBookRepository) {
    suspend operator fun invoke(book: Book) = repo.insertFavoriteBook(book)
}