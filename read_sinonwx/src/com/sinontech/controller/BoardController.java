package com.sinontech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.Article;
import com.sinontech.modle.Board;
import com.sinontech.modle.BoardInfo;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.BoardDetailServiceImpl;
import com.sinontech.service.impl.BoardServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("boardcontroller")
@RequestMapping(value = "/board")
public class BoardController extends BaseController{
	protected static final Logger logger = Logger.getLogger(ReadController.class);
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	BoardServiceImpl boardServiceImpl;
	@Autowired
	ArticleServiceImpl articleServiceImpl;
	@Autowired
	BoardDetailServiceImpl boardDetailServiceImpl;
	
	/**
	 * 
	 * @author liuzhen
	 * 时间 2018-2-26 下午5:20:11
	 *
	 * 用途：榜单列表
	 * @return String
	 */
	@RequestMapping("/listBoard/{webchatId}")
	public String listBoard(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response){
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
				return getWXCode(chat,"/board/listBoard/"+webchatId+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		List<Board> varList = null;
		Board listDowd = null;
		List<Board> listeDitor = null;
		List<Article> articleList = null;
		List<BoardInfo> boardInfolist = new ArrayList<BoardInfo>();
		try {
			varList = boardServiceImpl.listBoard();
			listDowd = boardServiceImpl.listdowd();
			long boardId = listDowd.getId();
			articleList = articleServiceImpl.boardList(boardId);
			String editorimg = "";
			if(articleList.size()>0){
				editorimg = articleList.get(0).getBookCover();
			}
			listeDitor = boardServiceImpl.listeDitor();
			for (int i = 0; i < listeDitor.size(); i++) {
				BoardInfo boardInfo = new BoardInfo();
				Article article  = boardDetailServiceImpl.seachBoardIfolist(listeDitor.get(i).getId());
				boardInfo.setArticle(article);
				boardInfo.setBoard(listeDitor.get(i));
				boardInfolist.add(boardInfo);
			}
			request.setAttribute("editorimg", editorimg);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		request.setAttribute("varList", varList);
		request.setAttribute("listDowd", listDowd);
		request.setAttribute("boardInfolist", boardInfolist);
		request.setAttribute("articleList", articleList);
		return "home/read/board/raunKing";
	}
	
}