package com.aamir.compose.eBooksLibrary.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.data.repository.HomeRepository
import com.aamir.compose.eBooksLibrary.data.repository.SearchRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private var bookFetchJob: Job? = null

    private val _uiState = MutableStateFlow(CategoriesScreenState())
    val uiState: StateFlow<CategoriesScreenState> = _uiState.onStart {
        createCategoriesList()
        fetchBooks()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun createCategoriesList() {
        _uiState.update {
            it.copy(
                categories = listOf(
                    "All",
                    "Novels",
                    "History",
                    "Science",
                    "Self Love",
                    "Adventure",
                    "Romantic",
                ),
                selectedCategory = "All"
            )
        }
    }

    private fun fetchBooks() {
        bookFetchJob?.cancel()
        bookFetchJob = repository
            .fetchBooks()
            .onEach { booksList ->
                _uiState.update {
                    it.copy(
                        books = booksList
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun updateSelectedCategory(category: String) {
        _uiState.update {
            it.copy(
                selectedCategory = category
            )
        }
    }

    fun onActions(actions: CategoriesScreenActions){
        when(actions){
            is CategoriesScreenActions.OnCategoryClick -> updateSelectedCategory(actions.selectedCategory)
        }
    }

}