package com.example.graduateproj.loginPack.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.ScreenUtil
import com.example.graduateproj.loginPack.ui.EasyDialog
import com.example.graduateproj.mainPack.mePack.ui.NotificationDialog

object DialogManager {

    private const val DIALOG_MAX_HEIGHT = 300F

    private const val HAS_DYNAMIC_HINT = "今日有新动态噢！"
    private const val NO_DYNAMIC_HINT = "今天没有新动态，快去圈子逛逛吧！"

    fun showVerifyCodeDialog(@NonNull context: Context) {
        val dialog = EasyDialog(context, R.style.verify_dialog)
        val view = View.inflate(context, R.layout.verify_code_dialog, null)
        dialog.setContentView(view)

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.BOTTOM)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtil.dp2pxInt(context, DIALOG_MAX_HEIGHT)
            )
            setWindowAnimations(R.style.dialogAnimation)
        }

        dialog.show()
    }

    fun showNotificationDialog(@NonNull context: Context, hasDynamic: Boolean) {
        val dialog: NotificationDialog = if (hasDynamic) {
            NotificationDialog(context, HAS_DYNAMIC_HINT)
        } else {
            NotificationDialog(context, NO_DYNAMIC_HINT)
        }

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.CENTER)
            setWindowAnimations(R.style.notificationAnimation)
        }

        dialog.show()
    }

}