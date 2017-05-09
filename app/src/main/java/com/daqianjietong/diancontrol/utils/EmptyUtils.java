package com.daqianjietong.diancontrol.utils;


/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class EmptyUtils {
    /**
     * 判断是否有网络连接
     * @param
     * @return
     */
    public static boolean isEmpty(String str) {
        if(str==null||str.isEmpty()||str.equals("")){
            return true;
        }else{
            return false;
        }
    }

}
