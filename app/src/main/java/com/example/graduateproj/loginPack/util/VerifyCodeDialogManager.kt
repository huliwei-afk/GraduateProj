package com.example.graduateproj.loginPack.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.ScreenUtil
import com.example.graduateproj.loginPack.ui.EasyDialog

object VerifyCodeDialogManager {

    private const val DIALOG_MAX_HEIGHT = 300F

    fun showDialog(@NonNull context: Context) {
        val dialog = EasyDialog(context, R.style.verify_dialog)
        val view = View.inflate(context, R.layout.verify_code_dialog, null)
        dialog.setContentView(view)

        val window = dialog.window
        window?.apply {
            setGravity(Gravity.BOTTOM)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2pxInt(context, DIALOG_MAX_HEIGHT))
            setWindowAnimations(R.style.dialogAnimation)
        }

        dialog.show()
    }

}