package com.hyb.zhbj.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	protected Activity mActivity;
	//fragment创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mActivity=getActivity();
	}
	//初始化fragment的布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=initView();
		return view;
	}
	//初始化布局,必须由子类实现
	public abstract View initView();
	//初始化数据,必须由子类实现
	public abstract void initData();
	//fragment所依赖的activity的onCreate方法执行结束
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//初始化数据
		initData();
	}
}
