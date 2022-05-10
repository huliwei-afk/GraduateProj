package com.example.graduateproj.mainPack.mePack.db;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "me_table")
public class MeInfoEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "phoneNumber", typeAffinity = ColumnInfo.TEXT)
    public String phoneNumber;

    @ColumnInfo(name = "selfName", typeAffinity = ColumnInfo.TEXT)
    public String selfName;

    @ColumnInfo(name = "customSign", typeAffinity = ColumnInfo.TEXT)
    public String customSign;

    @ColumnInfo(name = "fakePhoneNumber", typeAffinity = ColumnInfo.TEXT)
    public String fakePhoneNumber;

    @ColumnInfo(name = "QQNumber", typeAffinity = ColumnInfo.TEXT)
    public String QQNumber;

    @ColumnInfo(name = "institute", typeAffinity = ColumnInfo.TEXT)
    public String institute;

    @ColumnInfo(name = "headImageUri")
    public Uri headImageUri;

    public MeInfoEntity(@NonNull String phoneNumber, String selfName, String customSign, String fakePhoneNumber, String QQNumber, String institute, Uri headImageUri) {
        this.phoneNumber = phoneNumber;
        this.selfName = selfName;
        this.customSign = customSign;
        this.fakePhoneNumber = fakePhoneNumber;
        this.QQNumber = QQNumber;
        this.institute = institute;
        this.headImageUri = headImageUri;
    }
}
