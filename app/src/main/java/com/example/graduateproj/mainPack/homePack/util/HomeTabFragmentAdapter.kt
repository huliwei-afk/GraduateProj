package com.example.graduateproj.mainPack.homePack.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeTabFragmentAdapter : FragmentStateAdapter {

    private var homeTabFragments : List<Fragment>

    constructor(fragmentActivity: FragmentActivity, fragments: List<Fragment>) : super(fragmentActivity) {
        this.homeTabFragments = fragments
    }

    constructor(fragment: Fragment, fragments: List<Fragment>) : super(fragment) {
        this.homeTabFragments = fragments
    }

    override fun getItemCount(): Int {
        return homeTabFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return homeTabFragments[position]
    }
}