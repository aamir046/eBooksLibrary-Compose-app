package com.aamir.compose.eBooksLibrary.domain.interactor.userinfo

data class UserInfoUseCases(
    val getUserInfoUseCase: GetUserInfoUseCase,
    val insertUserInfoUseCase: InsertUserInfoUseCase,
)