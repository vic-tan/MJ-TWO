package com.tlfmj.bao.sampletwo.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tlfmj.bao.sampletwo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 关于
 */
public class AboutActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.version_name)
    TextView version_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_activity_about);
        ButterKnife.bind(this);
        version_name.setText(getString(R.string.app_name) + "V" + getVersionName());
        this.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * @return String 返回类型
     * @Title: getVersionName
     * @Description: 获得apk getVersionName
     * 上下文对象
     */
    public String getVersionName() {
        String versionCode = null;
        if (this == null) {
            return versionCode;
        }
        PackageManager pm = this.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            return versionCode;
        }
    }

}