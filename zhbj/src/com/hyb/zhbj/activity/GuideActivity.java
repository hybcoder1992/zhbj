package com.hyb.zhbj.activity;

import java.util.ArrayList;
import java.util.List;

import com.hyb.zhbj.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class GuideActivity extends Activity {

	private ViewPager vp_guide;
	private int [] images=new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	List<ImageView> imgViews=null;
	private LinearLayout ll_container;//用来装指示器小圆点
	private int distance;//第一个圆点和第二个圆点间距离
	private ImageView iv_reddot;//底部红色小圆点
	private Button btn_start;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		vp_guide = (ViewPager)findViewById(R.id.vp_guide);
		ll_container = (LinearLayout)findViewById(R.id.ll_container);
		iv_reddot = (ImageView)findViewById(R.id.iv_dot_red);
		btn_start=(Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//主页面
				SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
				Editor editor=sp.edit();
				editor.putBoolean("is_first_enter", false);
				editor.commit();
				Intent intent=new Intent(GuideActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		initData();
		vp_guide.setAdapter(new GuideViewPagerAdapter());
		vp_guide.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if(position==imgViews.size()-1)
				{
					btn_start.setVisibility(View.VISIBLE);
				}
			}
			//positionOffset移动偏移百分比
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				//计算小红点左边距
				float leftmargin=distance * (positionOffset + position);
				RelativeLayout.LayoutParams layoutParams= (android.widget.RelativeLayout.LayoutParams) iv_reddot.getLayoutParams();
				layoutParams.leftMargin=(int) leftmargin;
				iv_reddot.setLayoutParams(layoutParams);
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	private void initData()
	{
		imgViews=new ArrayList<ImageView>();
		ImageView view=null;
		for(int i=0;i<images.length;i++)
		{
			view=new ImageView(this);
			view.setBackgroundResource(images[i]);
			imgViews.add(view);
			//初始化小圆点
			ImageView iv_dot=new ImageView(this);
			
			iv_dot.setImageResource(R.drawable.dot_circle);
			if(i>0)//从第二个点开始设置左边距
			{
				LinearLayout.LayoutParams layoutParams=new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
						LinearLayout.LayoutParams.WRAP_CONTENT);
				layoutParams.setMargins(15, 0, 0, 0);
				iv_dot.setLayoutParams(layoutParams);
			}
			
			ll_container.addView(iv_dot);
		}
		//计算两个原点距离,移动距离的值=第二个圆点left值-第一个圆点left值
		//measure->layout->ondraw(在activity的oncreate方法之后走这个流程)
		//监听layout方法结束事件,位置确定好后在获取两个圆点距离	
		iv_reddot.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				//layout方法执行结束后的回调
				//移除OnGlobalLayoutListener
				iv_reddot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				distance = ll_container.getChildAt(1).getLeft() - ll_container.getChildAt(0).getLeft();
				Log.d("hyb", distance+"");
			}
		});
	}
	class GuideViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imgViews.size();
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		//初始化item布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			ImageView view=imgViews.get(position);
			container.addView(view);
			return view;
		}
		//销毁item
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			ImageView view=imgViews.get(position);
			container.removeView(view);
			//super.destroyItem(container, position, object);
		}
	}
}
