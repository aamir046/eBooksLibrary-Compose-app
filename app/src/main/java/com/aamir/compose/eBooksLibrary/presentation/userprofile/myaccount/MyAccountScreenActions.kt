package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

sealed interface MyAccountScreenActions {
    data class OnNameChange(val name: String) : MyAccountScreenActions
    data class OnEmailChange(val email: String) : MyAccountScreenActions
    data class OnPhoneNumberChange(val phoneNumber: String) : MyAccountScreenActions
    data object OnBackClick : MyAccountScreenActions
}