package com.kingsley.androidnews.base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

/**
 * class name : FragmentAdapter
 * created date : on 2018/1/26 08:29
 *
 * @author Kingsley
 * @version 1.0
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public FragmentAdapter(FragmentManager fm ,List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
