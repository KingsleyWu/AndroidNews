package com.kingsley.androidnews.gankio;

import com.kingsley.androidnews.contract.BaseContract;
import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;

import io.reactivex.disposables.Disposable;

/**
 * class name : GanKIoContract
 * created date : on 2018/1/22 02:34
 *
 * @author Kingsley
 * @version 1.0
 */

public interface GanKIoContract{
    interface Type {
        String ALL = "all";
        String ANDROID = "Android";
        String IOS = "iOS";
        String REST_VIDEO = "休息视频";
        String WELFARE = "福利";
        String EXPAND_THE_RESOURCES = "拓展资源";
        String WEB = "前端";
        String BLIND_TO_RECOMMEND = "瞎推荐";
        String APP = "App";
        //all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
    }

    interface View extends BaseContract.View<GankIoAllDate.Results>{

        /**
         * 返回定制消息类型
         *
         * @return 定制消息类型 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
         */
        String getCustomType();
    }

    interface Model extends BaseContract.Model{
        /**
         * 请求GankIo每日数据list
         *
         * @param type    type 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
         * @param prePage 请求个数： 数字，大于0
         * @param page    请求第几页：数字，大于0
         * @param clearOld 是否清楚数据
         * @return Observable
         */
        Disposable getCustomGankIoList(String type, int prePage, int page,boolean clearOld);
    }

    interface Presenter {

        /**
         * 加载下一页数据
         */
        void loadNextPage();

        /**
         * 刷新所有数据，清除旧有数据
         */
        void refreshList();

        /**
         * 取消加载数据
         */
        void cancelLoadList();
    }
}
