package com.kingsley.androidnews.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

import com.kingsley.androidnews.contract.BaseContract;

/**
 * Created by ${jay} on ${2016/8/17
 */
public abstract class BaseMVPDialog<V extends BaseContract.View, P extends BasePresenterImpl> extends Dialog {

    private static final String TAG = "BaseMVPDialog";
    
    private P mPresenter;

    private V mView;

    public BaseMVPDialog(@NonNull Context context) {
        super(context);
    }

    public BaseMVPDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public BaseMVPDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = bindPresenter();
        mView = bindView();
        if (mPresenter != null) {
            mPresenter.attach(mView);
        }
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onStop();
    }

    public abstract P bindPresenter();

    public abstract V bindView();

    protected P getBindPresenter() {
        return mPresenter;
    }

    protected V getBindView() {
        return mView;
    }
}
