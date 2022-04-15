package com.example.graduateproj.loginPack.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginDao {
    @Insert
    Long insertLoginInfo(LoginInfoEntity loginInfoEntity);

    @Query("SELECT * FROM user_table WHERE phoneNumber = :phoneNumber LIMIT 1")
    List<LoginInfoEntity> getLoginInfo(String phoneNumber);
}
