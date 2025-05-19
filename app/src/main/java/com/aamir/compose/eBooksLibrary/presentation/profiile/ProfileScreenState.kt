package com.aamir.compose.eBooksLibrary.presentation.profiile

data class ProfileScreenState(
    val userName: String = "Muhammad Aamir",
    val userEmail: String = "mhd.aamir046@gmail.com",
    val profileListingItems: List<ProfileListingItem> = emptyList()
)