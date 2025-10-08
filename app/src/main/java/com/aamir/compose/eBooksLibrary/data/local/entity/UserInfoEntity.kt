package com.aamir.compose.eBooksLibrary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfoEntity(
    @PrimaryKey val id: Int = 1,
    val name:String,
    val email:String,
    val imageUrl:String,
    val address:String,
    val phoneNumber:String
)