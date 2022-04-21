package com.example.graduateproj.mainPack.homePack.presenter

import android.util.Log
import com.example.graduateproj.commonUtil.RxOkHttpUtil
import com.example.graduateproj.interfaceUtil.InterfacesHolder
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean
import com.example.graduateproj.mainPack.homePack.tabFragment.CommodityFragment
import com.example.graduateproj.mainPack.homePack.tabFragment.RecyclerKind

class CommodityPresenter(val view: CommodityFragment) {

    private var electricBeanList : MutableList<RecyclerBean.RecyclerItemBean> = ArrayList()
    private var dailyBeanList : MutableList<RecyclerBean.RecyclerItemBean> = ArrayList()

    companion object {
        private val TAG = CommodityPresenter::class.java.simpleName
        private const val baseUrl = "http://192.168.43.132:8080/"
    }

    fun getItemAndSet(kind: Int) {
        RxOkHttpUtil.getInstance()
            .syncHttpRequestForElectric(baseUrl, object :
                InterfacesHolder.OnElectricDataObtainListener {
                override fun onSuccess(dataList: RecyclerBean?) {
                    dataList?.let {
                        for (bean in dataList.data) {
                            electricBeanList.add(bean)
                            dailyBeanList.add(bean)
                        }
                        when(kind) {
                            RecyclerKind.RECYCLER_NORMAL -> view.initRecyclerViewForElectric(electricBeanList)
                            RecyclerKind.RECYCLER_GRID -> view.initRecyclerViewForDaily(dailyBeanList)
                        }

                    }
                }

                override fun onFailure(e: Throwable?) {
                    Log.e(TAG, e.toString())
                }

            })
    }

}