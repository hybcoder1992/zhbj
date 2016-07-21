package com.hyb.zhbj.fragments;

import java.util.ArrayList;

import com.hyb.zhbj.R;
import com.hyb.zhbj.activity.MainActivity;
import com.hyb.zhbj.model.Data;
import com.hyb.zhbj.view.NewsCenterPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
//侧边栏fragment
public class LeftMenuFragment extends BaseFragment {
	@ViewInject(R.id.lv_category)
	private ListView lv_category;
	private ArrayList<Data> mDatas;
	private int currentPos=0;//当前可用的索引
	private MyListViewAdapter adapter;
	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view=View.inflate(mActivity, R.layout.fragment_left_menu, null);
		ViewUtils.inject(this, view);//注入view和事件
		return view;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		adapter=new MyListViewAdapter();
	}
	class MyListViewAdapter extends BaseAdapter
	{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Data data=mDatas.get(position);
			View view=View.inflate(mActivity, R.layout.list_item_left_fragment, null);
			TextView tv_name=(TextView)view.findViewById(R.id.tv_category_name);
			if(position==currentPos)
				tv_name.setEnabled(true);
			else
				tv_name.setEnabled(false);
			tv_name.setText(data.getTitle());
			return view;
		}
		
		@Override
		public long getItemId(int position) {
			
			// TODO Auto-generated method stub
			return position;
		}
		
		@Override
		public Data getItem(int position) {
			
			// TODO Auto-generated method stub
			return mDatas.get(position);
		}
		
		@Override
		public int getCount() {
			
			// TODO Auto-generated method stub
			return mDatas.size();
		}
	}
	//打开或关闭侧边栏
	private void toggle()
	{
		SlidingMenu slidingMenu=((MainActivity)mActivity).getSlidingMenu();
		slidingMenu.toggle();
	}
	//给侧边栏设置数据
	public void setMenuData(ArrayList<Data> datas)
	{
		currentPos=0;
		mDatas=datas;
		lv_category.setAdapter(adapter);
		lv_category.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				if(currentPos!=position)
				{
					currentPos=position;
					adapter.notifyDataSetChanged();
				}
				//关闭菜单栏
				toggle();
				//侧边栏关闭后,要修改新闻中心的FrameLayout中的布局
				setCurrentDetailPager(position);
			}
			
		});
	}
	//设置当前菜单详情页
	private void setCurrentDetailPager(int position)
	{
		//获取新闻中心对象
		MainActivity activity=(MainActivity)mActivity;
		NewsCenterPager newsCenterPager=(NewsCenterPager)((ContentFragment)activity.getContentFragment()).getNewsCenterPager();
		//修改新闻中心的FrameLayout的布局
		newsCenterPager.setCurrentDetailPager(position);
	}
}
