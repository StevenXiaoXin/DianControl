package com.daqianjietong.diancontrol.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daqianjietong.diancontrol.BaseActivity;
import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.bean.UserInfoBean;
import com.daqianjietong.diancontrol.utils.Api;
import com.daqianjietong.diancontrol.utils.EmptyUtils;
import com.daqianjietong.diancontrol.utils.HttpUtil;
import com.daqianjietong.diancontrol.utils.JumpActivityUtils;
import com.daqianjietong.diancontrol.utils.NetWorkUtil;
import com.daqianjietong.diancontrol.utils.SharedPreferencesUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2017/5/9 0009.
 * 登录页
 */
@ContentView(R.layout.uer_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.et_user_name)
    private EditText et_user_name;
    @ViewInject(R.id.et_user_password)
    private EditText et_user_password;
    @ViewInject(R.id.btn_login)
    private Button btn_login;
    private Activity act;
    private Context context;
    private String userName;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;

        if (!NetWorkUtil.isNetworkConnected(context)) {
            Toast.makeText(context,"没有可用网络",Toast.LENGTH_LONG).show();
        }
        initData();
    }
    private void initData() {

        btn_login.setOnClickListener(this);
    }

    private void doLogin() {

            userName=et_user_name.getText().toString();
            password=et_user_password.getText().toString();
            if (EmptyUtils.isEmpty(userName) || EmptyUtils.isEmpty(password)) {
                Toast.makeText(context, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
            } else {
                login();
            }

    }

    private void login() {
        showDialog();
        Api.getInstance().login(userName,password,new HttpUtil.URLListenter<UserInfoBean>() {
            @Override
            public void onsucess(UserInfoBean userInfoBean) throws Exception {
                dissDialog();
                if (userInfoBean.getCode() == 1) {
                    Log.e("解析列表数据OK---》",userInfoBean.toString());
                    SharedPreferencesUtil.saveData(act, "userName", userName);
                    SharedPreferencesUtil.saveData(act, "password", password);
                    SharedPreferencesUtil.saveData(act, "uid", userInfoBean.getData().getUid());
                    SharedPreferencesUtil.saveData(act, "token", userInfoBean.getData().getToken());
                    SharedPreferencesUtil.saveData(act, "photo", userInfoBean.getData().getPu_photo());
                    SharedPreferencesUtil.saveData(act, "phone", userInfoBean.getData().getPu_phone());
                    SharedPreferencesUtil.saveData(act, "parkname", userInfoBean.getData().getPu_parkname());
                    SharedPreferencesUtil.saveData(act, "parkid", userInfoBean.getData().getPu_parkid());
                    Log.e("dsadasdas",userInfoBean.getData().getToken());
                    JumpActivityUtils.Jump2Activity(act, MainActivity.class);
                    act.finish();
                } else if (userInfoBean.getCode() == 0) {
                    Toast.makeText(getApplicationContext(),userInfoBean.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onfaild(String error) {
                dissDialog();
                btn_login.setClickable(true);
                Log.e("解析列表数据失败---》",error);
            }
        });
    }

    @Override
    public void onClick(View v) {
        doLogin();
    }
}
