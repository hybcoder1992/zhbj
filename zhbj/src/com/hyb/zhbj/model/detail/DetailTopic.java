package com.hyb.zhbj.model.detail;
/** 
   *
   * @Package com.hyb.zhbj.model.detail
   * @author hyb
   * @date 创建时间：2016年7月22日 下午6:47:33 
*/
public class DetailTopic {
/**
 * description: "11111111",
	id: 10101,
	listimage: http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg,
	sort: 1,
	title: "北京",
	url: http://10.0.2.2:8080/zhbj/10007/list_1.json
 * */
	private String description;
	private String id;
	private String listimage;
	private String sort;
	private String title;
	private String url;
	public DetailTopic() {

		// TODO Auto-generated constructor stub
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
