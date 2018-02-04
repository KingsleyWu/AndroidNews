package com.kingsley.androidnews.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kingsley.androidnews.R;
import com.kingsley.androidnews.base.BaseMVPFragment;
import com.kingsley.androidnews.contract.Config;
import com.kingsley.androidnews.gankio.GanKIoContract;
import com.kingsley.androidnews.gankio.GanKIoPresenter;
import com.kingsley.androidnews.gankio.adapter.GankIoCustomAdapter;
import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;
import com.kingsley.androidnews.utils.ToastUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GankIoItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GankIoItemFragment extends BaseMVPFragment<GanKIoContract.View, GanKIoPresenter> implements GanKIoContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "GankIoItemFragment";
    private static final String FRAGMENT_TYPE = "mType";
    private String mType;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    GankIoCustomAdapter mAdapter;

    public static GankIoItemFragment newInstance(String type) {
        Logger.w(type);
        GankIoItemFragment fragment = new GankIoItemFragment();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(FRAGMENT_TYPE);
        }
    }

    @Override
    protected int setFragmentLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mRecyclerView.setAdapter(getAdapter());
        if (getString(R.string.welfare).equals(mType)) {
            mRecyclerView.setLayoutManager(
                    new StaggeredGridLayoutManager(2,
                            StaggeredGridLayoutManager.VERTICAL));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GankIoAllDate.Results results = mAdapter.getData().get(position);
                Intent intent = new Intent(mContext,BaseWebViewActivity.class);
                intent.putExtra(Config.URL,results.getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadNextPage();
        Log.d(TAG, "onLazyInitView: " + mType);
    }

    private GankIoCustomAdapter getAdapter() {
        mAdapter = new GankIoCustomAdapter(null);
        return mAdapter;
    }

    @Override
    protected GanKIoPresenter bindPresenter() {
        return new GanKIoPresenter();
    }

    @Override
    protected GanKIoContract.View bindView() {
        return this;
    }

    @Override
    public void hideLoading() {}

    @Override
    public void hideLoadMore() {
        mAdapter.loadMoreComplete();
        Log.d(TAG, "hideLoadMore: ");
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        mAdapter.loadMoreFail();
        ToastUtils.showToast(errorMsg);
    }

    @Override
    public void displayItem(List<GankIoAllDate.Results> datas, boolean clearOld) {
        Log.d(TAG, "displayItem: "+mAdapter.getData().size() + "  datas = "+datas.size() + "  is clear old = " +clearOld);
        if (clearOld) {
            mAdapter.replaceData(datas);
        }else {
            mAdapter.addData(datas);
        }
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

    @Override
    public String getCustomType() {
        return mType;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadNextPage();
        Log.d(TAG, "onLoadMoreRequested: ");
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshList();
    }
}
