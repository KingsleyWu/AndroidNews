package com.kingsley.androidnews.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kingsley.androidnews.utils.SpUtils;
import com.kingsley.androidnews.utils.ThemeUtils;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * class name : BaseActivity
 * created date : on 2018/1/21 17:50
 *
 * @author Kingsley
 * @version 1.0
 */

public abstract class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    /**
     * 用于初始化layout
     * @return layoutID
     */
    protected abstract int setLayoutId();

    /**
     * 用于初始化view
     * @param savedInstanceState 保存的数据
     */
    protected void init(Bundle savedInstanceState){
        int theme = ThemeUtils.themeArr[SpUtils.getThemeIndex(this)][SpUtils.getNightModel(this) ? 1 : 0];
        setTheme(theme);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView(savedInstanceState);
    }

    /**
     * 用于初始化view
     * @param savedInstanceState 保存的数据
     */
    protected abstract void initView(Bundle savedInstanceState);

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
