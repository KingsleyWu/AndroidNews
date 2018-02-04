package com.kingsley.androidnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingsley.androidnews.contract.BaseContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * class name : BaseMVPFragment
 * created date : on 2017/12/20 18:14
 *
 * @author Kingsley
 * @version 1.0
 */

public abstract class BaseMVPFragment<V extends BaseContract.View, P extends BasePresenterImpl> extends SupportFragment {
    protected OnBackToFirstListener mBackToFirstListener;

    public P mPresenter;
    public V mView;
    protected Context mContext;
    private Unbinder binder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(setFragmentLayoutId(), container, false);
        binder = ButterKnife.bind(this,view);
        getBundle(getArguments());
        initView(view,savedInstanceState);
        return view;
    }

    /**
     * 用于设置FragmentLayoutId
     * @return FragmentLayoutId
     */
    protected abstract int setFragmentLayoutId();

    /**
     * 得到Activity传进来的值
     */
    public void getBundle(Bundle arguments) {
    }

    /**
     * 初始化Fragment
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
        mPresenter = bindPresenter();
        mView = bindView();
        if (mPresenter != null) {
            mPresenter.attach(mView);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detach();
        }
        mBackToFirstListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null) {
            binder.unbind();
        }
    }

    /**
     * IPresenter
     * @return P Presenter
     */
    protected abstract P bindPresenter();

    /**
     * IView
     * @return V View
     */
    protected abstract V bindView();

    protected P getBindPresenter() {
        return mPresenter;
    }

    protected V getBindView() {
        return mView;
    }

    /**
     * 处理回退事件
     * @return 是否回退
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            // 如果是 第一个Fragment 则退出app
            if (this instanceof Fragment) {
                _mActivity.finish();
            } else {
                // 如果不是,则回到第一个Fragment
                mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {
        /**
         * 回到第一个Fragment
         */
        void onBackToFirstFragment();
    }
}
