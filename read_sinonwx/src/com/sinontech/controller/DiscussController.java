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
import com.sinontech.modle.Discuss;
import com.sinontech.modle.UserInfo;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.BoardServiceImpl;
import com.sinontech.service.impl.DiscussServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("discusscontroller")
@RequestMapping(value = "/discuss")
public class DiscussController extends BaseController{
	protected static final Logger logger = Logger.getLogger(DiscussController.class);
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	BoardServiceImpl boardServiceImpl;
	@Autowired
	ArticleServiceImpl articleServiceImpl;
	@Autowired
	DiscussServiceImpl discussServiceImpl;
	
	/**
	 * 用途说明：查询书籍评论列表
	 * @param articleId
	 * @param request
	 * @param response
	 * @return
	 * 2018年3月1日上午11:53:45
	 * @auther ljj
	 */
	@RequestMapping("/listDiscuss/{articleId}")
	public String listDiscuss(@PathVariable long articleId,HttpServletRequest request, HttpServletResponse response){
		Article article = this.articleServiceImpl.getArticleByArticleId(articleId);
		if(null!=article){
			List<Discuss> discusslist = this.discussServiceImpl.getDiscussByArticleId(article.getId());
			request.setAttribute("discusslist", discusslist);
			long discusssum = this.discussServiceImpl.getDiscussSumPeopleByArticleId(articleId);
			request.setAttribute("discusssum", discusssum);
		}
		request.setAttribute("articleId", articleId);
		return "home/read/bookstore/bookReviewAll";
	}
	/**
	 * 用途说明：新增评论
	 * @param articleId
	 * @param request
	 * @param response
	 * @return
	 * 2018年3月1日上午11:59:58
	 * @auther ljj
	 */
	@RequestMapping("/saveDiscuss/{articleId}")
	public String saveDiscuss(@PathVariable long articleId,HttpServletRequest request, HttpServletResponse response){
		Article article = this.articleServiceImpl.getArticleByArticleId(articleId);
		if(null!=article){
			UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
			if(null==user){
				return null;
			}
			String content = request.getParameter("content");
			String level = request.getParameter("level");
			int levels = 0 ;
			try {
				levels = Integer.parseInt(level);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Discuss discuss = new Discuss();
			discuss.setArticleId(article.getId());
			discuss.setContent(content);
			discuss.setCreateTime(DateUtil.getStringNow());
			discuss.setLevels(levels);
			discuss.setUser(user);
			this.discussServiceImpl.saveDiscuss(discuss); 
			List<Discuss> discusslist = this.discussServiceImpl.getDiscussByArticleId(article.getId());
			int lev = 0;
			for (Discuss d : discusslist) {
				lev+=d.getLevels();
			}
			double score = (double)lev/discusslist.size();
			this.articleServiceImpl.updateScore(article.getId(),score);
		}
		return "redirect:/discuss/listDiscuss/"+articleId;
	}
	
	/**
	 * 用途说明：去新增评论页面
	 * @param articleId
	 * @param request
	 * @param response
	 * @return
	 * 2018年3月1日下午1:35:29
	 * @auther ljj
	 */
	@RequestMapping("/todiscuss/{articleId}")
	public String todiscuss(@PathVariable long articleId,HttpServletRequest request, HttpServletResponse response){
		Article article = this.articleServiceImpl.getArticleByArticleId(articleId);
		if(null!=article){
			UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
			if(null==user){
				return null;
			}
			 
		}
		request.setAttribute("articleId", articleId);
		return "home/read/bookstore/review";
	}
	
}