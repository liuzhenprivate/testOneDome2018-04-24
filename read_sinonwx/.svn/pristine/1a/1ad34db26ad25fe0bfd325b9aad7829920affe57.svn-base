package com.sinontech.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * 微信公众号表
 * @author ljj
 *
 */
@Entity(name = "TB_WEBCHAT")
public class Webchat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WEBCHAT_ID")
	private long id;
	//所属渠道ID
	@Column(name = "USER_ID")
	private long userid;
	//类型 1服务号 2订阅号
	@Column(name = "TYPE")
	private int type;
	//名称
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DOMAIN_NAME")
	private String domainName;
	//微信号
	@Column(name = "WEBCHAT_CODE")
	private String webchatCode;
	@Column(name = "APPID")
	private String appid;
	@Column(name = "APPSECRET")
	private String appsecret;
	@Column(name = "SHOP_KEY")
	private String shopKey;
	@Column(name = "MCH_ID")
	private String mchId;
	//微信token有效期2小时
	@Column(name = "ACCESS_TOKEN")
	private String accessToken;
	//token获取时间
	@Column(name = "ACCESS_TOKEN_TIME")
	private String accessTokenTime;
	//微信临时票据有效期2小时
	@Column(name = "JSAPI_TICKET")
	private String jsapiTicket;
	//获取时间
	@Column(name = "JSAPI_TICKET_TIME")
	private String jsapiTicketTime;
	//公众号二维码地址
	@Column(name = "PIC_URL")
	private String picurl;
	
	@Column(name = "CREATE_TIME")
	private String createTime;
	//签到开关（默认0开启1关闭）
	@Column(name = "SIGN_SWITCH")
	private int signSwitch;
	//开启签到日期
	@Column(name = "OPEN_DATE")
	private String openDate;
	//站点名称
	@Column(name = "SETNAME")
	private String setname;
	//关键词
	@Column(name = "KEYWORDS")
	private String keywords;

	//客服电话
	@Column(name = "CUSTOM_PHONE")
	private String customPhone;
	//客服QQ
	@Column(name = "CUSTOM_QQ")
	private String customQQ;
	//客服EMAIL
	@Column(name = "CUSTOM_EMAIL")
	private String customEmail;
	//客服微信
	@Column(name = "CUSTOM_WEBCHAT")
	private String customWebchat;
	//站点描述
	@Column(name = "MEMO")
	private String memo;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	 

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	 

	public Webchat() {
		super();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebchatCode() {
		return webchatCode;
	}

	public void setWebchatCode(String webchatCode) {
		this.webchatCode = webchatCode;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getShopKey() {
		return shopKey;
	}

	public void setShopKey(String shopKey) {
		this.shopKey = shopKey;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getSignSwitch() {
		return signSwitch;
	}

	public void setSignSwitch(int signSwitch) {
		this.signSwitch = signSwitch;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getSetname() {
		return setname;
	}

	public void setSetname(String setname) {
		this.setname = setname;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCustomPhone() {
		return customPhone;
	}

	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
	}

	public String getCustomQQ() {
		return customQQ;
	}

	public void setCustomQQ(String customQQ) {
		this.customQQ = customQQ;
	}

	public String getCustomEmail() {
		return customEmail;
	}

	public void setCustomEmail(String customEmail) {
		this.customEmail = customEmail;
	}

	public String getCustomWebchat() {
		return customWebchat;
	}

	public void setCustomWebchat(String customWebchat) {
		this.customWebchat = customWebchat;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAccessTokenTime() {
		return accessTokenTime;
	}

	public void setAccessTokenTime(String accessTokenTime) {
		this.accessTokenTime = accessTokenTime;
	}

	public String getJsapiTicketTime() {
		return jsapiTicketTime;
	}

	public void setJsapiTicketTime(String jsapiTicketTime) {
		this.jsapiTicketTime = jsapiTicketTime;
	}

	@Override
	public String toString() {
		return "Webchat [id=" + id + ", userid=" + userid + ", type=" + type
				+ ", name=" + name + ", domainName=" + domainName
				+ ", webchatCode=" + webchatCode + ", appid=" + appid
				+ ", appsecret=" + appsecret + ", shopKey=" + shopKey
				+ ", mchId=" + mchId + ", accessToken=" + accessToken
				+ ", accessTokenTime=" + accessTokenTime + ", jsapiTicket="
				+ jsapiTicket + ", jsapiTicketTime=" + jsapiTicketTime
				+ ", picurl=" + picurl + ", createTime=" + createTime
				+ ", signSwitch=" + signSwitch + ", openDate=" + openDate
				+ ", setname=" + setname + ", keywords=" + keywords
				+ ", customPhone=" + customPhone + ", customQQ=" + customQQ
				+ ", customEmail=" + customEmail + ", customWebchat="
				+ customWebchat + ", memo=" + memo + "]";
	}

	 

}
