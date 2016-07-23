package com.hyb.zhbj.model.detail;

import java.util.ArrayList;

/** 
   *
   * @Package com.hyb.zhbj.model.detail
   * @author hyb
   * @date 创建时间：2016年7月22日 下午6:44:48 
*/
public class DetailData {
	private String countcommenturl;
	private String more;
	private ArrayList<DetailNews> news;
	private String title;
	
	private ArrayList<DetailTopic> topic;
	private ArrayList<DetailTopNews> topnews;
	
	public String getCountcommenturl() {
		return countcommenturl;
	}

	public void setCountcommenturl(String countcommenturl) {
		this.countcommenturl = countcommenturl;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<DetailNews> getNews() {
		return news;
	}

	public void setNews(ArrayList<DetailNews> news) {
		this.news = news;
	}

	public ArrayList<DetailTopic> getTopic() {
		return topic;
	}

	public void setTopic(ArrayList<DetailTopic> topic) {
		this.topic = topic;
	}

	

	public ArrayList<DetailTopNews> getTopnews() {
		return topnews;
	}

	public void setTopnews(ArrayList<DetailTopNews> topnews) {
		this.topnews = topnews;
	}

	public DetailData() {
		// TODO Auto-generated constructor stub
	}

}
