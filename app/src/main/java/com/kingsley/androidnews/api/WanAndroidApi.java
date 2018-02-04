package com.kingsley.androidnews.api;

import com.kingsley.androidnews.model.bean.wanandroid.ArticleData;
import com.kingsley.androidnews.model.bean.wanandroid.AuthData;
import com.kingsley.androidnews.model.bean.wanandroid.BannerData;
import com.kingsley.androidnews.model.bean.wanandroid.BaseResponseData;
import com.kingsley.androidnews.model.bean.wanandroid.CollectedArticleData;
import com.kingsley.androidnews.model.bean.wanandroid.FriendListData;
import com.kingsley.androidnews.model.bean.wanandroid.HotKeyData;
import com.kingsley.androidnews.model.bean.wanandroid.TreeData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * WanAndroid的可用API
 */
public interface WanAndroidApi {
    String BASE_URL = "http://wanandroid.com/";

    /**
     * 获取首页数据
     * @param page
     * @return ArticleData
     */
    @GET("article/list/{page}/json")
    Observable<ArticleData> getArticleData(@Path("page") int page);

    /**
     * 注册
     * @param userName
     * @param pwd
     * @param rePwd
     * @return AuthData
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<AuthData> register(@Field("username") String userName, @Field("password") String pwd, @Field("repassword") String rePwd);

    /**
     * 登录
     * @param userName
     * @param pwd
     * @return AuthData
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<AuthData> login(@Field("username") String userName, @Field("password") String pwd);

    /**
     * 收藏列表
     * @param page
     * @return CollectedArticleData
     */
    @GET("lg/collect/list/{page}/json")
    Observable<CollectedArticleData> getCollectData(@Path("page") int page);

    /**
     * 取消收藏文章
     * @param id
     * @return BaseResponseData
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponseData> unCollectArticle(@Path("id") int id);

    /**
     * 收藏文章
     * @param id
     * @return BaseResponseData
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponseData> collectArticle(@Path("id") int id);

    /**
     * 获取"知识体系"
     * @return TreeData
     */
    @GET("tree/json")
    @Headers("Cache-Control: public, max-age=3600")
    Observable<TreeData> getTreeData();

    /**
     * 获取"知识体系"数据
     * @param page
     * @param cid
     * @return ArticleData
     */
    @GET("article/list/{page}/json")
    Observable<ArticleData> getCidData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 搜索接口
     * @param page
     * @param key
     * @return ArticleData
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    Observable<ArticleData> searchArticle(@Path("page") int page, @Field("k") String key);

    /**
     * 热词接口
     * @return HotKeyData
     */

    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    Observable<HotKeyData> getHotKey();

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     * @return FriendListData
     */
    @GET("/friend/json")
    Observable<FriendListData> getFriendList();

    /**
     * http://www.wanandroid.com/banner/json
     * 首页Banner
     * @return BannerData
     */
    @GET("/banner/json")
    Observable<BannerData> getBanner();

    /**
     * http://www.wanandroid.com/lg/collect/usertools/json
     * 我的常用网址
     * @return FriendListData
     */
    @GET("/lg/collect/usertools/json")
    Observable<FriendListData> getBookmarkList();

}
