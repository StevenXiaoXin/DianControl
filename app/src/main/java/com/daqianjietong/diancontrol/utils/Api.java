package com.daqianjietong.diancontrol.utils;


import com.daqianjietong.diancontrol.bean.UserInfoBean;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhuang on 2017/5/1.
 */

public class Api {
    /**
     * api访问方法（post/get）
     */

    public enum  API_METHOD{
        POST,GET
    }
    private static  String HOST = "http://www.daqianjietong.com/dianapi.php/";

    private static final String LOGIN="ManagerLogin/login?";
    private static final String PERSIONAL="Muser/index?token=";
    private static final String CHANGEPSD="Muser/upPassword?token=";
    private static final String ORDERLIST="Mreserve/reserveList?token=";
    private static final String PARKLIST="Mreserve/parkingList?token=";
    private static final String QUICKPARK="Mreserve/parkCar?token=";
    private static final String CONTINUE="Mreserve/renewParking?token=";
    private static final String QUICKLIST="Mpayment/stopList?token=";
    private static final String LEAVE="Mpayment/subParking?token=";
    private static final String GETNUM="Muser/getParkNum?token=";

    /**
     * 是否debug调试（切换测试环境和生产环境）
     */
    private static final boolean Debug= true;


    private static  Api api;

    static {
        if(Debug){
            HOST = "http://test.daqianjietong.com/dianapi.php/";
        }
    }

    public static Api getInstance(){
        if(api == null)
            api = new Api();
        return api;
    }

    /**
     * 示例  登陆接口调用；
     * @param username
     * @param password
     * @param listenter
     */
  public  void login(String username, String password, HttpUtil.URLListenter<UserInfoBean> listenter){
      Map<String,String> params = new HashMap<>();
      params.put("txt_phone",username);
      params.put("txt_password",password);
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+LOGIN).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<UserInfoBean>(){}.getType()).seturllisenter(listenter).start();
  }


    /**
     * 示例1  测试百度接口调用；（默认返回String类型的数据）
     * @param listenter  回调监听传入需要解析数据类型
     */
    public  void test( HttpUtil.URLListenter<String> listenter){
        /**
         * setMethod()设置post get方法
         * setParams(Map params)设置请求参数
         * setUrl(string url)设置请求url地址
         * setClassType(Class<\?> clazz)设置解析类实体类型 传如数据如 UserInfoBean.class;
         * setTypeToken(new TypeToken<T></>(){}.gettype())
         * 传入解析的数据类型，在未调用setClassType方法时起作用（针对列表数据类型解析）
         * 用法：将方法中T替换成想要解析的类或者List<T></>传入需要解析类
         * seturlLisenter()设置回掉监听.
         *
         */

        HttpUtil httpUtil = new HttpUtil<String>();
        httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).seturllisenter(listenter).start();
    }
    /**
     * 示例2  测试百度接口调用；（设置返回解析后的list数据）
     * @param listenter  回调监听传入需要解析数据类型
     */
    public  void testList(HttpUtil.URLListenter<ArrayList<String>>listenter){
        /**
         * setMethod()设置post get方法
         * setParams(Map params)设置请求参数
         * setUrl(string url)设置请求url地址
         * setClassType(Class<\?> clazz)设置解析类实体类型 传如数据如 UserInfoBean.class;
         * setTypeToken(new TypeToken<T></>(){}.gettype())
         * 传入解析的数据类型，在未调用setClassType方法时起作用（针对列表数据类型解析）
         * 用法：将方法中T替换成想要解析的类或者List<T></>传入需要解析类
         * seturlLisenter()设置回掉监听.
         * 获取list数据时示例如下：
         */

        HttpUtil httpUtil = new HttpUtil<String>();
        httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).setTypetoken(new TypeToken<ArrayList<UserInfoBean>>(){}.getType()).seturllisenter(listenter).start();
    }

}
