package com.kingsley.androidnews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kingsley.androidnews.base.BaseActivity;
import com.kingsley.androidnews.base.adapter.FragmentAdapter;
import com.kingsley.androidnews.rxbus.RxBus;
import com.kingsley.androidnews.ui.GanKIoFragment;
import com.kingsley.androidnews.ui.MeFragment;
import com.kingsley.androidnews.ui.WanAndroidFragment;
import com.kingsley.androidnews.utils.SpUtils;
import com.kingsley.androidnews.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Kingsley
 * @date 2018-1-14
 */
public class MainActivity extends BaseActivity {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long touchTime = 0;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_toolbar)
    TextView mToolbarTitle;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.bnv_main)
    BottomNavigationView mBnvMain;
    @BindView(R.id.fab_main)
    FloatingActionButton mFabMain;
    @BindView(R.id.nv_menu)
    NavigationView mNvMenu;
    @BindView(R.id.dl_main)
    DrawerLayout mDlMain;
    List<String> gankIoTitles = new ArrayList<>();
    List<String> wanandroidTitles = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        RxBus.get().register(this);
        initToolbar();
        initNavigationView();
        initFloatingActionButton();
        initViewPager();
        initBottomNavigationView();
        mBnvMain.setSelectedItemId(R.id.menu_item_gank_io);
    }

    private void initViewPager() {
        mVpMain.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragments));
        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBnvMain.setSelectedItemId(R.id.menu_item_gank_io);
                        break;
                    case 1:
                        mBnvMain.setSelectedItemId(R.id.menu_item_wanandroid);
                        break;
                    case 2:
                        mBnvMain.setSelectedItemId(R.id.menu_item_personal);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments.add(0, GanKIoFragment.newInstance());
            mFragments.add(1, WanAndroidFragment.newInstance());
            mFragments.add(2, MeFragment.newInstance());
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments.add(0, findFragment(GanKIoFragment.class));
            mFragments.add(1, findFragment(WanAndroidFragment.class));
            mFragments.add(2, findFragment(MeFragment.class));
        }
    }

    private void initFloatingActionButton() {
        mFabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/1/26
            }
        });
    }

    private void setTitleDatas() {
        gankIoTitles.add("所有");
        gankIoTitles.add("安卓");
        gankIoTitles.add("苹果");
        gankIoTitles.add("前端");
        gankIoTitles.add("拓展资源");
        gankIoTitles.add("休息视频");
        gankIoTitles.add("福利");
        wanandroidTitles.add("首页");
        wanandroidTitles.add("知识体系");
        wanandroidTitles.add("常用网站");
    }

    private void initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_vector_menu);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDlMain.isDrawerOpen(GravityCompat.START)) {
                    mDlMain.openDrawer(GravityCompat.START);
                }
            }
        });
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    mFabMain.show();

                } else {
                    mFabMain.hide();
                }
            }
        });
    }

    private void initBottomNavigationView() {
        mBnvMain.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_gank_io:
                        mToolbarTitle.setText("干货");
                        mVpMain.setCurrentItem(0);
                        break;
                    case R.id.menu_item_wanandroid:
                        mToolbarTitle.setText("玩安卓");
                        mVpMain.setCurrentItem(1);
                        break;
                    case R.id.menu_item_personal:
                        mToolbarTitle.setText("个人");
                        mVpMain.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initNavigationView() {
        mNvMenu.getMenu().findItem(R.id.item_model).setTitle(SpUtils.getNightModel(this) ?
                "日间模式" : "夜间模式");
        mNvMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.group_item_github:
                       /* Bundle bundle = new Bundle();
                        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE, "Yizhi");
                        bundle.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                                "https://github.com/Horrarndoo/YiZhi");
                        startActivity(WebViewLoadActivity.class, bundle);*/
                        break;
                    case R.id.group_item_more:
                        /*Bundle bundle2 = new Bundle();
                        bundle2.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE,
                                "Horrarndoo");
                        bundle2.putString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL,
                                "http://blog.csdn.net/oqinyou");
                        startActivity(WebViewLoadActivity.class, bundle2);*/
                        break;
                    case R.id.group_item_qr_code:
                        /*startActivity(QRCodeActivity.class);*/
                        break;
                    case R.id.group_item_share_project:
                        /*showShare();*/
                        break;
                    case R.id.item_model:
                        SpUtils.setNightModel(MainActivity.this, !SpUtils.getNightModel(MainActivity.this));
                        MainActivity.this.reload();
                        break;
                    case R.id.item_about:
                        //startActivity(AboutActivity.class);
                        break;
                    default:
                        break;
                }

                item.setCheckable(false);
                mDlMain.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressedSupport() {

        if (mDlMain.isDrawerOpen(GravityCompat.START)) {
            mDlMain.closeDrawer(GravityCompat.START);
            return;
        }

        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //如果已经到root fragment了，2秒内点击2次退出
            if (System.currentTimeMillis() - touchTime < WAIT_TIME) {
                //setIsTransAnim(false);
                finish();
            } else {
                touchTime = System.currentTimeMillis();
                ToastUtils.showToast(getString(R.string.press_again));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}

