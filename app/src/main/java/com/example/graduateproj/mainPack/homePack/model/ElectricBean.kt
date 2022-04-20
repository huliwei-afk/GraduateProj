package com.example.graduateproj.mainPack.homePack.model

import java.io.Serializable

data class ElectricBean(val data: List<ElectricItemBean>): Serializable {
    data class ElectricItemBean(val saleText: String, val saleImage: String, val salePrice: String, val whoWants: String, val userName: String, val userHead: String)
}