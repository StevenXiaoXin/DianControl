package com.daqianjietong.diancontrol;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.xutils.x;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class BaseActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
