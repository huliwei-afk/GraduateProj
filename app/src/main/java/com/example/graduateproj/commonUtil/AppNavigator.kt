package com.example.graduateproj.commonUtil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.example.graduateproj.R
import com.example.graduateproj.loginPack.ui.MainLoginActivity
import com.example.graduateproj.mainPack.donatePack.SaleInfoActivity
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean
import com.example.graduateproj.mainPack.homePack.BannerActivity
import com.example.graduateproj.mainPack.homePack.ItemActivity
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean
import com.example.graduateproj.mainPack.mePack.ui.DetailActivity
import com.example.graduateproj.mainPack.mePack.ui.OtherActivity
import com.example.graduateproj.mainPack.ui.MainContentActivity
import com.example.graduateproj.mainPack.mePack.ui.PreferActivity

object AppNavigator {

    private const val BANNER_URL = "url"
    private const val ITEM_OBJECT = "item"
    private const val PUBLISH_KIND = "kind"

    fun openMainLoginActivity(context: Context) {
        val intent = Intent(context, MainLoginActivity::class.java)
        context.startActivity(intent)
    }

    fun openNoAccountFragment(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_forgetFragment)
    }

    fun backToLoginFragment(v: View) {
        Navigation.findNavController(v).popBackStack()
    }

    fun openMainContentActivity(context: Context) {
        val intent = Intent(context, MainContentActivity::class.java)
        context.startActivity(intent)
    }

    fun openEditDetailActivity(context: Context) {
        val intent = Intent(context, DetailActivity::class.java)
        context.startActivity(intent)
    }

    fun openPreferActivity(context: Context) {
        val intent = Intent(context, PreferActivity::class.java)
        context.startActivity(intent)
    }

    fun openAppMarket(context: Context) {
        val uri: Uri = Uri.parse("market://details?id=" + context.packageName)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun openOtherActivity(context: Context) {
        val intent = Intent(context, OtherActivity::class.java)
        context.startActivity(intent)
    }

    fun backToMainContentActivity(context: Activity) {
        context.finish()
    }

    fun openBannerActivity(context: Activity, url: String) {
        val intent = Intent(context, BannerActivity::class.java)
        intent.putExtra(BANNER_URL, url)
        context.startActivity(intent)
    }

    fun openItemActivityFromHome(context: Activity, bean: RecyclerBean.RecyclerItemBean) {
        val intent = Intent(context, ItemActivity::class.java)
        intent.putExtra(ITEM_OBJECT, bean)
        context.startActivity(intent)
    }

    fun openItemActivityFromDonate(context: Activity, bean: DonateJsonBean.DonateItemBean) {
        val intent = Intent(context, ItemActivity::class.java)
        intent.putExtra(ITEM_OBJECT, bean)
        context.startActivity(intent)
    }

    fun openSaleInfoActivity(context: Activity, kind: Int) {
        val intent = Intent(context, SaleInfoActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(PUBLISH_KIND, kind)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }
}