package com.tlfmj.bao.sampletwo.navigator;

import android.support.v4.app.Fragment;

import com.mj.utils.fragment.cp.HistoryFragment;
import com.mj.utils.fragment.cp.NewsFragment;
import com.mj.utils.fragment.cp.SettingFragment;
import com.mj.utils.fragment.cp.TrendFragment;


/**
 * 首页底部适配器
 * Created by aspsine on 16/3/31.
 */
public class HomeNavigatorFragmentAdapter implements FragmentNavigatorAdapter {


    @Override
    public Fragment onCreateFragment(int position) {
        if (position == 0) {
            return new TrendFragment();
        } else if (position == 1) {
            return new NewsFragment();
        } else if (position == 2) {
            return new HistoryFragment();
        } else if (position == 3) {
            return new SettingFragment();
        }
        return new TrendFragment();
    }


    @Override
    public String getTag(int position) {
        if (position == 0) {
            return TrendFragment.TAG;
        } else if (position == 1) {
            return NewsFragment.TAG;
        } else if (position == 2) {
            return HistoryFragment.TAG;
        } else if (position == 3) {
            return SettingFragment.TAG;
        }
        return TrendFragment.TAG;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
