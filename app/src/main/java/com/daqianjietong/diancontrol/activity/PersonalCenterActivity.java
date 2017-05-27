package com.daqianjietong.diancontrol.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daqianjietong.diancontrol.BaseActivity;
import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.bean.PersonalInfoBean;
import com.daqianjietong.diancontrol.utils.Api;
import com.daqianjietong.diancontrol.utils.HttpUtil;
import com.daqianjietong.diancontrol.utils.JumpActivityUtils;
import com.daqianjietong.diancontrol.utils.SharedPreferencesUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ContentView(R.layout.activity_personal_center)
public class PersonalCenterActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;
    @ViewInject(R.id.ll_change_psd)
    private LinearLayout ll_change_psd;
    @ViewInject(R.id.btn_login_out)
    private Button btn_login_out;

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
        getHttp();
        ll_change_psd.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_login_out.setOnClickListener(this);
    }
    private void getHttp() {
        showDialog();
        Api.getInstance().getPersonal(new HttpUtil.URLListenter<PersonalInfoBean>() {
            @Override
            public void onsucess(PersonalInfoBean personalInfoBean) throws Exception {
                dissDialog();
                if (personalInfoBean.getCode() == 1) {
                    tv_user_name.setText(personalInfoBean.getData().getPu_username());
                } else if (personalInfoBean.getCode() == 0) {
                    Toast.makeText(getApplicationContext(),personalInfoBean.getMessage(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onfaild(String error) {
                dissDialog();
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                Log.e("解析列表数据失败---》",error);
            }
        },context);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_change_psd:
                JumpActivityUtils.Jump2Activity(act, UpdatePwdActivity.class);
                break;
            case R.id.iv_back:
                JumpActivityUtils.Jump2Activity(act, MainActivity.class);
                act.finish();
                break;
            case R.id.btn_login_out:
                SharedPreferencesUtil.saveData(act, "userName", "");
                SharedPreferencesUtil.saveData(act, "password", "");
                SharedPreferencesUtil.saveData(act, "uid", "");
                SharedPreferencesUtil.saveData(act, "token", "");
                JumpActivityUtils.Jump2Activity(act, LoginActivity.class);
                act.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        JumpActivityUtils.Jump2Activity(act,MainActivity.class);
        act.finish();
    }
}
