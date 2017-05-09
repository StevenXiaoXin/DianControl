package com.daqianjietong.diancontrol.utils;

import com.daqianjietong.diancontrol.bean.UserInfoBean;

import org.xutils.http.RequestParams;



public class Constant {
		public static RequestParams requestParams;
		static{
			requestParams=new RequestParams();
		}
		public static String FileName = "user";
		public static UserInfoBean user = new UserInfoBean();
}
 