package com.kingsley.androidnews.contract;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.util.List;

import io.reactivex.Observable;

/**
 * class name : BaseContract
 * created date : on 2017/12/19 20:00
 *
 * @author Kingsley
 * @version 1.0
 */

public interface BaseContract {

    interface Model {

        /**
         * 记录item已阅到数据库
         * @param key key(item.id值作为key)
         */
        Observable<Boolean> recordItemIsRead(String key);
    }

    interface View<D> {

        /**
         * 隐藏loading
         */
        void hideLoading();

        /**
         * 隐藏LoadMore
         */
        void hideLoadMore();

        /**
         * 显示错误信息
         * @param errorMsg 错误信息
         */
        void showErrorMsg(String errorMsg);

        /**
         * 展示文章列表
         * @param datas 获取到的文章数据
         * @param clearOld 是否清除旧数据
         */
        void displayItem(List<D> datas, boolean clearOld);
    }

    interface Presenter<V> {
        void attach(@NonNull V v);

        void detach();

        V getView();

        boolean isViewAttached();
    }

    interface LceView<M> extends View{
        /**
         * Display a loading view while loading data in background.
         * <b>The loading view must have the id = R.id.loadingView</b>
         *
         * @param pullToRefresh true, if pull-to-refresh has been invoked loading.
         */
        @UiThread
        void showLoading(boolean pullToRefresh);

        /**
         * Show the content view.
         *
         * <b>The content view must have the id = R.id.contentView</b>
         */
        @UiThread
        void showContent();

        /**
         * Show the error view.
         * <b>The error view must be a TextView with the id = R.id.errorView</b>
         *
         * @param e The Throwable that has caused this error
         * @param pullToRefresh true, if the exception was thrown during pull-to-refresh, otherwise
         * false.
         */
        @UiThread
        void showError(Throwable e, boolean pullToRefresh);

        /**
         * The data that should be displayed with {@link #showContent()}
         */
        @UiThread
        void setData(M data);

        /**
         * Load the data. Typically invokes the presenter method to load the desired data.
         * <p>
         * <b>Should not be called from presenter</b> to prevent infinity loops. The method is declared
         * in
         * the views interface to add support for view state easily.
         * </p>
         *
         * @param pullToRefresh true, if triggered by a pull to refresh. Otherwise false.
         */
        @UiThread
        void loadData(boolean pullToRefresh);
    }
}
