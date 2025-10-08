package com.aamir.compose.eBooksLibrary.data.local.datasource

import com.aamir.compose.eBooksLibrary.data.local.dao.UserDao
import com.aamir.compose.eBooksLibrary.data.local.entity.UserInfoEntity

class UserInfoLocalDataSource(private val dao: UserDao) {
    fun getUserInfo() = dao.getUserInfo()
    suspend fun insertUserInfo(userInfoEntity: UserInfoEntity) =
        dao.insertUserInfo(userInfoEntity)
}
