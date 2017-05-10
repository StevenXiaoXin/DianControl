package com.daqianjietong.diancontrol;

import android.app.Application;

import org.xutils.x;


/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
