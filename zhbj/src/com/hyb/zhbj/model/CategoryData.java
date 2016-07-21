package com.hyb.zhbj.model;

import java.io.Serializable;
import java.util.ArrayList;

/** 
   *
   * @Package com.hyb.zhbj.model
   * @author hyb
   * @date 创建时间：2016年7月20日 下午8:27:32 
*/
public class CategoryData implements Serializable{
	private String retcode;
	private ArrayList<String> extend;
	private ArrayList<Data> data;
	public CategoryData() {

		// TODO Auto-generated constructor stub
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public ArrayList<String> getExtend() {
		return extend;
	}
	public void setExtend(ArrayList<String> extend) {
		this.extend = extend;
	}
	public ArrayList<Data> getData() {
		return data;
	}
	public void setData(ArrayList<Data> data) {
		this.data = data;
	}
	
	
}
