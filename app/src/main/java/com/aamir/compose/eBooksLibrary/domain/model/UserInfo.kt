package com.aamir.compose.eBooksLibrary.domain.model

data class UserInfo(
    var name:String="Guest User",
    var email:String = "",
    var imageUrl:String= "",
    var address:String = "",
    var phoneNumber:String = "",
)
