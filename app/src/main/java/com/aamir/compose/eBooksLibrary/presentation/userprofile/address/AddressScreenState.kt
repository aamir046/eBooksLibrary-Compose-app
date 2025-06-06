package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import AddressModel

data class AddressScreenState(
    val currentAddress: AddressModel? = null,
    val selectedAddress: AddressModel? = null,
    val showBottomSheet: Boolean = false
)