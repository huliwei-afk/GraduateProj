package com.example.graduateproj.mainPack.donatePack.presenter

import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.RxOkHttpUtil
import com.example.graduateproj.interfaceUtil.InterfacesHolder
import com.example.graduateproj.mainPack.donatePack.DonateFragment
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean

class DonatePresenter(val view: DonateFragment) {

    private var donateBeanList : MutableList<DonateJsonBean.DonateItemBean> = ArrayList()

    companion object {
        private val TAG = DonatePresenter::class.java.simpleName
        private const val baseUrl = "http://192.168.43.132:8080/"
    }

    fun getDonateItemAndSet() {
        RxOkHttpUtil.getInstance()
            .syncHttpRequestForDonate(baseUrl, object :
                InterfacesHolder.OnDonateDataObtainListener {
                override fun onNext(dataList: DonateJsonBean?) {
                    dataList?.let {
                        for (bean in dataList.data) {
                            donateBeanList.add(bean)
                        }
                        view.initRecyclerView(donateBeanList)
                    }
                }

                override fun onFailure(e: Throwable?) {
                    Log.e(TAG, e.toString())
                }

            })
    }

    fun getMoreDonateItem(originalList: MutableList<DonateJsonBean.DonateItemBean>) {
        RxOkHttpUtil.getInstance()
            .syncHttpRequestForDonate(baseUrl, object :
                InterfacesHolder.OnDonateDataObtainListener {
                override fun onNext(dataList: DonateJsonBean?) {
                    var size = 0
                    dataList?.let {
                        for (bean in dataList.data) {
                            originalList.add(bean)
                            size++
                        }
                    }
                    manipulateRecyclerView(originalList, size)
                }

                override fun onFailure(e: Throwable?) {
                    Log.e(TAG, e.toString())
                }

            })
    }

    private fun manipulateRecyclerView(originalList: MutableList<DonateJsonBean.DonateItemBean>, size: Int) {
        view.donateRecyclerView.layoutAnimation = LayoutAnimationController(AnimationUtils.loadAnimation(view.requireContext(), R.anim.donate_recycler_animation))
        view.donateRecyclerView.adapter?.notifyItemRangeInserted(originalList.size - 1, size)
        view.donateRecyclerView.scheduleLayoutAnimation()
        view.donateRefresh.isRefreshing = false
    }
}