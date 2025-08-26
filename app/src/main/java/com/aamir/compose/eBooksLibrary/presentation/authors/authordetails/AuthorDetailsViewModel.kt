package com.aamir.compose.eBooksLibrary.presentation.authors.authordetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.model.Author
import com.aamir.compose.eBooksLibrary.domain.model.Book

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AuthorDetailsViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(AuthorDetailsScreenState())

    val uiState = _uiState.onStart {
        createDummyBooksList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun createDummyBooksList() {
        _uiState.update {
            it.copy(
                books = listOf(
                    Book(
                        author = "Harper Lee",
                        title = "To Kill a Mockingbird",
                        year = "1960",
                        description = "A story of racial injustice and childhood innocence in the Deep South.",
                        imageUrl = "https://images.gr-assets.com/books/1553383690l/2657.jpg"
                    ),
                    Book(
                        author = "George Orwell",
                        title = "1984",
                        year = "1949",
                        description = "A chilling vision of a dystopian world under total surveillance.",
                        imageUrl = "https://images.penguinrandomhouse.com/cover/9780679417392"
                    ),
                    Book(
                        author = "J.K. Rowling",
                        title = "Harry Potter and the Sorcerer's Stone",
                        year = "1997",
                        description = "The beginning of Harry Potter’s magical journey at Hogwarts.",
                        imageUrl = "https://images.gr-assets.com/books/1474154022l/3.jpg"
                    )
                )
            )
        }
    }

    fun onSelectAuthor(selectedAuthor: Author?) {
        selectedAuthor?.let {author->
            _uiState.update {
                it.copy(
                    author = author
                )
            }
        }
    }
}