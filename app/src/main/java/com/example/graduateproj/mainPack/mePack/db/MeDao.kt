package com.example.graduateproj.mainPack.mePack.db

import android.net.Uri
import androidx.room.*

@Dao
interface MeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeInfo(meInfoEntity: MeInfoEntity): Long

    @Query("SELECT * FROM me_table WHERE phoneNumber = :phoneNumber LIMIT 1")
    suspend fun getMeInfo(phoneNumber: String): MeInfoEntity?

    @Query("SELECT headImageUri FROM me_table WHERE phoneNumber = :phoneNumber LIMIT 1")
    suspend fun getMeHead(phoneNumber: String): Uri?

    @Update
    suspend fun updateMeInfo() {

    }
}