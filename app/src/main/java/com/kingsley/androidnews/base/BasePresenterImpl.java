package com.kingsley.androidnews.base;

import com.kingsley.androidnews.callback.OnDataAcceptListener;
import com.kingsley.androidnews.contract.BaseContract;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 将Presenter的一些公共操作抽取到这里
 * <p>
 * 负责RxJava的解绑以及View的公共操作
 * </p>
 * @author Kingsley
 */
public abstract class BasePresenterImpl<M extends BaseContract.Model,V extends BaseContract.View<D> ,D> extends BasePresenter<M,V>implements OnDataAcceptListener<List<D>> {

    private static final String TAG = "BasePresenterImpl";

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Override
    public boolean isViewAttached() {
        return getView() != null;
    }

    @Override
    protected void onAttach() {

    }

    @Override
    protected void onDetach() {
        dispose();
    }

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            synchronized (this) {
                if (mCompositeDisposable == null) {
                    mCompositeDisposable = new CompositeDisposable();
                }
            }
        }
        mCompositeDisposable.add(disposable);
    }

    private void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    public void onGetDataComplete() {
        if (isViewAttached()){
            getView().hideLoading();
        }
    }

    @Override
    public void onGetDataSuccess(List<D> datas,boolean clearOld) {
        if (isViewAttached()){
            getView().displayItem(datas, clearOld);
        }
    }

    @Override
    public void onGetDataFail(String errMessage) {
        if (isViewAttached()){
            getView().showErrorMsg(errMessage);
        }

    }
}
