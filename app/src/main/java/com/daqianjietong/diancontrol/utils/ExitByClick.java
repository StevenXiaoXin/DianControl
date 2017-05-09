package com.daqianjietong.diancontrol.utils;

import android.app.Activity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class ExitByClick {
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    public static void exitBy2Click(Activity act) {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(act, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000);

        } else {
            System.exit(0);
        }


    }
}
