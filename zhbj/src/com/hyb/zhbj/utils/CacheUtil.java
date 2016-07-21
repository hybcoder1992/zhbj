package com.hyb.zhbj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/** 
   * 网络缓存工具类
   * @Package com.hyb.zhbj.utils
   * @author hyb
   * @date 创建时间：2016年7月20日 下午8:49:05 
*/
public class CacheUtil {
	
	//以url为key,以json字符串为value,保存在本地
	//也可以用文件缓存,以url为文件名,json数据为文件内容
	public static void setCache(String url,String json,Context context)
	{
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putString(url, json);
		editor.commit();
	}
	public static String getCache(String url,Context context)
	{
		SharedPreferences sp=context.getSharedPreferences("config", Context.MODE_PRIVATE);
		return sp.getString(url, null);
	}
	
}
