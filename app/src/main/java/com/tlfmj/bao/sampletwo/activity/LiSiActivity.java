package com.tlfmj.bao.sampletwo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mj.utils.bean.BaseJson;
import com.mj.utils.call.ResultCallback;
import com.mj.utils.tools.FastjsonUtils;
import com.tlf.basic.base.adapter.abslistview.AbsCommonAdapter;
import com.tlf.basic.base.adapter.abslistview.AbsViewHolder;
import com.tlf.basic.http.okhttp.OkHttpUtils;
import com.tlf.basic.uikit.linearlistview.LinearListView;
import com.tlf.basic.uikit.roundview.RoundTextView;
import com.tlf.basic.uikit.roundview.RoundViewDelegate;
import com.tlfmj.bao.sampletwo.R;
import com.tlfmj.bao.sampletwo.bean.CaiPiaoBean;
import com.tlfmj.bao.sampletwo.bean.DeitalBean;
import com.tlfmj.bao.sampletwo.bean.LiSiBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class LiSiActivity extends AppCompatActivity {
    @BindView(R.id.list)
    ListView listView;
    AbsCommonAdapter<LiSiBean> adapter;
    List<LiSiBean> list;
    CaiPiaoBean bean;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisi);
        ButterKnife.bind(this);
        list = (List<LiSiBean>) getIntent().getSerializableExtra("list");
        bean = (CaiPiaoBean) getIntent().getSerializableExtra("bean");
        title.setText(bean.getLotName());
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new AbsCommonAdapter<LiSiBean>(this, R.layout.lisi_list_item, list) {
            @Override
            protected void convert(AbsViewHolder holder, final LiSiBean item, int position) {
                holder.setText(R.id.date, "开奖日期：" + item.getEndTime().substring(0, 10) + "");
                holder.setText(R.id.qishu, "第" + item.getIssue() + "期");
                LinearListView numListView = holder.getView(R.id.num_list);
                String[] split = item.getWinNumber().replace("+", " ").split(" ");
                final List<String> numList = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    String str = split[i];
                    numList.add(str);
                }
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id()) || "120029".equals(bean.getTag_id())) {
                            deital(item.getIssue());
                        }
                    }
                });
                AbsCommonAdapter adapter1 = new AbsCommonAdapter<String>(mContext, R.layout.fragment_num_list_item, numList) {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    protected void convert(AbsViewHolder holder, String s, int position) {
                        RoundTextView name = holder.getView(R.id.num);
                        RoundViewDelegate delegate = name.getDelegate();
                        name.setText(s);
                        if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id())) {
                            if (position >= numList.size() - 1) {
                                delegate.setBackgroundColor(mContext.getColor(R.color.blue));
                            } else {
                                delegate.setBackgroundColor(mContext.getColor(R.color.red));
                            }

                        } else if ("120029".equals(bean.getTag_id())) {
                            if (position >= numList.size() - 2) {
                                delegate.setBackgroundColor(mContext.getColor(R.color.blue));
                            } else {
                                delegate.setBackgroundColor(mContext.getColor(R.color.red));
                            }

                        } else {
                            delegate.setBackgroundColor(mContext.getColor(R.color.red));
                        }
                        holder.setOnClickListener(R.id.num, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ("220051".equals(bean.getTag_id()) || "220028".equals(bean.getTag_id()) || "120029".equals(bean.getTag_id())) {
                                    deital(item.getIssue());
                                }
                            }
                        });
                    }
                };
                numListView.setAdapter(adapter1);
            }
        };
        listView.setAdapter(adapter);

    }

    DeitalBean jsonList;

    public void deital(String issue) {
        OkHttpUtils.get().url("http://m.cp.360.cn/kaijiang/qkj?lotId=" + bean.getTag_id() + "&issue=" + issue).build().execute(new ResultCallback(this) {
            @Override
            public void onCusResponse(BaseJson response) {
            }

            @Override
            public BaseJson parseNetworkResponse(Response response) throws Exception {
                String string = response.body().string();
                BaseJson jsonBean = new BaseJson();
                try {
                    jsonList = FastjsonUtils.parseToObjectBean(string, DeitalBean.class);

                } catch (Exception var5) {
                    var5.printStackTrace();
                }
                return jsonBean;
            }

            @Override
            public void onResponse(BaseJson response) {
                Intent intent = new Intent(LiSiActivity.this, DetailActivity.class);
                intent.putExtra("bean", (Serializable) jsonList);
                startActivity(intent);
            }
        });
    }

}
