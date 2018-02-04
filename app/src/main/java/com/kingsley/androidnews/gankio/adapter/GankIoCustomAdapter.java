package com.kingsley.androidnews.gankio.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kingsley.androidnews.R;
import com.kingsley.androidnews.base.GlideApp;
import com.kingsley.androidnews.contract.Config;
import com.kingsley.androidnews.model.bean.gankio.GankIoAllDate;
import com.orhanobut.logger.Logger;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Horrarndoo on 2017/10/11.
 * <p>
 */

public class GankIoCustomAdapter extends BaseMultiItemQuickAdapter<GankIoAllDate.Results, BaseViewHolder> {
    private String mImageSize = "?imageView2/0/w/200";

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GankIoCustomAdapter(List<GankIoAllDate.Results> data) {
        super(data);
        setEnableLoadMore(true);
        openLoadAnimation();
        addItemType(GankIoAllDate.Results.GANK_IO_ITEM_CUSTOM, R.layout.item_gankio_custom);
        addItemType(GankIoAllDate.Results.GANK_IO_ITEM_WELFARE, R.layout.item_gankio_image);
        addItemType(GankIoAllDate.Results.GANK_IO_ITEM_CUSTOM_NO_IMAGE, R.layout.item_gankio_custom);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoAllDate.Results item) {
        int itemViewType = helper.getItemViewType();
        GifImageView imageView = helper.getView(R.id.iv_item_image);
        switch (itemViewType) {
            case GankIoAllDate.Results.GANK_IO_ITEM_CUSTOM:
                initView(helper, item);
                if (item.getImages() != null) {
                    if (item.getImages().size() > 0 && !TextUtils.isEmpty(item.getImages().get(0))) {
                        GlideApp.with(mContext)
                                .asDrawable()
                                .load(item.getImages().get(0) + mImageSize)
                                .into(imageView);
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
                Logger.w("ANK_IO_ITEM_CUSTOM", item.getType());
                break;
            case GankIoAllDate.Results.GANK_IO_ITEM_WELFARE:
                if (!TextUtils.isEmpty(item.getUrl())) {
                    GlideApp.with(mContext)
                            //.asBitmap()
                            .load(item.getUrl() + mImageSize)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView);
                    imageView.setVisibility(View.VISIBLE);
                }
                Logger.w("GANK_IO_ITEM_WELFARE", item.getType());
                break;
            case GankIoAllDate.Results.GANK_IO_ITEM_CUSTOM_NO_IMAGE:
                initView(helper, item);
                imageView.setVisibility(View.GONE);
                Logger.w("GANK_IO_ITEM_CUSTOM_NO_IMAGE", item.getType());
                break;
            default:
                break;
        }

    }

    private void initView(BaseViewHolder helper, GankIoAllDate.Results item) {
        initTypeTextView(helper, item);
        helper.setText(R.id.tv_item_Desc, item.getDesc());
        String author = item.getWho();
        if (!TextUtils.isEmpty(author)) {
            helper.setText(R.id.tv_item_author, item.getWho());
            helper.setVisible(R.id.tv_item_author, true);
        } else {
            helper.setVisible(R.id.tv_item_author, false);
        }
        helper.setText(R.id.tv_item_time, item.getCreatedAt().substring(0, 10));
    }

    private void initTypeTextView(BaseViewHolder helper, GankIoAllDate.Results item) {
        TextView tvType = helper.getView(R.id.tv_item_type);
        String type = item.getType();
        tvType.setText(type);
        Drawable typeDrawable = null;
        typeDrawable = getDrawable(R.drawable.ic_vector_item_all);
        switch (type) {
            case Config.TYPE_ANDROID:
                typeDrawable = getDrawable(R.drawable.ic_vector_title_android);
                break;
            case Config.TYPE_IOS:
                typeDrawable = getDrawable(R.drawable.ic_vector_title_ios);
                break;
            case Config.TYPE_WEB:
                typeDrawable = getDrawable(R.drawable.ic_vector_title_web);
                break;
            case Config.TYPE_EXPAND_THE_RESOURCES:
                typeDrawable = getDrawable(R.drawable.ic_vector_item_expand_the_resources);
                break;
            case Config.TYPE_APP:
                typeDrawable = getDrawable(R.drawable.ic_vector_item_app);
                break;
            case Config.TYPE_BLIND_TO_RECOMMEND:
                typeDrawable = getDrawable(R.drawable.ic_vector_item_blind_to_recommend);
                break;
            case Config.TYPE_REST_VIDEO:
                typeDrawable = getDrawable(R.drawable.ic_vector_title_video);
                break;
            case Config.TYPE_WELFARE:
                typeDrawable = getDrawable(R.drawable.ic_vector_title_welfare);
                break;
            default:
                break;
        }
        if (typeDrawable != null) {
            typeDrawable.setBounds(0, 0, typeDrawable.getIntrinsicWidth(), typeDrawable.getIntrinsicHeight());
            tvType.setCompoundDrawables(typeDrawable, null, null, null);
        }
    }

    private Drawable getDrawable(int drawableId) {
        Drawable typeDrawable;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            typeDrawable = mContext.getDrawable(drawableId);
        } else {
            typeDrawable = mContext.getResources().getDrawable(drawableId);
        }
        return typeDrawable;
    }

}
