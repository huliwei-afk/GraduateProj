package com.example.graduateproj.mainPack.homePack.model

import java.io.Serializable

data class RecyclerBean(val data: List<RecyclerItemBean>): Serializable {
    data class RecyclerItemBean(val saleText: String, val saleImage: String, val salePrice: String, val whoWants: String, val userName: String, val userHead: String)
}