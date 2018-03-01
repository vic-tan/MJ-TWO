package com.tlfmj.bao.sampletwo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tlfmj.bao.sampletwo.R;
import com.tlfmj.bao.sampletwo.activity.AboutActivity;
import com.tlfmj.bao.sampletwo.activity.FeedbackActivity;
import com.tlfmj.bao.sampletwo.activity.LocalWebViewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 今天
 */
public class FourFragment extends Fragment {

    public static final String TAG = FourFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_four, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.rl_advice_rep, R.id.rl_exit,R.id.rl_about_us,R.id.rl_help_book})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_advice_rep:
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_exit:
                getActivity().finish();
                break;
            case R.id.rl_help_book:
                Intent intent2 = new Intent(getActivity(), LocalWebViewActivity.class);
                intent2.putExtra("title","玩法");
                intent2.putExtra("name","VIPDesc.html");
                startActivity(intent2);
                break;
            case R.id.rl_about_us:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            default:
                break;
        }
    }


}
