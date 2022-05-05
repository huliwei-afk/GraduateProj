package com.example.graduateproj.mainPack.homePack

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import java.util.concurrent.TimeUnit

class BannerActivity : AppCompatActivity() {

    companion object {
        private const val BANNER_URL = "url"
    }

    private lateinit var imageView: ImageView
    private lateinit var backArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        initViews()
        setImageBackground()
        initEvents()
    }

    private fun initViews() {
        imageView = findViewById(R.id.banner_image_view)
        backArrow = findViewById(R.id.back_arrow)
    }

    private fun setImageBackground() {
        val intent = intent
        val url = intent.getStringExtra(BANNER_URL)
        Glide.with(this).load(url).into(imageView)
    }

    private fun initEvents() {
        RxClickUtil.clickEvent(imageView, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.backToMainContentActivity(this)
            }
    }
}