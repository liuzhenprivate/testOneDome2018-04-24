package com.sinontech.modle;

/**
 * 微信支付类
 * @author ljj
 *
 */
public class WXPayInfo {

	private Webchat webchat;
	
	private long price;
	
	private String remark;
	
	private String ip;
	
	private String openId;
	
	private String notify_url;
	
	private String prepayId;
	//时间戳
	private String timeStamp;
	//随机字符串，不长于32位
	private String nonceStr; 
	 
	private String md5;
	//第三方订单号
	private String tradeNo;
	
	private String paySign;
	
	public WXPayInfo() {
		super();
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public Webchat getWebchat() {
		return webchat;
	}

	public void setWebchat(Webchat webchat) {
		this.webchat = webchat;
	}

	@Override
	public String toString() {
		return "WXPayInfo [webchat=" + webchat + ", price=" + price
				+ ", remark=" + remark + ", ip=" + ip + ", openId=" + openId
				+ ", notify_url=" + notify_url + ", prepayId=" + prepayId
				+ ", timeStamp=" + timeStamp + ", nonceStr=" + nonceStr
				+ ", md5=" + md5 + ", tradeNo=" + tradeNo + ", paySign="
				+ paySign + "]";
	}

	 

	 
}
