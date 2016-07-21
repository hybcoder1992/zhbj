package com.hyb.zhbj.view.menu;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

/** 
   * 专题菜单详情页
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月21日 上午9:21:08 
*/
public class TopicMenuDetailPager extends BaseMenuDetailPager {

	public TopicMenuDetailPager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		TextView textView=new TextView(mActivity);
		textView.setText("专题详情页");
		textView.setTextSize(22);
		return textView;
	}

}
