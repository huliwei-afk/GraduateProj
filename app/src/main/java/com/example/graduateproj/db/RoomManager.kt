package com.example.graduateproj.db

import android.net.Uri
import com.example.graduateproj.loginPack.db.LoginDao
import com.example.graduateproj.loginPack.db.LoginInfoEntity
import com.example.graduateproj.mainPack.mePack.db.MeDao
import com.example.graduateproj.mainPack.mePack.db.MeInfoEntity

class RoomManager {

    private var loginDao: LoginDao? = null
    private var meDao: MeDao? = null

    init {
        val appDataBase = AppDataBase.getDataBase()

        loginDao = appDataBase?.getLoginDao()
        meDao = appDataBase?.getMeDao()
    }

    companion object {
        var INSTANCE: RoomManager? = null

        fun getInstance(): RoomManager {
            if (INSTANCE == null) {
                synchronized(RoomManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = RoomManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    suspend fun insertLoginInfoToDB(loginInfoEntity: LoginInfoEntity) {
        loginDao?.insertLoginInfo(loginInfoEntity)
    }

    suspend fun getLoginInfoFromDB(phoneNumber: String?): String? {
        return phoneNumber?.let { loginDao?.getLoginInfo(it) }
    }

    suspend fun insertMeInfoToDB(meInfoEntity: MeInfoEntity) {
        meDao?.insertMeInfo(meInfoEntity)
    }

    suspend fun getMeHeadFromDB(phoneNumber: String?): Uri? {
        return phoneNumber?.let { meDao?.getMeHead(it) }
    }

}