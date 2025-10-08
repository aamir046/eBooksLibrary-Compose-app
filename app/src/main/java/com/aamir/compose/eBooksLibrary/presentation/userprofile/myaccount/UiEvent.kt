package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

sealed class UiEvent {
    data object NavigateBack : UiEvent()
    data class ShowMessage(val message: String) : UiEvent()
}