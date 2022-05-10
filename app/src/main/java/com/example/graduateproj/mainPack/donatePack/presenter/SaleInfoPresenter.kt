package com.example.graduateproj.mainPack.donatePack.presenter

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.graduateproj.commonUtil.BitmapHandler
import com.example.graduateproj.commonUtil.ToastUtil
import com.example.graduateproj.db.RoomManager
import com.example.graduateproj.loginPack.util.LoginStateUtil
import com.example.graduateproj.mainPack.donatePack.SaleInfoActivity
import kotlinx.coroutines.runBlocking
import java.io.FileNotFoundException
import java.io.IOException

class SaleInfoPresenter(val view: SaleInfoActivity) {

    companion object {
        private const val NAME_EMPTY_HINT = "好像商品还没有名字噢!"
        private const val PRICE_EMPTY_HINT = "好像商品还没有价格噢!"
        private const val IMAGE_EMPTY_HINT = "好像商品还没有图片噢!"
        private const val DESCRIPTION_EMPTY_HINT = "好像商品还没有描述噢!"
        private const val ALL_FULL = "发布成功! 快去看看自己的商品吧!"

        private const val NO_RIGHT_OPEN_ALBUM = "没有权限无法打开相册噢"
        private const val ALBUM_NOT_EXISTS = "您的手机没有手机相册噢"

        private const val INTENT_TYPE = "image/*"
        private const val BUNDLE_KEY = "data"
    }

    private var imageUri: Uri? = null

    fun checkInfoComplete(): Boolean {
        fun isEditTextEmpty(editText: EditText): Boolean {
            return TextUtils.equals(editText.text.toString(), "")
        }

        val nameEmpty = isEditTextEmpty(view.editName)
        val priceEmpty =
            if (view.inputPrice.visibility == View.GONE) false else isEditTextEmpty(view.editPrice)
        val imageEmpty = (view.editImage.drawable as BitmapDrawable).bitmap == null
        val descriptionEmpty = isEditTextEmpty(view.editDescription)

        if (nameEmpty) {
            ToastUtil.showToastBottom(view, NAME_EMPTY_HINT)
            return false
        }

        if (priceEmpty) {
            ToastUtil.showToastBottom(view, PRICE_EMPTY_HINT)
            return false
        }

        if (imageEmpty) {
            ToastUtil.showToastBottom(view, IMAGE_EMPTY_HINT)
            return false
        }

        if (descriptionEmpty) {
            ToastUtil.showToastBottom(view, DESCRIPTION_EMPTY_HINT)
            return false
        }

        ToastUtil.showToastBottom(view, ALL_FULL)
        return true
    }

    fun getMeHead(): Uri? {
        var uri: Uri? = null
        runBlocking {
            uri = RoomManager.getInstance().getMeHeadFromDB(LoginStateUtil.getInstance(view).localPhoneNumberOrDefault)
        }
        return uri
    }

    fun requestPermissionAndTryOpen() {
        requestWriteExternalStoragePermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private val requestPickImageLauncher =
        view.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                decodeBitmapAndSet(result)
            }
        }

    private val requestWriteExternalStoragePermissionLauncher =
        view.registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                tryOpenSystemAlbum()
            } else {
                ToastUtil.showToastBottom(view, NO_RIGHT_OPEN_ALBUM)
            }
        }

    private fun tryOpenSystemAlbum() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, INTENT_TYPE)
        }
        if (intent.resolveActivity(view.packageManager) != null) {
            requestPickImageLauncher.launch(intent)
        } else {
            ToastUtil.showToastBottom(view, ALBUM_NOT_EXISTS)
        }
    }

    private fun decodeBitmapAndSet(result: ActivityResult) {
        val uri = result.data?.data
        uri?.let { setImageUri(it) }
        var bm: Bitmap? = null
        if (uri != null) {
            try {
                bm = BitmapHandler.getBitmapFromUri(uri, view)
            } catch (e: FileNotFoundException) {
            } catch (e: IOException) {
            }
        } else {
            val bundleExtras = result.data?.extras
            bundleExtras?.let {
                bm = bundleExtras.getParcelable(BUNDLE_KEY)
            }
        }
        view.editImage.setImageBitmap(bm)
    }

    fun getImageUri(): Uri? {
        return imageUri
    }

    private fun setImageUri(uri: Uri) {
        imageUri = uri
    }
}