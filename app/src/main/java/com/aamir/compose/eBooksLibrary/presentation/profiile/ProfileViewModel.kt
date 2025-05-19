package com.aamir.compose.eBooksLibrary.presentation.profiile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ProfileViewModel:ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenState())
    val uiState: StateFlow<ProfileScreenState> = _uiState.onStart {
        createProfileListingItems()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    private fun createProfileListingItems() {
        _uiState.update {
            it.copy(
                profileListingItems = listOf(
                    ProfileListingItem(
                        iconRes = R.drawable.ic_my_account,
                        title = "My Account"
                    ),
                    ProfileListingItem(
                        iconRes = R.drawable.ic_address,
                        title = "Address"
                    ),
                    ProfileListingItem(
                        iconRes = R.drawable.ic_promos_offers,
                        title = "Offers & Promos"
                    ),
                    ProfileListingItem(
                        iconRes = R.drawable.ic_favrourites,
                        title = "Favourites"
                    ),
                    ProfileListingItem(
                        iconRes = R.drawable.ic_help_center,
                        title = "Help Center"
                    ),

                )
            )
        }
    }

}