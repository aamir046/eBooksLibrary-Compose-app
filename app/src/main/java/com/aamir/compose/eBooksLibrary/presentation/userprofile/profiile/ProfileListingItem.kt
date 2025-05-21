package com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile

import com.aamir.compose.eBooksLibrary.R

data class ProfileListingItem(
    val iconRes: Int = R.drawable.ic_my_account,
    val title: String = "My Account",
    val targetRoute: String = ""
)
