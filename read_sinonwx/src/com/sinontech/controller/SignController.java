package com.sinontech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.Signlog;
import com.sinontech.modle.Signset;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.CountServiceImpl;
import com.sinontech.service.impl.RechargelogServiceImpl;
import com.sinontech.service.impl.SignlogServiceImpl;
import com.sinontech.service.impl.SignsetServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;
import common.Logger;

@Controller("signcontroller")
@RequestMapping(value = "/sign")
public class SignController extends BaseController{
	protected static final Logger logger = Logger.getLogger(SignController.class);
	
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	SignlogServiceImpl signlogServiceImpl;
	@Autowired
	SignsetServiceImpl signsetServiceImpl;
	@Autowired
	CountServiceImpl countServiceImpl;
	@Autowired
	RechargelogServiceImpl rechargelogServiceImpl;
     
	
	//签到
	@RequestMapping(value = "/sign/{webchatid}")
	public void sign(@PathVariable long webchatid, HttpServletRequest request, HttpServletResponse response) {
		int times =0;
			try {
				UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
//				UserInfo user = this.userinfoService.getUserInfoById(webchatid,userid);
				if(null!=user){
					long userid = user.getId();
					Webchat chat = this.webchatServiceImpl.getWebchatById(user.getWebchatId());
					if(null!=chat){
						String day = DateUtil.getStringShortNow(); 
						List<Signlog> list = this.signlogServiceImpl.getSignlogByUserIdAndDay(userid, day);
						if(null!=list && list.size()>0){
							logger.error(userid+"已经签到过了："+day);
						}else{
							Signset set = this.signsetServiceImpl.getSignsetByTimes(0);
							if(null!=set){
								String time = DateUtil.getStringNow();
								long bookcurr = set.getBookCurrency();
								//添加签到日志
								Signlog log = new Signlog();
								log.setWebchatId(user.getWebchatId());
								log.setUserId(userid);
								log.setBookCurrency(bookcurr);
								log.setCreateTime(time);
								log.setChannelId(chat.getUserid());
								log.setTimes(0);
								log.setLogType("1");//1为每日签到2领取奖励
								log.setSignMonth(DateUtil.getStringMonth());
								this.signlogServiceImpl.saveSignlog(log);
								//给用户增加书币
//										user.setBookCurrency(user.getBookCurrency()+bookcurr);
								user.setBookCurrencyGift(user.getBookCurrencyGift()+bookcurr);
								user.setCumulativeCurrency(user.getCumulativeCurrency()+bookcurr);
								user.setSigntimes(user.getSigntimes()+1);
								this.userinfoService.updateUserInfo(user);
								times = user.getSigntimes();
								this.countServiceImpl.addBookCurrency(chat.getUserid(),webchatid, 0l, bookcurr, 0l, 0l, 0l, 0l, 0l, 0l);
								
							}
						}
						 
					}else{
						logger.error("公众号不存在："+user.getWebchatId());
					}
				}else{
					logger.error("用户不存在：");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		response(String.valueOf(times),response);
		
		
	}
	 
	 
	 
	@RequestMapping(value = "/list/{webchatid}")
	public String list(@PathVariable long webchatid,HttpServletRequest request, HttpServletResponse response) {
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
				return getWXCode(chat,"/sign/list/"+webchatid);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		List<Signlog> list = this.signlogServiceImpl.getSignlogByUserId(user.getId());
		request.setAttribute("signlist", list);
		return "home/read/mind/signRecord";
	}

	 
	 
}