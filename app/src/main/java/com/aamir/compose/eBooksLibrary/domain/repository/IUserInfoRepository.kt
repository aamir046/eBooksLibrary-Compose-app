package com.aamir.compose.eBooksLibrary.domain.repository

import com.aamir.compose.eBooksLibrary.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface IUserInfoRepository {
    suspend fun getUserInfo(): Flow<UserInfo>
    suspend fun insertUserInfo(userInfo: UserInfo)
}