package com.daqianjietong.diancontrol.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daqianjietong.diancontrol.BaseActivity;
import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.adapter.OrderListAdapter;
import com.daqianjietong.diancontrol.bean.OrderInfo;
import com.daqianjietong.diancontrol.utils.Api;
import com.daqianjietong.diancontrol.utils.HttpUtil;
import com.daqianjietong.diancontrol.utils.JumpActivityUtils;
import com.daqianjietong.diancontrol.utils.SharedPreferencesUtil;
import com.daqianjietong.diancontrol.utils.ToosUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ContentView(R.layout.activity_order_park)
public class OrderParkActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.order_list)
    PullToRefreshListView pullToRefreshListView;
    private List<OrderInfo.Data> partEntities=new ArrayList<>();
    private OrderListAdapter mOrderListAdapter;

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
        parkingIndex(1,(String)SharedPreferencesUtil.getData(context,"parkid",""),null);

    }

    private void initData() {
        mOrderListAdapter=new OrderListAdapter(context,partEntities);
        pullToRefreshListView.setAdapter(mOrderListAdapter);
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
                    parkingIndex(1, (String)SharedPreferencesUtil.getData(context,"parkid",1),null);
                } else if (refreshView.getCurrentMode().equals(PullToRefreshBase.Mode.PULL_FROM_END)) {
                    parkingIndex(pageIndex + 1, (String)SharedPreferencesUtil.getData(context,"parkid",""),null);
                }

            }
        });

    }

    public void parkingIndex(final int pageNo, String txt_parkid,String txt_parknum) {

        Api.getInstance().getOrderList(txt_parkid, String.valueOf(pageNo), txt_parknum, new HttpUtil.URLListenter<OrderInfo>() {
            @Override
            public void onsucess(OrderInfo orderInfo) throws Exception {
                Log.e("dsadasdas","zouzhel");
                pullToRefreshListView.onRefreshComplete();
                if (pageNo == 1) {
                    partEntities.clear();
                    mOrderListAdapter.notifyDataSetChanged();
                }
                pageIndex=pageNo;
                if (partEntities!=null){
                    for (OrderInfo.Data partEntity:partEntities){
                        partEntities.add(partEntity);
                    }
                }
                mOrderListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onfaild(String error) {
                Log.e("-------","zouzhel");
                Toast.makeText(act,error,Toast.LENGTH_SHORT).show();
                pullToRefreshListView.onRefreshComplete();
            }
        });

    }

    @Override
    public void onBackPressed() {
        JumpActivityUtils.Jump2Activity(act,MainActivity.class);
        act.finish();
    }
}

