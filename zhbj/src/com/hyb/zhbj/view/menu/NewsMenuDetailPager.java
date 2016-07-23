package com.hyb.zhbj.view.menu;

import java.util.ArrayList;

import com.hyb.zhbj.R;
import com.hyb.zhbj.model.CategoryChildren;
import com.hyb.zhbj.view.tab.TabDetailPager;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

/** 
   * 新闻菜单详情页
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月21日 上午9:20:11 
*/
public class NewsMenuDetailPager extends BaseMenuDetailPager {
	private ViewPager vp_detail;
	private TabPageIndicator indicator;
	private ArrayList<CategoryChildren> mCategoryChildren;
	private ArrayList<TabDetailPager> tabDetailPagers;
	private ImageView iv_next;
	public NewsMenuDetailPager(Activity activity,ArrayList<CategoryChildren> categoryChildren) {
		super(activity);
		mCategoryChildren=categoryChildren;
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view=View.inflate(mActivity, R.layout.news_menu_detail, null);
		vp_detail=(ViewPager)view.findViewById(R.id.vp_news_menu_detail);
		indicator=(TabPageIndicator)view.findViewById(R.id.indicator);
		iv_next = (ImageView)view.findViewById(R.id.iv_next);
		return view;
	}
	@Override
	public void initData() {	
		// TODO Auto-generated method stub
		super.initData();
		tabDetailPagers=new ArrayList<TabDetailPager>();
		TabDetailPager pager=null;
		for(int i=0;i<mCategoryChildren.size();i++)
		{
			pager=new TabDetailPager(mActivity,mCategoryChildren.get(i));
			tabDetailPagers.add(pager);
		}
		vp_detail.setAdapter(new NewsMenuDatilPagerAdapter());
		//vp_detail.setOnPageChangeListener(new MyPageOnChangeListener());//只能给指示器设置监听
		indicator.setOnPageChangeListener(new MyPageOnChangeListener());
		indicator.setViewPager(vp_detail);//将viewpager和指示器绑定在一起,必须在viewpager的setAdapter后
		iv_next.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {				
				// TODO Auto-generated method stub
				int pos=(vp_detail.getCurrentItem()+1) % tabDetailPagers.size();
				vp_detail.setCurrentItem(pos);
			}
		});
	}
	class MyPageOnChangeListener implements OnPageChangeListener
	{
		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onPageScrolled(int arg0, float positionOffset, int positionOffsetPixels) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class NewsMenuDatilPagerAdapter extends PagerAdapter
	{
		@Override
		public int getCount() {			
			// TODO Auto-generated method stub
			return tabDetailPagers.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view==object;
		}
		@Override
		public View instantiateItem(ViewGroup container, int position) {			
			// TODO Auto-generated method stub
			TabDetailPager pager=tabDetailPagers.get(position);
			View view=pager.getRootView();
			container.addView(view);
			pager.initData();
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
		//指定指示器的标题
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return mCategoryChildren.get(position).getTitle();
		}
	}
}
