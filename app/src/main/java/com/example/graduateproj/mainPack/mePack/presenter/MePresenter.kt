package com.example.graduateproj.mainPack.mePack.presenter

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.graduateproj.commonUtil.BitmapHandler
import com.example.graduateproj.commonUtil.ToastUtil
import com.example.graduateproj.mainPack.mePack.MeFragment
import java.io.FileNotFoundException
import java.io.IOException

class MePresenter(val view: MeFragment) {

    companion object {
        private const val NO_RIGHT_OPEN_ALBUM = "没有权限无法打开相册噢"
        private const val ALBUM_NOT_EXISTS = "您的手机没有手机相册噢"

        private const val INTENT_TYPE = "image/*"
        private const val BUNDLE_KEY = "data"
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
                ToastUtil.showToastBottom(view.requireContext(), NO_RIGHT_OPEN_ALBUM)
            }
        }

    private fun tryOpenSystemAlbum() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, INTENT_TYPE)
        }
        if (intent.resolveActivity(view.requireActivity().packageManager) != null) {
            requestPickImageLauncher.launch(intent)
        } else {
            ToastUtil.showToastBottom(view.requireContext(), ALBUM_NOT_EXISTS)
        }
    }

    private fun decodeBitmapAndSet(result: ActivityResult) {
        val uri = result.data?.data
        var bm: Bitmap? = null
        if (uri != null) {
            try { bm = BitmapHandler.getBitmapFormUri(uri, view.requireContext()) }
            catch (e: FileNotFoundException) { }
            catch (e: IOException) { }
        } else {
            val bundleExtras = result.data?.extras
            bundleExtras?.let {
                bm = bundleExtras.getParcelable(BUNDLE_KEY)
            }
        }
        view.circleImageHead.setImageBitmap(bm)
    }
}