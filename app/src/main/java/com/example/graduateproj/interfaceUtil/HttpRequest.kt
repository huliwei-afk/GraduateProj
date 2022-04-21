package com.example.graduateproj.interfaceUtil

import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HttpRequest {
    @GET("banner/image")
    fun callBanner(): Single<BannerImageBean>

    @GET("donate/item")
    fun callDonate(): Observable<DonateJsonBean>

    @GET("home/electric/item")
    fun callElectric(): Single<RecyclerBean>
}