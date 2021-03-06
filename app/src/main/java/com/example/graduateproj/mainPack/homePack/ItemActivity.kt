package com.example.graduateproj.mainPack.homePack

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.graduateproj.R
import com.example.graduateproj.commonUI.RoundCornerButton
import com.example.graduateproj.commonUtil.AppNavigator
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.commonUtil.WindowBarStatusUtil
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean
import de.hdodenhof.circleimageview.CircleImageView
import java.util.concurrent.TimeUnit

class ItemActivity : AppCompatActivity() {

    companion object {
        private const val ITEM_OBJECT = "item"
        private const val PURCHASE = "点击购买"
        private const val ASK_FOR = "向TA索要"
    }

    private lateinit var backArrow: ImageView
    private lateinit var sellerHead: CircleImageView
    private lateinit var sellerName: TextView
    private lateinit var contactButton: RoundCornerButton
    private lateinit var sellerImage: ImageView
    private lateinit var sellerDescription: TextView
    private lateinit var sellerPrice: TextView
    private lateinit var wantsCount: TextView
    private lateinit var actionButton: RoundCornerButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        WindowBarStatusUtil.setBarStatus(this, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        initViews()
        initEvents()
        resolveIntent()
    }

    private fun initViews() {
        backArrow = findViewById(R.id.back)
        sellerHead = findViewById(R.id.item_seller_head)
        sellerName = findViewById(R.id.seller_name)
        contactButton = findViewById(R.id.contact_button)
        sellerImage = findViewById(R.id.seller_image)
        sellerDescription = findViewById(R.id.seller_description)
        sellerPrice = findViewById(R.id.seller_price)
        wantsCount = findViewById(R.id.wants_count)
        actionButton = findViewById(R.id.item_action_button)
    }

    private fun initEvents() {
        RxClickUtil.clickEvent(backArrow, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.backToMainContentActivity(this@ItemActivity)
            }

        RxClickUtil.clickEvent(contactButton, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {

            }

        RxClickUtil.clickEvent(actionButton, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if(actionButton.text == PURCHASE) {

                }
            }
    }

    private fun resolveIntent() {
        val intent = intent
        when(val item = intent.getSerializableExtra(ITEM_OBJECT)) {
            is RecyclerBean.RecyclerItemBean -> {
                passHomeIntentToViews(item)
            }

            is DonateJsonBean.DonateItemBean -> {
                passDonateIntentToViews(item)
            }
        }

    }

    private fun passHomeIntentToViews(item: RecyclerBean.RecyclerItemBean) {
        Glide.with(this).load(item.userHead).into(sellerHead)
        Glide.with(this).load(item.saleImage).into(sellerImage)
        sellerName.text = item.userName
        sellerDescription.text = item.saleText
        sellerPrice.text = item.salePrice
        wantsCount.text = item.whoWants

        actionButton.text = PURCHASE
    }

    private fun passDonateIntentToViews(item: DonateJsonBean.DonateItemBean) {
        if (item.saleIcon == null) {
            Glide.with(this).load(R.drawable.ic_launcher_background).into(sellerHead)
        } else {
            Glide.with(this).load(item.saleIcon).into(sellerHead)
        }
        Glide.with(this).load(item.saleImage).into(sellerImage)
        sellerName.text = item.saleName
        sellerDescription.text = item.saleText
        sellerPrice.text = "¥ 0元"
        wantsCount.visibility = View.GONE

        actionButton.text = ASK_FOR
    }
}