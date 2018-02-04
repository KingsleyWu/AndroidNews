package com.kingsley.androidnews.callback;

/**
 * class name : OnDataAcceptListener
 * created date : on 2018/1/25 17:29
 *
 * @author Kingsley
 * @version 1.0
 */

public interface OnDataAcceptListener<M> {

    /**
     * 获取数据完成
     */
    void onGetDataComplete();

    /**
     * 获取数据成功
     *
     * @param data     数据
     * @param clearOld 是否清理数据
     */
    void onGetDataSuccess(M data, boolean clearOld);

    /**
     * 获取数据失败
     *
     * @param errMessage 错误信息
     */
    void onGetDataFail(String errMessage);
}
