package com.daqianjietong.diancontrol.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.List;

/**
 * sharedPreferences工具类
 * @author Steven
 *
 */
public  class SharedPreferencesUtil {
	public static  SharedPreferences sp;
	public static  Editor editor;
	public static  Context context;
	public static  Object obj;
	private static List list=new ArrayList<String>();
    public static boolean saveList(Context context, String key,List<String> list) {  
    	 getSp(context);
    	 Editor editor =sp.edit(); 
    	 editor.putInt("list_size",list.size());   
       for(int i=0;i<list.size();i++) {  
           editor.putString(""+i,list.get(i));    
       }  
       return editor.commit();       
   }  
    public static List<String> getList(Context context, String key,List<String> defaultList) {    
    	 getSp(context);
    	 list.clear();  
        int size = sp.getInt("list_size", 0);    
        for(int i=0;i<size;i++) {  
        	list.add(sp.getString(""+i, null));    
        }
		return list;  
    } 
	public static void saveData(Context context, String key,Object data){ 
        String type = data.getClass().getSimpleName(); 
        getSp(context);
        Editor editor =sp.edit(); 
        if ("Integer".equals(type)){ 
            editor.putInt(key, (Integer)data); 
        }else if ("Boolean".equals(type)){ 
            editor.putBoolean(key, (Boolean)data); 
        }else if ("String".equals(type)){ 
            editor.putString(key, (String)data); 
        }else if ("Float".equals(type)){ 
            editor.putFloat(key, (Float)data); 
        }else if ("Long".equals(type)){ 
            editor.putLong(key, (Long)data); 
        } 
        editor.commit(); 
    } 
    public static Object getData(Context context, String key, Object defValue){ 
        String type = defValue.getClass().getSimpleName(); 
        getSp(context);
        if(type.equals("Integer")){
        	return sp.getInt(key, (Integer)defValue); 
        }else if(type.equals("Boolean")){
        	return sp.getBoolean(key, (Boolean)defValue);
        }else if(type.equals("String")){
        	return sp.getString(key, (String)defValue); 
        }else if(type.equals("Float")){
        	return sp.getFloat(key, (Float)defValue);
        }else if(type.equals("Long")){
        	return sp.getLong(key, (Long)defValue); 
        }
        return null; 
    }  
    public static void getSp(Context context){
		 sp =context.getSharedPreferences(Constant.FileName,Context.MODE_WORLD_READABLE | Context.MODE_WORLD_WRITEABLE);
	}
    public static void getEditor(){
    	Editor editor =sp.edit(); 
    }
}
