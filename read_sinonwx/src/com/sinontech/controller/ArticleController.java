package com.sinontech.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.Article;
import com.sinontech.modle.ArticleChapter;
import com.sinontech.modle.ArticleChapterLog;
import com.sinontech.modle.ArticleLabel;
import com.sinontech.modle.ArticleLog;
import com.sinontech.modle.BookShelf;
import com.sinontech.modle.Collectionlog;
import com.sinontech.modle.Discuss;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.modle.ZanLog;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleChapterLogServiceImpl;
import com.sinontech.service.impl.ArticleChapterServiceImpl;
import com.sinontech.service.impl.ArticleLabelServiceImpl;
import com.sinontech.service.impl.ArticleLogServiceImpl;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.BookShelfServiceImpl;
import com.sinontech.service.impl.CollectionlogServiceImpl;
import com.sinontech.service.impl.DiscussServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;
import com.sinontech.service.impl.ZanlogServiceImpl;

import common.Logger;


@Controller("articlecontroller")
@RequestMapping(value = "/article")
public class ArticleController extends BaseController{
	protected static final Logger logger = Logger.getLogger(ArticleController.class);
	
	@Autowired
	ArticleServiceImpl articleServiceImpl;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	ArticleChapterServiceImpl articleChapterServiceImpl;
	@Autowired
	DiscussServiceImpl discussServiceImpl;
	@Autowired
	ZanlogServiceImpl zanlogServiceImpl;
	@Autowired
	BookShelfServiceImpl bookShelfServiceImpl;
	@Autowired
	ArticleChapterLogServiceImpl articleChapterLogServiceImpl;
	@Autowired
	ArticleLabelServiceImpl articleLabelServiceImpl;
	@Autowired
	ArticleLogServiceImpl articleLogServiceImpl;
	@Autowired
	CollectionlogServiceImpl collectionlogServiceImpl;
	
