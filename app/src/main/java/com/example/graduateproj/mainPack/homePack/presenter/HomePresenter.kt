package com.example.graduateproj.mainPack.homePack.presenter

import android.util.Log
import com.example.graduateproj.commonUtil.RxOkHttpUtil
import com.example.graduateproj.interfaceUtil.OnDataObtainListener
import com.example.graduateproj.mainPack.homePack.HomeFragment
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean
import kotlin.concurrent.thread

class HomePresenter(val view: HomeFragment) {
    companion object {
        private val TAG = HomePresenter::class.java.simpleName
        private const val baseUrl = "http://192.168.43.132:8080/"
    }

    fun getBannerImageDataAndSet() {
        thread {
            RxOkHttpUtil.getInstance()
                .syncHttpRequest(baseUrl, object :
                    OnDataObtainListener {
                    override fun onSuccess(dataList: BannerImageBean?) {
                        dataList?.let {
                            view.initBanner(dataList)
                        }
                    }

                    override fun onFailure(e: Throwable?) {
                        Log.d(TAG, e.toString())
                    }
                })
        }
    }
}