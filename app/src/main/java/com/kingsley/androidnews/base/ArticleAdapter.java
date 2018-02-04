package com.kingsley.androidnews.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kingsley.androidnews.base.adapter.RefreshRecycleAdapter;
import com.kingsley.androidnews.callback.OnArticleItemClickListener;

/**
 * class name : ArticleAdapter
 * created date : on 2018/1/18 17:45
 *
 * @author Kingsley
 * @version 1.0
 */

class ArticleAdapter extends RefreshRecycleAdapter<ArticleAdapter.ViewHolder> {
    private OnArticleItemClickListener onArticleItemClickListener;

    public ArticleAdapter(Context context) {
    }

    public void setOnArticleItemClickListener(OnArticleItemClickListener onArticleItemClickListener) {
        this.onArticleItemClickListener = onArticleItemClickListener;
    }

    @Override
    protected void onBindRefreshFooterViewHolder(ViewHolder holder, RefreshState state) {

    }

    @Override
    public boolean isRefreshing() {
        return false;
    }

    @Override
    public void setRefreshing(boolean isRefreshing) {


    }

    @Override
    protected ViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder holder, int position) {

    }

    @Override
    protected ViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public int getNormalItemCount() {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
