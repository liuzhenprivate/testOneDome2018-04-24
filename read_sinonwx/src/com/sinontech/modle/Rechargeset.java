package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 充值设置表
 * @author ljj
 *
 */
@Entity(name = "TB_RECHARGE_SET")
public class Rechargeset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RECHARGE_SET_ID")
	private long id;
	//充值类型(0单独包月 1连续包月 2普通充值)
	@Column(name = "RECHARGE_TYPE")
	private int rechargeType;
	//免费时间段  22:00-6:00
	@Column(name = "FREE_TIME")
	private String freeTime;
	//状态 默认0正常1隐藏2删除
	@Column(name = "STATE")
	private int state;
	//充值金额 单位分
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
	private int bookCurrencyAll;
	//创建时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	//范围
	@Column(name = "USE_SCOPE")
	private int useScop;
	//购买限制(默认0无限制  1vip读者)
	@Column(name = "BUY_LIMIT")
	private String buyLimit;
	//有效期
	@Column(name = "EXP_DATE")
	private String expdate;
	//排序
	@Column(name = "SORT_NUM")
	private int sortNum;
	//备注
	@Column(name = "MEMO")
	private String memo;
	//更新时间
	@Column(name = "UPDATE_TIME")
	private String updateTime;
	//充值名称
	@Column(name = "RECHARGE_NAME")
	private String rechargeName;
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

	public int getBookCurrencyAll() {
		return bookCurrencyAll;
	}

	public void setBookCurrencyAll(int bookCurrencyAll) {
		this.bookCurrencyAll = bookCurrencyAll;
	}

	public int getUseScop() {
		return useScop;
	}

	public void setUseScop(int useScop) {
		this.useScop = useScop;
	}

	public String getBuyLimit() {
		return buyLimit;
	}

	public void setBuyLimit(String buyLimit) {
		this.buyLimit = buyLimit;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(int rechargeType) {
		this.rechargeType = rechargeType;
	}

	public String getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(String freeTime) {
		this.freeTime = freeTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRechargeName() {
		return rechargeName;
	}

	public void setRechargeName(String rechargeName) {
		this.rechargeName = rechargeName;
	}

	public Rechargeset() {
		super();
	}

	 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookCurrency ^ (bookCurrency >>> 32));
		result = prime * result + bookCurrencyAll;
		result = prime * result
				+ (int) (bookCurrencyGift ^ (bookCurrencyGift >>> 32));
		result = prime * result
				+ ((buyLimit == null) ? 0 : buyLimit.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((expdate == null) ? 0 : expdate.hashCode());
		result = prime * result
				+ ((freeTime == null) ? 0 : freeTime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		result = prime * result + (int) (money ^ (money >>> 32));
		result = prime * result
				+ ((rechargeName == null) ? 0 : rechargeName.hashCode());
		result = prime * result + rechargeType;
		result = prime * result + sortNum;
		result = prime * result + state;
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + useScop;
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
		Rechargeset other = (Rechargeset) obj;
		if (bookCurrency != other.bookCurrency)
			return false;
		if (bookCurrencyAll != other.bookCurrencyAll)
			return false;
		if (bookCurrencyGift != other.bookCurrencyGift)
			return false;
		if (buyLimit == null) {
			if (other.buyLimit != null)
				return false;
		} else if (!buyLimit.equals(other.buyLimit))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (expdate == null) {
			if (other.expdate != null)
				return false;
		} else if (!expdate.equals(other.expdate))
			return false;
		if (freeTime == null) {
			if (other.freeTime != null)
				return false;
		} else if (!freeTime.equals(other.freeTime))
			return false;
		if (id != other.id)
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
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
		if (sortNum != other.sortNum)
			return false;
		if (state != other.state)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (useScop != other.useScop)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rechargeset [id=" + id + ", rechargeType=" + rechargeType
				+ ", freeTime=" + freeTime + ", state=" + state + ", money="
				+ money + ", bookCurrencyGift=" + bookCurrencyGift
				+ ", bookCurrency=" + bookCurrency + ", bookCurrencyAll="
				+ bookCurrencyAll + ", createTime=" + createTime + ", useScop="
				+ useScop + ", buyLimit=" + buyLimit + ", expdate=" + expdate
				+ ", sortNum=" + sortNum + ", memo=" + memo + ", updateTime="
				+ updateTime + ", rechargeName=" + rechargeName + "]";
	}

	 

}
