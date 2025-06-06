package com.aamir.compose.eBooksLibrary.presentation.userprofile.profiile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.R
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.ADDRESS_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.FAVOURITES_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.HELP_CENTER_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.MY_ACCOUNT_ROUTE
import com.aamir.compose.eBooksLibrary.app.EBooksLibraryAppDestinations.PROMOS_OFFER_ROUTE
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