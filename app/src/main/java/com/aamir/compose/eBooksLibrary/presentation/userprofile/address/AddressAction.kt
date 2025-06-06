package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import AddressModel
import org.osmdroid.util.GeoPoint

sealed class AddressAction {
    data object CenterOnUserLocation : AddressAction()
    data class UpdateAddress(val address: AddressModel) : AddressAction()
    data class LocationSelected(val addressGeoPoint: GeoPoint) : AddressAction()
    data object DismissBottomSheet : AddressAction()
}
