package com.hyb.zhbj.model;
/** 
   *
   * @Package com.hyb.zhbj.model
   * @author hyb
   * @date 创建时间：2016年7月20日 下午8:31:34 
*/
public class CategoryChildren {
	private String _id;
    private String title;
    private Integer  type;
    private String url;
	public CategoryChildren() {

		// TODO Auto-generated constructor stub
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
