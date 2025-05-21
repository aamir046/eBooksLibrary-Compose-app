package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

sealed interface MyAccountScreenActions {
    data object OnBackClick : MyAccountScreenActions
}