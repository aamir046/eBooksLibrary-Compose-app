package com.aamir.compose.eBooksLibrary.core.presentation

import androidx.lifecycle.ViewModel
import com.aamir.compose.eBooksLibrary.domain.model.Author
import com.aamir.compose.eBooksLibrary.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel:ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    private val _selectedAuthor = MutableStateFlow<Author?>(null)
    val selectedAuthor = _selectedAuthor.asStateFlow()

    fun onSelectBook(book: Book?) {
        _selectedBook.value = book
    }

    fun onSelectAuthor(author: Author?) {
        _selectedAuthor.value = author
    }

    init {
        println("SharedViewModel initialized")
    }
}