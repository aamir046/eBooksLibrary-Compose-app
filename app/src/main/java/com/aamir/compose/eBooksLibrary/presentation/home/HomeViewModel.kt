package com.aamir.compose.eBooksLibrary.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.data.repository.HomeRepository
import com.aamir.compose.eBooksLibrary.domain.model.Book
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(private val bookRepository: HomeRepository) : ViewModel() {

    private var bookFetchJob: Job? = null

    private val _uiState = MutableStateFlow<HomeScreenState>(HomeScreenState())

    val uiState = _uiState.onStart {
        fetchBooks()
        setTitleForHomeScreenSections()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun setTitleForHomeScreenSections() {
        _uiState.update {
            it.copy(
                titleRecommendedBooks = "Recommended Books",
                titlePopularBooks = "Popular & Trending Books",
                titleTopSearchedBooks = "Top Searched Books",
                tileNewReleasedBooks = "Newly Arrived",
            )
        }
    }

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
            HomeScreenSectionItem.UpComingBooks(books.filter { it.type == BookListingType.UPCOMING.type }),
            HomeScreenSectionItem.RecommendedBooks(books.filter { it.type == BookListingType.RECOMMENDED.type }),
            HomeScreenSectionItem.PopularBooks(books.filter { it.type == BookListingType.POPULAR.type }),
            HomeScreenSectionItem.TopSearchedBooks(books.filter { it.type == BookListingType.TOP_SEARCHED.type }),
            HomeScreenSectionItem.NewReleasedBooks(books.filter { it.type == BookListingType.NEW_RELEASED.type }),
        )
    }
}
enum class BookListingType(val type:String) {
    UPCOMING("UPCOMING"),
    RECOMMENDED("RECOMMENDED"),
    POPULAR("POPULAR"),
    TOP_SEARCHED("TOP_SEARCHED"),
    NEW_RELEASED("NEW_RELEASED")
}