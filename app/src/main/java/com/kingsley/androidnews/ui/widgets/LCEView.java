package com.kingsley.androidnews.ui.widgets;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * class name : LCEView
 * created date : on 2018/2/1 00:24
 *
 * @author Kingsley
 * @version 1.0
 */

public abstract class LCEView {
    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_NET_ERROR = 3;
    public static final int STATUS_NO_DATA = 4;

    private int mLCEStatusStatus = STATUS_DEFAULT;
    private boolean mLCEGone = false;

    public void setLCEStatus(int lceStatus) {
        this.mLCEStatusStatus = lceStatus;
    }

    public int getLCEStatus() {
        return mLCEStatusStatus;
    }

    public void convert(BaseViewHolder holder) {
        switch (mLCEStatusStatus) {
            case STATUS_LOADING:
                visibleLoading(holder, true);
                visibleNetError(holder, false);
                visibleNoData(holder, false);
                break;
            case STATUS_NET_ERROR:
                visibleLoading(holder, false);
                visibleNetError(holder, true);
                visibleNoData(holder, false);
                break;
            case STATUS_NO_DATA:
                visibleLoading(holder, false);
                visibleNetError(holder, false);
                visibleNoData(holder, true);
                break;
            case STATUS_DEFAULT:
                visibleLoading(holder, false);
                visibleNetError(holder, false);
                visibleNoData(holder, false);
                break;
            default:
                break;
        }
    }

    private void visibleLoading(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getLoadingViewId(), visible);
    }

    private void visibleNetError(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getNetErrorViewId(), visible);
    }

    private void visibleNoData(BaseViewHolder holder, boolean visible) {
        holder.setVisible(getNoDataViewId(), visible);
    }

    public final void setLCEGoneGone(boolean lceGone) {
        this.mLCEGone = lceGone;
    }

    public final boolean isLCEGone() {
        return mLCEStatusStatus == STATUS_DEFAULT || mLCEGone;
    }

    /**
     * lee layout
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * loading view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadingViewId();

    /**
     * load net error view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getNetErrorViewId();

    /**
     * load no data view, you can return 0
     *
     * @return
     */
    protected abstract
    @IdRes
    int getNoDataViewId();
}

