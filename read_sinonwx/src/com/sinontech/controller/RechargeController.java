package com.sinontech.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.ArticleLog;
import com.sinontech.modle.ExtensionContent;
import com.sinontech.modle.ExtensionUser;
import com.sinontech.modle.Rechargelog;
import com.sinontech.modle.Rechargeset;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.WXPayInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleLogServiceImpl;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.CountServiceImpl;
import com.sinontech.service.impl.ExtensionContentServiceImpl;
import com.sinontech.service.impl.ExtensionUserServiceImpl;
import com.sinontech.service.impl.RechargelogServiceImpl;
import com.sinontech.service.impl.RechargesetServiceImpl;
import com.sinontech.service.impl.SignlogServiceImpl;
import com.sinontech.service.impl.SignsetServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("rechargecontroller")
@RequestMapping(value = "/recharge")
public class RechargeController extends BaseController{
	protected static final Logger logger = Logger.getLogger(RechargeController.class);
	
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	SignlogServiceImpl signlogServiceImpl;
	@Autowired
	SignsetServiceImpl signsetServiceImpl;
	@Autowired
	RechargelogServiceImpl rechargelogServiceImpl;
	@Autowired
	RechargesetServiceImpl rechargesetServiceImpl;
	@Autowired
	CountServiceImpl countServiceImpl;
	@Autowired
	ExtensionContentServiceImpl extensionContentServiceImpl;
	@Autowired
	ArticleServiceImpl articleServiceImpl;
	 
	@Autowired
	ExtensionUserServiceImpl extensionUserServiceImpl;
	
	@Autowired
	ArticleLogServiceImpl articleLogServiceImpl;
	/**
	 * 用途说明：普通充值列表
	 * @param webchatid
	 * @param request
	 * @param response
	 * @return
	 * 2018年2月26日下午3:07:35
	 * @auther ljj
	 */
	@RequestMapping(value = "/list/{webchatid}")
	public String list(@PathVariable long webchatid,HttpServletRequest request, HttpServletResponse response) {
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
		}
		String rurl = request.getParameter("rurl");
		logger.debug("rurl===="+rurl);
		String redirect_uri ="";
		if(null!=rurl && !"null".equals(rurl) && !"".equals(rurl)){
			try {
				rurl = java.net.URLEncoder.encode(rurl,   "utf-8");
				redirect_uri ="?rurl="+ rurl;
			} catch (UnsupportedEncodingException e) {
			};
		}else{
			rurl = "";
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/recharge/list/"+webchatid+redirect_uri);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		List<Rechargeset> list = this.rechargesetServiceImpl.getRechargesetComon();
		user = this.userinfoService.getUserInfoById(webchatid,user.getId());
		request.setAttribute("user", user);
		request.setAttribute("rurl", rurl);
		request.setAttribute("rechargelist", list);
		request.setAttribute("cumulativeCurrency", user.getCumulativeCurrency()-user.getBookCurrency());
		return "home/read/mind/reCharge";
	}
      
