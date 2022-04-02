package com.example.graduateproj.interfaceUtil

import com.example.graduateproj.mainPack.homePack.model.BannerImageBean
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HttpRequest {
    @GET("banner/image")
    fun call(): Single<BannerImageBean>

}