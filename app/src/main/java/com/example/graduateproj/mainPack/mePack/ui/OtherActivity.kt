package com.example.graduateproj.mainPack.mePack.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import java.util.concurrent.TimeUnit

class OtherActivity : AppCompatActivity() {
    private lateinit var backIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

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