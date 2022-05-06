package com.example.graduateproj.mainPack.donatePack.presenter

import android.text.TextUtils
import android.view.View
import android.widget.EditText
import com.example.graduateproj.commonUtil.ToastUtil
import com.example.graduateproj.mainPack.donatePack.SaleInfoActivity

class SaleInfoPresenter(val view: SaleInfoActivity) {

    companion object {
        private const val NAME_EMPTY_HINT = "好像商品还没有名字噢!"
        private const val PRICE_EMPTY_HINT = "好像商品还没有价格噢!"
        private const val DESCRIPTION_EMPTY_HINT = "好像商品还没有详细的描述噢!"
        private const val ALL_FULL = "发布成功! 快去看看自己的商品吧!"
    }

    fun checkInfoComplete() {
        fun isEditTextEmpty(editText: EditText): Boolean {
            return TextUtils.equals(editText.text.toString(), "");
        }

        val nameEmpty = isEditTextEmpty(view.editName)
        val priceEmpty = if(view.inputPrice.visibility == View.GONE) false else isEditTextEmpty(view.editPrice)
        val descriptionEmpty = isEditTextEmpty(view.editDescription)

        if (nameEmpty) {
            ToastUtil.showToastBottom(view, NAME_EMPTY_HINT)
            return
        }

        if(priceEmpty) {
            ToastUtil.showToastBottom(view, PRICE_EMPTY_HINT)
            return
        }

        if(descriptionEmpty) {
            ToastUtil.showToastBottom(view, DESCRIPTION_EMPTY_HINT)
            return
        }

        ToastUtil.showToastBottom(view, ALL_FULL)
    }
}