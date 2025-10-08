package com.aamir.compose.eBooksLibrary.data.repository

import com.aamir.compose.eBooksLibrary.data.local.datasource.UserInfoLocalDataSource
import com.aamir.compose.eBooksLibrary.data.mapper.toDomain
import com.aamir.compose.eBooksLibrary.data.mapper.toEntity
import com.aamir.compose.eBooksLibrary.domain.model.UserInfo
import com.aamir.compose.eBooksLibrary.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoRepositoryImpl(
    private val local: UserInfoLocalDataSource,
) : IUserInfoRepository {

    override suspend fun getUserInfo(): Flow<UserInfo> =
        local.getUserInfo().map { it?.toDomain()?:UserInfo() }

    override suspend fun insertUserInfo(userInfo: UserInfo) =
        local.insertUserInfo(userInfo.toEntity())

}
