package com.aamir.compose.eBooksLibrary.domain.interactor.address

import com.aamir.compose.eBooksLibrary.domain.model.Address
import com.aamir.compose.eBooksLibrary.domain.repository.IAddressRepository

class DeleteAddressUseCase(private val repository: IAddressRepository) {
    suspend operator fun invoke(address: Address) = repository.deleteAddress(address)
}