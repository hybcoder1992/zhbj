package com.hyb.zhbj.view.tab;

import com.hyb.zhbj.model.CategoryChildren;
import com.hyb.zhbj.view.menu.BaseMenuDetailPager;

import android.app.Activity;
import android.view.View;

/** 
   * 页签页面对象
   * @Package com.hyb.zhbj.view.tab
   * @author hyb
   * @date 创建时间：2016年7月21日 上午10:39:36 
*/
public class TabDetailPager extends BaseMenuDetailPager {
	private CategoryChildren mChild;
	public TabDetailPager(Activity activity,CategoryChildren child) {

		super(activity);
		// TODO Auto-generated constructor stub
		mChild=child;
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void initData() {
		
		// TODO Auto-generated method stub
		super.initData();
	}
}
