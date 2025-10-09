package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter

sealed class HelpCenterScreenUiEvent {
    data object NavigateBack : HelpCenterScreenUiEvent()
    data class ShowMessage(val message: String) : HelpCenterScreenUiEvent()
}