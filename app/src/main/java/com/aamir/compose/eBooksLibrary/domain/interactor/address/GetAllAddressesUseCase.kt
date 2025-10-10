package com.aamir.compose.eBooksLibrary.domain.interactor.address

import com.aamir.compose.eBooksLibrary.domain.model.Address
import com.aamir.compose.eBooksLibrary.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow

class GetAllAddressesUseCase(private val repository: IAddressRepository) {
    suspend operator fun invoke(): Flow<List<Address>> = repository.getAllAddresses()
}