package com.kingsley.androidnews.ui.widgets;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.kingsley.androidnews.R;

/**
 * class name : RvLoadMoreView
 * created date : on 2018/1/31 22:36
 *
 * @author Kingsley
 * @version 1.0
 */

public class RvLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.load_more_layout;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
