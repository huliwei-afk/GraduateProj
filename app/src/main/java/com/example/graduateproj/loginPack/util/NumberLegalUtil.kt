package com.example.graduateproj.loginPack.util

import android.content.Context
import android.text.TextUtils
import com.example.graduateproj.commonUtil.ToastUtil
import java.util.*

object NumberLegalUtil {

    private const val FORGET_ACCOUNT = "手机号码好像忘记输入了噢!"
    private const val FORGET_PASSWORD = "学号好像忘记输入了噢!"
    private const val ACCOUNT_WRONG = "手机账号似乎不对呢，再检查下吧!"
    private const val PASSWORD_WRONG = "学号似乎不对呢，再检查下吧!"

    private const val PHONE_LENGTH = 11
    private const val PASSWORD_LENGTH = 10

    private var verifyCode: Int = Int.MIN_VALUE

    fun checkLoginInfoLegal(context: Context, number: String, password: String): Boolean {
        fun checkInfoEmptyOrNot(info: String): Boolean {
            return TextUtils.equals(info, "")
        }

        fun checkPhoneNumberLengthAndFormat(info: String, length: Int): Boolean {
            if (info.length != length) {
                return false
            }

            val charArray: CharArray = info.toCharArray()
            for (c in charArray) {
                if (!c.isDigit()) {
                    return false
                }
            }
            return true
        }

        if (checkInfoEmptyOrNot(number)) {
            ToastUtil.showToastBelowCenter(context, FORGET_ACCOUNT)
            return false
        }

        if (checkInfoEmptyOrNot(password)) {
            ToastUtil.showToastBelowCenter(context, FORGET_PASSWORD)
            return false
        }

        if (!checkPhoneNumberLengthAndFormat(number, PHONE_LENGTH)) {
            ToastUtil.showToastBelowCenter(context, ACCOUNT_WRONG)
            return false
        }

        if (!checkPhoneNumberLengthAndFormat(password, PASSWORD_LENGTH)) {
            ToastUtil.showToastBelowCenter(context, PASSWORD_WRONG)
            return false
        }

        return true
    }

    fun generateRandomVerifyCode(): String {
        val random = Random()
        val code = random.nextInt(9999 - 1000 + 1) + 1000
        verifyCode = code

        return verifyCode.toString() //为变量赋随机值1000-9999
    }

    fun getVerifyCode(): Int {
        return verifyCode
    }
}