package com.kingsley.androidnews.wanandroid;

import com.kingsley.androidnews.contract.BaseContract;
import com.kingsley.androidnews.model.bean.gankio.GankIoCustomItem;

import io.reactivex.disposables.Disposable;

/**
 * class name : WanAndroidContract
 * created date : on 2018/1/22 02:34
 *
 * @author Kingsley
 * @version 1.0
 */

public interface WanAndroidContract{
    interface Type{
        String HOME_PAGE = "首页";
        String THE_KNOWLEDGE_SYSTEM = "知识体系";
        String USEFUL_SITES = "常用网站";

    }

    interface View extends BaseContract.View<GankIoCustomItem>{

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
