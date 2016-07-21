package com.hyb.zhbj.view.menu;

import android.app.Activity;
import android.view.View;

/** 
   * 菜单详情页基类
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月21日 上午9:17:11 
*/
public abstract class BaseMenuDetailPager {
	protected Activity mActivity;
	protected View rootView;//菜单详情页根布局
	public BaseMenuDetailPager(Activity activity) {
		// TODO Auto-generated constructor stub
		mActivity=activity;
		rootView = initView();
	}
	public abstract View initView();
	
	public void initData(){}
	public View getRootView() {
		return rootView;
	}
	
}
