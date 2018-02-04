package com.kingsley.androidnews.api;

import com.kingsley.androidnews.model.bean.news.NewsListBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * class name : WangYiApi
 * created date : on 2017/12/20 15:05
 *
 * @author Kingsley
 * @version 1.0
 */

public interface WangYiApi {
    String HOST = "http://c.m.163.com";

    /**
     *
     * @param id
     * @return
     */
    @GET("/nc/article/headline/T1348647909107/{id}-20.html")
    Observable<NewsListBean> getNewsList(@Path("id") int id);

    /**
     *
     * @param id
     * @return
     */

    @GET("/nc/article/{id}/full.html")
    Observable<ResponseBody> getNewsDetail(@Path("id") String id);
}
