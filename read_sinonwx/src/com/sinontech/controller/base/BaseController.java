package com.sinontech.controller.base;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alipay.api.share.ShareSign;
import com.github.wxpay.sdk.TestWXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.sinontech.modle.ExtensionContent;
import com.sinontech.modle.ExtensionUser;
import com.sinontech.modle.HtmlModle;
import com.sinontech.modle.HtmlModleDetail;
import com.sinontech.modle.HtmlModleDetailInfo;
import com.sinontech.modle.HtmlModleInfo;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.WXPayInfo;
import com.sinontech.modle.WXUserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.CountServiceImpl;
import com.sinontech.service.impl.ExtensionContentServiceImpl;
import com.sinontech.service.impl.ExtensionUserServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

public class BaseController {
	 protected static final Logger logger = Logger.getLogger(BaseController.class);
	 @Autowired
	 WebchatServiceImpl webchatServiceImpl;
	 @Autowired
	 UserInfoServiceImpl userinfoService;
	 @Autowired
	 ExtensionContentServiceImpl extensionContentServiceImpl;
	 @Autowired
	 ArticleServiceImpl articleServiceImpl;
	 @Autowired
	 CountServiceImpl countServiceImpl;
	 @Autowired
	 ExtensionUserServiceImpl extensionUserServiceImpl;
		
	 //微信支付APPID
	 protected final String payAPPID ="wx38433c4627a1b4ea";
	 //微信支付密钥
	 private final String payAppsecret ="d106ebc860f424edf5774ae9639555c0";
	 //微信支付商户KEY
	 private final String payShopKEY = "sinontech123456789read9876543210";
	 
	 private final String mchId = "1489031072";
			 
	 
	 private final String Notify_url = "http://kuwx.xinxinwx.cn/wx/read/pay/notify";
	 
	 protected final String imghosturl = "http://kuwx.xinxinwx.cn/read/";
	 
	 public static void main(String[] args){
		 BaseController b = new BaseController();
		
		 /* String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx38433c4627a1b4ea&secret=b19a9e89484dc4ee8a8e2f0efba6e26c";
		 logger.debug(url);
		 String rs = b.httpPost(url);
		 logger.debug(rs); */
		 
//		  String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=7_0AyxAd2ZL_lQh2qzaBxrU0PSunpX-KmYElrftKr-KBt1eJikAWDkWF9w8alsV9HQdIdijioeeK_nha_E5lL_maeB5po8ee-4A0H5KN2b4slKeHjiqdPVjiVtYRAQFZfAFAAPJ&openid=oPRYr0dcrtwn5hGzVHM4EGYstzR4&lang=zh_CN";
		  String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=7_0AyxAd2ZL_lQh2qzaBxrU0PSunpX-KmYElrftKr-KBt1eJikAWDkWF9w8alsV9HQdIdijioeeK_nha_E5lL_maeB5po8ee-4A0H5KN2b4slKeHjiqdPVjiVtYRAQFZfAFAAPJ&type=wx_card";
			 
		  logger.debug(url);
		 String rs = b.httpPost(url);
		 logger.debug(rs);  
		 
	 }
	 /**
	  * 微信支付商户号
		1486849332
		商户平台登录帐号
		1486849332@1486849332
		商户平台登录密码
		839462
		申请对应的公众号
		小说热搜（xiaoshuoresou）
		公众号APPID
		wxabfb9969455a36cb
	  */
	/* 欣网好机惠
	 * http://kuwx.xinxinwx.cn
	 Appid：wx38433c4627a1b4ea
	 AppSecret：
	 d106ebc860f424edf5774ae9639555c0
	 微信支付商户号
	1489031072
	商户平台登录帐号
	1489031072@1489031072
	商户平台登录密码
	066628
	申请对应的公众号
	欣网好机惠（xwhjh007）
	公众号APPID
	wx38433c4627a1b4ea
	
	258633706@1489031072
	sinontech123=
	欣网好机惠
	15805810070@139.com
	cao8810jun
	 */
	 
