package com.example.graduateproj.mainPack

import androidx.appcompat.app.AppCompatActivity
import com.example.graduateproj.commonUI.BottomNavView
import android.os.Bundle
import com.example.graduateproj.R

class MainContentActivity : AppCompatActivity() {
    private lateinit var navHome: BottomNavView
    private lateinit var navDonate: BottomNavView
    private lateinit var navMessage: BottomNavView
    private lateinit var navMe: BottomNavView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)

        initView()
        initEvents()
    }

    private fun initView() {
        navHome = findViewById(R.id.home_page)
        navDonate = findViewById(R.id.donate_page)
        navMessage = findViewById(R.id.message_page)
        navMe = findViewById(R.id.me_page)
    }

    private fun initEvents() {

    }
}