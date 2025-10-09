package com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile

import com.aamir.compose.eBooksLibrary.domain.model.UserInfo

data class ProfileScreenState(
    val userInfo: UserInfo = UserInfo(),
    val profileListingItems: List<ProfileListingItem> = emptyList()
)