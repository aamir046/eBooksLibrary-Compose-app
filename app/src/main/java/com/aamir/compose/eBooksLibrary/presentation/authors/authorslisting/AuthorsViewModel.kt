package com.aamir.compose.eBooksLibrary.presentation.authors.authorslisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.data.repository.AuthorsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AuthorsViewModel(
    private val authorsRepository: AuthorsRepository
):ViewModel() {

    private var authorsFetchJob: Job? = null

    private val _uiState = MutableStateFlow(AuthorsScreenState())
    val uiState: StateFlow<AuthorsScreenState> = _uiState.onStart {
        createCategoriesList()
        fetchAuthors()
        
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    fun onActions(actions: AuthorsScreenActions) {
        when (actions) {
            is AuthorsScreenActions.OnAuthorsCategory -> {
                _uiState.value = _uiState.value.copy(
                    authorsCategory = actions.selectedCategory
                )
            }
            else -> {}
        }
    }

    private fun createCategoriesList() {
        _uiState.update {
            it.copy(
                authorsCategoriesList = listOf(
                    "All",
                    "Poets",
                    "Novelists",
                    "Journalists",
                    "Historians",
                    "Playwrights",
                    "Columnists",
                    "Biographers",
                ),
                authorsCategory = "All"
            )
        }
    }

    private fun fetchAuthors() {
        authorsFetchJob?.cancel()
        authorsFetchJob = authorsRepository
            .fetchAuthors()
            .onEach { authors ->
                _uiState.update {
                    it.copy(
                        authors = authors
                    )
                }
            }
            .launchIn(viewModelScope)
    }

}