package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HelpCenterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HelpCenterScreenState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvents = MutableSharedFlow<HelpCenterScreenUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

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
            is HelpCenterScreenActions.OnShareFeedback -> {
                viewModelScope.launch {
                    // validate input and send feedback to a server
                    _uiEvents.emit(HelpCenterScreenUiEvent.ShowMessage("Feedback submitted"))
                    _uiEvents.emit(HelpCenterScreenUiEvent.NavigateBack)
                }
            }

            else -> {}
        }
    }
}