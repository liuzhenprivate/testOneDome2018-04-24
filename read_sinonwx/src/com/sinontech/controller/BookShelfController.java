package com.sinontech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.BookShelf;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.service.impl.BookShelfServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("bookshelfcontroller")
@RequestMapping(value = "/bookshelf")
public class BookShelfController extends BaseController{
	protected static final Logger logger = Logger.getLogger(ArticleController.class);
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	
	@Autowired
	BookShelfServiceImpl bookShelfServiceImpl;
	
	/**
	 * 
	 * @author liuzhen
	 * 时间 2018-2-27 上午10:50:52
	 *
	 * 用途：用户书架列表
	 * @return String
	 */
	@RequestMapping("/userlistBookShelf/{webchatId}")
	public String userlistBookShelf(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response){
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/bookshelf/userlistBookShelf/"+webchatId);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		List<BookShelf> varList = null;
		try {
			varList = bookShelfServiceImpl.bookShelfList(user.getId());
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		request.setAttribute("varList", varList);
		return "home/read/bookshelf/bookshelf";
	}
	
	/**
	 * 
	 * @author liuzhen
	 * 时间 2018-2-27 下午2:48:25
	 *
	 * 用途：去书架管理页面
	 * @return String
	 */
	@RequestMapping("/goEdit/{webchatId}")
	public String goEdit(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response){
		long userid = Long.parseLong(request.getParameter("userid"));
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/bookshelf/goEdit/"+webchatId+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		List<BookShelf> varList = null;
		try {
			varList = bookShelfServiceImpl.bookShelfList(userid);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		request.setAttribute("varList", varList);
		return "home/read/bookshelf/bookshelfEdit";
	}
	
	
	/**
	 * 
	 * @author liuzhen
	 * 时间 2018-2-27 下午6:48:13
	 *
	 * 用途：删除书架书籍
	 * @return void
	 */
	@RequestMapping("/delShelf/{webchatId}/{userid}")
	public String delShelf(@PathVariable long webchatId,@PathVariable long userid,HttpServletRequest request, HttpServletResponse response){
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/bookshelf/delShelf/"+webchatId+"/"+userid+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		String bookShelfId = request.getParameter("bookShelfId");
		String[] str = null;
		if(!"".equals(bookShelfId)&&null!=bookShelfId){
			str = bookShelfId.split(",");
			for (int i = 0; i < str.length; i++) {
				try {
					if(!"".equals(str[i])){
						long bookShelf = Long.parseLong(str[i]);
						bookShelfServiceImpl.del(bookShelf);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		List<BookShelf> varList = null;
		try {
			varList = bookShelfServiceImpl.bookShelfList(userid);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		request.setAttribute("varList", varList);
		return "home/read/bookshelf/bookshelfEdit";
	}
}