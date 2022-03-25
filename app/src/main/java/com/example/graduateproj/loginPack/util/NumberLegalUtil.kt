package com.example.graduateproj.loginPack.util

import java.util.*

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

    fun generateRandomVerifyCode(): String {
        val random = Random()
        return (random.nextInt(9999-1000+1)+1000).toString() //为变量赋随机值1000-9999
    }
}