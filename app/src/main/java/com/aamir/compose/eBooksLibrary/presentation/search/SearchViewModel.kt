package com.aamir.compose.eBooksLibrary.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class SearchViewModel(
    private val searchRepository: SearchRepository
):ViewModel() {

    private var searchJob: Job? = null

    private val _uiState = MutableStateFlow(SearchScreenState())
    val uiState: StateFlow<SearchScreenState> = _uiState.onStart {
        observeSearchQuery()
        createDummyList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun createDummyList() {
        _uiState.update {
            it.copy(
                recentSearches = listOf(
                    "The great wall",
                    "The hunger Games",
                    "Kite Runner",
                    "Life of Pie"
                )
            )
        }
    }

    fun onSearchQuery(searchQuery:String){
        _uiState.update {
            it.copy(
                searchQuery = searchQuery
            )
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        _uiState
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _uiState.update {
                            //also load recent Searches
                           it.copy(
                               searchResult = emptyList()
                           )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchBooks(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
       val searchResult = searchRepository.searchBooks(query)
        searchResult.onEach { books->
            _uiState.update {
                it.copy(
                    searchResult = books
                )
            }
        }.stateIn(viewModelScope)
    }
}