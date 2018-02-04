package com.kingsley.androidnews.gankio;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.kingsley.androidnews.api.RetrofitClient;
import com.kingsley.androidnews.callback.OnDataAcceptListener;
import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * class name : GanKIoModel
 * created date : on 2018/1/25 11:25
 *
 * @author Kingsley
 * @version 1.0
 */

public class GanKIoModel implements GanKIoContract.Model {

    private OnDataAcceptListener<List<GankIoAllDate.Results>> mOnDataAcceptListener;

    public GanKIoModel(OnDataAcceptListener<List<GankIoAllDate.Results>> onDataAcceptListener){
        mOnDataAcceptListener = onDataAcceptListener;
    }

    @NonNull
    public static GanKIoModel newInstance(OnDataAcceptListener<List<GankIoAllDate.Results>> onDataAcceptListener) {
        return new GanKIoModel(onDataAcceptListener);
    }

    public String[] getTabs() {
        return new String[]{"Android","iOS","App","拓展资源", "前端","瞎推荐","福利","休息视频"};
    }

    @Override
    public Observable<Boolean> recordItemIsRead(String key) {
        return null;
    }


    @Override
    public Disposable getCustomGankIoList(String type, int prePage, int page, final boolean clearOld) {
        return RetrofitClient.getGankIoApiService()
                .getGankIoCustomList(type, prePage, page)
                .map(new Function<GankIoAllDate, List<GankIoAllDate.Results>>() {
                    @Override
                    public List<GankIoAllDate.Results> apply(GankIoAllDate gankIoCustomList) throws Exception {
                        for (GankIoAllDate.Results bean : gankIoCustomList.getResults()) {
                            if (bean.getType().equals("福利")) {
                                bean.itemType = GankIoAllDate.Results.GANK_IO_ITEM_WELFARE;
                            } else if (bean.getImages() != null) {
                                if (bean.getImages().size() > 0 && !TextUtils.isEmpty(bean
                                        .getImages().get(0))) {
                                    bean.itemType = GankIoAllDate.Results.GANK_IO_ITEM_CUSTOM;
                                }
                            } else {
                                bean.itemType = GankIoAllDate.Results.GANK_IO_ITEM_CUSTOM_NO_IMAGE;
                            }
                        }
                        return gankIoCustomList.getResults();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mOnDataAcceptListener != null) {
                            mOnDataAcceptListener.onGetDataComplete();
                        }
                    }
                })
                .subscribe(new Consumer<List<GankIoAllDate.Results>>() {
                    @Override
                    public void accept(List<GankIoAllDate.Results> gankIoCustomItems) throws Exception {
                        if (mOnDataAcceptListener != null) {
                            mOnDataAcceptListener.onGetDataSuccess(gankIoCustomItems,clearOld);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mOnDataAcceptListener != null) {
                            mOnDataAcceptListener.onGetDataFail(throwable.getMessage());
                        }
                    }
                });
    }

    @Override
    public String toString() {
        return "GanKIoModel{" +
                "mOnDataAcceptListener=" + mOnDataAcceptListener +
                '}';
    }
}
