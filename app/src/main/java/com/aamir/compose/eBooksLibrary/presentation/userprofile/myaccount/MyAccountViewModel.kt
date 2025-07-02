package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.core.presentation.UserInfo
import com.aamir.compose.eBooksLibrary.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MyAccountViewModel:ViewModel(){
    fun onAction(actions: MyAccountScreenActions) {
        when(actions){
            is MyAccountScreenActions.OnEmailChange -> _uiState.update {
                it.copy(userInfo = it.userInfo?.copy(email = actions.email))
            }
            is MyAccountScreenActions.OnNameChange -> _uiState.update {
                it.copy(userInfo = it.userInfo?.copy(name = actions.name))
            }
            is MyAccountScreenActions.OnPhoneNumberChange -> _uiState.update {
                it.copy(userInfo = it.userInfo?.copy(phoneNumber = actions.phoneNumber))
            }
            else -> {}
        }
    }

    private val _uiState = MutableStateFlow(MyAccountScreenState())

    val uiState = _uiState.onStart {
        loadUserData()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun loadUserData() {
        // Need to add local DB and fetching
        _uiState.update {
            it.copy(
                userInfo = UserInfo(
                    name = "",
                    email = "",
                    phoneNumber = "",
                    imageUrl = "",
                    address = ""
                )
            )
        }
    }

}