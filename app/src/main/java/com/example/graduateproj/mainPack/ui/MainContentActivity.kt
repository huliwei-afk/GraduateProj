package com.example.graduateproj.mainPack.ui

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.graduateproj.R
import com.example.graduateproj.commonUtil.RxClickUtil
import com.example.graduateproj.databinding.ActivityMainContentBinding
import com.example.graduateproj.loginPack.util.MainFragmentStateAdapter
import com.example.graduateproj.mainPack.donatePack.DonateFragment
import com.example.graduateproj.mainPack.homePack.HomeFragment
import com.example.graduateproj.mainPack.mePack.MeFragment
import com.example.graduateproj.mainPack.messagePack.MessageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit

class MainContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainContentBinding

    private lateinit var radioGroup: RadioGroup
    private lateinit var mainViewPager: ViewPager2
    private lateinit var radioButtonForHome: RadioButton
    private lateinit var radioButtonForDonate: RadioButton
    private lateinit var radioButtonForMessage: RadioButton
    private lateinit var radioButtonForMe: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initRadioGroup()
        initViewPagerAndFragment()
    }

    private fun initViews() {
        radioGroup = findViewById(R.id.main_radio_group)
        mainViewPager = findViewById(R.id.main_content_view_pager2)

        radioButtonForHome = findViewById(R.id.radio_button_home)
        radioButtonForDonate = findViewById(R.id.radio_button_donate)
        radioButtonForMessage = findViewById(R.id.radio_button_message)
        radioButtonForMe = findViewById(R.id.radio_button_me)

        val gradientDrawable = GradientDrawable()
        gradientDrawable.apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 90F
            color = resources.getColorStateList(R.color.main_18CAE4)
        }
        radioGroup.background = gradientDrawable
        radioGroup.elevation
    }

    private fun initRadioGroup() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_home -> {
                    mainViewPager.setCurrentItem(0, false)
                    showClickAnim(radioButtonForHome)
                }

                R.id.radio_button_donate -> {
                    mainViewPager.setCurrentItem(1, false)
                    showClickAnim(radioButtonForDonate)
                }

                R.id.radio_button_message -> {
                    mainViewPager.setCurrentItem(2, false)
                    showClickAnim(radioButtonForMessage)
                }

                R.id.radio_button_me -> {
                    mainViewPager.setCurrentItem(3, false)
                    showClickAnim(radioButtonForMe)
                }
            }
        }
    }

    private fun initViewPagerAndFragment() {
        val allFragments: List<Fragment> = listOf(HomeFragment(), DonateFragment(), MessageFragment(), MeFragment())
        mainViewPager.apply {
            adapter = MainFragmentStateAdapter(this@MainContentActivity, allFragments)
            mainViewPager.offscreenPageLimit = 1
            isUserInputEnabled = false
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> radioGroup.check(R.id.radio_button_home)
                        1 -> radioGroup.check(R.id.radio_button_donate)
                        2 -> radioGroup.check(R.id.radio_button_message)
                        3 -> radioGroup.check(R.id.radio_button_me)
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
}