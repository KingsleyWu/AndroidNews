package com.kingsley.androidnews.wanandroid;

import com.kingsley.androidnews.base.BasePresenterImpl;
import com.kingsley.androidnews.gankio.GanKIoContract;
import com.kingsley.androidnews.gankio.GanKIoModel;
import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;

import io.reactivex.disposables.Disposable;

/**
 * class name : WanAndroidPresenter
 * created date : on 2018/1/22 02:38
 *
 * @author Kingsley
 * @version 1.0
 */

public class WanAndroidPresenter extends BasePresenterImpl<GanKIoModel, GanKIoContract.View,GankIoAllDate.Results> implements GanKIoContract.Presenter {

    private Disposable disposable;

    private int mCurrPage = 0;

    @Override
    protected void onAttach() {
        super.onAttach();
        setModel(GanKIoModel.newInstance(this));
    }

    @Override
    public void loadNextPage() {
        getData(getView().getCustomType(), mCurrPage++,false);
    }

    @Override
    public void refreshList() {
        mCurrPage = 1;
        getData(getView().getCustomType(),mCurrPage,true);
    }

    @Override
    public void cancelLoadList() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void getData(String type, int page,boolean clearOld){
        disposable = getModel().getCustomGankIoList(type, 20, page,clearOld);
        addDisposable(disposable);
    }

}
