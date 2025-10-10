package com.aamir.compose.eBooksLibrary.domain.interactor.address

data class AddressUseCases(
    val getAllAddressesUseCase: GetAllAddressesUseCase,
    val saveAddressUseCase: SaveAddressUseCase,
    val deleteAddressUseCase: DeleteAddressUseCase,
    val updateAddressUseCase: UpdateAddressUseCase
)