package com.aamir.compose.eBooksLibrary.presentation.authors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class AuthorsViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(AuthorsScreenState())
    val uiState: StateFlow<AuthorsScreenState> = _uiState.onStart {}.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

}