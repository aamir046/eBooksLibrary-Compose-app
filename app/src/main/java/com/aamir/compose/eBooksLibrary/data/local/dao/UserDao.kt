package com.aamir.compose.eBooksLibrary.data.local.dao

import androidx.room.*
import com.aamir.compose.eBooksLibrary.data.local.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_info")
    fun getUserInfo():Flow<UserInfoEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfoEntity: UserInfoEntity)

}
