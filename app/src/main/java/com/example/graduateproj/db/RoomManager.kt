package com.example.graduateproj.db

import com.example.graduateproj.loginPack.db.LoginDao
import com.example.graduateproj.loginPack.db.LoginInfoEntity

class RoomManager {

    private var loginDao: LoginDao? = null

    init {
        val appDataBase = AppDataBase.getDataBase()

        loginDao = appDataBase?.getLoginDao()
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

    suspend fun getLoginInfoFromDB(phoneNumber: String): String? {
        return loginDao?.getLoginInfo(phoneNumber)
    }

}