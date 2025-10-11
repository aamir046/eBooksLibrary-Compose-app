package com.aamir.compose.eBooksLibrary.data.repository

import com.aamir.compose.eBooksLibrary.data.local.datasource.AddressLocalDataSource
import com.aamir.compose.eBooksLibrary.data.mapper.toDomain
import com.aamir.compose.eBooksLibrary.data.mapper.toEntity
import com.aamir.compose.eBooksLibrary.domain.model.Address
import com.aamir.compose.eBooksLibrary.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddressRepositoryImpl(
    private val local: AddressLocalDataSource,
) : IAddressRepository {

    override suspend fun getAllAddresses(): Flow<List<Address>> =
        local.getAllAddresses().map { it.map { entity -> entity.toDomain() } }

    override suspend fun saveAddress(address: Address) =
        local.insertAddress(address.toEntity())

    override suspend fun deleteAddress(address: Address) =
        local.deleteAddress(address.toEntity())

}
