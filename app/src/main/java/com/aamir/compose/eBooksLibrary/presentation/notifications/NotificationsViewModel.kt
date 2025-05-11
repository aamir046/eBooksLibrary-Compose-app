package com.aamir.compose.eBooksLibrary.presentation.notifications

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationsViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(NotificationsScreenState())
    val uiState = _uiState.asStateFlow()
}