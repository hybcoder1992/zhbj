package com.hyb.zhbj.fragments;

import java.util.ArrayList;

import com.hyb.zhbj.R;
import com.hyb.zhbj.activity.MainActivity;
import com.hyb.zhbj.view.BasePager;
import com.hyb.zhbj.view.GovaffairsPager;
import com.hyb.zhbj.view.HomePager;
import com.hyb.zhbj.view.NewsCenterPager;
import com.hyb.zhbj.view.SettingPager;
import com.hyb.zhbj.view.SmartServicePager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/** 
   *
   * @Package com.hyb.zhbj.fragments
   * @author hyb
   * @date 创建时间：2016年7月20日 上午10:19:57  
*/
public class ContentFragment extends BaseFragment {
	private ArrayList<BasePager> pagers;
	private ViewPager vp_content;
	
	private RadioGroup rg_bottom_tab;
	private TextView tv_title;
	private ImageButton ib_menu;

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view=View.inflate(mActivity, R.layout.fragment_content, null);
		vp_content = (ViewPager)view.findViewById(R.id.vp_content);
		rg_bottom_tab = (RadioGroup)view.findViewById(R.id.rg_bottom_tab);
		//顶部标题栏的两个控件
		
		tv_title = (TextView)view.findViewById(R.id.tv_title);
		ib_menu = (ImageButton)view.findViewById(R.id.ib_menu);
		return view;
	}
	//initData这个方法在父类的onActivityCreated方法中调用,onActivityCreated在fragment所依附的activity的onCreate方法之后调用
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		pagers=new ArrayList<BasePager>();
		//设置顶部标题栏文字
		tv_title.setText("首页");
		//
		ib_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity activity=(MainActivity)mActivity;
				SlidingMenu slidingMenu=activity.getSlidingMenu();
				if(!slidingMenu.isMenuShowing())
					slidingMenu.showMenu();
			}
		});
		//添加五个标签页
		pagers.add(new HomePager(mActivity));
		pagers.add(new NewsCenterPager(mActivity));
		pagers.add(new SmartServicePager(mActivity));
		pagers.add(new GovaffairsPager(mActivity));
		pagers.add(new SettingPager(mActivity));
		
		vp_content.setAdapter(new ContentAdapter());
		vp_content.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				//页面被选中才调用BasePager的initData方法
				BasePager pager=pagers.get(position);
				pager.initData();
				switch (position) {
				//主页和设置的pager被选中时隐藏titlebar的菜单按钮,以及禁用侧边栏
				case 0:
				case 4:
					ib_menu.setVisibility(View.GONE);
					setSlidingMenuEnable(false);
					break;
				case 1:
				case 2:
				case 3:
					ib_menu.setVisibility(View.VISIBLE);
					setSlidingMenuEnable(true);
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		rg_bottom_tab.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_home:
					//首页
					//vp_content.setCurrentItem(0);
					vp_content.setCurrentItem(0, true);//带平滑滑动效果(滑动动画)
					tv_title.setText("首页");
					break;
				case R.id.rb_newscenter:
					//新闻中心
					vp_content.setCurrentItem(1, true);
					tv_title.setText("新闻中心");
					break;
				case R.id.rb_smart_service:
					//智慧服务
					vp_content.setCurrentItem(2, true);
					tv_title.setText("智慧服务");
					break;
				case R.id.rb_govaffairs:
					//政务
					vp_content.setCurrentItem(3, true);
					tv_title.setText("政务");
					break;
				case R.id.rb_setting:
					//设置
					vp_content.setCurrentItem(4, true);
					tv_title.setText("设置");
					break;
				default:
					break;
				}
			}
		});
		//手动加载第一页数据
		pagers.get(0).initData();
	}
	//开启或禁用侧边栏
	private void setSlidingMenuEnable(boolean enabled) {
		MainActivity activity=(MainActivity)mActivity;
		SlidingMenu slidingMenu=activity.getSlidingMenu();
		if(enabled)
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		else
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		
	}
	//获取新闻中心的页面
	public BasePager getNewsCenterPager()
	{
		return pagers.get(1);
	}
	class ContentAdapter extends PagerAdapter
	{
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			BasePager pager=pagers.get(position);
			View view=pager.getRootView();
			//不要在这里调用pager的initData()方法,应该initData方法使用来加载数据的,耗资源
			//在viewpager被选中才去调initData方法
			container.addView(view);
			return view;
		}
	}
}
