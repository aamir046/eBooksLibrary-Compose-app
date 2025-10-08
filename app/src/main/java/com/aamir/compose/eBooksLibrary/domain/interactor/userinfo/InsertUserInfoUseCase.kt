package com.aamir.compose.eBooksLibrary.domain.interactor.userinfo

import com.aamir.compose.eBooksLibrary.domain.model.UserInfo
import com.aamir.compose.eBooksLibrary.domain.repository.IUserInfoRepository

class InsertUserInfoUseCase(private val repo: IUserInfoRepository) {
    suspend operator fun invoke(userInfo: UserInfo) = repo.insertUserInfo(userInfo)
}