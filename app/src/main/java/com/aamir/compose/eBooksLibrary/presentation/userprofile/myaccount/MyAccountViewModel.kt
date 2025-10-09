package com.aamir.compose.eBooksLibrary.presentation.userprofile.myaccount

import android.content.Context
import android.net.Uri
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
import java.io.File
import java.io.FileOutputStream

class MyAccountViewModel (
    private val userInfoUseCases: UserInfoUseCases
):ViewModel() {

    private val _uiState = MutableStateFlow(MyAccountScreenState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MyAccountScreenUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        loadUserInfoData()
    }

    fun onAction(actions: MyAccountScreenActions) {
        when (actions) {
            is MyAccountScreenActions.OnEmailChange -> _uiState.update {
                it.copy(userInfo = it.userInfo.copy(email = actions.email))
            }

            is MyAccountScreenActions.OnNameChange -> _uiState.update {
                it.copy(userInfo = it.userInfo.copy(name = actions.name))
            }

            is MyAccountScreenActions.OnPhoneNumberChange -> _uiState.update {
                it.copy(userInfo = it.userInfo.copy(phoneNumber = actions.phoneNumber))
            }

            is MyAccountScreenActions.OnUpdateUserInfo -> {
                viewModelScope.launch {
                    insertUserInfoData()
                    _uiEvent.emit(MyAccountScreenUiEvent.ShowMessage("Profile updated successfully"))
                    _uiEvent.emit(MyAccountScreenUiEvent.NavigateBack)
                }
            }

            is MyAccountScreenActions.OnImagePicked -> {
                onImagePicked(actions.uri, actions.context)
            }

            else -> {}
        }
    }

    private fun loadUserInfoData() {
        viewModelScope.launch {
            userInfoUseCases.getUserInfoUseCase()
                .flowOn(Dispatchers.IO)
                .collect { userInfo ->
                    _uiState.update {
                        it.copy(
                            userInfo = userInfo
                        )
                    }
                }
        }
    }

    private suspend fun insertUserInfoData() {
        withContext(Dispatchers.IO) {
            userInfoUseCases.insertUserInfoUseCase(_uiState.value.userInfo)
        }
    }

    private fun onImagePicked(uri: Uri, context: Context) {
        viewModelScope.launch {
            val savedUri = saveImageToInternalStorage(uri, context)
            _uiState.update {
                it.copy(userInfo = it.userInfo.copy(imageUrl = savedUri))
            }
            insertUserInfoData()
        }
    }

    private suspend fun saveImageToInternalStorage(uri: Uri, context: Context): String {
        val savedPath = withContext(Dispatchers.IO) {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.filesDir, "profile_${System.currentTimeMillis()}.jpg")

            inputStream.use { input ->
                FileOutputStream(file).use { output ->
                    input?.copyTo(output)
                }
            }
            Uri.fromFile(file).toString()
        }
        _uiEvent.emit(MyAccountScreenUiEvent.ShowMessage("Profile Picture successfully"))
        return savedPath
    }
}