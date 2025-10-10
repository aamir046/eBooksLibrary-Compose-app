package com.aamir.compose.eBooksLibrary.data.local.datasource

import com.aamir.compose.eBooksLibrary.data.local.dao.AddressDao
import com.aamir.compose.eBooksLibrary.data.local.entity.AddressEntity

class AddressLocalDataSource(private val dao: AddressDao) {
    fun getAllAddresses() = dao.getAllAddresses()
    suspend fun insertAddress(address: AddressEntity) = dao.insertAddress(address)
    suspend fun deleteAddress(address: AddressEntity) = dao.deleteAddress(address)
    suspend fun updateAddress(address: AddressEntity) = dao.updateAddress(address)
}
