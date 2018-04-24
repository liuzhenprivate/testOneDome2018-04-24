package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 页面配置表
 * @author ljj
 *
 */
@Entity(name = "TB_HTML")
public class Html {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HTML_ID")
	private long id;

	//位置类型1书城 2男生 3女生 4VIP
	@Column(name = "PLACE_TYPE")
	private int placType;

	//名称
	@Column(name = "NAME")
	private String name;

	//页面类型1系统页面 2其他
	@Column(name = "HTML_TYPE")
	private int htmlType;

	//状态 1上线 2未上线
	@Column(name = "STATE")
	private int state;

	//页面连接
	@Column(name = "HTML_URL")
	private String htmlUrl;
	//访问量
	@Column(name = "PVS")
	private long pvs;
	//描述说明
	@Column(name = "MEMO")
	private String memo;
	
	// 创建时间
	@Column(name = "CREATE_TIME")
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

	public Html() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + htmlType;
		result = prime * result + ((htmlUrl == null) ? 0 : htmlUrl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + placType;
		result = prime * result + (int) (pvs ^ (pvs >>> 32));
		result = prime * result + state;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Html other = (Html) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (htmlType != other.htmlType)
			return false;
		if (htmlUrl == null) {
			if (other.htmlUrl != null)
				return false;
		} else if (!htmlUrl.equals(other.htmlUrl))
			return false;
		if (id != other.id)
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (placType != other.placType)
			return false;
		if (pvs != other.pvs)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Html [id=" + id + ", placType=" + placType + ", name=" + name
				+ ", htmlType=" + htmlType + ", state=" + state + ", htmlUrl="
				+ htmlUrl + ", pvs=" + pvs + ", memo=" + memo + ", createTime="
				+ createTime + "]";
	}

	 
 
}
