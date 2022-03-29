package com.example.graduateproj.loginPack.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentStateAdapter : FragmentStateAdapter {

    private var activityFragments : List<Fragment>

    constructor(fragmentActivity: FragmentActivity, fragments: List<Fragment>) : super(fragmentActivity) {
        this.activityFragments = fragments
    }

    constructor(fragment: Fragment, fragments: List<Fragment>) : super(fragment) {
        this.activityFragments = fragments
    }

    override fun getItemCount(): Int {
        return activityFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return activityFragments[position]
    }
}