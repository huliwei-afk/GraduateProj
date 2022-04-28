package com.example.graduateproj.mainPack.mePack.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.commonUtil.WindowBarStatusUtil
import java.util.concurrent.TimeUnit

class OtherActivity : AppCompatActivity() {
    private lateinit var backIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        WindowBarStatusUtil.setBarStatus(this, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        initViews()
        initEvents()
    }

    private fun initViews() {
        backIcon = findViewById(R.id.other_activity_back)
    }

    private fun initEvents() {
        RxClickUtil.clickEvent(backIcon, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.backToMainContentActivity(this)
            }
    }
}