package com.example.graduateproj.loginPack.presenter

import android.widget.Toast
import com.example.graduateproj.db.RoomManager
import com.example.graduateproj.loginPack.ui.ForgetFragment
import kotlinx.coroutines.runBlocking

class ForgetPresenter(val view: ForgetFragment) {

    var info: String? = null

    companion object {
        private const val ACCOUNT_WRONG = "没有查到您的账号呢，再检查下吧!"
    }

    private fun getLostPasswordForUser(phoneNumber: String): String? {
        runBlocking {
            info = RoomManager.getInstance().getLoginInfoFromDB(phoneNumber)
        }
        return info
    }

    fun checkCanShowVerifyCode(phoneNumber: String): Boolean {
        if(getLostPasswordForUser(phoneNumber) != null) {
            return true
        }
        Toast.makeText(view.requireContext(), ACCOUNT_WRONG, Toast.LENGTH_LONG).show()
        return false
    }
}