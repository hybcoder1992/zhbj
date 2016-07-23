package com.hyb.zhbj.view;

import javax.security.auth.PrivateCredentialPermission;

import com.hyb.zhbj.activity.MainActivity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/** 
   *
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月22日 下午8:19:06 
*/
public class TopNewsViewPager extends ViewPager{

	private int startx;
	private int starty;
	public TopNewsViewPager(Context context, AttributeSet attrs) {
		
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TopNewsViewPager(Context context) {
		
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		getParent().requestDisallowInterceptTouchEvent(true);//父控件不拦截子控件的触摸事件
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startx = (int) ev.getX();
			starty = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int endx=(int) ev.getX();
			int endy=(int) ev.getY();
			int dx=endx-startx;
			int dy=endy-starty;
			if(Math.abs(dx)>Math.abs(dy))
			{
				//左右滑动
				if(dx>0)
				{
					//向右滑
					if(getCurrentItem()==0)
					{
						getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截子控件的触摸事件
					}
				}
				else
				{
					//向左滑
					if(getCurrentItem()==getAdapter().getCount()-1)
					{
						getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截子控件的触摸事件
					}
				}
			}
			else
			{
				//上下滑动,需要拦截
				getParent().requestDisallowInterceptTouchEvent(false);//父控件拦截子控件的触摸事件
			}
			break;

		default:
			break;
		}
		
		return super.dispatchTouchEvent(ev);
	}
	
}
