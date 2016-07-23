package com.hyb.zhbj.view;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.hyb.zhbj.activity.MainActivity;
import com.hyb.zhbj.fragments.LeftMenuFragment;
import com.hyb.zhbj.model.CategoryData;
import com.hyb.zhbj.utils.CacheUtil;
import com.hyb.zhbj.utils.GlobalConstants;
import com.hyb.zhbj.view.menu.BaseMenuDetailPager;
import com.hyb.zhbj.view.menu.InteractMenuDeatilPager;
import com.hyb.zhbj.view.menu.NewsMenuDetailPager;
import com.hyb.zhbj.view.menu.PhotoMenuDetailPager;
import com.hyb.zhbj.view.menu.TopicMenuDetailPager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

/** 
   *
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月20日 下午3:40:02 
*/
public class NewsCenterPager extends BasePager {
	private ArrayList<BaseMenuDetailPager> detailPagers;
	public NewsCenterPager(Activity activity) {
		
		super(activity);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();		
		//先判断是否有缓存
		String cacheResult=CacheUtil.getCache(GlobalConstants.CATEGORY_URL, mActivity);
		if(!TextUtils.isEmpty(cacheResult))
		{
			Log.d("hyb", "有缓存");
			processData(cacheResult);
		}
		//请求服务器获取数据
		getDataFromServer();
		
	}
	private void processData(String result)
	{
		CategoryData datas=new Gson().fromJson(result, CategoryData.class);
		//获取左侧侧边栏的fragment
		MainActivity actiivty=(MainActivity)mActivity;
		LeftMenuFragment menuFragment=(LeftMenuFragment)actiivty.getLeftMenuFragment();
		//设置侧边栏数据
		menuFragment.setMenuData(datas.getData());
		//初始化4个菜单详情页
		detailPagers=new ArrayList<BaseMenuDetailPager>();
		detailPagers.add(new NewsMenuDetailPager(mActivity,datas.getData().get(0).getChildren()));
		detailPagers.add(new TopicMenuDetailPager(mActivity));
		detailPagers.add(new PhotoMenuDetailPager(mActivity));
		detailPagers.add(new InteractMenuDeatilPager(mActivity));
		//设置新闻菜单详情页为默认页面
		setCurrentDetailPager(0);
	}
	private void getDataFromServer()
	{
		Log.d("hyb", "请求数据");
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				error.printStackTrace();
			}
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				Log.d("hyb", "请求成功");
				//请求成功写缓存
				CacheUtil.setCache(GlobalConstants.CATEGORY_URL, responseInfo.result, mActivity);
				//解析数据
				processData(responseInfo.result);				
			}
		});
		
	}
	//设置菜单详情页
	public void setCurrentDetailPager(int position)
	{
		BaseMenuDetailPager detailPager=detailPagers.get(position);
		View view=detailPager.getRootView();
		//清除之前的帧布局里面的所有内容
		fl_content.removeAllViews();
		fl_content.addView(view);//给帧布局添加view
		detailPager.initData();
		//
	}
	
}