	 public WXPayInfo pay(WXPayInfo wxpay){
		 TestWXPay pay;
//		 Webchat webchat = wxpay.getWebchat();
		 String remark= wxpay.getRemark();//"充值名称";
		 String tradeno = wxpay.getTradeNo();
		 String ip= wxpay.getIp();
		 String openid = wxpay.getOpenId();
		 String price= String.valueOf(wxpay.getPrice());
			try {
				pay = new TestWXPay();
//				String notify_url= wxpay.getNotify_url();//webchat.getDomainName()+"/read/pay/notify";
				
				Map<String, String> map =pay.doUnifiedOrder(remark,tradeno,price,ip,openid,Notify_url);
				
				String prepay_id = map.get("prepay_id");
				String timeStamp= String.valueOf(WXPayUtil.getCurrentTimestamp());
				String nonceStr= WXPayUtil.generateNonceStr();
		//		StringBuffer sb = new StringBuffer();
				String prepayId ="prepay_id="+prepay_id;
//				String APPID = webchat.getAppid();
				Map<String, String> data = new HashMap<String, String>();
				data.put("package", prepayId);
				data.put("timeStamp", timeStamp);
				data.put("nonceStr", nonceStr);
				data.put("appId", payAPPID);
				data.put("signType", "MD5");
//				data.put("mch_id", webchat.getMchId());
				
				Set<String> keySet = data.keySet();
		        String[] keyArray = keySet.toArray(new String[keySet.size()]);
		        Arrays.sort(keyArray);
		        StringBuilder sb = new StringBuilder();
		        for (String k : keyArray) {
		            if (k.equals(WXPayConstants.FIELD_SIGN)) {
		                continue;
		            }
		            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
		                sb.append(k).append("=").append(data.get(k).trim()).append("&");
		        }
		        sb.append("key=").append(payShopKEY);
		       // logger.debug(sb.toString());
		        String paySign = null;
				try {
					paySign = WXPayUtil.MD5(sb.toString()).toUpperCase();
					//logger.debug("paySign="+paySign);    
				} catch (Exception e) {
					e.printStackTrace();
				}
				wxpay.setMd5((sb.toString()));
				wxpay.setNonceStr(nonceStr);
				wxpay.setPrepayId("prepay_id="+prepay_id);
				wxpay.setTimeStamp(timeStamp);
				wxpay.setPaySign(paySign);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return wxpay;
	 }
	 
	 /**
	  * 用途说明：获取微信支付用户的openid
	  * @param code
	  * @return
	  * 2018年3月7日上午10:11:48
	  * @auther ljj
	  */
	 public String getUserOpenid(String code){
		 	String openid = null;
		 	Webchat chat = new Webchat();
		 	chat.setAppid(payAPPID);
		 	chat.setAppsecret(payAppsecret);
		    WXUserInfo wxuser = getAccessTokenAndOpenidByCode(chat,code);
			openid = wxuser.getOpenid();
			return openid;
	 }
	 /**
	  * 用途说明：通过微信授权返回的CODE获取用户信息
	  * @param chat
	  * @param code
	  * @param tid 推荐者id
	  * @return
	  * 2018年3月5日下午3:39:16
	  * @auther ljj
	  */
	 public UserInfo getUserInfo(Webchat chat,String code,long tid,ExtensionContent extensionContent){
		 	UserInfo user = null;
		    WXUserInfo wxuser = getAccessTokenAndOpenidByCode(chat,code);
			String openid = wxuser.getOpenid();
			long spreadSourceId = 0l;
			if(null!=extensionContent){
				spreadSourceId = extensionContent.getId();
			}
			if(null!=openid && !"".equals(openid)){
				user =  this.userinfoService.getUserInfoByOpenId(openid);
				if(null!=user){
					 
					if(spreadSourceId>0l){
						ExtensionUser euser = new ExtensionUser();
						euser.setCtime(DateUtil.getStringNow());
						euser.setExtensionId(spreadSourceId);
						euser.setUser_id(user.getId());
						euser.setFollowstate(user.getFollowstate());
						euser.setRecharge(0l);
						euser.setRechargeTimes(0);
						this.extensionUserServiceImpl.saveExtensionUser(euser);
						
						//保存第二推广ID
						user.setSpreadSource1(spreadSourceId);
						this.userinfoService.updateUserInfo(user);
						int fstate = user.getFollowstate();
						extensionContent.setGuide(extensionContent.getGuide()+1);
						if(1==fstate){
							extensionContent.setFollow(extensionContent.getFollow()+1);
						}
						this.extensionContentServiceImpl.updateExtensionContent(extensionContent);
					}
					 
					
					//更新用户信息
					user = updateUserInfo(chat,user);
				}else{
					//用户首次进入
					user = getWXUserWXAccessTokenAndOpenid(chat,openid);
					if(null!=user){
						user.setOpenid(openid);
						user.setBookCurrency(0l);
						user.setBookCurrencyGift(0l);
						user.setWebchatId(chat.getId());
						user.setCumulativeCurrency(0l);
						user.setChannelId(chat.getUserid());
						user.setUsercode(0);
						user.setLevel("0");
						user.setRechargeMoney(0l);
						user.setRecommendId(tid);
						user.setSpreadSource(spreadSourceId);
						user.setSpreadSource1(0l);
						user.setFollowstate(0);
						this.userinfoService.saveUserInfo(user);
						user =  this.userinfoService.getUserInfoByOpenId(openid);
						if(spreadSourceId>0l){
							ExtensionUser euser = new ExtensionUser();
							euser.setCtime(DateUtil.getStringNow());
							euser.setExtensionId(spreadSourceId);
							euser.setUser_id(user.getId());
							euser.setFollowstate(0);
							euser.setRecharge(0l);
							euser.setRechargeTimes(0);
							this.extensionUserServiceImpl.saveExtensionUser(euser);
							extensionContent.setGuide(extensionContent.getGuide()+1);
							this.extensionContentServiceImpl.updateExtensionContent(extensionContent);
						}
					}else{
						return null;
					}
				}
			}
			return user;
	 }
	 
	 /**
	  * 用途说明：通过微信授权返回的CODE获取用户信息
	  * @param chat
	  * @param code
	  * @return
	  * 2018年3月5日下午3:39:16
	  * @auther ljj
	  */
	 public UserInfo getUserInfo(Webchat chat,String code){
		 	UserInfo user = null;
		    WXUserInfo wxuser = getAccessTokenAndOpenidByCode(chat,code);
			String openid = wxuser.getOpenid();
			if(null!=openid && !"".equals(openid)){
				user =  this.userinfoService.getUserInfoByOpenId(openid);
				if(null!=user){
					//更新用户信息
					user = updateUserInfo(chat,user);
				}else{
					//用户首次进入
					user = getWXUserWXAccessTokenAndOpenid(chat,openid);
					if(null!=user){
						user.setOpenid(openid);
						user.setBookCurrency(0l);
						user.setBookCurrencyGift(0l);
						user.setWebchatId(chat.getId());
						user.setCumulativeCurrency(0l);
						user.setChannelId(chat.getUserid());
						user.setUsercode(0);
						user.setLevel("0");
						user.setRechargeMoney(0l);
						user.setRecommendId(0l);
						user.setSpreadSource(0l);
						user.setSpreadSource1(0l);
						this.userinfoService.saveUserInfo(user);
						user =  this.userinfoService.getUserInfoByOpenId(openid);
					}else{
						return null;
					}
				}
			}
			return user;
	 }
	 /**
	  * 用途说明：获取分享签名信息
	  * @param webchat
	  * @param url
	  * @return
	  * 2018年3月8日上午9:50:46
	  * @auther ljj
	  */
	 public Map<String,String> getShareSignature(Webchat webchat,String url){
		 Map<String, String> ret =null;
		 String tickettime = webchat.getJsapiTicketTime();
		 String tokentime = webchat.getAccessTokenTime();
		 String token = webchat.getAccessToken();
		 boolean tokenflag = true;
		 if(null!=token && !"".equals(token) && !"null".equals(token)){
			 if(null!=tokentime && !"".equals(tokentime) && !"null".equals(tokentime)){
					long mins = DateUtil.mininuesBetween(tokentime);
					logger.debug("mins==="+mins);
					if(mins<115){
						tokenflag = false;
					}
				}
		 }
		 if(tokenflag){
			 token = getAccess_token(webchat);
			 webchat.setAccessToken(token);
		 }
		  
			String ticket = webchat.getJsapiTicket();
			boolean flag = true;
			if(null!=ticket && !"".equals(ticket) && !"null".equals(ticket)){
				if(null!=tickettime && !"".equals(tickettime) && !"null".equals(tickettime)){
					long mins = DateUtil.mininuesBetween(tickettime);
					logger.debug("mins==="+mins);
					if(mins<115){
						flag = false;
					}
				}
			}
			if(flag){
				//重新获取ticket
				webchat = getjsapiTicket(webchat);
				ticket = webchat.getJsapiTicket();
			}
			
			if(null!=ticket && !"".equals(ticket) && !"null".equals(ticket)){
				ret = ShareSign.sign(ticket, url);
			}
		 return ret;
	 }
	 /**
	  * 用途说明：生成分享需要的签名
	  * @param jsapi_ticket
	  * @param noncestr
	  * @param timestamp
	  * @param url
	  * @return
	  * 2018年3月7日下午6:04:21
	  * @auther ljj
	  */
	 public String getsignature(String jsapi_ticket,String noncestr,String timestamp,String url){
		 StringBuffer sb = new StringBuffer();
		 sb
		 .append("jsapi_ticket=").append(jsapi_ticket)
		 .append("&noncestr=").append(noncestr)
		 .append("&timestamp=").append(timestamp)
		 .append("url=").append(url);
		 
		 
		 return null;
	 }
	 /**
	  * 用途说明：获取微信临时票据
	  * @param webchat
	  * @return
	  * 2018年3月7日下午4:47:18
	  * @auther ljj
	  */
	 public Webchat getjsapiTicket(Webchat webchat){
		 String ticket = getjsapiTicketInfo(webchat);
		 if(null==ticket || "".equals(ticket)||"null".equals(ticket)){
			 String token = getAccess_token(webchat);
			 if(null!=token && !"".equals(token) && !"null".equals(token)){
				 webchat.setAccessToken(token);
				 ticket = getjsapiTicketInfo(webchat);
			 } 
		 }
		 
		 if(null!=ticket && !"".equals(ticket) && !"null".equals(ticket)){
			webchat.setJsapiTicket(ticket);
			webchat.setJsapiTicketTime(DateUtil.getStringNow());
//			logger.debug(webchat.toString());
			this.webchatServiceImpl.updateWebchat(webchat);
//			logger.debug("edit===="+webchat.toString());
		 }else{
			 webchat.setJsapiTicket(null);
		 }
		 return webchat;
	}
	 /**
	  * 用途说明：获取微信临时票据
	  * @param webchat
	  * @return
	  * 2018年3月7日下午4:36:42
	  * @auther ljj
	  */
	 public String getjsapiTicketInfo(Webchat webchat){
		 String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+webchat.getAccessToken()+"&type=wx_card";
//		 logger.debug(url);
		 String rs = httpPost(url);
//		 logger.debug(rs);
		 String ticket=null;
		 if(null!=rs){
			Map responseMap = JSON.parseObject(rs);
			ticket = String.valueOf(responseMap.get("ticket"));
			String errcode = String.valueOf(responseMap.get("errcode"));
			String errmsg = String.valueOf(responseMap.get("errmsg"));
			if(null!=ticket && !"".equals(ticket) && !"null".equals(ticket)){
				webchat.setJsapiTicket(ticket);
				webchat.setJsapiTicketTime(DateUtil.getStringNow());
//				logger.debug(webchat.toString());
				this.webchatServiceImpl.updateWebchat(webchat);
//				logger.debug("edit===="+webchat.toString());
			}else{
				return null;
			}
		 }
		 
		 return ticket;
	 }
	 /**
	  * 用途说明：查询未关注的用户信息进行更新用户信息
	  * @param webchat
	  * @param user
	  * @return
	  * 2018年3月5日下午3:14:15
	  * @auther ljj
	  */
	 public UserInfo updateUserInfo(Webchat webchat,UserInfo user){
		 int followstate = user.getFollowstate();
		 if(0==followstate){
			UserInfo userinfo = getWXUserWXAccessTokenAndOpenid(webchat,user.getOpenid());
			if(userinfo.getFollowstate()==1){
				user.setNickname(userinfo.getNickname());
				user.setCountry(userinfo.getCountry());
				user.setCity(userinfo.getCity());
				user.setcTime(userinfo.getcTime());
				user.setFollowstate(1);
				user.setHeadimgurl(userinfo.getHeadimgurl());
				user.setPrivilege(userinfo.getPrivilege());
				user.setProvince(userinfo.getProvince());
				user.setSex(userinfo.getSex());
				user.setUnionid(userinfo.getUnionid());
				user.setLoginTime(DateUtil.getStringNow());
				this.userinfoService.updateUserInfo(user);
				//已关注
				long tid1 = user.getSpreadSource();
				long tid2 = user.getSpreadSource1();
				if(tid1>0l){
					//给推广1关注数加1
					ExtensionUser euser = this.extensionUserServiceImpl.getExtensionUserByTIdandUid(tid1, user.getId());
					if(null!=euser){
						int fstate = euser.getFollowstate();
						if(0==fstate){
							euser.setFollowstate(1);
							this.extensionUserServiceImpl.updateExtensionUser(euser);
							ExtensionContent extensionContent = this.extensionContentServiceImpl.selectFindById(tid1);
							extensionContent.setFollow(extensionContent.getFollow()+1);
							this.extensionContentServiceImpl.updateExtensionContent(extensionContent);
						}
					}
				}
				if(tid2>0l){
					//给推广2关注数加1
					ExtensionUser euser = this.extensionUserServiceImpl.getExtensionUserByTIdandUid(tid2, user.getId());
					if(null!=euser){
						int fstate = euser.getFollowstate();
						if(0==fstate){
							euser.setFollowstate(1);
							this.extensionUserServiceImpl.updateExtensionUser(euser);
							ExtensionContent extensionContent = this.extensionContentServiceImpl.selectFindById(tid2);
							extensionContent.setFollow(extensionContent.getFollow()+1);
							this.extensionContentServiceImpl.updateExtensionContent(extensionContent);
						}
					}
				}
			}
		}
		return user;
	 }
	 /**
	  * 用途说明：获取公众号的最新access_token
	  * @param webchat
	  * @return
	  * 2018年3月5日下午3:11:27
	  * @auther ljj
	  */
	 public String getAccess_token(Webchat webchat){
		 String access_token ="";
		 String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+webchat.getAppid()+"&secret="+webchat.getAppsecret();
		 logger.debug(url);
		 String rs = httpPost(url);
		 logger.debug(rs);
		 if(null!=rs){
			Map responseMap = JSON.parseObject(rs);
			access_token = String.valueOf(responseMap.get("access_token"));
			if(null!=access_token && !"".equals(access_token) && !"null".equals(access_token)){
				webchat.setAccessToken(access_token);
				webchat.setAccessTokenTime(DateUtil.getStringNow());
//				logger.debug(webchat.toString());
				this.webchatServiceImpl.updateWebchat(webchat);
//				logger.debug("edit===="+webchat.toString());
			}
		 }
		 return access_token;
	 }
	 /**
	  * 用途说明：重定向到获取微信code链接
	  * @param webchat
	  * @param redirect_uri
	  * @return
	  * 2018年3月5日上午10:57:30
	  * @auther ljj
	  */
	 public String getWXCode(Webchat webchat,String redirect_uri){
		 String url= null;
		 try {
			redirect_uri = webchat.getDomainName()+redirect_uri;
			redirect_uri = java.net.URLEncoder.encode(redirect_uri,   "utf-8");
			url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+webchat.getAppid()+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
			// 若提示“该链接无法访问”，请检查参数是否填写错误，是否拥有scope参数对应的授权作用域权限。
//			logger.debug(url);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 return "redirect:"+url ;
	 }
	 
	 /**
	  * 用途说明：通过code获取到access_token和openid
	  * @param webchat
	  * @param code
	  * @return
	  * 2018年3月5日上午11:17:33
	  * @auther ljj
	  */
	 public WXUserInfo getAccessTokenAndOpenidByCode(Webchat webchat,String code){
		 WXUserInfo user = new WXUserInfo();
		 user.setWebchat(webchat);
		 user.setCode(code);
		 String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+webchat.getAppid()
					+"&secret="+webchat.getAppsecret()+"&code="+code+"&grant_type=authorization_code";
		logger.debug(url);
		String rs = httpPost(url);
		logger.debug(rs);
		if(null!=rs){
			Map responseMap = JSON.parseObject(rs);
			String access_token = String.valueOf(responseMap.get("access_token"));
			String openid = String.valueOf(responseMap.get("openid"));
			String errcode = String.valueOf(responseMap.get("errcode"));
			String errmsg = String.valueOf(responseMap.get("errmsg"));
			user.setOpenid(openid);
			user.setAccessToken(access_token);
			user.setErrcode(errcode);
			user.setErrmsg(errmsg);
		}else{
			
		}
		return user;
	 }
	 /**
	  * 用途说明：通过用户的access_token和openid拉取用户信息
	  * @param wxuser
	  * @return
	  * 2018年3月5日上午11:40:41
	  * @auther ljj
	  */
	 public UserInfo getWXUserAccessTokenAndOpenid(WXUserInfo wxuser){
		 UserInfo user = null;
		 String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+wxuser.getAccessToken()+"&openid="+wxuser.getOpenid()+"&lang=zh_CN";
		 logger.debug(url);
		 String rs = httpPost(url);
		 logger.debug(rs);
		 if(null!=rs){
			 user = new UserInfo();
			 Map responseMap = JSON.parseObject(rs);
			/*  
			 String openid = String.valueOf(responseMap.get("openid"));
			 String errcode = String.valueOf(responseMap.get("errcode"));
			 String errmsg = String.valueOf(responseMap.get("errmsg"));*/
			 
			 String nickname = String.valueOf(responseMap.get("nickname"));
			 //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			 String sex = String.valueOf(responseMap.get("sex"));
			 int sexint =0;
			 try {
				sexint = Integer.parseInt(sex);
			 } catch (NumberFormatException e) {
				e.printStackTrace();
			 }
			 String province = String.valueOf(responseMap.get("province"));
			 String city = String.valueOf(responseMap.get("city"));
			 String country = String.valueOf(responseMap.get("country"));
			 String headimgurl = String.valueOf(responseMap.get("headimgurl"));
			 String privilege = String.valueOf(responseMap.get("privilege"));
			 String unionid = String.valueOf(responseMap.get("unionid"));
			 user.setNickname(nickname);
			 user.setSex(sexint);
			 user.setProvince(province);
			 user.setCity(city);
			 user.setCountry(country);
			 user.setHeadimgurl(headimgurl);
			 user.setPrivilege(privilege);
			 user.setUnionid(unionid);
			 user.setSource(1);
			 user.setCreateTime(DateUtil.getStringNow());
			 user.setLoginTime(DateUtil.getStringNow());
			 if(null!=unionid && !"".equals(unionid) && !"null".equals(unionid)){
				 user.setcTime(DateUtil.getStringNow());
				 user.setFollowstate(1);
			 } 
			 
		 }
		 
		 return user;
	 }
	 
	 /**
	  * 用途说明：通过微信accessToken和用户的openid查询用户信息
	  * @param wxuser
	  * @param accessToken
	  * @return
	  * 2018年3月5日下午2:11:27
	  * @auther ljj
	  */
	 public UserInfo getWXUserWXAccessTokenAndOpenid(Webchat webchat,String openid){
		 UserInfo user = null;
		 String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+webchat.getAccessToken()+"&openid="+openid+"&lang=zh_CN";
		 logger.debug(url);
		 String rs = httpPost(url);
		 logger.debug(rs);
		 if(null!=rs){
			 user = new UserInfo();
			 Map responseMap = JSON.parseObject(rs);
			 String errcode = String.valueOf(responseMap.get("errcode"));
			 String errmsg = String.valueOf(responseMap.get("errmsg"));
			 if(null!=errcode && !"".equals(errcode) && !"null".equals(errcode)){
				 String accessToken = getAccess_token(webchat);
				 webchat.setAccessToken(accessToken);
				 return getWXUserWXAccessTokenAndOpenidEnd(webchat,openid);
			 }
			 String subscribe = String.valueOf(responseMap.get("subscribe"));
			/*  
			 String openid = String.valueOf(responseMap.get("openid"));
			 */
			 
			 String nickname = String.valueOf(responseMap.get("nickname"));
			 if(null==nickname || "".equals(nickname) || "null".equals(nickname)){
				 nickname = "游客";
			 }
			 //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			 String sex = String.valueOf(responseMap.get("sex"));
			 int sexint =0;
			 try {
				sexint = Integer.parseInt(sex);
			 } catch (NumberFormatException e) {
			 }
			 String province = String.valueOf(responseMap.get("province"));
			 String city = String.valueOf(responseMap.get("city"));
			 String country = String.valueOf(responseMap.get("country"));
			 String headimgurl = String.valueOf(responseMap.get("headimgurl"));
			 if(null==headimgurl ||"".equals(headimgurl) || "null".equals(headimgurl)){
				 headimgurl = "pages/home/read/images/myPic87.png";
			 }
			 String privilege = String.valueOf(responseMap.get("privilege"));
			 String unionid = String.valueOf(responseMap.get("unionid"));
			 //关注时间
			 String subscribe_time = String.valueOf(responseMap.get("subscribe_time"));
			 user.setcTime(changeDate(subscribe_time));
			 user.setNickname(nickname);
			 user.setSex(sexint);
			 user.setProvince(province);
			 user.setCity(city);
			 user.setCountry(country);
			 user.setHeadimgurl(headimgurl);
			 user.setPrivilege(privilege);
			 user.setUnionid(unionid);
			 user.setSource(0);
			 user.setCreateTime(DateUtil.getStringNow());
			 user.setLoginTime(DateUtil.getStringNow());
			 user.setFollowstate(0);
			 if(null!=subscribe && !"".equals(subscribe) && !"null".equals(subscribe)){
				 int follow = 0 ;
				 try {
					follow = Integer.parseInt(subscribe);
				 } catch (NumberFormatException e) {
				 }
				 user.setFollowstate(follow);
			 } 
			 
		 }
		 
		 return user;
	 }
	 /**
	  * 用途说明：只查询一次 通过微信accessToken和用户的openid查询用户信息
	  * @param wxuser
	  * @param accessToken
	  * @return
	  * 2018年3月5日下午2:15:40
	  * @auther ljj
	  */
	 public UserInfo getWXUserWXAccessTokenAndOpenidEnd(Webchat webchat,String openid){
		 UserInfo user = null;
		 String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+webchat.getAccessToken()+"&openid="+openid+"&lang=zh_CN";
		 logger.debug(url);
		 String rs = httpPost(url);
		 logger.debug(rs);
		 if(null!=rs){
			 user = new UserInfo();
			 Map responseMap = JSON.parseObject(rs);
			 String errcode = String.valueOf(responseMap.get("errcode"));
			 String errmsg = String.valueOf(responseMap.get("errmsg"));
			 if(null!=errcode && !"".equals(errcode) && !"null".equals(errcode)){
				 return null;
			 }
			 //用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
			 String subscribe = String.valueOf(responseMap.get("subscribe"));
			 
			 String nickname = String.valueOf(responseMap.get("nickname"));
			 if(null==nickname || "".equals(nickname) || "null".equals(nickname)){
				 nickname = "游客";
			 }
			 //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			 String sex = String.valueOf(responseMap.get("sex"));
			 int sexint =0;
			 try {
				sexint = Integer.parseInt(sex);
			 } catch (NumberFormatException e) {
			 }
			 String province = String.valueOf(responseMap.get("province"));
			 String city = String.valueOf(responseMap.get("city"));
			 String country = String.valueOf(responseMap.get("country"));
			 String headimgurl = String.valueOf(responseMap.get("headimgurl"));
			 if(null==headimgurl ||"".equals(headimgurl) || "null".equals(headimgurl)){
				 headimgurl = "pages/home/read/images/myPic87.png";
			 }
			 String privilege = String.valueOf(responseMap.get("privilege"));
			 String unionid = String.valueOf(responseMap.get("unionid"));
			 //关注时间
			 String subscribe_time = String.valueOf(responseMap.get("subscribe_time"));
			 user.setcTime(changeDate(subscribe_time));
			 user.setNickname(nickname);
			 user.setSex(sexint);
			 user.setProvince(province);
			 user.setCity(city);
			 user.setCountry(country);
			 user.setHeadimgurl(headimgurl);
			 user.setPrivilege(privilege);
			 user.setUnionid(unionid);
			 user.setSource(0);
			 user.setCreateTime(DateUtil.getStringNow());
			 user.setLoginTime(DateUtil.getStringNow());
			 user.setFollowstate(0);
			 if(null!=subscribe && !"".equals(subscribe) && !"null".equals(subscribe)){
				 int follow = 0 ;
				 try {
					follow = Integer.parseInt(subscribe);
				 } catch (NumberFormatException e) {
				 }
				 user.setFollowstate(follow);
			 } 
			 
		 }
		 
		 return user;
	 }
	 
	 
	 public void response(String text, HttpServletResponse response) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.write(text);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	 }
	 public   String httpPost(String url ){
			String result=null;
			HttpClient client = new HttpClient();
			client.getParams().setContentCharset("UTF-8");
			client.getParams().setHttpElementCharset("UTF-8");
			PostMethod post=new PostMethod(url);
			post.setFollowRedirects(false);
			InputStream inputStream = null;
			try{
				client.executeMethod(post);
				inputStream = post.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
				stringBuffer .append(str );  
				}
				result = stringBuffer.toString();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				post.releaseConnection();
			}
			return result;
		}
	 public   String httpPost(String url,Map<String,String> params){
			String result=null;
			HttpClient client = new HttpClient();
			client.getParams().setContentCharset("UTF-8");
			client.getParams().setHttpElementCharset("UTF-8");
			PostMethod post=new PostMethod(url);
			post.setFollowRedirects(false);
			InputStream inputStream = null;
			try{
				 if(params != null){  
			            Set<String> keySet = params.keySet();  
			            NameValuePair[] param = new NameValuePair[keySet.size()];  
			            int i = 0;  
			            for(String key : keySet){  
			                param[i] = new NameValuePair(key, params.get(key));  
			                i++;  
			            }  
			            post.setRequestBody(param);  
			        }  
				client.executeMethod(post);
				inputStream = post.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
				stringBuffer .append(str );  
				}
				result = stringBuffer.toString();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				post.releaseConnection();
			}
			return result;
		}
	 
	 /**
	  * 用途说明：将微信的时间戳转换成日期格式yyyy-MM-dd HH:mm:ss
	  * @param subscribe_time
	  * @return
	  * 2018年3月5日下午2:22:56
	  * @auther ljj
	  */
	 public String changeDate(String subscribe_time){
		 String subtime = "";
		 try {
			Long time = Long.parseLong(subscribe_time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			subtime = sdf.format(new Date(time*1000L));
		 } catch (NumberFormatException e) {
			e.printStackTrace();
		 }
		 return subtime;
	 }
	 
	 public HtmlModleInfo getHtmlModleInfo(HtmlModle modle){
		 HtmlModleInfo modleinfo = new HtmlModleInfo();
		 modleinfo.setId(modle.getId());
		 modleinfo.setName(modle.getName());
		 modleinfo.setCreateTime(modle.getCreateTime());
		 modleinfo.setDivClass(modle.getDivClass());
		 modleinfo.setHtml(modle.getHtml());
		 modleinfo.setModleType(modle.getModleType());
		 modleinfo.setPlace(modle.getPlace());
		 modleinfo.setNums(modle.getNums());
		 return modleinfo;
	 }
	 
	 public HtmlModleDetailInfo getHtmlModleDetailInfo(HtmlModleDetail modle){
		 HtmlModleDetailInfo modleinfo = new HtmlModleDetailInfo();
		 modleinfo.setId(modle.getId());
		 modleinfo.setCreateTime(modle.getCreateTime());
		 modleinfo.setArticleID(modle.getArticleID());
		 modleinfo.setContent(modle.getContent());
		 modleinfo.setHtmlmodleId(modle.getHtmlmodleId());
		 modleinfo.setHtmlUrl(modle.getHtmlUrl());
		 modleinfo.setImgUrl(modle.getImgUrl());
		 modleinfo.setMemo(modle.getMemo());
		 modleinfo.setSort(modle.getSort());
		 modleinfo.setSubhead(modle.getSubhead());
		 modleinfo.setTitle(modle.getTitle());
		 return modleinfo;
	 }
	 
	 /**
	 * @purpose：读取章节内容
	 * @author liuzhen
	 * @Time：2018-3-13 上午11:34:52
	 */
	public static String readInputStream(InputStream inputStream)throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return new String(bos.toByteArray(), "gbk");
	}
}