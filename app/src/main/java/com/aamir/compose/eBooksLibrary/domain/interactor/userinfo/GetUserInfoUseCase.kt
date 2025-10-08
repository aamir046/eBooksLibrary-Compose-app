package com.aamir.compose.eBooksLibrary.domain.interactor.userinfo

import com.aamir.compose.eBooksLibrary.domain.repository.IUserInfoRepository

class GetUserInfoUseCase(private val repo: IUserInfoRepository) {
    suspend operator fun invoke() = repo.getUserInfo()
}