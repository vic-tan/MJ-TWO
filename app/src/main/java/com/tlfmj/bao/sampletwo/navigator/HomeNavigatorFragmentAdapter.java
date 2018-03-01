package com.tlfmj.bao.sampletwo.navigator;

import android.support.v4.app.Fragment;

import com.tlfmj.bao.sampletwo.fragment.FiveFragment;
import com.tlfmj.bao.sampletwo.fragment.FourFragment;
import com.tlfmj.bao.sampletwo.fragment.OneFragment;
import com.tlfmj.bao.sampletwo.fragment.ThreeFragment;
import com.tlfmj.bao.sampletwo.fragment.TwoFragment;


/**
 * 首页底部适配器
 * Created by aspsine on 16/3/31.
 */
public class HomeNavigatorFragmentAdapter implements FragmentNavigatorAdapter {


    @Override
    public Fragment onCreateFragment(int position) {
        if (position == 0) {
            return new OneFragment();
        } else if (position == 1) {
            return new TwoFragment();
        } else if (position == 2) {
            return new ThreeFragment();
        } else if (position == 3) {
            return new FourFragment();
        } else if (position == 4) {
            return new FiveFragment();
        }
        return new OneFragment();
    }


    @Override
    public String getTag(int position) {
        if (position == 0) {
            return OneFragment.TAG;
        } else if (position == 1) {
            return TwoFragment.TAG;
        } else if (position == 2) {
            return ThreeFragment.TAG;
        } else if (position == 3) {
            return FourFragment.TAG;
        } else if (position == 4) {
            return FiveFragment.TAG;
        }
        return OneFragment.TAG;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
