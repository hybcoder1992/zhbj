package com.hyb.zhbj.model.detail;

import java.util.Date;

/** 
   *
   * @Package com.hyb.zhbj.model.detail
   * @author hyb
   * @date 创建时间：2016年7月22日 下午6:46:49 
*/
public class DetailNews {
	/**
	 * comment: true,
		commentlist: http://10.0.2.2:8080/zhbj/10007/comment_1.json,
		commenturl: http://zhbj.qianlong.com/client/user/newComment/35319,
		id: 35318,
		listimage: http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg,
		pubdate: "2014-10-1109:52",
		title: "APEC期间净空区放带灯风筝可拘留",
		type: "news",
		url: http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html
	 * */
	private boolean comment;
	private String commentlist;
	private String commenturl;
	private String id;
	private String listimage;
	private String pubdate;
	private String title;
	private String type;
	private String url;
	
	public DetailNews() {

		// TODO Auto-generated constructor stub
	}

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public String getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(String commentlist) {
		this.commentlist = commentlist;
	}

	public String getCommenturl() {
		return commenturl;
	}

	public void setCommenturl(String commenturl) {
		this.commenturl = commenturl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListimage() {
		return listimage;
	}

	public void setListimage(String listimage) {
		this.listimage = listimage;
	}

	

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
