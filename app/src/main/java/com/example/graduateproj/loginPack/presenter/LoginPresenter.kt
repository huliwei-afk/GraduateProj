package com.example.graduateproj.loginPack.presenter

import android.content.Context
import com.example.graduateproj.db.RoomManager
import com.example.graduateproj.loginPack.db.LoginInfoEntity
import com.example.graduateproj.loginPack.ui.LoginFragment
import com.example.graduateproj.loginPack.util.LoginStateUtil
import com.example.graduateproj.loginPack.util.NumberLegalUtil
import kotlinx.coroutines.runBlocking

class LoginPresenter(val view: LoginFragment) {

    fun loginStrategy(context: Context): Boolean {
        if(!view.policyCheckbox.isChecked) {
            view.shakePolicy()
            return false
        }

        val phoneNumber = view.accountNumber.text.toString()
        val passwordNumber = view.passwordNumber.text.toString()

        if(NumberLegalUtil.checkLoginInfoLegal(context, phoneNumber, passwordNumber)) {
            saveLoginInfoToLocal(context, phoneNumber, passwordNumber)
            saveLoginInfoToDB(phoneNumber, passwordNumber)
            ensureLogin(context)

            return true
        }
        return false
    }

    private fun saveLoginInfoToLocal(context: Context, phoneNumber: String, passwordNumber: String) {
        // 本地存一次
        LoginStateUtil.getInstance(context).savePhoneNumberToLocal(phoneNumber)
        LoginStateUtil.getInstance(context).savePasswordToLocal(passwordNumber)
    }

    private fun saveLoginInfoToDB(phoneNumber: String, passwordNumber: String) {
        runBlocking {
            RoomManager.getInstance().insertLoginInfoToDB(LoginInfoEntity(phoneNumber, passwordNumber))
        }
    }

    private fun ensureLogin(context: Context) {
        LoginStateUtil.getInstance(context).saveLoginStateToLocal(true)
    }

}