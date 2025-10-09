package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import android.content.Context
import android.net.Uri

sealed interface MyAccountScreenActions {
    data class OnNameChange(val name: String) : MyAccountScreenActions
    data class OnEmailChange(val email: String) : MyAccountScreenActions
    data class OnPhoneNumberChange(val phoneNumber: String) : MyAccountScreenActions
    data class OnImagePicked(val uri: Uri, val context: Context) : MyAccountScreenActions
    data object OnUpdateUserInfo : MyAccountScreenActions
    data object OnBackClick : MyAccountScreenActions
}