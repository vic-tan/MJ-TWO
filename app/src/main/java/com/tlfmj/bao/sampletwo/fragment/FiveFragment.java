package com.tlfmj.bao.sampletwo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tlfmj.bao.sampletwo.R;

import butterknife.ButterKnife;


/**
 * 我的
 */
public class FiveFragment extends Fragment {
    public static final String TAG = FiveFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_five, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
