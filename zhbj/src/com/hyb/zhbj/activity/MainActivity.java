package com.hyb.zhbj.activity;

import com.hyb.zhbj.R;
import com.hyb.zhbj.fragments.ContentFragment;
import com.hyb.zhbj.fragments.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SlidingFragmentActivity {
	private static final String LEFT_MENU_TAG="left_menu_tag";
	private static final String CONTENT_MENU_TAG="content_menu_tag";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//activity_main就只有一个帧布局,id为fl_main
		
		//设置slidemenu
		setBehindContentView(R.layout.left_menu);//left_menu也只有一个framelayout,id为fl_left_menu
		SlidingMenu slidingMenu=getSlidingMenu();
		//设置SlidingMenu 的手势模式  
        //TOUCHMODE_FULLSCREEN 全屏模式，在整个content页面中，滑动，可以打开SlidingMenu  
        //TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开SlidingMenu,你需要在屏幕边缘滑动才可以打开SlidingMenu  
        //TOUCHMODE_NONE 不能通过手势打开SlidingMenu
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//全屏触摸
		slidingMenu.setBehindOffsetRes(R.dimen.slidemenu_behind_offset);//SlidingMenu划出时主页面显示的剩余宽度 
		initFragment();
	}
	/**
	 * 初始化 
	 * */
	private void initFragment() 
	{
		FragmentManager fm= getSupportFragmentManager();
		FragmentTransaction ft=fm.beginTransaction();
		ft.replace(R.id.fl_left_menu, new LeftMenuFragment(),LEFT_MENU_TAG);
		ft.replace(R.id.fl_main, new ContentFragment(), CONTENT_MENU_TAG);
		ft.commit();
	}
	//获取左侧侧边栏的fragment
	public Fragment getLeftMenuFragment()
	{
		FragmentManager fm = getSupportFragmentManager();
		return fm.findFragmentByTag(LEFT_MENU_TAG);
	}
	//获取contentfragment
	public Fragment getContentFragment()
	{
		FragmentManager fm = getSupportFragmentManager();
		return fm.findFragmentByTag(CONTENT_MENU_TAG);
	}
}
