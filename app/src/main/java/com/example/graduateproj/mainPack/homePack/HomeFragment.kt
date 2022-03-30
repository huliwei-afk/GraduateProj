package com.example.graduateproj.mainPack.homePack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.graduateproj.R
import com.example.graduateproj.databinding.FragmentHomeBinding
import com.example.graduateproj.mainPack.homePack.tabFragment.CommodityFragment
import com.example.graduateproj.mainPack.homePack.tabFragment.ElectricFragment
import com.example.graduateproj.mainPack.homePack.tabFragment.OtherFragment
import com.example.graduateproj.mainPack.homePack.util.HomeTabFragmentAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeTabLayout: TabLayout
    private lateinit var homeTabFragmentViewPager: ViewPager
    private var titles = arrayListOf<String>()
    private var tabFragments = arrayListOf<Fragment>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        return root
    }

    private fun initViews(root: View) {
        homeTabLayout = root.findViewById(R.id.home_tab_layout)
        homeTabFragmentViewPager = root.findViewById(R.id.tab_fragment_viewpager)
    }

    private fun setupTabAndVp() {
        titles.apply {
            add("电子产品")
            add("日用品")
            add("其他")
        }

        tabFragments.apply {
            add(ElectricFragment())
            add(CommodityFragment())
            add(OtherFragment())
        }

        val homeTabFragmentAdapter = HomeTabFragmentAdapter(fragmentManager, tabFragments, titles)
        homeTabFragmentViewPager.adapter = homeTabFragmentAdapter
        homeTabLayout.setupWithViewPager(homeTabFragmentViewPager)
    }








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}