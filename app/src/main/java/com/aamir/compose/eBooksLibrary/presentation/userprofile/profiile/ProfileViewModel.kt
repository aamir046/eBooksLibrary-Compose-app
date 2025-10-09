package com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.ADDRESS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.FAVOURITES_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HELP_CENTER_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.MY_ACCOUNT_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.PROMOS_OFFER_ROUTE
import com.aamir.compose.eBooksLibrary.domain.interactor.userinfo.GetUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(
    private val userInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenState())
    val uiState = _uiState.asStateFlow()

    private var fetchUserInfoJob: Job? = null

    init {
        fetchUserInfo()
        createProfileListingItems()
    }

    private fun fetchUserInfo() {
        fetchUserInfoJob?.cancel()
        fetchUserInfoJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {}
            userInfoUseCase()
                .collect {
                    _uiState.update { state ->
                        state.copy(
                            userInfo = it
                        )
                    }
                }
        }
    }

private fun createProfileListingItems() {
    _uiState.update {
        it.copy(
            profileListingItems = listOf(
                ProfileListingItem(
                    iconRes = R.drawable.ic_my_account,
                    title = "My Account",
                    targetRoute = MY_ACCOUNT_ROUTE
                ),
                ProfileListingItem(
                    iconRes = R.drawable.ic_address,
                    title = "Address",
                    targetRoute = ADDRESS_ROUTE
                ),
                ProfileListingItem(
                    iconRes = R.drawable.ic_promos_offers,
                    title = "Offers & Promos",
                    targetRoute = PROMOS_OFFER_ROUTE
                ),
                ProfileListingItem(
                    iconRes = R.drawable.ic_favrourites,
                    title = "Favourites",
                    targetRoute = FAVOURITES_ROUTE
                ),
                ProfileListingItem(
                    iconRes = R.drawable.ic_help_center,
                    title = "Help Center",
                    targetRoute = HELP_CENTER_ROUTE
                ),
                )
        )
    }
}

}