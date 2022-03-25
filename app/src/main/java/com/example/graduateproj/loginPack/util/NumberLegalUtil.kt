package com.example.graduateproj.loginPack.util

object NumberLegalUtil {

    fun checkPhoneNumberLengthAndFormat(number: String): Boolean {
        if (number.length != 11) {
            return false
        }

        val charArray: CharArray = number.toCharArray()
        for (c in charArray) {
            if (!c.isDigit()) {
                return false
            }
        }
        return true
    }

    fun checkPassword() {

    }
}