package com.hyb.zhbj.view.tab;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.hyb.zhbj.R;
import com.hyb.zhbj.model.CategoryChildren;
import com.hyb.zhbj.model.detail.DetailBean;
import com.hyb.zhbj.model.detail.DetailNews;
import com.hyb.zhbj.model.detail.DetailTopNews;
import com.hyb.zhbj.utils.CacheUtil;
import com.hyb.zhbj.utils.GlobalConstants;
import com.hyb.zhbj.view.PullToRefreshListView;
import com.hyb.zhbj.view.menu.BaseMenuDetailPager;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

/** 
   * 页签页面对象
   * @Package com.hyb.zhbj.view.tab
   * @author hyb
   * @date 创建时间：2016年7月21日 上午10:39:36 
*/
public class TabDetailPager extends BaseMenuDetailPager {
	private CategoryChildren mChild;
	private ViewPager viewPager;
	private String mUrl;
	private ArrayList<DetailTopNews> topNews;
	private TextView tv_title;
	private CirclePageIndicator indicator_circle;
	private PullToRefreshListView lvTopNews;
	private ArrayList<DetailNews> detailNews;
	private View headerView;
	public TabDetailPager(Activity activity,CategoryChildren child) {
		super(activity);
		// TODO Auto-generated constructor stub
		mChild=child;
		mUrl = GlobalConstants.SERVER_URL+ mChild.getUrl();
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		View view=View.inflate(mActivity, R.layout.pager_tab_detail, null);
		headerView=View.inflate(mActivity, R.layout.list_item_header, null);
		
		viewPager = (ViewPager)headerView.findViewById(R.id.vp_top_news);
		tv_title = (TextView)headerView.findViewById(R.id.tv_title);
		indicator_circle = (CirclePageIndicator)headerView.findViewById(R.id.indicator_circle);
		lvTopNews = (PullToRefreshListView)view.findViewById(R.id.lv_top_news);
		//把上面的viewpager设置成listview的headview
		lvTopNews.addHeaderView(headerView);
		return view;
	}
	class TopNewsAdapter extends PagerAdapter
	{
		private BitmapUtils mBitmapUtils;
		public TopNewsAdapter() {
			mBitmapUtils = new BitmapUtils(mActivity);
			mBitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);//设置图片还没下载完之前的默认显示图片
		}
		@Override
		public int getCount() {			
			// TODO Auto-generated method stub
			return topNews.size();
		}
		@Override
		public boolean isViewFromObject(View view, Object object) {			
			// TODO Auto-generated method stub
			return view==object;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {		
			// TODO Auto-generated method stub
			ImageView view=new ImageView(mActivity);
			view.setScaleType(ScaleType.FIT_XY);
			//FIT_XY宽高填充父控件
			String imgUrl=topNews.get(position).getTopimage();
			imgUrl=imgUrl.replace("10.0.2.2", "192.168.0.3");			
			//下载图片
			mBitmapUtils.display(view, imgUrl);
			//view.setImageResource(R.drawable.topnews_item_default);
			container.addView(view);
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {			
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}
	}
	@Override
	public void initData() {		
		// TODO Auto-generated method stub
		super.initData();
		String cache=CacheUtil.getCache(mUrl, mActivity);
		if(!TextUtils.isEmpty(cache))//检查下是否有缓存
		{
			processData(cache);
		}
		getDataFromServer();
	}
	private void processData(String data)
	{
		DetailBean detailBean=new Gson().fromJson(data, DetailBean.class);
		//
		topNews = detailBean.getData().getTopnews();
		//新闻列表
		detailNews = detailBean.getData().getNews();
		if(detailNews!=null && detailNews.size()>0)
		{
			lvTopNews.setAdapter(new NewsAdapter());
		}
		if(topNews!=null && topNews.size()>0)
		{
			tv_title.setText(topNews.get(0).getTitle());
			indicator_circle.onPageSelected(0);//小圆圈指示器指向第一个页面
			viewPager.setAdapter(new TopNewsAdapter());
			indicator_circle.setViewPager(viewPager);
			indicator_circle.setSnap(true);
			indicator_circle.setOnPageChangeListener(new OnPageChangeListener() {		
				@Override
				public void onPageSelected(int position) {				
					// TODO Auto-generated method stub	
					DetailTopNews top= topNews.get(position);
					tv_title.setText(top.getTitle());
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
		}
		
			
	}
	private void getDataFromServer()
	{
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {				
				// TODO Auto-generated method stub
				String result=responseInfo.result;
				CacheUtil.setCache(mUrl, result, mActivity);//设置缓存
				processData(result);
				
			}
			@Override
			public void onFailure(HttpException error, String msg) {				
				// TODO Auto-generated method stub				
			}
			
		});
	}
	class NewsAdapter extends BaseAdapter{
		private BitmapUtils mBitmapUtils;
		public NewsAdapter() {
			mBitmapUtils = new BitmapUtils(mActivity);
			mBitmapUtils.configDefaultLoadingImage(R.drawable.topnews_item_default);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return detailNews.size();
		}
		@Override
		public DetailNews getItem(int position) {	
			// TODO Auto-generated method stub
			return detailNews.get(position);
		}
		@Override
		public long getItemId(int position) {		
			// TODO Auto-generated method stub
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder=null;
			if(convertView==null)
			{
				convertView=View.inflate(mActivity, R.layout.list_item_news, null);
				holder=new ViewHolder();
				holder.iv_icon=(ImageView)convertView.findViewById(R.id.iv_icon);
				holder.tv_title=(TextView)convertView.findViewById(R.id.tv_title);
				holder.tv_content=(TextView)convertView.findViewById(R.id.tv_content);
				holder.tv_pubdate=(TextView)convertView.findViewById(R.id.tv_pubdate);
				convertView.setTag(holder);
			}
			else
			{
				holder=(ViewHolder)convertView.getTag();
			}
			
			DetailNews news=detailNews.get(position);
			String imgUrl=news.getListimage().replace("10.0.2.2", "192.168.0.3");
			mBitmapUtils.display(holder.iv_icon, imgUrl);
			holder.tv_title.setText(news.getTitle());
			
			return convertView;
		}
		class ViewHolder
		{
			public ImageView iv_icon;
			public TextView tv_title;
			public TextView tv_content;
			public TextView tv_pubdate;
		}
		
	}
}
