package com.hyb.zhbj.view.menu;

import java.util.ArrayList;

import com.hyb.zhbj.R;
import com.hyb.zhbj.model.CategoryChildren;
import com.hyb.zhbj.model.CategoryData;
import com.hyb.zhbj.view.tab.TabDetailPager;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/** 
   * 新闻菜单详情页
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月21日 上午9:20:11 
*/
public class NewsMenuDetailPager extends BaseMenuDetailPager {
	private ViewPager vp_detail;
	private ArrayList<CategoryChildren> mCategoryChildren;
	private ArrayList<TabDetailPager> tabDetailPagers;
	public NewsMenuDetailPager(Activity activity,ArrayList<CategoryChildren> categoryChildren) {
		super(activity);
		mCategoryChildren=categoryChildren;
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view=View.inflate(mActivity, R.layout.news_menu_detail, null);
		vp_detail=(ViewPager)view.findViewById(R.id.vp_news_menu_detail);
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
	}
}
