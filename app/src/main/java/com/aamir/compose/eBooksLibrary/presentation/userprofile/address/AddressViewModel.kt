package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import AddressModel
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.osmdroid.util.GeoPoint
import java.io.IOException

class AddressViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddressScreenState())
    val uiState: StateFlow<AddressScreenState> = _uiState.asStateFlow()
    private lateinit var geocoder: Geocoder

    fun onAction(action: AddressAction) {
        when (action) {
            is AddressAction.UpdateAddress -> {
                _uiState.update {
                    it.copy(currentAddress = action.address)
                }
            }

            is AddressAction.LocationSelected -> {
                val selectedAddress: AddressModel = getAddressFromLocation(action.addressGeoPoint)
                _uiState.update {
                    it.copy(selectedAddress = selectedAddress)
                }
//                // Notify UI to trigger marker and geocoding
//                viewModelScope.launch {
//                    _uiEvent.emit(AddressEvent.FetchAddress(action.geoPoint))
//                }
            }

            is AddressAction.CenterOnUserLocation -> {
//                viewModelScope.launch {
//                    _uiEvent.emit(AddressEvent.CenterToUser)
//                }
            }

           is AddressAction.DismissBottomSheet -> {

            }
        }
    }

    fun setGeocoder(geo: Geocoder) {
        geocoder = geo
    }

    private fun getAddressFromLocation(point: GeoPoint): AddressModel {
        return try {
            val addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1)
            val addressLine = addresses?.firstOrNull()?.getAddressLine(0) ?: "Unknown"
            AddressModel(
                title = "Address 1",
                fullAddress = addressLine,
                latitude = point.latitude,
                longitude = point.longitude
            )
        } catch (e: IOException) {
            AddressModel(
                title = "Address 1",
                fullAddress = "Unable to fetch address",
                latitude = point.latitude,
                longitude = point.longitude
            )
        }
    }

}
