package com.hyb.zhbj.view;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

/** 
   *
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月20日 下午3:15:43 
*/
public class HomePager extends BasePager {

	/**
	 * @Description: 
	 * @param   
	 * @return  
	 * @throws
	 * @author hyb
	 * @date 2016年7月20日
	 */
	public HomePager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		TextView textView=new TextView(mActivity);
		textView.setText("首页");
		textView.setGravity(Gravity.CENTER);
		fl_content.addView(textView);
		
		//设置标题栏
		//tv_title.setText("首页");
	}
}
