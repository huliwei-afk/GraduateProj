package com.example.graduateproj.loginPack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graduateproj.R

class MainLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}