package com.example.graduateproj.mainPack.homePack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.graduateproj.R
import com.example.graduateproj.databinding.FragmentHomeBinding
import com.example.graduateproj.interfaceUtil.OnBannerImageLoadListener
import com.example.graduateproj.mainPack.homePack.model.BannerImageBean
import com.example.graduateproj.mainPack.homePack.presenter.HomePresenter
import com.example.graduateproj.mainPack.homePack.tabFragment.CommodityFragment
import com.example.graduateproj.mainPack.homePack.tabFragment.RecyclerKind
import com.example.graduateproj.mainPack.homePack.util.BetterBannerScroll
import com.example.graduateproj.mainPack.homePack.util.DepthPageTransformer
import com.example.graduateproj.mainPack.homePack.util.HomeBannerAdapter
import com.example.graduateproj.mainPack.homePack.util.HomeTabFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.Exception
import java.lang.reflect.Field
import java.util.*


class HomeFragment : Fragment() {

    companion object {
        private const val tabElectric = "电子产品"
        private const val tabDaily = "日用品"
        private const val tabOther = "其他"
    }

    private val TAG = HomeFragment::class.java.simpleName
    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeTabLayout: TabLayout
    private lateinit var homeTabFragmentViewPager: ViewPager2
    private lateinit var homeBanner: ViewPager
    private var titles = arrayListOf<String>()
    private var titlesImage = arrayListOf<Int>()
    private var tabFragments = arrayListOf<Fragment>()
    private lateinit var homePresenter: HomePresenter

    private var timer: Timer? = null
    private var timerTask: TimerTask? = null
    private var betterBannerScroll: BetterBannerScroll? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun initViews(root: View) {
        homeTabLayout = root.findViewById(R.id.home_tab_layout)
        homeTabFragmentViewPager = root.findViewById(R.id.tab_fragment_viewpager)
        homeBanner = root.findViewById(R.id.home_banner)

        homePresenter = HomePresenter(this)

    }

    fun initBanner(bean: BannerImageBean) {
        homeBanner.apply {
            setPageTransformer(true, DepthPageTransformer())
            offscreenPageLimit = 5
            adapter = object :
                HomeBannerAdapter(bean, OnBannerImageLoadListener { bean, position, imageView ->
                    Glide.with(requireContext())
                        .load(bean.data[position])
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(40)))
                        .into(imageView as ImageView)

                }) {}
            // TODO：这行代码位置不对会导致崩溃
            currentItem = (homeBanner.adapter?.count)?.div(2) ?: 0
        }
    }

    private fun setupTabAndVp() {
//
            homeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    updateTab(tab)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    updateTab(tab)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
//        }

        titles.apply {
            add(tabElectric)
            add(tabDaily)
            add(tabOther)
        }

        titlesImage.apply {
            add(R.drawable.home_tab_electric_icon)
            add(R.drawable.home_tab_daily_icon)
            add(R.drawable.home_tab_other_icon)
        }

        tabFragments.apply {
            add(CommodityFragment.newInstance(RecyclerKind.RECYCLER_NORMAL))
            add(CommodityFragment.newInstance(RecyclerKind.RECYCLER_NORMAL))
            add(CommodityFragment.newInstance(RecyclerKind.RECYCLER_NORMAL))
        }

        homeTabFragmentViewPager.apply {
            adapter = HomeTabFragmentAdapter(this@HomeFragment, tabFragments)
            offscreenPageLimit = 1
            isUserInputEnabled = true
        }

        TabLayoutMediator(homeTabLayout, homeTabFragmentViewPager, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.setCustomView(R.layout.fragment_commodity_tab_icon)

                val customView = tab.customView
                val tabText = customView?.findViewById<TextView>(R.id.tab_text)
                val tabImage = customView?.findViewById<ImageView>(R.id.tab_image)

                tabText?.text = titles[position]
                tabImage?.setImageResource(titlesImage[position])
            }

        }).attach()
    }

    private fun updateTab(tab: TabLayout.Tab?) {
        val customView = tab?.customView
        val tabText = customView?.findViewById<TextView>(R.id.tab_text)
        if(tab?.isSelected == true) {
            tabText?.setTextColor(resources.getColor(R.color.main_FC438C))
        } else {
            tabText?.setTextColor(resources.getColor(R.color.login_white))
        }
    }

    private fun executeTimerTask() {
        clearAllTimerTask()

        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread {
                    showNextImageAuto()
                }
            }
        }
        timer?.schedule(timerTask, 3000, 4000)
    }

    private fun clearAllTimerTask() {
        timer?.cancel()
        timerTask?.cancel()
    }

    private fun showNextImageAuto() {
        var currentIndex = homeBanner.currentItem
        homeBanner.setCurrentItem(++currentIndex, true)
    }

    private fun initViewPagerTouchEvent() {
        with(homeBanner) {
            setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> clearAllTimerTask()
                    MotionEvent.ACTION_MOVE -> clearAllTimerTask()
                    MotionEvent.ACTION_UP -> executeTimerTask()
                }
                false
            }
        }
    }

    private fun changeViewPagerScroller() {
        betterBannerScroll = BetterBannerScroll(requireContext())
        val clazz = ViewPager::class.java
        try {
            val field: Field = clazz.getDeclaredField("mScroller")
            field.isAccessible = true
            //利用反射设置mScroller域为自己定义的MScroller
            field.set(homeBanner, betterBannerScroll)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews(root)
        setupTabAndVp()
        executeTimerTask()
        initViewPagerTouchEvent()
        changeViewPagerScroller()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePresenter.getBannerImageDataAndSet()
    }

    override fun onResume() {
        super.onResume()
        executeTimerTask()
    }

    override fun onPause() {
        super.onPause()
        clearAllTimerTask()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
