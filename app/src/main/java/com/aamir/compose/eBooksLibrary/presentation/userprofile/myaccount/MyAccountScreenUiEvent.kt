package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

sealed class MyAccountScreenUiEvent {
    data object NavigateBack : MyAccountScreenUiEvent()
    data class ShowMessage(val message: String) : MyAccountScreenUiEvent()
}