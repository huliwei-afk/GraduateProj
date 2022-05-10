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
    private var otherBeanList : MutableList<RecyclerBean.RecyclerItemBean> = ArrayList()

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
                            otherBeanList.add(bean)
                        }
                        when(kind) {
                            RecyclerKind.RECYCLER_NORMAL -> view.initRecyclerViewForDaily(dailyBeanList)
                            RecyclerKind.RECYCLER_GRID -> view.initRecyclerViewForOther(otherBeanList)
                            RecyclerKind.RECYCLER_STAGGERED -> view.initRecyclerViewForElectric(electricBeanList)
                        }
                    }
                }

                override fun onFailure(e: Throwable?) {
                    Log.e(TAG, e.toString())
                }

            })
    }

    fun getMoreItem(originalList: MutableList<RecyclerBean.RecyclerItemBean>) {
        RxOkHttpUtil.getInstance()
            .syncHttpRequestForElectric(baseUrl, object :
                InterfacesHolder.OnElectricDataObtainListener {
                override fun onSuccess(dataList: RecyclerBean?) {
                    var size = 0
                    dataList?.let {
                        for (bean in dataList.data) {
                            originalList.add(size, bean)
                            size++
                        }
                    }
                    manipulateRecyclerView(size)
                }

                override fun onFailure(e: Throwable?) {
                    Log.e(TAG, e.toString())
                }

            })
    }

    private fun manipulateRecyclerView(size: Int) {
        view.recyclerView.adapter?.notifyItemRangeInserted(0, size)
        view.recyclerView.scrollToPosition(0)
        view.recyclerView.scheduleLayoutAnimation()
        view.swipeRefreshLayout.isRefreshing = false
    }

    fun insertNewHomeItem(bean: RecyclerBean.RecyclerItemBean, originalList: MutableList<RecyclerBean.RecyclerItemBean>) {
        originalList.add(0, bean)
        view.recyclerView.adapter?.notifyItemInserted(0)
        view.recyclerView.scrollToPosition(0)
    }

}