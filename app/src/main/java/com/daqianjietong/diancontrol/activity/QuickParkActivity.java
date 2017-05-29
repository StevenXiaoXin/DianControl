package com.daqianjietong.diancontrol.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daqianjietong.diancontrol.BaseActivity;
import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.bean.GetMessageBean;
import com.daqianjietong.diancontrol.utils.Api;
import com.daqianjietong.diancontrol.utils.HttpUtil;
import com.daqianjietong.diancontrol.utils.JumpActivityUtils;
import com.daqianjietong.diancontrol.utils.ToosUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ContentView(R.layout.activity_quick_park)
public class QuickParkActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.et_park_num)
    private EditText et_park_num;
    @ViewInject(R.id.btn_park)
    private Button btn_park;

    private Activity act;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context = this;
        initData();
    }

    private void initData() {
        tv_title.setText("车位列表");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpActivityUtils.Jump2Activity(act,MainActivity.class);
                act.finish();
            }
        });
        btn_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setPark();
            }
        });
    }


    public void setPark() {
        if (ToosUtils.isTextEmpty(et_park_num)) {
            Toast.makeText(context,"请输入车位编号",Toast.LENGTH_SHORT).show();
            return;
        }
        showDialog();
        Api.getInstance().upPark(ToosUtils.getTextContent(et_park_num), new HttpUtil.URLListenter<GetMessageBean>() {
            @Override
            public void onsucess(GetMessageBean getMessageBean) throws Exception {
                dissDialog();
                if (getMessageBean.getCode() == 1) {
                    Toast.makeText(context,"停车成功!",Toast.LENGTH_SHORT).show();
                    et_park_num.setText("");
                } else  {
                    Toast.makeText(context,getMessageBean.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onfaild(String error) {
                dissDialog();
                Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
            }
        },context);
    }

    @Override
    public void onBackPressed() {
        JumpActivityUtils.Jump2Activity(act,MainActivity.class);
        act.finish();
    }
}
