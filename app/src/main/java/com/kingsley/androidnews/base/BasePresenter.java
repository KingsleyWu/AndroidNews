package com.kingsley.androidnews.base;

import android.support.annotation.NonNull;

import com.kingsley.androidnews.contract.BaseContract;

import java.lang.ref.WeakReference;

/**
 * class name : BasePresenter
 * created date : on 2017/12/19 20:00
 *
 * @author Kingsley
 * @version 1.0
 */

public abstract class BasePresenter<M extends BaseContract.Model, V extends BaseContract.View> implements BaseContract.Presenter<V> {
    private M modelRef;
    private WeakReference<V> viewRef;

    /**
     * attach()
     * @param v IView
     */

    @Override
    public void attach(@NonNull V v) {
        this.viewRef = new WeakReference<>(v);
        onAttach();
    }

    public void setModel(@NonNull M m) {
        this.modelRef = m;
    }

    /**
     * detach()
     */

    @Override
    public void detach() {
        if (modelRef != null) {
           // modelRef.clear();
            this.modelRef = null;
        }
        if (viewRef != null) {
            viewRef.clear();
            this.viewRef = null;
        }
        onDetach();
    }

    /**
     * 是否已连接上View
     *
     * @return isViewAttached
     */
    @Override
    public boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * 获取IModel
     *
     * @return IModel
     */

    public M getModel() {
        return modelRef;
    }

    /**
     * 获取IView
     *
     * @return IView
     */
    @Override
    public V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    /**
     * 当实现Presenter时如需调用一些方法则可以直接调用此方法
     */
    protected abstract void onAttach();

    /**
     * 当View与Presenter断开时如需调用一些方法则可以直接调用此方法
     */
    abstract void onDetach();
}
