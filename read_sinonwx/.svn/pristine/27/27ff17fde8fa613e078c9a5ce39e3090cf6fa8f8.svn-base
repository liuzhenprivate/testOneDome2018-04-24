package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 充值日志表
 * @author ljj
 *
 */
@Entity(name = "TB_RECHARGE_LOG")
public class Rechargelog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECHARGE_LOG_ID")
	private long id;
	//读者ID
	@Column(name = "USERID")
	private long userId;
	//渠道  默认微信
	@Column(name = "USER_ID")
	private long channelId;
	//微信公众号ID
	@Column(name = "WEBCHAT_ID")
	private long webchatId;
	//充值金额
	@Column(name = "MONEY")
	private long money;
	//赠送的书币
	@Column(name = "BOOK_CURRENCY_GIFT")
	private long bookCurrencyGift;
	//对应的书币
	@Column(name = "BOOK_CURRENCY")
	private long bookCurrency;
	//总充值书币
	@Column(name = "BOOK_CURRENCY_ALL")
	private long bookCurrencyAll;
	//消费金额
	@Column(name = "CONSUME")
	private long consume;
	//充值时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	//状态//0未付款，1已付款，2付款失败，3充值成功，4充值失败，5申请退款6退款成功7退款失败8撤销支付
	@Column(name = "STATE")
	private int state;
	//支付结果
	@Column(name = "RESULT_PAY")
	private String resultPay;
	//支付完成时间
	@Column(name = "TIME_PAY")
	private String timePay;
	//充值结果
	@Column(name = "RESULT_RECHARGE")
	private String resultRecharge;
	//充值完成时间
	@Column(name = "TIME_RECHARGE")
	private String timeRecharge;
	//渠道
	@Column(name = "CHANNEL")
	private String channel;
	//渠道分成
	@Column(name = "CHANNEL_INCOME")
	private String channelIncome;
	//平台收益
	@Column(name = "INCOME")
	private int income;
	//上游收益
	@Column(name = "UPSTREAM_INCOME")
	private int upstreamIncome;
	//充值类型(默认0 普通充值 1单月vip充值2连续VIP充值)
	@Column(name = "RECHARGE_TYPE")
	private int rechargeType;
	
	@Column(name = "EXP_DATE")
	private String expDate;
	//充值名称
	@Column(name = "RECHARGE_NAME")
	private String rechargeName;
	
	//订单号
	@Column(name = "TRADE_NO")
	private String tradeNo;
	
	//书籍ID
	@Column(name = "ARTICLE_ID")
	private long articleId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	 

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

 

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public long getBookCurrencyGift() {
		return bookCurrencyGift;
	}

	public void setBookCurrencyGift(long bookCurrencyGift) {
		this.bookCurrencyGift = bookCurrencyGift;
	}

	public long getBookCurrency() {
		return bookCurrency;
	}

	public void setBookCurrency(long bookCurrency) {
		this.bookCurrency = bookCurrency;
	}

	public long getBookCurrencyAll() {
		return bookCurrencyAll;
	}

	public void setBookCurrencyAll(long bookCurrencyAll) {
		this.bookCurrencyAll = bookCurrencyAll;
	}

	 

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getConsume() {
		return consume;
	}

	public void setConsume(long consume) {
		this.consume = consume;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getResultPay() {
		return resultPay;
	}

	public void setResultPay(String resultPay) {
		this.resultPay = resultPay;
	}

	public String getTimePay() {
		return timePay;
	}

	public void setTimePay(String timePay) {
		this.timePay = timePay;
	}

	public String getResultRecharge() {
		return resultRecharge;
	}

	public void setResultRecharge(String resultRecharge) {
		this.resultRecharge = resultRecharge;
	}

	public String getTimeRecharge() {
		return timeRecharge;
	}

	public void setTimeRecharge(String timeRecharge) {
		this.timeRecharge = timeRecharge;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannelIncome() {
		return channelIncome;
	}

	public void setChannelIncome(String channelIncome) {
		this.channelIncome = channelIncome;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getUpstreamIncome() {
		return upstreamIncome;
	}

	public void setUpstreamIncome(int upstreamIncome) {
		this.upstreamIncome = upstreamIncome;
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

	public int getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getRechargeName() {
		return rechargeName;
	}

	public void setRechargeName(String rechargeName) {
		this.rechargeName = rechargeName;
	}

	public Rechargelog() {
		super();
	}

	
	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (articleId ^ (articleId >>> 32));
		result = prime * result + (int) (bookCurrency ^ (bookCurrency >>> 32));
		result = prime * result
				+ (int) (bookCurrencyAll ^ (bookCurrencyAll >>> 32));
		result = prime * result
				+ (int) (bookCurrencyGift ^ (bookCurrencyGift >>> 32));
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + (int) (channelId ^ (channelId >>> 32));
		result = prime * result
				+ ((channelIncome == null) ? 0 : channelIncome.hashCode());
		result = prime * result + (int) (consume ^ (consume >>> 32));
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((expDate == null) ? 0 : expDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + income;
		result = prime * result + (int) (money ^ (money >>> 32));
		result = prime * result
				+ ((rechargeName == null) ? 0 : rechargeName.hashCode());
		result = prime * result + rechargeType;
		result = prime * result
				+ ((resultPay == null) ? 0 : resultPay.hashCode());
		result = prime * result
				+ ((resultRecharge == null) ? 0 : resultRecharge.hashCode());
		result = prime * result + state;
		result = prime * result + ((timePay == null) ? 0 : timePay.hashCode());
		result = prime * result
				+ ((timeRecharge == null) ? 0 : timeRecharge.hashCode());
		result = prime * result + ((tradeNo == null) ? 0 : tradeNo.hashCode());
		result = prime * result + upstreamIncome;
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
		Rechargelog other = (Rechargelog) obj;
		if (articleId != other.articleId)
			return false;
		if (bookCurrency != other.bookCurrency)
			return false;
		if (bookCurrencyAll != other.bookCurrencyAll)
			return false;
		if (bookCurrencyGift != other.bookCurrencyGift)
			return false;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (channelId != other.channelId)
			return false;
		if (channelIncome == null) {
			if (other.channelIncome != null)
				return false;
		} else if (!channelIncome.equals(other.channelIncome))
			return false;
		if (consume != other.consume)
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (expDate == null) {
			if (other.expDate != null)
				return false;
		} else if (!expDate.equals(other.expDate))
			return false;
		if (id != other.id)
			return false;
		if (income != other.income)
			return false;
		if (money != other.money)
			return false;
		if (rechargeName == null) {
			if (other.rechargeName != null)
				return false;
		} else if (!rechargeName.equals(other.rechargeName))
			return false;
		if (rechargeType != other.rechargeType)
			return false;
		if (resultPay == null) {
			if (other.resultPay != null)
				return false;
		} else if (!resultPay.equals(other.resultPay))
			return false;
		if (resultRecharge == null) {
			if (other.resultRecharge != null)
				return false;
		} else if (!resultRecharge.equals(other.resultRecharge))
			return false;
		if (state != other.state)
			return false;
		if (timePay == null) {
			if (other.timePay != null)
				return false;
		} else if (!timePay.equals(other.timePay))
			return false;
		if (timeRecharge == null) {
			if (other.timeRecharge != null)
				return false;
		} else if (!timeRecharge.equals(other.timeRecharge))
			return false;
		if (tradeNo == null) {
			if (other.tradeNo != null)
				return false;
		} else if (!tradeNo.equals(other.tradeNo))
			return false;
		if (upstreamIncome != other.upstreamIncome)
			return false;
		if (userId != other.userId)
			return false;
		if (webchatId != other.webchatId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rechargelog [id=" + id + ", userId=" + userId + ", channelId="
				+ channelId + ", webchatId=" + webchatId + ", money=" + money
				+ ", bookCurrencyGift=" + bookCurrencyGift + ", bookCurrency="
				+ bookCurrency + ", bookCurrencyAll=" + bookCurrencyAll
				+ ", consume=" + consume + ", createTime=" + createTime
				+ ", state=" + state + ", resultPay=" + resultPay
				+ ", timePay=" + timePay + ", resultRecharge=" + resultRecharge
				+ ", timeRecharge=" + timeRecharge + ", channel=" + channel
				+ ", channelIncome=" + channelIncome + ", income=" + income
				+ ", upstreamIncome=" + upstreamIncome + ", rechargeType="
				+ rechargeType + ", expDate=" + expDate + ", rechargeName="
				+ rechargeName + ", tradeNo=" + tradeNo + ", articleId="
				+ articleId + "]";
	}

	
	 

}
