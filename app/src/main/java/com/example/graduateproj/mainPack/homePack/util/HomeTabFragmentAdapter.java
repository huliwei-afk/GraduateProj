package com.example.graduateproj.mainPack.homePack.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class HomeTabFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;

    public HomeTabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
