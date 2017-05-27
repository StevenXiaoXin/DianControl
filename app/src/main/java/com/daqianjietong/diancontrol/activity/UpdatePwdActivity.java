package com.daqianjietong.diancontrol.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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
 * Created by Administrator on 2017/5/7.
 */
@ContentView(R.layout.activity_updatepwd)
public class UpdatePwdActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(R.id.tv_title)
    TextView titleTitle;
    @ViewInject(R.id.iv_back)
    ImageView iv_back;
    @ViewInject(R.id.updatepwd_oldpwd)
    EditText updatepwdOldpwd;
    @ViewInject(R.id.updatepwd_newpwd)
    EditText updatepwdNewpwd;
    @ViewInject(R.id.updatepwd_repwd)
    EditText updatepwdRepwd;
    @ViewInject(R.id.updatepwd_btn)
    TextView updatepwd_btn;
    private Context context;
    private Activity act;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;
        initView();
    }

    public void initView() {
        titleTitle.setText("修改密码");
        iv_back.setOnClickListener(this);
        updatepwd_btn.setOnClickListener(this);

    }

    private void forgetPass(){
        if (ToosUtils.isTextEmpty(updatepwdOldpwd)){
            Toast.makeText(context,"旧密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (ToosUtils.isTextEmpty(updatepwdNewpwd)){
            Toast.makeText(context,"新密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!ToosUtils.getTextContent(updatepwdNewpwd).equals(ToosUtils.getTextContent(updatepwdRepwd))){
            Toast.makeText(context,"两次输入的密码不一致！",Toast.LENGTH_SHORT).show();
            return;
        }
        showDialog();
        Api.getInstance().upPassword(ToosUtils.getTextContent(updatepwdOldpwd),ToosUtils.getTextContent(updatepwdNewpwd), new HttpUtil.URLListenter<GetMessageBean>() {
            @Override
            public void onsucess(GetMessageBean getMessageBean) throws Exception {
                dissDialog();
                if (getMessageBean.getCode() == 1) {

                    Toast.makeText(context,"修改成功!",Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(context,getMessageBean.getMessage(),Toast.LENGTH_SHORT).show();
                }
                act.finish();
            }

            @Override
            public void onfaild(String error) {
                dissDialog();
                Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
            }
        },context);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                act.finish();
                break;
            case R.id.updatepwd_btn:
                forgetPass();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        act.finish();
    }
}
