package com.aamir.compose.eBooksLibrary.presentation.userprofile.address

import com.aamir.compose.eBooksLibrary.domain.model.Address

data class AddressScreenState(
    val bottomSheetAddress: Address = Address(),
    val savedAddresses: List<Address> = emptyList(),
    val showBottomSheet: Boolean = false
)