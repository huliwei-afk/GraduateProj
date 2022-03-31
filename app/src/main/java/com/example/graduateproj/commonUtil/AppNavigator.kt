package com.example.graduateproj.commonUtil

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.navigation.Navigation
import com.example.graduateproj.R
import com.example.graduateproj.loginPack.ui.MainLoginActivity
import com.example.graduateproj.mainPack.ui.DetailActivity
import com.example.graduateproj.mainPack.ui.MainContentActivity
import com.example.graduateproj.mainPack.ui.PreferActivity

object AppNavigator {

    fun openMainLoginActivity(context: Context) {
        val intent = Intent(context, MainLoginActivity::class.java)
        context.startActivity(intent)
    }

    fun openNoAccountFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgetFragment)
    }

    fun openLoginFragment(v: View) {
        Navigation.findNavController(v).popBackStack()
    }

    fun openMainContentActivity(context: Context) {
        val intent = Intent(context, MainContentActivity::class.java)
        context.startActivity(intent)
    }

    fun openEditDetailActivity(context: Context) {
        val intent = Intent(context, DetailActivity::class.java)
        context.startActivity(intent)
    }

    fun openPreferActivity(context: Context) {
        val intent = Intent(context, PreferActivity::class.java)
        context.startActivity(intent)
    }
}