package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import com.aamir.compose.eBooksLibrary.domain.model.Address
import org.osmdroid.util.GeoPoint

sealed class AddressAction {
    data object OnCenterOnUserLocation : AddressAction()
    data class OnSaveAddress(val address: Address) : AddressAction()
    data class OnUpdateBottomSheetAddress(val address: Address) : AddressAction()
    data class OnMapLocationSelected(val addressGeoPoint: GeoPoint) : AddressAction()
}
