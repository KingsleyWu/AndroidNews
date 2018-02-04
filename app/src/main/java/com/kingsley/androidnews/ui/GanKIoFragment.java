package com.kingsley.androidnews.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.kingsley.androidnews.R;
import com.kingsley.androidnews.base.BaseMainFragment;
import com.kingsley.androidnews.base.adapter.FragmentAdapter;
import com.kingsley.androidnews.contract.Config;
import com.kingsley.androidnews.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GanKIoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author kingsley
 */
public class GanKIoFragment extends BaseMainFragment {

    @BindView(R.id.linear_layout)
    LinearLayout mLinearlayout;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    List<Fragment> mFragments = new ArrayList<>();

    String mCurType = Config.TYPE_ALL;

    public static GanKIoFragment newInstance() {
        GanKIoFragment fragment = new GanKIoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab_view_pager_layout;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        RxBus.get().register(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showTabList("所有", "安卓", "苹果", "前端", "拓展资源", "应用", "瞎推荐", "休息视频", "福利");
    }

    public void showTabList(String... tabs) {
        //Logger.w(Arrays.toString(tabs));
        for (int i = 0,tabSize = tabs.length; i < tabSize; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabs[i]));
            if (mFragments.size() < tabSize) {
                initType(i);
                mFragments.add(GankIoItemFragment.newInstance(mCurType));
            }
        }
        mViewPager.setAdapter(new FragmentAdapter(getChildFragmentManager(), mFragments));
        mViewPager.setCurrentItem(0);
        // 要设置到viewpager.setAdapter后才起作用
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setVerticalScrollbarPosition(0);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
       for (int i = 0; i < tabs.length; i++) {
            mTabLayout.getTabAt(i).setText(tabs[i]);
        }

    }

    private void initType(int index) {
        switch (index) {
            case 0:
                mCurType = Config.TYPE_ALL;
                break;
            case 1:
                mCurType = Config.TYPE_ANDROID;
                break;
            case 2:
                mCurType = Config.TYPE_IOS;
                break;
            case 3:
                mCurType = Config.TYPE_WEB;
                break;
            case 4:
                mCurType = Config.TYPE_EXPAND_THE_RESOURCES;
                break;
            case 5:
                mCurType = Config.TYPE_APP;
                break;
            case 6:
                mCurType = Config.TYPE_BLIND_TO_RECOMMEND;
                break;
            case 7:
                mCurType = Config.TYPE_REST_VIDEO;
                break;
            case 8:
                mCurType = Config.TYPE_WELFARE;
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }


}
