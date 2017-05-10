package com.daqianjietong.diancontrol.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daqianjietong.diancontrol.BaseActivity;
import com.daqianjietong.diancontrol.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 首页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.btn_order_park)
    private ImageButton btn_order_park;
    @ViewInject(R.id.btn_park_list)
    private ImageButton btn_park_list;
    @ViewInject(R.id.btn_quick_park)
    private ImageButton btn_quick_park;
    @ViewInject(R.id.btn_personal_center)
    private ImageButton btn_personal_center;
    @ViewInject(R.id.btn_receipt)
    private ImageButton btn_receipt;
    @ViewInject(R.id.tv_order_num)
    private TextView tv_order_num;
    @ViewInject(R.id.tv_list_num)
    private TextView tv_list_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initData() {
        iv_back.setVisibility(View.GONE);
        tv_title.setText(R.string.main);
        btn_order_park.setOnClickListener(this);
        btn_park_list.setOnClickListener(this);
        btn_quick_park.setOnClickListener(this);
        btn_personal_center.setOnClickListener(this);
        btn_receipt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_park:
                break;
            case R.id.btn_park_list:
                break;
            case R.id.btn_quick_park:
                break;
            case R.id.btn_personal_center:
                break;
            case R.id.btn_receipt:
                break;

        }

    }
}
