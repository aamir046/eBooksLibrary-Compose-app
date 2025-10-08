package com.aamir.compose.eBooksLibrary.data.mapper

import com.aamir.compose.eBooksLibrary.data.local.entity.UserInfoEntity
import com.aamir.compose.eBooksLibrary.domain.model.UserInfo

fun UserInfoEntity.toDomain() = UserInfo(name, email, imageUrl, address, phoneNumber)
fun UserInfo.toEntity() =
    UserInfoEntity(
        name = name,
        email = email,
        imageUrl = imageUrl,
        address = address,
        phoneNumber = phoneNumber
    )
