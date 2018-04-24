package com.sinontech.util.wx;


public class UserInfo {

	 
	private long id;
	
	private long webchatId;
	
	private String nickname;
	private String openid;
	private int sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String privilege;
	private String unionid;
	
	private String createTime;
	
	private long bookCurrency;
	private long bookCurrencyGift;
	private long bookCurrencyAll;
	private Long rechargeMoney;
	private String level;
	
	
	private int signtimes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
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

	public long getBookCurrencyAll() {
		return bookCurrencyAll;
	}

	public void setBookCurrencyAll(long bookCurrencyAll) {
		this.bookCurrencyAll = bookCurrencyAll;
	}

	public Long getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(Long rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSigntimes() {
		return signtimes;
	}

	public void setSigntimes(int signtimes) {
		this.signtimes = signtimes;
	}
	
	public long getWebchatId() {
		return webchatId;
	}

	public void setWebchatId(long webchatId) {
		this.webchatId = webchatId;
	}

	public long getBookCurrencyGift() {
		return bookCurrencyGift;
	}

	public void setBookCurrencyGift(long bookCurrencyGift) {
		this.bookCurrencyGift = bookCurrencyGift;
	}

	public UserInfo() {
		super();
	}

	@Override
	public String toString() {
		return "UserInfo [bookCurrency=" + bookCurrency + ", bookCurrencyAll="
				+ bookCurrencyAll + ", bookCurrencyGift=" + bookCurrencyGift
				+ ", city=" + city + ", country=" + country + ", createTime="
				+ createTime + ", headimgurl=" + headimgurl + ", id=" + id
				+ ", level=" + level + ", nickname=" + nickname + ", openid="
				+ openid + ", privilege=" + privilege + ", province="
				+ province + ", rechargeMoney=" + rechargeMoney + ", sex="
				+ sex + ", signtimes=" + signtimes + ", unionid=" + unionid
				+ ", webchatId=" + webchatId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookCurrency ^ (bookCurrency >>> 32));
		result = prime * result
				+ (int) (bookCurrencyAll ^ (bookCurrencyAll >>> 32));
		result = prime * result
				+ (int) (bookCurrencyGift ^ (bookCurrencyGift >>> 32));
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((headimgurl == null) ? 0 : headimgurl.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		result = prime * result
				+ ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime * result
				+ ((rechargeMoney == null) ? 0 : rechargeMoney.hashCode());
		result = prime * result + sex;
		result = prime * result + signtimes;
		result = prime * result + ((unionid == null) ? 0 : unionid.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (bookCurrency != other.bookCurrency)
			return false;
		if (bookCurrencyAll != other.bookCurrencyAll)
			return false;
		if (bookCurrencyGift != other.bookCurrencyGift)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (headimgurl == null) {
			if (other.headimgurl != null)
				return false;
		} else if (!headimgurl.equals(other.headimgurl))
			return false;
		if (id != other.id)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (rechargeMoney == null) {
			if (other.rechargeMoney != null)
				return false;
		} else if (!rechargeMoney.equals(other.rechargeMoney))
			return false;
		if (sex != other.sex)
			return false;
		if (signtimes != other.signtimes)
			return false;
		if (unionid == null) {
			if (other.unionid != null)
				return false;
		} else if (!unionid.equals(other.unionid))
			return false;
		if (webchatId != other.webchatId)
			return false;
		return true;
	}
	
	
}
