package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 签到日志表
 * @author ljj
 *
 */
@Entity(name = "TB_SIGN_LOG")
public class Signlog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SIGN_LOG_ID")
	private long id;
	//用户ID
	@Column(name = "USERID")
	private long userId;
	//渠道ID
	@Column(name = "USER_ID")
	private long channelId;
	//微信公众号ID
	@Column(name = "WEBCHAT_ID")
	private long webchatId;
	//记录类型 默认1每日签到2领取奖励
	@Column(name = "LOG_TYPE")
	private String logType;
	//对应奖励次数  对应奖励设置表的times字段
	@Column(name = "TIMES")
	private int times;
	//获取的书币
	@Column(name = "BOOK_CURRENCY")
	private long bookCurrency;
	//签到月份  YYYY-MM
	@Column(name = "SIGN_MONTH")
	private String signMonth;
	//签到时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public long getBookCurrency() {
		return bookCurrency;
	}

	public void setBookCurrency(long bookCurrency) {
		this.bookCurrency = bookCurrency;
	}

	public String getSignMonth() {
		return signMonth;
	}

	public void setSignMonth(String signMonth) {
		this.signMonth = signMonth;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public long getWebchatId() {
		return webchatId;
	}

	public void setWebchatId(long webchatId) {
		this.webchatId = webchatId;
	}

	public Signlog() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookCurrency ^ (bookCurrency >>> 32));
		result = prime * result + (int) (channelId ^ (channelId >>> 32));
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((logType == null) ? 0 : logType.hashCode());
		result = prime * result
				+ ((signMonth == null) ? 0 : signMonth.hashCode());
		result = prime * result + times;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + (int) (webchatId ^ (webchatId >>> 32));
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
		Signlog other = (Signlog) obj;
		if (bookCurrency != other.bookCurrency)
			return false;
		if (channelId != other.channelId)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (logType == null) {
			if (other.logType != null)
				return false;
		} else if (!logType.equals(other.logType))
			return false;
		if (signMonth == null) {
			if (other.signMonth != null)
				return false;
		} else if (!signMonth.equals(other.signMonth))
			return false;
		if (times != other.times)
			return false;
		if (userId != other.userId)
			return false;
		if (webchatId != other.webchatId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Signlog [id=" + id + ", userId=" + userId + ", channelId="
				+ channelId + ", webchatId=" + webchatId + ", logType="
				+ logType + ", times=" + times + ", bookCurrency="
				+ bookCurrency + ", signMonth=" + signMonth + ", createTime="
				+ createTime + "]";
	}
	 
}
