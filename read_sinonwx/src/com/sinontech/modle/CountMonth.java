package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 月报统计
 * @author ljj
 *
 */
@Entity(name = "TB_COUNT_MONTH")
public class CountMonth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNT_MONTH_ID")
	private long id;
	//渠道ID
	@Column(name = "USER_ID")
	private long channelId;
	//微信公众号ID
	@Column(name = "WEBCHAT_ID")
	private long webchatId;
	//会员总数
	@Column(name = "USERS")
	private long users;
	//充值总数
	@Column(name = "RECHARGES")
	private long recharges;
	//充值笔数
	@Column(name = "RECHARGETIMES")
	private long rechargeTimes;
	//消费阅读币`CONSUMES`
	@Column(name = "CONSUMES")
	private long consumes;
		
	//消费人民币
	@Column(name = "MONEY")
	private long money;
	//消费笔数
	@Column(name = "CONSUMENUMBER")
	private long consumenumber;
	//充值阅读币数
	@Column(name = "BOOK_CURRENCY")
	private long bookCurrency;
	//赠送阅读币数
	@Column(name = "BOOK_CURRENCY_GIFT")
	private long bookCurrencyGift;
	//统计月份
	@Column(name = "COUNT_MONTH")
	private String countMonth;
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	@Column(name = "UPDATE_TIME")
	private String updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	 

	public long getWebchatId() {
		return webchatId;
	}

	public void setWebchatId(long webchatId) {
		this.webchatId = webchatId;
	}

	public long getUsers() {
		return users;
	}

	public void setUsers(long users) {
		this.users = users;
	}

	public long getRecharges() {
		return recharges;
	}

	public void setRecharges(long recharges) {
		this.recharges = recharges;
	}

	 

	public String getCountMonth() {
		return countMonth;
	}

	public void setCountMonth(String countMonth) {
		this.countMonth = countMonth;
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

	public long getBookCurrencyGift() {
		return bookCurrencyGift;
	}

	public void setBookCurrencyGift(long bookCurrencyGift) {
		this.bookCurrencyGift = bookCurrencyGift;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public long getRechargeTimes() {
		return rechargeTimes;
	}

	public void setRechargeTimes(long rechargeTimes) {
		this.rechargeTimes = rechargeTimes;
	}

	public long getConsumes() {
		return consumes;
	}

	public void setConsumes(long consumes) {
		this.consumes = consumes;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getConsumenumber() {
		return consumenumber;
	}

	public void setConsumenumber(long consumenumber) {
		this.consumenumber = consumenumber;
	}

	public CountMonth() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookCurrency ^ (bookCurrency >>> 32));
		result = prime * result
				+ (int) (bookCurrencyGift ^ (bookCurrencyGift >>> 32));
		result = prime * result + (int) (channelId ^ (channelId >>> 32));
		result = prime * result
				+ (int) (consumenumber ^ (consumenumber >>> 32));
		result = prime * result + (int) (consumes ^ (consumes >>> 32));
		result = prime * result
				+ ((countMonth == null) ? 0 : countMonth.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (money ^ (money >>> 32));
		result = prime * result
				+ (int) (rechargeTimes ^ (rechargeTimes >>> 32));
		result = prime * result + (int) (recharges ^ (recharges >>> 32));
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + (int) (users ^ (users >>> 32));
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
		CountMonth other = (CountMonth) obj;
		if (bookCurrency != other.bookCurrency)
			return false;
		if (bookCurrencyGift != other.bookCurrencyGift)
			return false;
		if (channelId != other.channelId)
			return false;
		if (consumenumber != other.consumenumber)
			return false;
		if (consumes != other.consumes)
			return false;
		if (countMonth == null) {
			if (other.countMonth != null)
				return false;
		} else if (!countMonth.equals(other.countMonth))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (money != other.money)
			return false;
		if (rechargeTimes != other.rechargeTimes)
			return false;
		if (recharges != other.recharges)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (users != other.users)
			return false;
		if (webchatId != other.webchatId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CountMonth [id=" + id + ", channelId=" + channelId
				+ ", webchatId=" + webchatId + ", users=" + users
				+ ", recharges=" + recharges + ", rechargeTimes="
				+ rechargeTimes + ", consumes=" + consumes + ", money=" + money
				+ ", consumenumber=" + consumenumber + ", bookCurrency="
				+ bookCurrency + ", bookCurrencyGift=" + bookCurrencyGift
				+ ", countMonth=" + countMonth + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	 
	 
	
}
