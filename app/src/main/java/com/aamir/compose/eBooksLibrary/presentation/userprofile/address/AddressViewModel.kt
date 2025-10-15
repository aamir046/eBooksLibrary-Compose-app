package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.interactor.address.AddressUseCases
import com.aamir.compose.eBooksLibrary.domain.model.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import java.io.IOException

class AddressViewModel(
    private val addressUseCases: AddressUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressScreenState())
    val uiState: StateFlow<AddressScreenState> = _uiState.asStateFlow()
    private lateinit var geocoder: Geocoder

    init {
        getAllSavedAddresses()
    }

    private fun getAllSavedAddresses() {
        viewModelScope.launch {
            addressUseCases.getAllAddressesUseCase()
                .flowOn(Dispatchers.IO)
                .collect { addresses ->
                _uiState.update {
                    it.copy(savedAddresses = addresses)
                }
            }
        }
    }

    private fun saveAddress(address: Address) {
      viewModelScope.launch(Dispatchers.IO) {
            addressUseCases.saveAddressUseCase(address)
        }

    }

    private fun deleteAddress(address: Address) {
        viewModelScope.launch(Dispatchers.IO) {
            addressUseCases.deleteAddressUseCase(address)
        }
    }

    fun onAction(action: AddressAction) {
        when (action) {
            is AddressAction.OnSaveAddress -> {
                saveAddress(action.address)
            }

            is AddressAction.OnMapLocationSelected -> {
                viewModelScope.launch {
                    val selectedAddress: Address = getAddressFromLocation(action.addressGeoPoint)
                    _uiState.update {
                        it.copy(bottomSheetAddress = selectedAddress)
                    }
                }
            }

            is AddressAction.OnUpdateBottomSheetAddress -> {
                _uiState.update {
                    it.copy(bottomSheetAddress = action.address)
                }
            }

            is AddressAction.OnCenterOnUserLocation -> {

            }
        }
    }

    fun setGeocoder(geo: Geocoder) {
        geocoder = geo
    }

    private suspend fun getAddressFromLocation(point: GeoPoint): Address {
        return withContext(Dispatchers.IO){
            try {
                val addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1)
                val addressLine = addresses?.firstOrNull()?.getAddressLine(0) ?: "Unknown"
                Address(
                    title = "Address 1",
                    fullAddress = addressLine,
                    latitude = point.latitude,
                    longitude = point.longitude
                )
            } catch (e: IOException) {
                e.printStackTrace()
                Address(
                    title = "Address 1",
                    fullAddress = "Unable to fetch address",
                    latitude = point.latitude,
                    longitude = point.longitude
                )
            }
        }
    }

}
