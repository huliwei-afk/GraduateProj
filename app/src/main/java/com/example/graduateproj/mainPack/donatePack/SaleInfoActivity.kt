package com.example.graduateproj.mainPack.donatePack

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduateproj.R
import com.example.graduateproj.commonUI.RoundCornerButton
import com.example.graduateproj.commonUtil.*
import com.example.graduateproj.mainPack.donatePack.presenter.SaleInfoPresenter
import com.example.graduateproj.mainPack.donatePack.util.PublishKind
import java.util.concurrent.TimeUnit

class SaleInfoActivity : AppCompatActivity() {

    companion object {
        private const val PUBLISH_KIND = "kind"
        private const val TEXT_SIZE = 45F
    }

    private lateinit var arrowBack: ImageView
    internal lateinit var editName: EditText
    internal lateinit var editPrice: EditText
    private lateinit var editImage: ImageView
    internal lateinit var editDescription: EditText
    private lateinit var publishButton: RoundCornerButton

    internal lateinit var inputPrice: RelativeLayout
    private lateinit var zeroRmb: TextView
    private lateinit var rootView: ConstraintLayout

    private lateinit var saleInfoPresenter: SaleInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_info)

        WindowBarStatusUtil.setBarStatus(this, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        initViews()
        resolveIntentForSetView()
        initEvents()
    }

    private fun initViews() {
        editName = findViewById(R.id.sale_good_input_name)
        editPrice = findViewById(R.id.sale_good_input_price)
        editImage = findViewById(R.id.input_good_image)
        arrowBack = findViewById(R.id.sale_back)
        editDescription = findViewById(R.id.input_good_description)
        publishButton = findViewById(R.id.publish_button)

        inputPrice = findViewById(R.id.input_good_price)
        zeroRmb = findViewById(R.id.zero_rmb)
        rootView = findViewById(R.id.view_container)

        saleInfoPresenter = SaleInfoPresenter(this)
    }

    private fun resolveIntentForSetView() {
        fun setView(id: Int) {
            val priceTitle = TextView(this).apply {
                textSize = ScreenUtil.px2spInt(this@SaleInfoActivity, TEXT_SIZE).toFloat()
                setTextColor(resources.getColor(R.color.login_black))
                text = resources.getText(R.string.good_price_title)
            }
            val params: ConstraintLayout.LayoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            params.leftToLeft = R.id.good_name_title
            params.topToTop = id
            params.bottomToBottom = id
            priceTitle.layoutParams = params
            rootView.addView(priceTitle)
        }

        when (intent.extras?.getInt(PUBLISH_KIND)) {
            PublishKind.FREE -> {
                if(zeroRmb.visibility == View.GONE) {
                    zeroRmb.visibility = View.VISIBLE
                    inputPrice.visibility = View.GONE
                }
                setView(R.id.zero_rmb)
            }

            PublishKind.PRICE -> {
                if(inputPrice.visibility == View.GONE) {
                    inputPrice.visibility = View.VISIBLE
                    zeroRmb.visibility = View.GONE
                }
                setView(R.id.input_good_price)
            }
        }
    }

    private fun initEvents() {
        RxClickUtil.clickEvent(arrowBack, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.backToMainContentActivity(this@SaleInfoActivity)
            }

        RxClickUtil.clickEvent(publishButton, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                saleInfoPresenter.checkInfoComplete()
            }
    }
}