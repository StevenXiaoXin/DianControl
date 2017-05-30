package com.daqianjietong.diancontrol.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daqianjietong.diancontrol.BaseActivity;
import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.adapter.ParkListAdapter;
import com.daqianjietong.diancontrol.adapter.QuickListAdapter;
import com.daqianjietong.diancontrol.bean.ParkListInfo;
import com.daqianjietong.diancontrol.bean.QuickListBean;
import com.daqianjietong.diancontrol.utils.Api;
import com.daqianjietong.diancontrol.utils.HttpUtil;
import com.daqianjietong.diancontrol.utils.JumpActivityUtils;
import com.daqianjietong.diancontrol.utils.SharedPreferencesUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ContentView(R.layout.activity_receipt)
public class ReceiptActivity extends BaseActivity {

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.quick_list)
    PullToRefreshListView pullToRefreshListView;

    private List<QuickListBean.Data> partEntities=new ArrayList<>();
    private QuickListAdapter mQuickListAdapter;
    private Activity act;
    private Context context;
    private int pageIndex = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context = this;
        initView();
        initData();
    }


    private void initView() {
        parkingIndex((String) SharedPreferencesUtil.getData(context,"parkid",""));
    }

    private void initData() {

        mQuickListAdapter=new QuickListAdapter(context,partEntities);
        pullToRefreshListView.setAdapter(mQuickListAdapter);
        tv_title.setText("临时停车");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpActivityUtils.Jump2Activity(act,MainActivity.class);
                act.finish();
            }
        });


        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode().equals(PullToRefreshBase.Mode.PULL_FROM_START)) {
                    parkingIndex( (String) SharedPreferencesUtil.getData(context,"parkid",""));
                } else if (refreshView.getCurrentMode().equals(PullToRefreshBase.Mode.PULL_FROM_END)) {
                    parkingIndex((String)SharedPreferencesUtil.getData(context,"parkid",""));
                }

            }
        });
    }

    public void parkingIndex(String txt_parknum) {
        showDialog();
        Api.getInstance().leaveQuick(txt_parknum, new HttpUtil.URLListenter<QuickListBean>() {
            @Override
            public void onsucess(QuickListBean quickListBean) throws Exception {
                dissDialog();
                pullToRefreshListView.onRefreshComplete();
                if (quickListBean.getData().size()>=1) {
                    for (int i = 0; i < quickListBean.getData().size(); i++) {
                        partEntities.add(quickListBean.getData().get(i));
                    }
                } else {
                    Toast.makeText(act,"没有更多数据",Toast.LENGTH_SHORT).show();
                }
                mQuickListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onfaild(String error) {
                dissDialog();
                Toast.makeText(act,error,Toast.LENGTH_SHORT).show();
                pullToRefreshListView.onRefreshComplete();
            }
        },context);

    }

    @Override
    public void onBackPressed() {
        JumpActivityUtils.Jump2Activity(act,MainActivity.class);
        act.finish();
    }
}
