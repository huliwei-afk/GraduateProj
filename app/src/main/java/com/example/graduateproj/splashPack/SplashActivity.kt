package com.example.graduateproj.splashPack

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.loginPack.util.LoginStateUtil

class SplashActivity : AppCompatActivity() {

    companion object {
        const val DURATION = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        compatDelay(splashScreen)

        splashScreen.setOnExitAnimationListener { provider ->
            val iconView = provider.iconView
            AnimatorSet().apply {
                playSequentially(
                    ObjectAnimator.ofFloat(
                        iconView,
                        View.TRANSLATION_Y,
                        0f,
                        50f
                    ),

                    ObjectAnimator.ofFloat(
                        iconView,
                        View.TRANSLATION_Y,
                        50f,
                        -provider.view.height.toFloat()
                    ),
                )
                doOnEnd {
                    provider.remove()
                    if(LoginStateUtil.getInstance(this@SplashActivity).localLoginStateOrDefault) {
                        AppNavigator.openMainContentActivity(this@SplashActivity)
                    } else {
                        AppNavigator.openMainLoginActivity(this@SplashActivity)
                    }
                    finish()
                }
                start()
            }
        }
    }

    //控制画面时长
    private fun compatDelay(splashScreen: SplashScreen) {
        val initTime = SystemClock.uptimeMillis()
        splashScreen.setKeepVisibleCondition {
            (SystemClock.uptimeMillis() - initTime) < DURATION
        }
    }
}