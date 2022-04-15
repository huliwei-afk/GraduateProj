package com.example.graduateproj.loginPack.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class LoginInfoEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name = "phoneNumber", typeAffinity = ColumnInfo.TEXT)
    public String phoneNumber;

    @ColumnInfo(name = "passwordNumber", typeAffinity = ColumnInfo.TEXT)
    public String passwordNumber;

    public LoginInfoEntity(String phoneNumber, String passwordNumber) {
        this.phoneNumber = phoneNumber;
        this.passwordNumber = passwordNumber;
    }

    @Ignore
    public LoginInfoEntity(int id, String phoneNumber, String passwordNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.passwordNumber = passwordNumber;
    }

}
