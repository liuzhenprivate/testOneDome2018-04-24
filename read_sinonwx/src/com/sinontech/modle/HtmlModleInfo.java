package com.sinontech.modle;

import java.util.List;

 
/**
 * 页面配置模块表
 * @author ljj
 *
 */
public class HtmlModleInfo {
	private long id;

	private Html html;
	
	private List<HtmlModleDetailInfo> htmlModleDetaillist;
	//模板名称
	private String name;
	//显示几个书籍或者专题
	private int nums;
	
	 //所在位置排第几个
	private int place;
	
	//模块样式
	private String divClass;

	//模板类型1系统页面 2其他
	private int modleType;
	
	// 创建时间
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
  
	public Html getHtml() {
		return html;
	}

	public void setHtml(Html html) {
		this.html = html;
	}

	public String getDivClass() {
		return divClass;
	}

	public void setDivClass(String divClass) {
		this.divClass = divClass;
	}

	public int getModleType() {
		return modleType;
	}

	public void setModleType(int modleType) {
		this.modleType = modleType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public List<HtmlModleDetailInfo> getHtmlModleDetaillist() {
		return htmlModleDetaillist;
	}

	public void setHtmlModleDetaillist(List<HtmlModleDetailInfo> htmlModleDetaillist) {
		this.htmlModleDetaillist = htmlModleDetaillist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNums() {
		return nums;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public HtmlModleInfo() {
		super();
	}

	@Override
	public String toString() {
		return "HtmlModleInfo [id=" + id + ", html=" + html
				+ ", htmlModleDetaillist=" + htmlModleDetaillist + ", name="
				+ name + ", nums=" + nums + ", place=" + place + ", divClass="
				+ divClass + ", modleType=" + modleType + ", createTime="
				+ createTime + "]";
	}

	 

	 
 
}