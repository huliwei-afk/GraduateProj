package com.example.graduateproj.loginPack.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class LoginInfoEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "phoneNumber", typeAffinity = ColumnInfo.TEXT)
    public String phoneNumber;

    @ColumnInfo(name = "passwordNumber", typeAffinity = ColumnInfo.TEXT)
    public String passwordNumber;

    public LoginInfoEntity(@NonNull String phoneNumber, String passwordNumber) {
        this.phoneNumber = phoneNumber;
        this.passwordNumber = passwordNumber;
    }
}
