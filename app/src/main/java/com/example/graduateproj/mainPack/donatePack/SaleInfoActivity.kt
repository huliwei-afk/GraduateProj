package com.example.graduateproj.mainPack.donatePack

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduateproj.R
import com.example.graduateproj.commonUI.RoundCornerButton
import com.example.graduateproj.commonUtil.*
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean
import com.example.graduateproj.mainPack.donatePack.presenter.SaleInfoPresenter
import com.example.graduateproj.mainPack.donatePack.util.PublishKind
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean
import com.example.graduateproj.mainPack.homePack.tabFragment.HomeItemKind
import com.example.graduateproj.mainPack.mePack.util.DetailStateUtil
import java.util.concurrent.TimeUnit

class SaleInfoActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    companion object {
        private const val PUBLISH_KIND = "kind"
        private const val TEXT_SIZE = 45F
    }

    private lateinit var arrowBack: ImageView
    internal lateinit var editName: EditText
    internal lateinit var editPrice: EditText
    internal lateinit var editImage: ImageView
    internal lateinit var editDescription: EditText
    private lateinit var publishButton: RoundCornerButton

    internal lateinit var inputPrice: RelativeLayout
    private lateinit var zeroRmb: TextView
    private lateinit var rootView: ConstraintLayout

    private lateinit var saleInfoPresenter: SaleInfoPresenter

    internal lateinit var checkBoxElectric: CheckBox
    internal lateinit var checkBoxDaily: CheckBox
    internal lateinit var checkBoxOther: CheckBox

    private var kind: Int? = null

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

        checkBoxElectric = findViewById(R.id.electric_check)
        checkBoxDaily = findViewById(R.id.daily_check)
        checkBoxOther = findViewById(R.id.other_check)

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

        kind = intent.extras?.getInt(PUBLISH_KIND)

        when (kind) {
            PublishKind.FREE -> {
                if (zeroRmb.visibility == View.GONE) {
                    zeroRmb.visibility = View.VISIBLE
                    inputPrice.visibility = View.GONE
                }
                setView(R.id.zero_rmb)
            }

            PublishKind.PRICE -> {
                if (inputPrice.visibility == View.GONE) {
                    inputPrice.visibility = View.VISIBLE
                    zeroRmb.visibility = View.GONE
                }
                setView(R.id.input_good_price)
            }
        }
    }

    private fun initEvents() {
        checkBoxElectric.setOnCheckedChangeListener(this)
        checkBoxDaily.setOnCheckedChangeListener(this)
        checkBoxOther.setOnCheckedChangeListener(this)

        RxClickUtil.clickEvent(arrowBack, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                AppNavigator.backToMainContentActivity(this@SaleInfoActivity)
            }

        RxClickUtil.clickEvent(editImage, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                saleInfoPresenter.requestPermissionAndTryOpen()
            }

        RxClickUtil.clickEvent(publishButton, this)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (saleInfoPresenter.checkInfoComplete()) {
                    when (kind) {
                        PublishKind.FREE -> {
                            val item = DonateJsonBean.DonateItemBean(
                                saleText = editDescription.text.toString(),
                                saleImage = saleInfoPresenter.getImageUri().toString(),
                                saleName = DetailStateUtil.getInstance(this).localSelfNameOrDefault,
                                saleIcon = saleInfoPresenter.getMeHead()?.toString()
                            )

                            RxBus.getInstance().post(item)
                        }

                        PublishKind.PRICE -> {
                            val item = RecyclerBean.RecyclerItemBean(
                                saleText = editDescription.text.toString(),
                                saleImage = saleInfoPresenter.getImageUri().toString(),
                                salePrice = "¥${editPrice.text}元",
                                whoWants = "0人想要",
                                userName = DetailStateUtil.getInstance(this).localSelfNameOrDefault,
                                userHead = saleInfoPresenter.getMeHead()?.toString(),
                                kind = getKind()
                            )

                            RxBus.getInstance().post(item)
                        }
                    }
                }
            }
    }

    private fun getKind(): Int {
        return if (checkBoxElectric.isChecked) HomeItemKind.ELECTRIC
        else if (checkBoxDaily.isChecked) HomeItemKind.DAILY
        else HomeItemKind.OTHER
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.electric_check -> {
                if (checkBoxElectric.isChecked) {
                    checkBoxDaily.isChecked = false
                    checkBoxOther.isChecked = false
                }
            }

            R.id.daily_check -> {
                if (checkBoxDaily.isChecked) {
                    checkBoxElectric.isChecked = false
                    checkBoxOther.isChecked = false
                }
            }

            R.id.other_check -> {
                if (checkBoxOther.isChecked) {
                    checkBoxElectric.isChecked = false
                    checkBoxDaily.isChecked = false
                }
            }
        }
    }
}