package com.kingsley.androidnews.callback;

import com.kingsley.androidnews.model.bean.wanandroid.ArticleData;

/**
 * 文章被点击的回调
 */
public interface OnArticleItemClickListener {

    void OnArticleItemClick(ArticleData.Data.Article article);

}
