package com.daqianjietong.diancontrol.utils;


import android.content.Context;
import android.util.Log;

import com.daqianjietong.diancontrol.bean.GetMainNumBean;
import com.daqianjietong.diancontrol.bean.GetMessageBean;
import com.daqianjietong.diancontrol.bean.LeaveBean;
import com.daqianjietong.diancontrol.bean.OrderInfo;
import com.daqianjietong.diancontrol.bean.ParkListInfo;
import com.daqianjietong.diancontrol.bean.PersonalInfoBean;
import com.daqianjietong.diancontrol.bean.QuickListBean;
import com.daqianjietong.diancontrol.bean.UserInfoBean;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     *登陆接口调用；
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
     * 个人中心接口调用；
     * @param listenter
     */
  public  void getPersonal( HttpUtil.URLListenter<PersonalInfoBean> listenter,Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_userid",(String)SharedPreferencesUtil.getData(context,"uid","1"));
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+PERSIONAL).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<PersonalInfoBean>(){}.getType()).seturllisenter(listenter).start();
  }

  /**
     * 首页数量接口调用；
     * @param listenter
     */
  public  void getMainNum( HttpUtil.URLListenter<GetMainNumBean> listenter,Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_parkid",(String)SharedPreferencesUtil.getData(context,"parkid","1"));
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+GETNUM).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<GetMainNumBean>(){}.getType()).seturllisenter(listenter).start();
  }
/**
     * 预约列表接口调用；
     * @param txt_parkid
     * @param listenter
     */
  public  void getOrderList(String txt_parkid, String p, String txt_parknum, HttpUtil.URLListenter<OrderInfo> listenter, Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_parkid",String.valueOf(txt_parkid));
      params.put("p",String.valueOf(p));
      params.put("txt_parknum",txt_parknum);
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+ORDERLIST).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<OrderInfo>(){}.getType()).seturllisenter(listenter).start();

  }
  /**
     * 车位列表接口调用；
     * @param txt_parkid
     * @param listenter
     */
  public  void getParkList(String txt_parkid, String p, String txt_parknum, HttpUtil.URLListenter<ParkListInfo> listenter, Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_parkid",String.valueOf(txt_parkid));
      params.put("p",String.valueOf(p));
      params.put("txt_parknum",txt_parknum);
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+PARKLIST).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<ParkListInfo>(){}.getType()).seturllisenter(listenter).start();

  }
  /**
     * 修改密码接口调用；
     * @param listenter
     */
  public  void upPassword(String old_pass, String new_pass,  HttpUtil.URLListenter<GetMessageBean> listenter, Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_userid",(String)SharedPreferencesUtil.getData(context,"uid","1"));
      params.put("old_pass",old_pass);
      params.put("new_pass",new_pass);
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+CHANGEPSD).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<GetMessageBean>(){}.getType()).seturllisenter(listenter).start();

  }
  /**
     * 快速泊车接口调用；
     * @param listenter
     */
  public  void upPark(String txt_parknum,  HttpUtil.URLListenter<GetMessageBean> listenter, Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_parknum",txt_parknum);
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+QUICKPARK).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<GetMessageBean>(){}.getType()).seturllisenter(listenter).start();

  }



  /**
     * 临时停车接口调用；
     * @param listenter
     */
  public  void leaveQuick(String txt_parknum, HttpUtil.URLListenter<QuickListBean> listenter, Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_parknum",txt_parknum);
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+QUICKLIST).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<QuickListBean>(){}.getType()).seturllisenter(listenter).start();

  }
  /**
     * 临时停车接口调用；
     * @param listenter
     */
  public  void leave(String txt_reserveid, HttpUtil.URLListenter<LeaveBean> listenter, Context context){
      Map<String,String> params = new HashMap<>();
      params.put("txt_reserveid",txt_reserveid);
      params.put("token",(String)SharedPreferencesUtil.getData(context,"token",""));
      HttpUtil httpUtil = new HttpUtil();
      httpUtil.setUrl(HOST+LEAVE).setMethod(API_METHOD.GET).setParams(params).setTypetoken(new TypeToken<LeaveBean>(){}.getType()).seturllisenter(listenter).start();

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
