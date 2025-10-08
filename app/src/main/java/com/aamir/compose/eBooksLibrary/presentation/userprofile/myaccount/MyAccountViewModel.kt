package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.interactor.userinfo.UserInfoUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyAccountViewModel (
    private val userInfoUseCases: UserInfoUseCases
):ViewModel(){

    private val _uiState = MutableStateFlow(MyAccountScreenState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadUserInfoData()
    }

    fun onAction(actions: MyAccountScreenActions) {
        when(actions){
            is MyAccountScreenActions.OnEmailChange -> _uiState.update {
                it.copy(userInfo = it.userInfo.copy(email = actions.email))
            }
            is MyAccountScreenActions.OnNameChange -> _uiState.update {
                it.copy(userInfo = it.userInfo.copy(name = actions.name))
            }
            is MyAccountScreenActions.OnPhoneNumberChange -> _uiState.update {
                it.copy(userInfo = it.userInfo.copy(phoneNumber = actions.phoneNumber))
            }
            is MyAccountScreenActions.UpdateUserInfo -> {
                insertUserInfoData()
            }
            else -> {}
        }
    }

    private fun loadUserInfoData() {
        viewModelScope.launch {
            userInfoUseCases.getUserInfoUseCase()
                .flowOn(Dispatchers.IO)
                .collect{userInfo->
                    _uiState.update {
                        it.copy(
                            userInfo = userInfo
                        )
                    }
                }
        }
    }

    private fun insertUserInfoData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                userInfoUseCases.insertUserInfoUseCase(_uiState.value.userInfo)
            }
            _uiEvent.emit(UiEvent.NavigateBack)
        }
    }

}