package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 页面配置模块表
 * @author ljj
 *
 */
@Entity(name = "TB_HTMLMODLE")
public class HtmlModle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HTMLMODLE_ID")
	private long id;

	@OneToOne
	@JoinColumn(name = "HTML_ID")
	private Html html;
	//模板名称
	@Column(name = "MODLENAME")
	private String name;
	//显示几个书籍或者专题
	@Column(name = "NUMS")
	private int nums;
	
	 //所在位置排第几个
	@Column(name = "PLACE")
	private int place;
	
	//模块样式
	@Column(name = "DIV_CLASS")
	private String divClass;

	//模板类型1书籍 2专题
	@Column(name = "MODLE_TYPE")
	private int modleType;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
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

	public HtmlModle() {
		super();
	}

	@Override
	public String toString() {
		return "HtmlModle [id=" + id + ", html=" + html + ", name=" + name
				+ ", nums=" + nums + ", place=" + place + ", divClass="
				+ divClass + ", modleType=" + modleType + ", createTime="
				+ createTime + "]";
	}

	 
	 
 
}