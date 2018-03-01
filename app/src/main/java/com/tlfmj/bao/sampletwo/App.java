package com.tlfmj.bao.sampletwo;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;

import com.tlfmj.bao.sampletwo.bean.CaiPiaoBean;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Dmytro Denysenko on 5/6/15.
 */
public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;
    public static List<CaiPiaoBean> list;

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        list = new ArrayList<>();
        initTypeface();
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
