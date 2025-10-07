package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            is HelpCenterScreenActions.OnDescriptionChange -> _uiState.update {
                it.copy(
                    userFeedback = it.userFeedback.copy(description = actions.description)
                )
            }

            is HelpCenterScreenActions.OnEmailChange -> _uiState.update {
                it.copy(
                    userFeedback = it.userFeedback.copy(email = actions.email)
                )
            }

            is HelpCenterScreenActions.OnSubjectChange -> _uiState.update {
                it.copy(
                    userFeedback = it.userFeedback.copy(subject = actions.subject)
                )
            }

            else -> {}
        }
    }
}