package com.daqianjietong.diancontrol;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daqianjietong.diancontrol.view.ProDialog;

import org.xutils.x;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class BaseActivity extends Activity{
    private ProDialog proDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }


    public void showDialog(){
        if (proDialog==null){
            proDialog=new ProDialog(this);
        }
        if (proDialog.isShowing()==false){
            proDialog.show();
        }
    }

    public void dissDialog(){
        if (proDialog!=null && proDialog.isShowing()){
            proDialog.dismiss();
        }
    }
}
