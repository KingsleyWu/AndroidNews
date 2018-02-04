package com.kingsley.androidnews.callback;

import com.kingsley.androidnews.base.BaseArticlesFragment;

/**
 * {@link BaseArticlesFragment}中的刷新回调
 */
public interface OnArticleFragmentRefreshListener {

    /**
     * 下拉刷新
     */
    void onPullTopRefresh();

    /**
     * 上拉刷新
     */
    void onPullBottomRefresh();
}
