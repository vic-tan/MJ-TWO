package com.tlfmj.bao.sampletwo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.tlfmj.bao.sampletwo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 查看vip特权
 * Created by hth on 2016/5/31.
 */
public class LocalWebViewActivity extends AppCompatActivity {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView titleView;
    private String title;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_activity_html);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("title");
        name = getIntent().getStringExtra("name");
        titleView.setText(title);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initwidget();
    }

    @SuppressWarnings("deprecation")
    private void initwidget() {
        WebSettings ws = webView.getSettings();
        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSavePassword(true);
        ws.setJavaScriptEnabled(true);
        ws.setUseWideViewPort(true);// 可任意比例缩放
        webView.setWebViewClient(new xWebViewClientent());
        webView.loadUrl("file:///android_asset/" + name);
    }

    /**
     * 处理各种通知、请求等事件
     *
     * @author
     */
    public class xWebViewClientent extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }


}
