package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.Book

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class BookDetailsViewModel:ViewModel(){
    private val _uiState = MutableStateFlow<BookDetailsScreenState>(BookDetailsScreenState())

    val uiState = _uiState.onStart {
     //   updateBookDetails()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

//    private fun updateBookDetails() {
//        _uiState.update {
//            it.copy(
//                book = Book(
//                    author = "J.R.R. Tolkien",
//                    title = "The Lord of the Rings",
//                    year = "1954",
//                    description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
//                    imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg"
//                )
//            )
//        }
//    }

    fun onSelectBook(selectedBook:Book?) {
        selectedBook?.let {book->
            _uiState.update {
                it.copy(
                    book = book
                )
            }
        }
    }
}