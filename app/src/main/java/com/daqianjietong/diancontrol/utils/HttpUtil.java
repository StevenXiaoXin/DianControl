package com.daqianjietong.diancontrol.utils;


import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;


/**
 * Created by Administrator on 2017/4/13 0013.
 * xUtils的网络请求工具类
 */

public class HttpUtil <T>{
    private Api.API_METHOD method;

    private RequestParams params;

    private URLListenter lisenter;
    private Class<?> clazz;

    private Type type;

   private  Gson gson = new Gson();

    public  String result;
    Callback.CommonCallback callback  = new Callback.CommonCallback<String>(){
        @Override
        public void onSuccess(String result) {
            if(null == lisenter)
                return;
            try {
            if(clazz != null){

                    lisenter.onsucess(gson.fromJson(result,clazz));

                return;
            }else if(type!=null){
                    lisenter.onsucess(gson.fromJson(result,type));
                    return;
            }else{
                    lisenter.onsucess(result);
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
            reset();
        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            if(null!=lisenter)
            lisenter.onfaild(ex.toString());
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {

        }
    };

    /**
     * 重设工具类
     */
    private void reset(){
        clazz = null;
        type = null;
        params.clearParams();

    }



//     @Deprecated
//    public   String getRequest(RequestParams params){
//
//
//        x.http().get(params, new Callback.CommonCallback<String>() {
//
//            @Override
//            public void onSuccess(String result) {
//                HttpUtil.this.result=result;
//                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    // ...
//                } else { // 其他错误
//                    // ...
//                }
//            }
//
//
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//            //取消请求，后期可以加取消提示
//            }
//
//
//            // 不管成功或者失败最后都会回调该接口，把最后必须处理的放在这个方法中。
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//        return result;
//    }
//    @Deprecated
//    public String postRequest(RequestParams params){
//
//
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                HttpUtil.this.result=result;
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                if (ex instanceof HttpException) { // 网络错误
//                    HttpException httpEx = (HttpException) ex;
//                    int responseCode = httpEx.getCode();
//                    String responseMsg = httpEx.getMessage();
//                    String errorResult = httpEx.getResult();
//                    // ...
//                } else { // 其他错误
//                    // ...
//                }
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//
//
//        return result;
//    }

    /**
     * 设置访问方式（post/get）
     * @param method
     * @return
     */
   public HttpUtil setMethod(Api.API_METHOD method){
       this.method = method;
       return this;
   }

    /**
     * 设置访问参数
     * @param maps
     * @return
     */
   public HttpUtil setParams(Map<String,String> maps){
       if(maps == null)
           return this;
       Set<Map.Entry<String,String>>  entries = maps.entrySet();
       for (Map.Entry<String,String> entity:entries) {
           params.addQueryStringParameter(entity.getKey(),entity.getValue());

       }
       return this;
   }

    /**
     * 设置访问url
     * @param hostUrl
     * @return
     */
  public HttpUtil setUrl(String hostUrl){
      if(params!=null){
          params.clearParams();
      }else{
          params = new RequestParams();
      }
      params.setUri(hostUrl);
      return this;
  }

    /**
     * 开始调用网络访问
     */
  public void start(){
      if (method.equals(Api.API_METHOD.GET)) {
          x.http().get(params, callback);
      } else {
          x.http().post(params, callback);

      }
  }

    /**
     * 设置URL的监听回调
     * @param lisenter
     * @return
     */
  public HttpUtil seturllisenter(URLListenter<T> lisenter){
      this.lisenter = lisenter;
      return this;
  }

    /**
     * 设置解析类型（针对于列表数据）
     * @param type
     * @return
     */
  public HttpUtil setTypetoken(Type type){
     this.type = type;
     return this;
  }

    /**
     * 设置类实体解析类型
     * @param clazz
     * @return
     */
    public HttpUtil  setClassTye(Class<?> clazz){
        this.clazz = clazz;
        return this;
    }

    /**
     * 回调接口
     * @param <T>
     */
    public interface URLListenter<T>{
         void onsucess(T t) throws Exception;
        void  onfaild(String error);
    }

}
