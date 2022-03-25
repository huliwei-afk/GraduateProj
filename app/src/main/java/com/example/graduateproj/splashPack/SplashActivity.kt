package com.example.graduateproj.splashPack

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {

    @Volatile
    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val contentView: View = findViewById(android.R.id.content)
        contentView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (isReady) {
                    contentView.viewTreeObserver.removeOnPreDrawListener(this)
                    AppNavigator.openMainLoginActivity(this@SplashActivity)
                    finish()
                }
                return isReady
            }
        })

        thread {
            isReady = true
        }
    }

}