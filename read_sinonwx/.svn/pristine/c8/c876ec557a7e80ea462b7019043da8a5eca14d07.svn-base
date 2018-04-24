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
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleCategoryServiceImpl;
import com.sinontech.service.impl.SignlogServiceImpl;
import com.sinontech.service.impl.SignsetServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;
import common.Logger;

@Controller("myindexcontroller")
@RequestMapping(value = "/my")
public class MyIndexController extends BaseController{
	protected static final Logger logger = Logger.getLogger(MyIndexController.class);
	
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	SignlogServiceImpl signlogServiceImpl;
	@Autowired
	SignsetServiceImpl signsetServiceImpl;
	@Autowired
	ArticleCategoryServiceImpl articleCategoryServiceImpl;
	
	@RequestMapping(value = "/index/{webchatId}")
	public String index(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response) {
		//logger.info("欢迎进入+myindex=" );
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.debug("-----------------"+chat.toString()); 
		
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		int flag = isSign(webchatId,user.getId());
				 		request.setAttribute("signflag", flag);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/my/index/"+webchatId);
			}
		}else{
			int flag = isSign(webchatId,user.getId());
			request.setAttribute("signflag", flag);
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		user = this.userinfoService.getUserInfoById(user.getId());
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		 
		return "home/read/mind/myindex";
	}
	@RequestMapping(value = "/account/{webchatId}")
	public String myaccount(@PathVariable long webchatId, HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
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
				return getWXCode(chat,"/my/account/"+webchatId);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		}
		user = this.userinfoService.getUserInfoById(user.getId());
		request.setAttribute("bookCurrency", user.getCumulativeCurrency()-user.getBookCurrency());
		request.getSession().setAttribute("userinfo", user);
		request.setAttribute("user", user);
		request.setAttribute("webchatId", webchatId);
		return "home/read/mind/myCount";
	}
	//判断是否已签到0未开启签到功能 1 已签到2未签到
	public int isSign(long webchatId,long userid){
		int flag =0;//未开启签到功能
		String day = DateUtil.getStringShortNow(); 
		List<Signlog> list = this.signlogServiceImpl.getSignlogByUserIdAndDay(userid, day);
		if(null!=list && list.size()>0){
			flag=1;
			logger.error(userid+"已经签到过了："+day);
		}else{
			flag=2;
		}
		return flag;
	}
	
	
	 
}
