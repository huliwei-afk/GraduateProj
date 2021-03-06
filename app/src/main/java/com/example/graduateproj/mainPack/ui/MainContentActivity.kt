package com.example.graduateproj.mainPack.ui

import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.ToastUtil
import com.example.graduateproj.commonUtil.WindowBarStatusUtil
import com.example.graduateproj.databinding.ActivityMainContentBinding
import com.example.graduateproj.loginPack.util.MainFragmentStateAdapter
import com.example.graduateproj.mainPack.donatePack.DonateFragment
import com.example.graduateproj.mainPack.homePack.HomeFragment
import com.example.graduateproj.mainPack.mePack.MeFragment

class MainContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainContentBinding

    private lateinit var radioGroup: RadioGroup
    private lateinit var mainViewPager: ViewPager2
    private lateinit var radioButtonForHome: RadioButton
    private lateinit var radioButtonForDonate: RadioButton
    private lateinit var radioButtonForMe: RadioButton

    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowBarStatusUtil.setBarStatus(this, View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        initViews()
        initRadioGroup()
        initViewPagerAndFragment()
    }

    private fun initViews() {
        radioGroup = findViewById(R.id.main_radio_group)
        mainViewPager = findViewById(R.id.main_content_view_pager2)

        radioButtonForHome = findViewById(R.id.radio_button_home)
        radioButtonForDonate = findViewById(R.id.radio_button_donate)
        radioButtonForMe = findViewById(R.id.radio_button_me)

        val gradientDrawable = GradientDrawable()
        gradientDrawable.apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 90F
            color = resources.getColorStateList(R.color.main_18CAE4)
        }
        radioGroup.background = gradientDrawable
    }

    private fun initRadioGroup() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_home -> {
                    WindowBarStatusUtil.initBar(this, resources.getColor(R.color.login_white), 1)
                    mainViewPager.setCurrentItem(0, false)
                    showClickAnim(radioButtonForHome)
                }

                R.id.radio_button_donate -> {
                    WindowBarStatusUtil.initBar(this, resources.getColor(R.color.main_18CAE4), 1)
                    mainViewPager.setCurrentItem(1, false)
                    showClickAnim(radioButtonForDonate)
                }

                R.id.radio_button_me -> {
                    WindowBarStatusUtil.initBar(this, resources.getColor(R.color.main_18CAE4), 1)
                    mainViewPager.setCurrentItem(2, false)
                    showClickAnim(radioButtonForMe)
                }
            }
        }
    }

    private fun initViewPagerAndFragment() {
        val allFragments: List<Fragment> = listOf(HomeFragment(), DonateFragment(), MeFragment())
        mainViewPager.apply {
            adapter = MainFragmentStateAdapter(this@MainContentActivity, allFragments)
            mainViewPager.offscreenPageLimit = 2
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> radioGroup.check(R.id.radio_button_home)
                        1 -> radioGroup.check(R.id.radio_button_donate)
                        2-> radioGroup.check(R.id.radio_button_me)
                    }
                }
            })
        }
    }

    private fun showClickAnim(radioButton: RadioButton) {
        val scaleAnim = ValueAnimator.ofFloat(1.0F, 0.7F, 1.2F, 0.9F, 1.0F).apply {
            interpolator = LinearInterpolator()
            duration = 500
            addUpdateListener { animation ->
                val scale = animation?.animatedValue as Float
                radioButton.scaleX = scale
                radioButton.scaleY = scale
            }
        }.start()
    }

    override fun onBackPressed() {
        // ????????????????????????
        val nowTime = System.currentTimeMillis()
        if (nowTime - pressedTime > 2000) {
            // ???????????????????????????
            ToastUtil.showToastCenter(this, "????????????????????????")
            pressedTime = nowTime
        } else {
            // ????????????
            super.onBackPressed()
            finishAffinity()
        }
    }
}