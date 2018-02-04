package com.kingsley.androidnews.gankio;

import android.util.Log;

import com.kingsley.androidnews.base.BasePresenterImpl;
import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;

import io.reactivex.disposables.Disposable;

/**
 * class name : GanKIoPresenter
 * created date : on 2018/1/22 02:38
 *
 * @author Kingsley
 * @version 1.0
 */

public class GanKIoPresenter extends BasePresenterImpl<GanKIoModel,GanKIoContract.View,GankIoAllDate.Results> implements GanKIoContract.Presenter {
    private static final String TAG = "GanKIoPresenter";
    private Disposable disposable;

    private int mCurrPage = 0;
    public GanKIoPresenter (){
        setModel(new GanKIoModel(this));
    }

    @Override
    protected void onAttach() {

    }

    @Override
    public void loadNextPage() {
        Log.d(TAG, "loadNextPage: ");
        getData(getView().getCustomType(), ++mCurrPage, false);
    }

    @Override
    public void refreshList() {
        Log.d(TAG, "refreshList: ");
        mCurrPage = 1;
        getData(getView().getCustomType(),mCurrPage,true);
    }

    @Override
    public void cancelLoadList() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void getData(String type, int page, boolean clearOld){
        Log.d(type, getModel().toString());
        disposable = getModel().getCustomGankIoList(type, 10, page,clearOld);
        addDisposable(disposable);
    }

}
