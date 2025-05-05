package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.data.repository.BookRepository
import com.aamir.compose.eBooksLibrary.domain.Book
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(private val bookRepository: BookRepository) : ViewModel() {

    private var bookFetchJob: Job? = null

    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState())

    val uiState = _uiState.onStart {
        fetchBooks()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun fetchBooks() {
        bookFetchJob?.cancel()
        bookFetchJob = bookRepository
            .fetchBooks()
            .onEach { booksList ->
                _uiState.update {
                    it.copy(
                        screenSectionItems = createHomeSectionsItemList(booksList)
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun createHomeSectionsItemList(books:List<Book>):List<HomeScreenSectionItem>{
        return listOf(
            HomeScreenSectionItem.UpComingBooks(books),
            HomeScreenSectionItem.RecommendedBooks(books),
            HomeScreenSectionItem.PopularBooks(books),
            HomeScreenSectionItem.TopSearchedBooks(books),
            HomeScreenSectionItem.NewReleasedBooks(books),
        )
    }
}