	/**
	 * 用途说明：VIP包月列表
	 * @param webchatid
	 * @param request
	 * @param response
	 * @return
	 * 2018年2月26日下午3:07:56
	 * @auther ljj
	 */
	@RequestMapping(value = "/viplist/{webchatid}")
	public String viplist(@PathVariable long webchatid,HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
		}
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/recharge/viplist/"+webchatid);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		List<Rechargeset> list = this.rechargesetServiceImpl.getRechargesetVip();
		user = this.userinfoService.getUserInfoById(webchatid,user.getId());
		request.setAttribute("rechargelist", list);
		request.setAttribute("user", user);
		return "home/read/mind/myvip";
	}
	/**
	 * 用途说明：特权详情页面
	 * @param request
	 * @param response
	 * @return
	 * 2018年2月27日下午2:16:09
	 * @auther ljj
	 */
	@RequestMapping(value = "/privilegesDetail/{webchatid}")
	public String privilegesDetail(@PathVariable long webchatid,HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
		}
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/recharge/privilegesDetail/"+webchatid);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		request.setAttribute("user", user);
		return "home/read/mind/privilegesDetail";
	}
	/**
	 * 用途说明：VIP说明页面
	 * @param request
	 * @param response
	 * @return
	 * 2018年2月27日下午2:19:32
	 * @auther ljj
	 */
	@RequestMapping(value = "/vipExplain/{webchatid}")
	public String vipExplain(@PathVariable long webchatid,HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
		}
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/recharge/vipExplain/"+webchatid);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		request.setAttribute("user", user);
		return "home/read/mind/vipExplain";
	}
	/**
	 * 用途说明：获取充值详情信息
	 * @param rechargeid
	 * @param request
	 * @param response
	 * @return
	 * 2018年2月27日下午2:33:32
	 * @auther ljj
	 */
	@RequestMapping(value = "/getrecharge")
	public String getrecharge(HttpServletRequest request, HttpServletResponse response) {
		String srechargeid = request.getParameter("rechargeid");
		long rechargeid =0l;
		try {
			rechargeid = Long.parseLong(srechargeid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		String swebchatid = request.getParameter("webchatid");
		long webchatid = 0l;
		try {
			webchatid = Long.parseLong(swebchatid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		String rurl = request.getParameter("rurl");
		String redirect_uri ="";
		if(null!=rurl && !"null".equals(rurl) && !"".equals(rurl)){
			redirect_uri ="&rurl="+ rurl;
		}else{
			rurl = "";
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
		}
		String openidpay = null;
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/recharge/getrecharge.do?webchatid="+webchatid+"&rechargeid="+rechargeid+redirect_uri);
			}
		} 
		//判断未关注用户更新用户信息
		if(user.getFollowstate()==0){
			user = updateUserInfo(chat,user);
		}
		openidpay = user.getOpenidPay();
		if(null==openidpay || "".equals(openidpay) || "null".equals(openidpay)){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	openidpay = getUserOpenid(CODE);
				 	logger.debug("openidpay="+openidpay);
				 	logger.debug("user="+user.toString());
				 	if(null!=openidpay && !"".equals(openidpay) && !"null".equals(openidpay)){ 
				 		user.setOpenidPay(openidpay);
				 		this.userinfoService.updateUserInfo(user);
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				Webchat chat1 = new Webchat();
				chat1.setAppid(payAPPID);
				chat1.setDomainName(chat.getDomainName());
				return getWXCode(chat1,"/recharge/getrecharge.do?webchatid="+webchatid+"&rechargeid="+rechargeid+redirect_uri);
			}
		} 
		 
		Rechargeset rechargeset = this.rechargesetServiceImpl.getRechargesetByID(rechargeid);
		if(null!=rechargeset){
			int state = rechargeset.getState();
			if(0==state){
				request.setAttribute("rechargeset", rechargeset);
				Rechargelog log = save(rechargeset);
				log.setUserId(user.getId());
				log.setWebchatId(user.getWebchatId());
				
				this.rechargelogServiceImpl.saveRechargelog(log);
				request.setAttribute("tradeNo", log.getTradeNo());
				if(null!=openidpay && !"".equals(openidpay) && !"null".equals(openidpay)){ 
					WXPayInfo wxpay = new WXPayInfo();
					wxpay.setRemark(rechargeset.getRechargeName());
					wxpay.setOpenId(openidpay);
					wxpay.setIp("139.224.19.31");
					wxpay.setPrice(rechargeset.getMoney());
					wxpay.setTradeNo(log.getTradeNo());
					logger.debug("wxpay=="+wxpay.toString());
					wxpay = pay(wxpay);
					logger.debug("wxpay=="+wxpay.toString());
					request.setAttribute("prepayId", wxpay.getPrepayId());
					request.setAttribute("timeStamp", wxpay.getTimeStamp());
					request.setAttribute("appId", payAPPID);
					request.setAttribute("nonceStr", wxpay.getNonceStr());
					request.setAttribute("paySign", wxpay.getPaySign());
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
		request.setAttribute("webchatid", webchatid);
		request.setAttribute("user", user);
		request.setAttribute("rurl", rurl);
		//充值类型(0单独包月 1连续包月 2普通充值)
		int type = rechargeset.getRechargeType();
		if(2==type){
			return "home/read/mind/paySure";
		}else{
			return "home/read/mind/openVip";
		}
	}
	 
	public Rechargelog save(Rechargeset rechargeset){
		Rechargelog log = new Rechargelog();
		String tradeNo = "TM"+System.currentTimeMillis();
		
		log.setMoney(rechargeset.getMoney());
		log.setBookCurrency(rechargeset.getBookCurrency());
		log.setBookCurrencyAll(rechargeset.getBookCurrencyAll());
		log.setBookCurrencyGift(rechargeset.getBookCurrencyGift());
		log.setState(0);
		log.setCreateTime(DateUtil.getStringNow());
		log.setTradeNo(tradeNo);
		log.setExpDate(rechargeset.getExpdate());
		log.setChannel("微信");
		log.setChannelId(0l);
		log.setIncome(0);
		log.setChannelIncome("0");
		log.setConsume(rechargeset.getMoney());
		log.setUpstreamIncome(0);
		
		log.setTimeRecharge("");
		log.setRechargeName(rechargeset.getRechargeName());
		log.setRechargeType(rechargeset.getRechargeType());
		
		return log;
	}
	/**
	 * 用途说明：开启关闭自动购买付费章节功能
	 * @param webchatid
	 * @param request
	 * @param response
	 * 2018年2月26日下午3:59:20
	 * @auther ljj
	 */
	@RequestMapping(value = "/autoOrder/{webchatid}/{autoOrder}")
	public void autoOrder(@PathVariable long webchatid,@PathVariable int autoOrder, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			response("1",response);
			return;
		}
		user.setAutoOrder(autoOrder);
		this.userinfoService.updateUserInfo(user);
		request.getSession().setAttribute("userinfo", user);
		response("0",response);
	}
	
	@RequestMapping(value = "/rechargeLog/{webchatid}")
	public String rechargeLog(@PathVariable long webchatid,HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
		}
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/recharge/rechargeLog/"+webchatid );
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		List<Rechargelog> list = this.rechargelogServiceImpl.getRechargelogByUserId(user.getId());
		request.setAttribute("rechargeloglist", list);
		return "home/read/mind/reChargelog";
	}
	/**
	 * 用途说明：更新充值结果
	 * @param id
	 * @param state 0未付款，1已付款，2付款失败，3充值成功，4充值失败，5申请退款6退款成功7退款失败8撤销支付
	 * @param request
	 * @param response
	 * 2018年3月9日下午4:58:05
	 * @auther ljj
	 */
	@RequestMapping(value = "/rechargeover/{webchatid}/{tradeNo}/{state}")
	public void rechargeover(@PathVariable long webchatid,@PathVariable String tradeNo,@PathVariable int state, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			response("1",response);
			return;
		}
		user= this.userinfoService.getUserInfoById(user.getId());
		Rechargelog log = this.rechargelogServiceImpl.getRechargelogByTradeno(tradeNo);
		if(null!=log){
			//0未付款，1已付款，2付款失败，3充值成功，4充值失败，5申请退款6退款成功7退款失败8撤销支付
			int states = log.getState();
			if(0==states ){
				//已付款
				if(1==state || 8==state){
					ArticleLog articleLog = articleLogServiceImpl.firstArticleLog(user.getId());
					if(null!=articleLog){
						this.articleServiceImpl.updateRecharges(articleLog.getArticle().getId(),log.getMoney());
					}
					log.setState(1);
					log.setChannelId(user.getChannelId());
					log.setTimePay(DateUtil.getStringNow());
					this.rechargelogServiceImpl.updateRechargelog(log);
					//充值类型(0单独包月 1连续包月 2普通充值)
					int rechargetype = log.getRechargeType();
					long bookCurrencyGift = 0;
					long bookCurrency =0;
					long recharges = log.getMoney();
					//2普通充值
					if(2==rechargetype){
						bookCurrencyGift = log.getBookCurrencyGift();
						user.setBookCurrencyGift(user.getBookCurrencyGift()+bookCurrencyGift);
						bookCurrency = log.getBookCurrency();
						user.setCumulativeCurrency(user.getCumulativeCurrency()+log.getBookCurrencyAll());
					}else{
						//是否是vip 默认 0不是 1是单月VIP 2是连续包月VIP
						user.setIsvip(rechargetype+1);
						String date = DateUtil.getNextMonthDay();
						user.setExpDate(date);
					}
					user.setRechargeMoney(user.getRechargeMoney()+recharges);
					this.userinfoService.updateUserInfo(user);
					request.getSession().setAttribute("userinfo", user);
					
					Webchat webchat = this.webchatServiceImpl.getWebchatById(webchatid);
					System.out.println("webchat:------"+webchat.toString());
					countServiceImpl.addBookCurrency(webchat.getUserid(), webchatid, 
							bookCurrency, bookCurrencyGift, recharges, 1l,
							1l,0,0,0);
					
					//推广链接增加充值金额
					this.extensionContentServiceImpl.updateExtensionContent(user, log);
					 
				}else if(8==state){
					//取消付款
					log.setState(8);
					log.setTimePay(DateUtil.getStringNow());
					this.rechargelogServiceImpl.updateRechargelog(log);
				}
				
			}
		}
		this.userinfoService.updateUserInfo(user);
		response("0",response);
	} 
    
}