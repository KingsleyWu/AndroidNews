package com.kingsley.androidnews.api;

import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;
import com.kingsley.androidnews.model.bean.gankio.GankIoDay;
import com.kingsley.androidnews.model.bean.gankio.GankIoWelfareList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * class name : GankIoApi
 * created date : on 2017/12/20 15:05
 *
 * @author Kingsley
 * @version 1.0
 */

public interface GankIoApi {
    String HOST = "http://gank.io";

    /**
     * 每日数据： http://gank.io/api/day/年/月/日
     * eg:http://gank.io/api/day/2017/08/06
     */
    @GET("/api/day/{year}/{month}/{day}")
    Observable<GankIoDay> getGankIoDay(@Path("year") String year, @Path("month") String
            month, @Path("day") String day);

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("/api/data/{type}/{pre_page}/{page}")
    Observable<GankIoAllDate> getGankIoCustomList(@Path("type") String type, @Path
            ("pre_page") int
            prePage, @Path("page") int page);
    /**
     * 分类数据: http://gank.io/api/data/福利/请求个数/第几页
     * 数据类型： 福利
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/福利/10/1
     */
    @GET("/api/data/福利/{pre_page}/{page}")
    Observable<GankIoWelfareList> getGankIoWelfareList(@Path("pre_page") int prePage,
                                                       @Path("page") int page);
}
