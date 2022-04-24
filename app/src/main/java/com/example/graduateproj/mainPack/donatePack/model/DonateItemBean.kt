package com.example.graduateproj.mainPack.donatePack.model

import java.io.Serializable

data class DonateJsonBean(val data: List<DonateItemBean>) : Serializable {
    data class DonateItemBean(
        val saleIcon: String,
        val saleName: String,
        val saleText: String,
        val saleImage: String
    ) : Serializable
}

