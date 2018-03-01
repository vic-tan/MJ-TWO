package com.tlfmj.bao.sampletwo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mj.utils.bean.BaseJson;
import com.mj.utils.call.ResultCallback;
import com.mj.utils.tools.FastjsonUtils;
import com.tlf.basic.base.adapter.abslistview.AbsCommonAdapter;
import com.tlf.basic.base.adapter.abslistview.AbsViewHolder;
import com.tlf.basic.http.okhttp.OkHttpUtils;
import com.tlfmj.bao.sampletwo.App;
import com.tlfmj.bao.sampletwo.R;
import com.tlfmj.bao.sampletwo.activity.LiSiActivity;
import com.tlfmj.bao.sampletwo.bean.CaiPiaoBean;
import com.tlfmj.bao.sampletwo.bean.LiSiBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;


/**
 * 今天
 */
public class ThreeFragment extends Fragment {

    public static final String TAG = ThreeFragment.class.getSimpleName();
    private static final String url = "http://m.cp.360.cn/kaijiang/qkjlist?lotId=";
    @BindView(R.id.list)
    ListView listView;
    AbsCommonAdapter<CaiPiaoBean> adapter;
    List<CaiPiaoBean> list;
    List<LiSiBean> liSiList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_three, container, false);
        ButterKnife.bind(this, view);
        list = new ArrayList<>();
        liSiList = new ArrayList<>();
        if (null != App.list && list.size() >= 0) {
            list.addAll(App.list);
        }
        adapter = new AbsCommonAdapter<CaiPiaoBean>(getActivity(), R.layout.fragment_lisi_list_item, list) {
            @Override
            protected void convert(AbsViewHolder holder, final CaiPiaoBean bean, int position) {
                holder.setText(R.id.name, bean.getLotName());
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OkHttpUtils.get().url(url + bean.getTag_id() + "&page=1").build().execute(new ResultCallback(getActivity()) {
                            @Override
                            public void onCusResponse(BaseJson response) {
                            }

                            @Override
                            public BaseJson parseNetworkResponse(Response response) throws Exception {
                                String string = response.body().string();
                                BaseJson jsonBean = new BaseJson();
                                try {
                                    liSiList.clear();
                                    String listStr = FastjsonUtils.getKeyResult(string, "list");
                                    List<LiSiBean> jsonList = FastjsonUtils.parseToObjectList(listStr, LiSiBean.class);
                                    if (null != jsonList && jsonList.size() >= 0) {
                                        liSiList.addAll(jsonList);
                                    }
                                } catch (Exception var5) {
                                    var5.printStackTrace();
                                }
                                return jsonBean;
                            }

                            @Override
                            public void onResponse(BaseJson response) {
                                Intent intent = new Intent(getActivity(), LiSiActivity.class);
                                intent.putExtra("list", (Serializable) liSiList);
                                intent.putExtra("bean", (Serializable) bean);
                                getActivity().startActivity(intent);
                            }
                        });
                    }
                });
            }
        };
        listView.setAdapter(adapter);
        return view;
    }


}
