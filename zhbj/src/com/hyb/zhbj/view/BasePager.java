package com.hyb.zhbj.view;

import com.hyb.zhbj.R;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/** 
   *
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月20日 下午3:07:20 
*/
public class BasePager {
	
	protected Activity mActivity;

	protected FrameLayout fl_content; 
	protected View rootView;
	public BasePager(Activity activity)
	{
		mActivity=activity;
		rootView=initView();
		
	}
	//初始化布局
	protected View initView()
	{
		View view=View.inflate(mActivity, R.layout.base_layout	, null);
		
		fl_content = (FrameLayout)view.findViewById(R.id.fl_content);
		return view;
	}
	//初始化数据
	public void initData()
	{
		
	}
	public View getRootView() {
		return rootView;
	}
	
}
