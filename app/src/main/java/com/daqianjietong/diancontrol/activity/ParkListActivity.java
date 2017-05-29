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
import com.daqianjietong.diancontrol.bean.ParkListInfo;
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
@ContentView(R.layout.activity_park_list)
public class ParkListActivity extends BaseActivity {

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.park_list)
    PullToRefreshListView pullToRefreshListView;

    private List<ParkListInfo.Data> partEntities=new ArrayList<>();
    private ParkListAdapter mParkListAdapter;
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
        parkingIndex(1,(String)SharedPreferencesUtil.getData(context,"parkid",""),"");
    }

    private void initData() {

        mParkListAdapter=new ParkListAdapter(context,partEntities);
        pullToRefreshListView.setAdapter(mParkListAdapter);
        tv_title.setText("车位列表");
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
                    parkingIndex(1, (String) SharedPreferencesUtil.getData(context,"parkid",""),"");
                } else if (refreshView.getCurrentMode().equals(PullToRefreshBase.Mode.PULL_FROM_END)) {
                    parkingIndex(pageIndex + 1, (String)SharedPreferencesUtil.getData(context,"parkid",""),"");
                }

            }
        });
    }


    public void parkingIndex(final int pageNo, String txt_parkid,String txt_parknum) {
        showDialog();
        Api.getInstance().getParkList(txt_parkid, String.valueOf(pageNo), txt_parknum, new HttpUtil.URLListenter<ParkListInfo>() {
            @Override
            public void onsucess(ParkListInfo parkListInfo) throws Exception {
                dissDialog();

                pullToRefreshListView.onRefreshComplete();
                if (pageNo == 1) {
                    partEntities.clear();
                    mParkListAdapter.notifyDataSetChanged();
                }
                pageIndex=pageNo;
                if (parkListInfo.getData().size()>=1) {
                    for (int i = 0; i < parkListInfo.getData().size(); i++) {
                        partEntities.add(parkListInfo.getData().get(i));
                    }
                } else {
                    Toast.makeText(act,"没有更多数据",Toast.LENGTH_SHORT).show();
                }
                mParkListAdapter.notifyDataSetChanged();
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
