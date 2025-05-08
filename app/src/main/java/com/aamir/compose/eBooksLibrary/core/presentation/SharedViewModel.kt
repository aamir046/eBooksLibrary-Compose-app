package com.aamir.compose.eBooksLibrary.core.presentation

import androidx.lifecycle.ViewModel
import com.aamir.compose.eBooksLibrary.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel:ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBook.value = book
    }

    init {
        println("SharedViewModel initialized")
    }
}