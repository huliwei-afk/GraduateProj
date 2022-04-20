package com.example.graduateproj.mainPack.homePack.presenter

import android.util.Log
import com.example.graduateproj.commonUtil.RxOkHttpUtil
import com.example.graduateproj.interfaceUtil.InterfacesHolder
import com.example.graduateproj.mainPack.homePack.model.ElectricBean
import com.example.graduateproj.mainPack.homePack.tabFragment.CommodityFragment

class CommodityPresenter(val view: CommodityFragment) {

    private var electricBeanList : MutableList<ElectricBean.ElectricItemBean> = ArrayList()

    companion object {
        private val TAG = CommodityPresenter::class.java.simpleName
        private const val baseUrl = "http://192.168.43.132:8080/"
    }

    fun getElectricItemAndSet() {
        RxOkHttpUtil.getInstance()
            .syncHttpRequestForElectric(baseUrl, object :
                InterfacesHolder.OnElectricDataObtainListener {
                override fun onSuccess(dataList: ElectricBean?) {
                    dataList?.let {
                        for (bean in dataList.data) {
                            electricBeanList.add(bean)
                        }
                        view.initRecyclerViewForElectric(electricBeanList)
                    }
                }

                override fun onFailure(e: Throwable?) {
                    Log.e(TAG, e.toString())
                }

            })
    }

}