package com.example.graduateproj.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.graduateproj.loginPack.db.LoginDao
import com.example.graduateproj.loginPack.db.LoginInfoEntity
import com.example.graduateproj.mainPack.mePack.db.MeDao
import com.example.graduateproj.mainPack.mePack.db.MeInfoEntity

@Database(entities = [LoginInfoEntity::class, MeInfoEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getLoginDao(): LoginDao
    abstract fun getMeDao(): MeDao

    companion object {
        const val DB_NAME = "DB"
        private var INSTANCE: AppDataBase? = null

        // Application 调用
        fun getDatabase(context: Context): AppDataBase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                    .allowMainThreadQueries()//允许在主线程查询
                    .build()
            }
            return INSTANCE
        }

        fun getDataBase(): AppDataBase? = INSTANCE
    }
}
