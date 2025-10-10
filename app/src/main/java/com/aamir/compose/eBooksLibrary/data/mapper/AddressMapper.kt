package com.aamir.compose.eBooksLibrary.data.mapper

import com.aamir.compose.eBooksLibrary.data.local.entity.AddressEntity
import com.aamir.compose.eBooksLibrary.domain.model.Address

fun AddressEntity.toDomain(): Address {
    return Address(
        id = id,
        title = title,
        fullAddress = fullAddress,
        latitude = latitude,
        longitude = longitude,
        addressTag = addressTag
    )
}

fun Address.toEntity(): AddressEntity {
    return AddressEntity(
        id = id,
        title = title,
        fullAddress = fullAddress,
        latitude = latitude,
        longitude = longitude,
        addressTag = addressTag
    )
}