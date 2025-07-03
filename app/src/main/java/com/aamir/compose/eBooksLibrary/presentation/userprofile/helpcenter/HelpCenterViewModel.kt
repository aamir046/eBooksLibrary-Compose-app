package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.core.presentation.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HelpCenterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HelpCenterScreenState())

    val uiState = _uiState.onStart {

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    fun onAction(actions: HelpCenterScreenActions) {
        when (actions) {
            else -> {}
        }
    }

}