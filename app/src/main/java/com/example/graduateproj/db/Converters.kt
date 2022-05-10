package com.example.graduateproj.db

import android.net.Uri
import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun uriToString(uri: Uri): String {
        return uri.toString()
    }

    @TypeConverter
    fun strToUri(str :String): Uri {
        return Uri.parse(str)
    }
}