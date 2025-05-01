package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.lifecycle.ViewModel
import com.aamir.compose.eBooksLibrary.data.repository.BookRepository
import com.aamir.compose.eBooksLibrary.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books = _books.asStateFlow()

    init {
        _books.value = bookRepository.fetchBooks()
    }

}