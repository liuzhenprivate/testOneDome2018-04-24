package com.sinontech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.Article;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.service.impl.BoardDetailServiceImpl;
import com.sinontech.service.impl.BoardServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("boarddetailcontroller")
@RequestMapping(value = "/boarddetil")
public class BoardDetailController extends BaseController{
	protected static final Logger logger = Logger.getLogger(ReadController.class);
	@Autowired
	BoardDetailServiceImpl boardDetailServiceImpl;
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	BoardServiceImpl boardServiceImpl;
	
	/**
	 * 
	 *用途：查询榜单
	 *@return List<Board>
	 *@author 刘振
	 *2018-2-24 下午4:29:22
	 */
	@RequestMapping("/listBoardDetail/{webchatId}/{boardId}/{boardType}")
	public String listBoardDetail(@PathVariable long webchatId,@PathVariable long boardId,@PathVariable int boardType,
			HttpServletRequest request, HttpServletResponse response){
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
				return getWXCode(chat,"/boarddetil/listBoardDetail/"+webchatId+"/"+boardId+"/"+boardType+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		
		if(!"".equals(request.getParameter("one"))&&null!=request.getParameter("one")){
			boardServiceImpl.increase(boardId);
		}
		List<Article> varList = null;
		try {
			varList = boardDetailServiceImpl.listBoardDetail(boardId,boardType);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		request.setAttribute("varList", varList);
		request.setAttribute("boardType", boardType);
		request.setAttribute("boardId", boardId);
		return "home/read/board/detailsList";
	}
}
