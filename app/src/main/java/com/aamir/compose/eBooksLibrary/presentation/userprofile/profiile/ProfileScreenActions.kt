package com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile

sealed interface ProfileScreenActions {
   data class OnProfileListingItemClick(val targetRoute: String) : ProfileScreenActions
}