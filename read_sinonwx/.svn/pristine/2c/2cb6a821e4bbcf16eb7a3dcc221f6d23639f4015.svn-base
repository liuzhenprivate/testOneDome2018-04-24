package com.sinontech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.ArticleChapterLog;
import com.sinontech.modle.ArticleLog;
import com.sinontech.modle.ConsumeLog;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.service.impl.ArticleChapterLogServiceImpl;
import com.sinontech.service.impl.ArticleLogServiceImpl;
import com.sinontech.service.impl.ConsumeLogServiceImpl;
import com.sinontech.service.impl.CountServiceImpl;
import com.sinontech.service.impl.RechargelogServiceImpl;
import com.sinontech.service.impl.RechargesetServiceImpl;
import com.sinontech.service.impl.SignlogServiceImpl;
import com.sinontech.service.impl.SignsetServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("consumcontroller")
@RequestMapping(value = "/consum")
public class ConsumController extends BaseController{
	protected static final Logger logger = Logger.getLogger(ConsumController.class);
	
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
	ConsumeLogServiceImpl consumeLogServiceImpl;
	@Autowired
	ArticleChapterLogServiceImpl articleChapterLogServiceImpl;
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
				return getWXCode(chat,"/consum/list/"+webchatid);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		//查询有消费过阅读币的书籍阅读记录
		List<ArticleLog> list = this.articleLogServiceImpl.getConsumArticleLogByUserId(user.getId());
		request.setAttribute("articleLoglist", list);
		request.setAttribute("webchatid", webchatid);
		return "home/read/mind/recordConsumpte";
	}
      
	@RequestMapping(value = "/listchapter/{webchatid}/{articleid}")
	public String listchapter(@PathVariable long webchatid,@PathVariable long articleid,HttpServletRequest request, HttpServletResponse response) {
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return null;
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
				return getWXCode(chat,"/consum/listchapter/"+webchatid+"/"+articleid);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		List<ConsumeLog> list = this.consumeLogServiceImpl.getConsumeLogByUserIdAndArticleId(user.getId(),articleid);
		request.setAttribute("consums", list);
		return "home/read/mind/detailConsumpte";
	}
	
	 
}
