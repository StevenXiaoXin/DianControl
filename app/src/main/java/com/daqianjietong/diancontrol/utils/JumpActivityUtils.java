package com.daqianjietong.diancontrol.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class JumpActivityUtils {
    public static void Jump2Activity(Activity act1, Class<?> act2){
        Intent intent = new Intent(act1, act2);
        act1.startActivity(intent);
    }
}
