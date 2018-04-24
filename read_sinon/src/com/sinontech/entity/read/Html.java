package com.sinontech.entity.read;

import java.util.List;

/**
 * 页面配置表
 * @author ljj
 *
 */
public class Html {
	
	private long id;
	
	private List<HtmlModle> htmlmodles;
	
	//位置类型1书城 2男生 3女生 4VIP
	private int placType;

	//名称
	private String name;

	//页面类型1系统页面 2其他
	private int htmlType;

	//状态 1上线 2未上线
	private int state;

	//页面连接
	private String htmlUrl;
	//访问量
	private long pvs;
	//描述说明
	private String memo;
	
	// 创建时间
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
 
	public int getPlacType() {
		return placType;
	}

	public void setPlacType(int placType) {
		this.placType = placType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
 
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public long getPvs() {
		return pvs;
	}

	public void setPvs(long pvs) {
		this.pvs = pvs;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getHtmlType() {
		return htmlType;
	}

	public void setHtmlType(int htmlType) {
		this.htmlType = htmlType;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public List<HtmlModle> getHtmlmodles() {
		return htmlmodles;
	}

	public void setHtmlmodles(List<HtmlModle> htmlmodles) {
		this.htmlmodles = htmlmodles;
	}
 

	public Html() {
		super();
	}

	@Override
	public String toString() {
		return "Html [id=" + id   + ", htmlmodles="
				+ htmlmodles + ", placType=" + placType + ", name=" + name
				+ ", htmlType=" + htmlType + ", state=" + state + ", htmlUrl="
				+ htmlUrl + ", pvs=" + pvs + ", memo=" + memo + ", createTime="
				+ createTime + "]";
	}

}
