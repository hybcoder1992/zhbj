package com.hyb.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/** 
   * 不允许滑动的viewpager
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月20日 下午4:00:59 
*/
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context) {

		super(context);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {

		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		
		// TODO Auto-generated method stub
		//重写此方法,触摸时什么都不做,从而实现禁止滑动功能
		return true;
	}
}
