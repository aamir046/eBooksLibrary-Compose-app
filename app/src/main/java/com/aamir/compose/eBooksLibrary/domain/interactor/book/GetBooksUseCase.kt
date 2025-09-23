package com.aamir.compose.eBooksLibrary.domain.interactor.book

import com.aamir.compose.eBooksLibrary.domain.repository.IBookRepository

class GetBooksUseCase(private val repo: IBookRepository) {
    suspend operator fun invoke() = repo.getBooks()
}