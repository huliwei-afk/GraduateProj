package com.example.graduateproj.loginPack.util

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.ScreenUtil
import com.example.graduateproj.loginPack.ui.EasyDialog
import com.example.graduateproj.mainPack.donatePack.ui.DonateDialog
import com.example.graduateproj.mainPack.mePack.ui.NotificationDialog

object DialogManager {

    private const val VERIFY_CODE_DIALOG_MAX_HEIGHT = 300F
    private const val ADD_DONATE_DIALOG_MAX_HEIGHT = 230F

    private const val HAS_DYNAMIC_HINT = "今日有新动态噢！"
    private const val NO_DYNAMIC_HINT = "今天没有新动态，快去圈子逛逛吧！"
    private const val BUTTON_HINT = "知道啦"
    private const val PUBLISH_DONE = "已经发布完成啦，快去看看吧！"
    private const val GO_SEE = "去看看"
    private const val ME_NOTIFICATION_DIALOG_KIND = -1
    private const val PUBLISH_NOTIFICATION_DIALOG_KIND = 1

    fun showVerifyCodeDialog(@NonNull context: Context) {
        val dialog = EasyDialog(context, R.style.verify_dialog)
        val view = View.inflate(context, R.layout.verify_code_dialog, null)
        dialog.setContentView(view)

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.BOTTOM)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtil.dp2pxInt(context, VERIFY_CODE_DIALOG_MAX_HEIGHT)
            )
            setWindowAnimations(R.style.dialogAnimation)
        }

        dialog.show()
    }

    fun showNotificationDialog(@NonNull context: Activity, hasDynamic: Boolean) {
        val dialog: NotificationDialog = if (hasDynamic) {
            NotificationDialog(context, HAS_DYNAMIC_HINT, BUTTON_HINT, ME_NOTIFICATION_DIALOG_KIND)
        } else {
            NotificationDialog(context, NO_DYNAMIC_HINT, BUTTON_HINT, ME_NOTIFICATION_DIALOG_KIND)
        }

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.CENTER)
            setWindowAnimations(R.style.notificationAnimation)
        }

        dialog.show()
    }

    fun showAddDonateDialog(@NonNull context: Activity) {
        val dialog = DonateDialog(context, R.style.verify_dialog)
        val view = View.inflate(context, R.layout.add_donate_dialog, null)
        dialog.setContentView(view)

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.BOTTOM)
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtil.dp2pxInt(context, ADD_DONATE_DIALOG_MAX_HEIGHT)
            )
            setWindowAnimations(R.style.dialogAnimation)
        }

        dialog.show()
    }

    fun showPublishDoneDialog(@NonNull context: Activity) {
        val dialog = NotificationDialog(context, PUBLISH_DONE, GO_SEE, PUBLISH_NOTIFICATION_DIALOG_KIND)

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.CENTER)
            setWindowAnimations(R.style.notificationAnimation)
        }

        dialog.show()
    }

}