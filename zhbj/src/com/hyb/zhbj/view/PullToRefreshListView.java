package com.hyb.zhbj.view;

import com.hyb.zhbj.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/** 
   *
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月23日 下午4:25:26 
*/
public class PullToRefreshListView extends ListView {

	private int headrViewHeight;
	private int starty=-1;
	private View mHeaderView;

	public PullToRefreshListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initHeaderView();
	}

	public PullToRefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initHeaderView();
	}

	public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initHeaderView();
	}

	//初始化頭佈局
	private void initHeaderView()
	{
		mHeaderView = View.inflate(getContext(), R.layout.pull_to_refresh, null);
		this.addHeaderView(mHeaderView);
		//隱藏headerview
		mHeaderView.measure(0, 0);
		headrViewHeight = mHeaderView.getMeasuredHeight();
		mHeaderView.setPadding(0, -headrViewHeight, 0, 0);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		// TODO Auto-generated method stub
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			starty = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			if(starty==-1)
			{
				starty = (int) ev.getY();
			}
			int endy=(int) ev.getY();
			int dy=endy-starty;
			int firstVisiblePos=getFirstVisiblePosition();//當前顯示的第一個item的位置
			//必須下拉並且當前顯示的是第一個item
			if(dy>0 && firstVisiblePos==0)
			{
				int padding=dy-headrViewHeight;
				mHeaderView.setPadding(0, padding, 0, 0);
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
}
