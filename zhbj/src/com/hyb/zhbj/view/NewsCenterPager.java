package com.hyb.zhbj.view;

import com.hyb.zhbj.utils.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/** 
   *
   * @Package com.hyb.zhbj.view
   * @author hyb
   * @date 创建时间：2016年7月20日 下午3:40:02 
*/
public class NewsCenterPager extends BasePager {

	public NewsCenterPager(Activity activity) {
		
		super(activity);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
		TextView textView=new TextView(mActivity);
		textView.setText("新闻中心");
		textView.setGravity(Gravity.CENTER);
		fl_content.addView(textView);
		//请求服务器获取数据
		getDataFromServer();
	}
	private void getDataFromServer()
	{
		HttpUtils httpUtils=new HttpUtils();
		httpUtils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				error.printStackTrace();
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				Log.d("hyb", "获取数据成功");
				//解析数据
				
			}
		});
	}
}
