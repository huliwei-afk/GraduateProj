package com.example.graduateproj.loginPack.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.ToastUtil

class MainLoginActivity : AppCompatActivity() {

    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
    }

    override fun onBackPressed() {
        // 记录本次按键时刻
        val nowTime = System.currentTimeMillis()
        if (nowTime - pressedTime > 2000) {
            // 比较两次按键时间差
            ToastUtil.showToastCenter(this, "再按一次退出应用")
            pressedTime = nowTime
        } else {
            // 退出程序
            super.onBackPressed()
            finishAffinity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}