	/**
	 * 
	 *用途：查询书籍详情
	 *@return Article
	 *@author 刘振
	 *2018-2-24 下午3:53:02
	 */
	@RequestMapping("/findArticleId/{webchatId}/{articleid}/{tid}")
	public String findArticleId(@PathVariable long webchatId,@PathVariable long articleid,@PathVariable long tid,HttpServletRequest request, HttpServletResponse response){
		Article article = null;
		try {
			Webchat webchat = this.webchatServiceImpl.getWebchatById(webchatId);
			if(null==webchat){
				return null;
			}
			UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
			if(null==user){
				String CODE = request.getParameter("code");
				if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
						//通过CODE获取到用户信息
						if(tid>0){
							user = getUserInfo(webchat,CODE,tid,null);
						}else{
							user = getUserInfo(webchat,CODE);
						}
					 	
					 	if(null!=user){
					 		request.getSession().setAttribute("userinfo", user);
					 	}else{
					 		return null;
					 	}
				}else{
					return getWXCode(webchat,"/article/findArticleId/"+webchatId+"/"+articleid+"/"+tid+".do");
				}
			}else{
				//判断未关注用户更新用户信息
				if(user.getFollowstate()==0){
					user = updateUserInfo(webchat,user);
				}
			}
			 
			article = articleServiceImpl.getArticleByArticleId(articleid);
			if(null!=article){
				String url = webchat.getDomainName()+"/article/findArticleId/"+webchatId+"/"+articleid+"/"+tid+".do";
				request.setAttribute("url", url);
				long discusssum = this.discussServiceImpl.getDiscussSumPeopleByArticleId(article.getId());
				request.setAttribute("discusssum", discusssum);
				List<Discuss> discusslist = this.discussServiceImpl.getPageDiscussByArticleId(article.getId(),1,4);
				request.setAttribute("discusslist", discusslist);
				List<ArticleLabel> labellist = articleLabelServiceImpl.varListLabel(articleid);
				request.setAttribute("labellist", labellist);
				ArticleChapterLog articleChapterLog = articleChapterLogServiceImpl.getNewArticleChapterLogByUserIdAndArticleid(user.getId(),articleid);
				if(articleChapterLog==null){
					ArticleChapter articleChapter = articleChapterServiceImpl.getArticleChapterFirstByarticleId(articleid);
					if(articleChapter!=null){
						request.setAttribute("articleChapterId", articleChapter.getId());
					}else{
						request.setAttribute("articleChapterId", 0);
					}
				}else{
					request.setAttribute("articleChapterId", articleChapterLog.getArticleChapter().getId());
				}
				request.setAttribute("user", user);
				if(null!=user){
					//判断用户是否已收藏过该书籍
					BookShelf bookShelf = this.bookShelfServiceImpl.findBookShelf(user.getId(), articleid);
					if(null==bookShelf){
						//未收藏
						request.setAttribute("isBookShelf", 0);
					}else{
						//已收藏
						request.setAttribute("isBookShelf", 1);
					}
				}
				/*ArticleLog articleLog = this.articleLogServiceImpl.getArticleLogByUIdAndAid(user.getId(),article.getId());
				if(null==articleLog){
					ArticleChapter chapter = new ArticleChapter();
					chapter.setId(1l);
					articleLog = new ArticleLog();
					articleLog.setArticle(article);
					articleLog.setArticleName(article.getArticleName());
					articleLog.setUserId(user.getId());
					articleLog.setCreateTime(DateUtil.getStringNow());
					articleLog.setFee(0l);
					articleLog.setWebchatId(webchatId);
					this.articleLogServiceImpl.saveArticleLog(articleLog);
				}*/
			}else{
				return null;
			}
			request.setAttribute("webchat", webchat);
			request.setAttribute("webchatId", webchatId);
			request.setAttribute("articleid", articleid);
			request.setAttribute("tid", tid);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		request.setAttribute("article", article);
		return "home/read/bookstore/bookDetail";
	}
	/**
	 * 用途说明：收藏书籍
	 * @param articleid
	 * @param request
	 * @param response
	 * 2018年3月1日上午9:31:09
	 * @auther ljj
	 */
	@RequestMapping(value = "/collect/{articleid}")
	public void collect(@PathVariable long articleid, HttpServletRequest request, HttpServletResponse response) {
		int collections=0;	
		try {
				UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
//				UserInfo user = this.userinfoService.getUserInfoById(webchatid,userid);
				if(null!=user){
					long userid = user.getId();
					Article article = this.articleServiceImpl.getArticleByArticleId(articleid);
					if(null!=article){
						Collectionlog log = this.collectionlogServiceImpl.getCollectionlogByUIdAndAid(user.getId(), articleid);
						if(null==log){
//							System.out.println("====log is null ");
							log = new Collectionlog();
							log.setArticleId(article.getId());
							log.setUserid(user.getId());
							log.setWebchatId(user.getWebchatId());
							log.setCreateTime(DateUtil.getStringNow());
							this.collectionlogServiceImpl.saveCollectionlog(log);
							//书籍收藏数加一
							collections = this.articleServiceImpl.updateColletion(articleid);
						}
//						System.out.println("====log is  == "+log.toString());
						BookShelf bookShelf = this.bookShelfServiceImpl.findBookShelf(userid, articleid);
						if(null!=bookShelf){
							//已收藏
							logger.error("书籍已收藏：" );
						}else{
							bookShelf = new BookShelf();
							bookShelf.setUser_id(0l);
							bookShelf.setCreateTime(DateUtil.getStringNow());
							bookShelf.setUpdateTime(DateUtil.getStringNow());
							bookShelf.setUserid(userid);
							bookShelf.setArticle(article);
							//查询用户阅读记录到第几章了
							ArticleChapterLog articleChapterLog =this.articleChapterLogServiceImpl.getNewArticleChapterLogByUserIdAndArticleid(userid, articleid);
							ArticleChapter ac = null;
							if(null==articleChapterLog){
								ac = this.articleChapterServiceImpl.getArticleChapterFirstByarticleId(articleid);
								if(null==ac){
									return;
								}
							}else{
								ac = articleChapterLog.getArticleChapter();
							}
							 
							bookShelf.setArticleChapter(ac);
//							logger.debug("bookShelf=="+bookShelf.toString());
							//新增书架
							this.bookShelfServiceImpl.add(bookShelf);
							
//							logger.debug("collections="+collections);
						}
					}else{
						logger.error("书籍不存在："+articleid);
					}
				}else{
					logger.error("用户不存在：");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		response(String.valueOf(collections),response);
	}
	
	/**
	 * 用途说明：查看书籍目录
	 * @param articleid
	 * @param request
	 * @param response
	 * @return
	 * 2018年2月28日下午2:24:57
	 * @auther ljj
	 */
	@RequestMapping("/lookbookCatalogues/{webchatId}/{articleid}")
	public String lookbookCatalogues(@PathVariable long webchatId,@PathVariable long articleid,HttpServletRequest request, HttpServletResponse response){
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/article/lookbookCatalogues/"+webchatId+"/"+articleid+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		user = this.userinfoService.getUserInfoById(user.getId());
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		try {
			Article article = articleServiceImpl.getArticleByArticleId(articleid);
			if(null!=article){
				List<ArticleChapter> list = this.articleChapterServiceImpl.getArticleChapterByarticleId(article.getId());
				request.setAttribute("list", list);
			}
			request.setAttribute("article", article);
			request.setAttribute("userCurrency", user.getCumulativeCurrency()-user.getBookCurrency());
			request.setAttribute("isorder", user.getAutoOrder());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return "home/read/bookstore/bookCatalogues";
	}
	/**
	 * 用途说明：点赞
	 * @param articleid
	 * @param discussId
	 * @param request
	 * @param response
	 * 2018年2月28日下午3:56:50
	 * @auther ljj
	 */
	@RequestMapping(value = "/zan/{articleid}/{discussId}")
	public void zan(@PathVariable long articleid,@PathVariable long discussId, HttpServletRequest request, HttpServletResponse response) {
		long zans =0l;
		logger.error(articleid+"=discussId=："+discussId);
			try {
				UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
//				UserInfo user = this.userinfoService.getUserInfoById(webchatid,userid);
				if(null!=user){
					long userid = user.getId();
					Article article = this.articleServiceImpl.getArticleByArticleId(articleid);
					if(null!=article){
						Discuss discuss = this.discussServiceImpl.getDiscussById(discussId);
						if(null!=discuss){
							String day = DateUtil.getStringShortNow(); 
							List<ZanLog> list = this.zanlogServiceImpl.getZanLogByUserIdAndDiscussId(userid,discussId);
							if(null!=list && list.size()>0){
								logger.error(userid+"已经赞过了："+day);
							}else{
								String time = DateUtil.getStringNow();
								//添加点赞日志
								ZanLog log = new ZanLog();
								log.setArticleId(articleid);
								log.setUserId(userid);
								log.setCreateTime(time);
								log.setLevels(0);
								log.setDiscussId(discussId);
								this.zanlogServiceImpl.saveZanLog(log);	
								zans = discuss.getZans()+1;
								discuss.setZans(zans);
								this.discussServiceImpl.updateDiscuss(discuss);
							}
						}else{
							logger.error("评论不存在" );
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
		response(String.valueOf(zans),response);
	}
	
	/**
	 * 
	 * @purpose：查询用户历史阅读记录
	 * @param webchatId
	 * @param userid
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 * @author liuzhen
	 * @Time：2018-3-12 下午3:47:51
	 */
	@RequestMapping(value = "/historicalRecords/{webchatId}/{userId}")
	public String historicalRecords(@PathVariable long webchatId,@PathVariable long userId,
			HttpServletRequest request,HttpServletResponse response) {
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/article/historicalRecords/"+webchatId+"/"+userId+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		List<ArticleChapterLog> varList = null;
		try {
			try {
				varList = articleChapterLogServiceImpl.getArticleChapterLogByUserId(userId);
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("varList", varList);
		return "home/read/mind/historyRead";
	}
	
	/**
	 * 
	 * @purpose：删除历史阅读记录
	 * @return void
	 * @author liuzhen
	 * @Time：2018-3-12 下午4:28:57
	 */
	@RequestMapping(value = "/delArticleChapterLog/{webchatId}/{id}")
	public String delArticleChapterLog(@PathVariable long id,@PathVariable long webchatId,HttpServletRequest request,HttpServletResponse response){
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/article/delArticleChapterLog/"+webchatId+"/"+id+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);

		ArticleChapterLog articleChapterLog = new ArticleChapterLog();
		articleChapterLog.setId(id);
		try {
			articleChapterLogServiceImpl.delArticleChapterLog(articleChapterLog);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return null;
	}
	/**
	 * 
	 * @purpose：清空用户阅读所有阅读记录
	 * @param userId
	 * @param request
	 * @param response
	 * @return void
	 * @author liuzhen
	 * @Time：2018-3-12 下午5:01:48
	 */
	@RequestMapping(value = "/delAllArticleChapterLog/{webchatId}/{userId}")
	public String delAllArticleChapterLog(@PathVariable long userId,@PathVariable long webchatId,
			HttpServletRequest request,HttpServletResponse response){
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.info("-----------------"+chat.toString());  
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
				return getWXCode(chat,"/article/delAllArticleChapterLog/"+webchatId+"/"+userId+".do");
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		try {
			List<ArticleChapterLog> varList = articleChapterLogServiceImpl.getArticleChapterLogByUserId(userId);
			if(varList.size()>0){
				for (ArticleChapterLog log:varList) {
					articleChapterLogServiceImpl.delArticleChapterLog(log);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return null;
	}
	
}