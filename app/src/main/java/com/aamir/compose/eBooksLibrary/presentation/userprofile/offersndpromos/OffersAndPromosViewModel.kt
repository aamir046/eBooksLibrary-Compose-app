package com.aamir.compose.eBooksLibrary.presentation.userprofile.offersndpromos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class OffersAndPromosViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OffersAndPromosScreenState())

    val uiState = _uiState.onStart {

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    fun onAction(actions: OffersAndPromosScreenActions) {
        when (actions) {
            else -> {}
        }
    }

}