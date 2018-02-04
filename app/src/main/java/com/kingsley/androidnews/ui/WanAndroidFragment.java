package com.kingsley.androidnews.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingsley.androidnews.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Kingsley
 */
public class WanAndroidFragment extends SupportFragment {

    public static WanAndroidFragment newInstance() {
        WanAndroidFragment fragment = new WanAndroidFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

}
