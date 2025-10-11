package com.aamir.compose.eBooksLibrary.domain.repository

import com.aamir.compose.eBooksLibrary.domain.model.Address
import kotlinx.coroutines.flow.Flow

interface IAddressRepository {
    suspend fun getAllAddresses(): Flow<List<Address>>
    suspend fun saveAddress(address: Address)
    suspend fun deleteAddress(address: Address)
}