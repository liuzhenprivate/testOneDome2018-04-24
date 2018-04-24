package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 签到设置表
 * @author ljj
 *
 */
@Entity(name = "TB_SIGN_SET")
public class Signset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SIGN_SET_ID")
	private long id;
	//签到设置名称
	@Column(name = "CNAME")
	private String cname;
	//签到天数(默认0位每日签到)
	@Column(name = "TIMES")
	private int times;
	//赠送书币
	@Column(name = "BOOK_CURRENCY")
	private long bookCurrency;
	//签到类型(默认0,1为连续签到)
	@Column(name = "TYPE")
	private int type;
	@Column(name = "CREATE_TIME")
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	 

	public long getBookCurrency() {
		return bookCurrency;
	}

	public void setBookCurrency(long bookCurrency) {
		this.bookCurrency = bookCurrency;
	}

	public Signset() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookCurrency ^ (bookCurrency >>> 32));
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + times;
		result = prime * result + type;
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
		Signset other = (Signset) obj;
		if (bookCurrency != other.bookCurrency)
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (times != other.times)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Signset [id=" + id + ", cname=" + cname + ", times=" + times
				+ ", bookCurrency=" + bookCurrency + ", type=" + type
				+ ", createTime=" + createTime + "]";
	}

}
