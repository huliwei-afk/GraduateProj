package com.example.graduateproj.commonUtil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.navigation.Navigation
import com.example.graduateproj.R
import com.example.graduateproj.loginPack.ui.ForgetFragment
import com.example.graduateproj.loginPack.ui.MainLoginActivity

object AppNavigator {

    fun openMainLoginActivity(context: Context) {
        val intent = Intent(context, MainLoginActivity::class.java)
        context.startActivity(intent)
    }

    fun openNoAccountFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgetFragment)
    }

    fun openLoginFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_forgetFragment_to_loginFragment)
    }

}