package com.example.graduateproj.loginPack.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoginInfo(loginInfoEntity: LoginInfoEntity): Long

    @Query("SELECT passwordNumber FROM user_table WHERE phoneNumber = :phoneNumber LIMIT 1")
    suspend fun getLoginInfo(phoneNumber: String): String?
}