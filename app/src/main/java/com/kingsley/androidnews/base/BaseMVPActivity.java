package com.kingsley.androidnews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kingsley.androidnews.contract.BaseContract;

/**
 * MVP公共类
 * <p>
 * 负责View以及Presenter的自动绑定与解绑
 * </p>
 */
public abstract class BaseMVPActivity<V extends BaseContract.View, P extends BasePresenterImpl> extends BaseActivity {

    private P mPresenter;

    private V mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = bindPresenter();
        mView = bindView();
        if (mPresenter != null) {
            mPresenter.attach(mView);
        }
    }

    public abstract P bindPresenter();

    public abstract V bindView();

    protected P getBindPresenter() {
        return mPresenter;
    }

    protected V getBindView() {
        return mView;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }
}
