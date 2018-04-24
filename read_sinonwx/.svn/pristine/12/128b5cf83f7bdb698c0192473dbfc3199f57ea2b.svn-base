package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 读者表
 * @author ljj
 *
 */
@Entity(name = "TB_USER")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERID")
	private long id;
	//渠道ID
	@Column(name = "USER_ID")
	private long channelId;
	//微信公众号ID
	@Column(name = "WEBCHAT_ID")
	private long webchatId;
	//平台ID
	@Column(name = "USER_CODE")
	private long usercode;
	//openid
	@Column(name = "OPENID")
	private String openid;
	//微信支付公众号OPENID
	@Column(name = "OPENID_PAY")
	private String openidPay;
	//微信昵称
	@Column(name = "NICKNAME")
	private String nickname;
	//累计充值金额
	@Column(name = "RECHARGE_MONEY")
	private long rechargeMoney;
	//性别
	@Column(name = "SEX")
	private int sex;
	//省份
	@Column(name = "PROVINCE")
	private String province;
	//城市
	@Column(name = "CITY")
	private String city;
	//国家
	@Column(name = "COUNTRY")
	private String country;
	//微信头像
	//用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	@Column(name = "HEADIMGURL")
	private String headimgurl;
	//用户特权信息
	@Column(name = "PRIVILEGE")
	private String privilege;
	
	@Column(name = "UNIONID")
	private String unionid;
	//关注时间
	@Column(name = "CTIME")
	private String cTime;
	//使用阅读币
	@Column(name = "BOOK_CURRENCY")
	private long bookCurrency;
	//等级
	@Column(name = "LEVEL")
	private String level;
	//赠送阅读币
	@Column(name = "BOOK_CURRENCY_GIFT")
	private long bookCurrencyGift;
	
	//累计阅读币
	@Column(name = "CUMULATIVE_CURRENCY")
	private long cumulativeCurrency;
	//注册时间
	@Column(name = "CREATE_TIME")
	private String createTime;
	//读者来源（默认 0微信 ）
	@Column(name = "SOURCE")
	private int source;
	//最后登陆时间
	@Column(name = "LOGIN_TIME")
	private String loginTime;
	//关注状态 默认 0未关注 1已关注
	@Column(name = "FOLLOWSTATE")
	private int followstate;
	//是否是vip 默认 0不是 1是单月VIP 2是连续包月VIP
	@Column(name = "ISVIP")
	private int isvip;
	//绑定手机号
	@Column(name = "PHONE")
	private String phone;
	//签到次数
	@Column(name = "SIGNTIMES")
	private int signtimes;
	
	@Column(name = "STATE")
	private int state;
	
	//自动购买付费章节，默认0未开启1开启
	@Column(name = "AUTO_ORDER")
	private int autoOrder;
	//会员有限期
	@Column(name = "EXP_DATE")
	private String expDate;
	//推荐者ID
	@Column(name="RECOMMENDID")
	private long recommendId;
	//第一推广ID
	@Column(name="SPREAD_SOURCE")
	private long spreadSource;
	//第二推广ID
	@Column(name="SPREAD_SOURCE1")
	private long spreadSource1;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getUsercode() {
		return usercode;
	}
	public void setUsercode(long usercode) {
		this.usercode = usercode;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getOpenidPay() {
		return openidPay;
	}
	public void setOpenidPay(String openidPay) {
		this.openidPay = openidPay;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getRechargeMoney() {
		return rechargeMoney;
	}
	public void setRechargeMoney(long rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
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
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
	public long getBookCurrency() {
		return bookCurrency;
	}
	public void setBookCurrency(long bookCurrency) {
		this.bookCurrency = bookCurrency;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public long getBookCurrencyGift() {
		return bookCurrencyGift;
	}
	public void setBookCurrencyGift(long bookCurrencyGift) {
		this.bookCurrencyGift = bookCurrencyGift;
	}
	public long getCumulativeCurrency() {
		return cumulativeCurrency;
	}
	public void setCumulativeCurrency(long cumulativeCurrency) {
		this.cumulativeCurrency = cumulativeCurrency;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public int getFollowstate() {
		return followstate;
	}
	public void setFollowstate(int followstate) {
		this.followstate = followstate;
	}
	public int getIsvip() {
		return isvip;
	}
	public void setIsvip(int isvip) {
		this.isvip = isvip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSigntimes() {
		return signtimes;
	}
	public void setSigntimes(int signtimes) {
		this.signtimes = signtimes;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getAutoOrder() {
		return autoOrder;
	}
	public void setAutoOrder(int autoOrder) {
		this.autoOrder = autoOrder;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public long getRecommendId() {
		return recommendId;
	}
	public void setRecommendId(long recommendId) {
		this.recommendId = recommendId;
	}
	
	public long getSpreadSource() {
		return spreadSource;
	}
	public void setSpreadSource(long spreadSource) {
		this.spreadSource = spreadSource;
	}
	public long getSpreadSource1() {
		return spreadSource1;
	}
	public void setSpreadSource1(long spreadSource1) {
		this.spreadSource1 = spreadSource1;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", channelId=" + channelId
				+ ", webchatId=" + webchatId + ", usercode=" + usercode
				+ ", openid=" + openid + ", openidPay=" + openidPay
				+ ", nickname=" + nickname + ", rechargeMoney=" + rechargeMoney
				+ ", sex=" + sex + ", province=" + province + ", city=" + city
				+ ", country=" + country + ", headimgurl=" + headimgurl
				+ ", privilege=" + privilege + ", unionid=" + unionid
				+ ", cTime=" + cTime + ", bookCurrency=" + bookCurrency
				+ ", level=" + level + ", bookCurrencyGift=" + bookCurrencyGift
				+ ", cumulativeCurrency=" + cumulativeCurrency
				+ ", createTime=" + createTime + ", source=" + source
				+ ", loginTime=" + loginTime + ", followstate=" + followstate
				+ ", isvip=" + isvip + ", phone=" + phone + ", signtimes="
				+ signtimes + ", state=" + state + ", autoOrder=" + autoOrder
				+ ", expDate=" + expDate + ", recommendId=" + recommendId
				+ ", spreadSource=" + spreadSource + ", spreadSource1="
				+ spreadSource1 + "]";
	}
	 
	
}
