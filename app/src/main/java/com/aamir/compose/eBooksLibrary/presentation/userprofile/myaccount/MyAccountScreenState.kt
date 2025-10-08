package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import com.aamir.compose.eBooksLibrary.domain.model.UserInfo

data class MyAccountScreenState(
    val userInfo: UserInfo = UserInfo()
)