package com.sinontech.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.Article;
import com.sinontech.modle.ArticleChapter;
import com.sinontech.modle.ArticleChapterLog;
import com.sinontech.modle.ArticleLog;
import com.sinontech.modle.ConsumeLog;
import com.sinontech.modle.ExtensionContent;
import com.sinontech.modle.ExtensionUser;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleChapterLogServiceImpl;
import com.sinontech.service.impl.ArticleChapterServiceImpl;
import com.sinontech.service.impl.ArticleLogServiceImpl;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.ConsumeLogServiceImpl;
import com.sinontech.service.impl.CountServiceImpl;
import com.sinontech.service.impl.ExtensionContentServiceImpl;
import com.sinontech.service.impl.ExtensionUserServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("articlechaptercontroller")
@RequestMapping(value = "/articlechapter")
public class ArticleChapterController extends BaseController{
	protected static final Logger logger = Logger
			.getLogger(ArticleController.class);

	private final String hosturl = "http://127.0.0.1:8089/read/";
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	ArticleChapterServiceImpl articleChapterServiceImpl;
	@Autowired
	ArticleChapterLogServiceImpl articleChapterLogServiceImpl;
	@Autowired
	ConsumeLogServiceImpl consumeLogServiceImpl;
	@Autowired
	ArticleLogServiceImpl articleLogServiceImpl;
	@Autowired
	ExtensionContentServiceImpl extensionContentServiceImpl;
	@Autowired
	ArticleServiceImpl articleServiceImpl;
	@Autowired
	CountServiceImpl countServiceImpl;
	@Autowired
	ExtensionUserServiceImpl extensionUserServiceImpl;
	
	/**
	 * @purpose：查询书籍章节内容
	 * @param userId
	 * @param articlechapterid
	 * @param request
	 * @param response
	 * @return String
	 * @author liuzhen
	 * @Time：2018-3-11 上午11:09:28
	 */
	@RequestMapping(value = "/articlechapterGetById/{webchatId}/{articlechapterid}/{userId}/{forceChapter}")
	public String articlechapterGetById(@PathVariable long webchatId,@PathVariable long userId,
			@PathVariable long articlechapterid,@PathVariable long forceChapter,HttpServletRequest request,HttpServletResponse response) {
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		 
		//如果id为1则是购买阅读章节
		String id = request.getParameter("id");
		String orderurl = "";
		if(null!=id && !"".equals(id) && !"null".equals(id) && "1".equals(id)){
			orderurl = "?id=1";
		}else{
			id ="";
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
				return getWXCode(chat,"/articlechapter/articlechapterGetById/"+webchatId+"/"+articlechapterid+"/"+userId+"/"+forceChapter+orderurl);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		user = this.userinfoService.getUserInfoById(user.getId());
		
		ArticleChapter articleChapter = null;
		String chapterTxt = "";
		ConsumeLog consumelog = null;
		//是否可阅读 0可免费阅读 1未开启自动扣费且阅读币不够  2未开启自动扣费且费用够 3未开启自动扣费用户直接购买 4用户已购买过 5开启自动扣费且费用够6开启自动扣费且费用不够
		int isread = 0;
		boolean isreadflag = false;
		articleChapter = articleChapterServiceImpl.getArticleChapterById(articlechapterid);
		if(null==articleChapter){
			return null;
		}
		if(forceChapter>0l){
			ArticleChapter articleChapter1 = this.articleChapterServiceImpl.getArticleChapterById(forceChapter);
			//已到强制关注章节
			if(articleChapter1.getChapterNo()<=articleChapter.getChapterNo()){
				//该用户还未关注
				if(user.getFollowstate()==0){
					request.setAttribute("webchat", chat);
					request.setAttribute("imghosturl", imghosturl);
					return "home/read/extension/compulsoryAttention";
				}
			}
		}
		String dateTime = DateUtil.getStringNow();
		ArticleLog articleLog = articleLogServiceImpl.getArticleLogByUIdAndAid(userId,articleChapter.getArticle().getId());
		if(null==articleLog){
			articleLog = new ArticleLog();
			articleLog.setArticle(articleChapter.getArticle());
			articleLog.setUserId(userId);
			articleLog.setChannelId(chat.getUserid());
			articleLog.setWebchatId(user.getWebchatId());
			articleLog.setArticleName(articleChapter.getArticle().getArticleName());
			articleLog.setChapterName(articleChapter.getChapterName());
			articleLog.setCreateTime(dateTime);
			articleLog.setFee(articleChapter.getConsumes());
			articleLog.setArticleChapterId(articlechapterid);
			articleLogServiceImpl.saveArticleLog(articleLog);
			
			logger.debug("第一次阅读"+userId+"=="+articleChapter.getArticle().getId());
			this.articleServiceImpl.updateReaders(articleChapter.getArticle().getId());
		}else{
			logger.error("已阅读="+userId+"=="+articleChapter.getArticle().getId());
		}
		
		//判断是否收费
		if (articleChapter.getConsumes()>0) {
			//查询是否消费过此章节
			System.out.println(userId+"------"+articlechapterid);
			consumelog = consumeLogServiceImpl.selectIsNotNull(userId,articlechapterid);
			if(consumelog==null){
				int order = user.getAutoOrder();
				//未消费过
				//判断用户的阅读币是否够
				//剩余的阅读币
				long usercurrency = user.getCumulativeCurrency()-user.getBookCurrency();
				if (usercurrency < articleChapter.getConsumes() ) {
					if(order==0){
						isread = 1;
					}else{
						isread = 6;
					}
					
				}else{
					
					//未开启
					if(0==order){
						if("1".equals(id)){
							isread = 3;
						}else{
							isread = 2;
						}
					}else{
						isread = 5;
					}
				}
			}else{
				isread = 4;
			}
		}
		//免费阅读和已购买过保存阅读记录即可
		if(1==isread || 2==isread || 6==isread){
			//1未开启自动扣费且阅读币不够  2未开启自动扣费且费用够6开启自动扣费且费用不够
		}else{
			isreadflag = true;
			//添加书籍章节阅读记录
			ArticleChapterLog articleChapterLog = new ArticleChapterLog();
			articleChapterLog.setArticle(articleChapter.getArticle());
			articleChapterLog.setArticleChapter(articleChapter);
			articleChapterLog.setFee(articleChapter.getConsumes());
			articleChapterLog.setCreateTime(dateTime);
			articleChapterLog.setArticleName(articleChapter.getArticle().getArticleName());
			articleChapterLog.setChannelId(chat.getUserid());
			articleChapterLog.setChapterName(articleChapter.getChapterName());
			articleChapterLog.setUserId(user.getId());
			articleChapterLog.setWebchatId(user.getWebchatId());
			articleChapterLogServiceImpl.saveArticleChapterLog(articleChapterLog);
			
			//保存消费记录
			if(3==isread || 5==isread  ){
				//更新用户信息
				user.setBookCurrency((user.getBookCurrency()+articleChapter.getConsumes()));
				userinfoService.updateUserInfo(user);
				//更新书籍付费人数  +1
				ConsumeLog c = consumeLogServiceImpl.seachWhetherReading(userId,articleChapter.getArticle().getId()); 
				if(c==null){
					articleServiceImpl.updateArticleFeePoples(articleChapter.getArticle().getId());
				}
				//3未开启自动扣费用户直接购买 5开启自动扣费且费用够
				//添加书籍消费记录
				ConsumeLog consumeLog = new ConsumeLog();
				consumeLog.setUserId(userId);
				consumeLog.setChannelId(chat.getUserid());
				consumeLog.setWebchatId(user.getWebchatId());
				consumeLog.setArticleId(articleChapter.getArticle().getId());
				consumeLog.setArticleChapterId(articleChapter.getId());
				consumeLog.setArticleName(articleChapter.getArticle().getArticleName());
				consumeLog.setChapterName(articleChapter.getChapterName());
				consumeLog.setCreateTime(dateTime);
				consumeLog.setConsumes(articleChapter.getConsumes());
				consumeLog.setConsumeType(0);
				consumeLogServiceImpl.saveConsumeLog(consumeLog);
				countServiceImpl.addBookCurrency(chat.getUserid(), user.getWebchatId(), 0l,
						0l, 0l, 0l,0l, articleChapter.getConsumes(), 0, 1);
				//更新书籍阅读记录的消费阅读币
				//添加书籍阅读记录
				articleLog = articleLogServiceImpl.getArticleLogByUIdAndAid(userId,articleChapter.getArticle().getId());
				articleLog.setFee(articleLog.getFee()+articleChapter.getConsumes());
				this.articleLogServiceImpl.updateArticleLog(articleLog);
			}
		}
		if(isreadflag){
			//可以阅读章节
			//书籍章节内容
			chapterTxt = getArticleChapter(articleChapter.getContentUrl());
		}
		ArticleChapter articleChapterup = this.articleChapterServiceImpl.readUpperArticleChapter(articleChapter.getArticle().getId(), articleChapter.getChapterNo());
		ArticleChapter articleChapternext = this.articleChapterServiceImpl.readNextArticleChapter(articleChapter.getArticle().getId(), articleChapter.getChapterNo());
		if(null != articleChapterup){
			request.setAttribute("articleChapterup", articleChapterup);
		}
		if(null != articleChapternext){
			request.setAttribute("articleChapternext", articleChapternext);
		}
		request.setAttribute("userCurrency", user.getCumulativeCurrency()-user.getBookCurrency());
		request.setAttribute("isorder", user.getAutoOrder());
		request.setAttribute("isread", isread);
		request.setAttribute("isreadflag", isreadflag);
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		request.setAttribute("articleChapter", articleChapter);
		request.setAttribute("chapterTxt", chapterTxt);
		request.setAttribute("forceChapter", forceChapter);
		return "home/read/bookstore/changeColor";
	}
	@RequestMapping(value = "/articleChapterfee/{webchatid}/{articleChapterId}")
	public void articleChapterfee(@PathVariable long webchatid,
			@PathVariable long articleChapterId, HttpServletRequest request, HttpServletResponse response) {
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatid);
		if(null==chat){
			return ;
		}
		//是否可阅读 0可免费阅读 1未开启自动扣费且阅读币不够  2未开启自动扣费且费用够 3未开启自动扣费用户直接购买 
		//4用户已购买过 5开启自动扣费且费用够6开启自动扣费且费用不够
		String isread = "-1";
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null!=user){
			long usercurrency = user.getCumulativeCurrency()-user.getBookCurrency();
			user = this.userinfoService.getUserInfoById(user.getId());
			ArticleChapter articleChapter = this.articleChapterServiceImpl.getArticleChapterById(articleChapterId);
			if(null!=articleChapter){
				ConsumeLog consumelog = consumeLogServiceImpl.selectIsNotNull(user.getId(),articleChapterId);
				if(consumelog!=null){
					isread = "4=="+usercurrency;
				}else{
					long fee = articleChapter.getConsumes();
					int autoorder = user.getAutoOrder();
					if(fee>0){
						if(fee>usercurrency){
							//阅读币不够
							if(1 == autoorder){
								isread = "6=="+usercurrency;
							}else{
								isread = "1=="+usercurrency;
							}
						}else{
							if(0 == autoorder){
								isread = "2=="+usercurrency;
							}else{
								isread = "5=="+usercurrency;
							}
						}
					}else{
						isread = "0=="+usercurrency;
					}
				}
			}else{
				return;
			}
		}else{
			return;
		}
		response( isread ,response);
		
	}
	/**
	 * 
	 * @purpose：阅读下一章节
	 * @param webchatId
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 * @author liuzhen
	 * @Time：2018-3-20 下午5:22:36
	 *//*
	@RequestMapping(value = "/readNextArticleChapter/{webchatId}/{userId}")
	public String readNextArticleChapter(@PathVariable long webchatId,@PathVariable long userId,
			HttpServletRequest request,HttpServletResponse response){
		long articleId = Long.parseLong(request.getParameter("articleId"));
		long articlechapterid = Long.parseLong(request.getParameter("articleChpterId"));
		
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
				return getWXCode(chat,"/articlechapter/readNextArticleChapter/"+webchatId+"/"+userId+".do?articlechapterid="+articlechapterid+"&articleId="+articleId);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		ArticleChapter articleChapter = null;
		String chapterTxt = "";
		ConsumeLog consumelog = null;
		try {
			articleChapter = articleChapterServiceImpl.getArticleChapterById(articlechapterid);
			if(null==articleChapter){
				return null;
			}
			//判断是否收费
			if (articleChapter.getIsFree() == 1) {
				//查询是否消费过此章节
				System.out.println(userId+"------"+articlechapterid);
				consumelog = consumeLogServiceImpl.selectIsNotNull(userId,articlechapterid);
				if(consumelog==null){
					if (articleChapter.getConsumes() <= (user.getCumulativeCurrency()-user.getBookCurrency())) {
						//书籍章节内容
						chapterTxt = getArticleChapter(articleChapter.getContentUrl());
						user.setBookCurrency((user.getBookCurrency()+articleChapter.getConsumes()));
						//更新用户信息
						userinfoService.updateUserInfo(user);
						String dateTime = DateUtil.getStringNow();
						//添加书籍章节阅读记录
						ArticleChapterLog articleChapterLog = new ArticleChapterLog();
						articleChapterLog.setArticle(articleChapter.getArticle());
						articleChapterLog.setArticleChapter(articleChapter);
						articleChapterLog.setFee(articleChapter.getConsumes());
						articleChapterLog.setCreateTime(dateTime);
						articleChapterLog.setArticleName(articleChapter.getArticle().getArticleName());
						articleChapterLog.setChannelId(user.getChannelId());
						articleChapterLog.setChapterName(articleChapter.getChapterName());
						articleChapterLog.setUserId(user.getId());
						articleChapterLog.setWebchatId(user.getWebchatId());
						articleChapterLogServiceImpl.saveArticleChapterLog(articleChapterLog);
						
						//添加书籍阅读记录
						ArticleLog a = articleLogServiceImpl.getArticleLogByUIdAndAid(userId,articleChapter.getArticle().getId());
						ArticleLog articleLog = new ArticleLog();
						articleLog.setArticle(articleChapter.getArticle());
						articleLog.setUserId(userId);
						articleLog.setChannelId(user.getChannelId());
						articleLog.setWebchatId(user.getWebchatId());
						articleLog.setArticleName(articleChapter.getArticle().getArticleName());
						articleLog.setChapterName(articleChapter.getChapterName());
						articleLog.setCreateTime(dateTime);
						articleLog.setFee(articleChapter.getConsumes());
						articleLog.setArticleChapterId(articlechapterid);
						if(a!=null){
							articleLogServiceImpl.updateArticleLog(articleLog);
						}else{
							articleLogServiceImpl.saveArticleLog(articleLog);
							//添加书籍消费记录
							ConsumeLog consumeLog = new ConsumeLog();
							consumeLog.setUserId(userId);
							consumeLog.setChannelId(user.getChannelId());
							consumeLog.setWebchatId(user.getWebchatId());
							consumeLog.setArticleId(articleChapter.getArticle().getId());
							consumeLog.setArticleChapterId(articleChapter.getId());
							consumeLog.setArticleName(articleChapter.getArticle().getArticleName());
							consumeLog.setChapterName(articleChapter.getChapterName());
							consumeLog.setCreateTime(dateTime);
							consumeLog.setConsumes(articleChapter.getConsumes());
							consumeLog.setConsumeType(0);
							consumeLogServiceImpl.saveConsumeLog(consumeLog);
							countServiceImpl.addBookCurrency(user.getChannelId(), user.getWebchatId(), 0l,
									0l, 0l, 0l,0l, articleChapter.getConsumes(), 0,
									1);
						}
					}else{
						//如果用户账户里剩余阅读币不够购买下一下章节
						request.setAttribute("currency", 0);
						
					}
				}else{
					//书籍章节内容
					chapterTxt = getArticleChapter(articleChapter.getContentUrl());
					String dateTime = DateUtil.getStringNow();
					//添加书籍章节阅读记录
					ArticleChapterLog articleChapterLog = new ArticleChapterLog();
					articleChapterLog.setArticle(articleChapter.getArticle());
					articleChapterLog.setArticleChapter(articleChapter);
					articleChapterLog.setFee(articleChapter.getConsumes());
					articleChapterLog.setCreateTime(dateTime);
					articleChapterLog.setArticleName(articleChapter.getArticle().getArticleName());
					articleChapterLog.setChannelId(user.getChannelId());
					articleChapterLog.setChapterName(articleChapter.getChapterName());
					articleChapterLog.setUserId(user.getId());
					articleChapterLog.setWebchatId(user.getWebchatId());
					articleChapterLogServiceImpl.saveArticleChapterLog(articleChapterLog);
					
					//添加书籍阅读记录
					ArticleLog a = articleLogServiceImpl.getArticleLogByUIdAndAid(userId,articleChapter.getArticle().getId());
					ArticleLog articleLog = new ArticleLog();
					articleLog.setArticle(articleChapter.getArticle());
					articleLog.setUserId(userId);
					articleLog.setChannelId(user.getChannelId());
					articleLog.setWebchatId(user.getWebchatId());
					articleLog.setArticleName(articleChapter.getArticle().getArticleName());
					articleLog.setChapterName(articleChapter.getChapterName());
					articleLog.setCreateTime(dateTime);
					articleLog.setFee(articleChapter.getConsumes());
					if(a!=null){
						articleLogServiceImpl.updateArticleLog(articleLog);
					}else{
						articleLogServiceImpl.saveArticleLog(articleLog);
					}
				}
			}else{
				 
				//书籍章节内容
				chapterTxt = getArticleChapter(articleChapter.getContentUrl());
				String dateTime = DateUtil.getStringNow();
				//添加书籍章节阅读记录
				ArticleChapterLog articleChapterLog = new ArticleChapterLog();
				articleChapterLog.setArticle(articleChapter.getArticle());
				articleChapterLog.setArticleChapter(articleChapter);
				articleChapterLog.setFee(articleChapter.getConsumes());
				articleChapterLog.setCreateTime(dateTime);
				articleChapterLog.setArticleName(articleChapter.getArticle().getArticleName());
				articleChapterLog.setChannelId(user.getChannelId());
				articleChapterLog.setChapterName(articleChapter.getChapterName());
				articleChapterLog.setUserId(user.getId());
				articleChapterLog.setWebchatId(user.getWebchatId());
				articleChapterLogServiceImpl.saveArticleChapterLog(articleChapterLog);
				
				//添加书籍阅读记录
				ArticleLog a = articleLogServiceImpl.getArticleLogByUIdAndAid(userId,articleChapter.getArticle().getId());
				ArticleLog articleLog = new ArticleLog();
				articleLog.setArticle(articleChapter.getArticle());
				articleLog.setUserId(userId);
				articleLog.setChannelId(user.getChannelId());
				articleLog.setWebchatId(user.getWebchatId());
				articleLog.setArticleName(articleChapter.getArticle().getArticleName());
				articleLog.setChapterName(articleChapter.getChapterName());
				articleLog.setCreateTime(dateTime);
				articleLog.setFee(articleChapter.getConsumes());
				if(a!=null){
					articleLogServiceImpl.updateArticleLog(articleLog);
				}else{
					articleLogServiceImpl.saveArticleLog(articleLog);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		request.setAttribute("user", user);
		request.getSession().setAttribute("userinfo", user);
		request.setAttribute("articleChapter", articleChapter);
		request.setAttribute("chapterTxt", chapterTxt);
		return "home/read/bookstore/changeColor";
	}*/
	
	
	@RequestMapping(value = "/findNextArticleChapter")
	@ResponseBody
	public Object findNextArticleChapter(HttpServletRequest request,HttpServletResponse response){
		long articleChapterId = Long.parseLong(request.getParameter("articleChapterId"));
		long articleId = Long.parseLong(request.getParameter("articleId"));
		ArticleChapter articleChapter = null;
		try {
			articleChapter = articleChapterServiceImpl.seachNextChapter(articleId,articleChapterId);	
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		return articleChapter;
	}
	
	
	/**
	 * 
	 * @purpose：接收渠道分享链接
	 * @param request
	 * @param response
	 * @return
	 * @return String
	 * @author liuzhen
	 * @Time：2018-3-16 下午4:06:12
	 */
	@RequestMapping(value = "/visit/{tid}")
	public String Manufacture(@PathVariable long tid,HttpServletRequest request,HttpServletResponse response){
		ExtensionContent extensionContent = null;
		List<ArticleChapter> articleChapterlist = null;
		List<Map> chapterList = new ArrayList<Map>();
		try {
			extensionContent = extensionContentServiceImpl.selectFindById(tid);
			if(extensionContent==null){
				return null;
			}
			long user_id = extensionContent.getUser_id();
			Webchat chat = this.webchatServiceImpl.selectFindByChannelId(user_id);
			UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
			if(null==chat){
				return null;
			}
			if(null==user){
				String CODE = request.getParameter("code");
				if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
						//通过CODE获取到用户信息
					 	user = getUserInfo(chat,CODE,0l,extensionContent);
					 	if(null!=user){
					 		request.getSession().setAttribute("userinfo", user);
					 	}else{
					 		return null;
					 	}
				}else{
					return getWXCode(chat,"/articlechapter/visit/"+tid );
				}
			}else{
				//判断未关注用户更新用户信息
				if(user.getFollowstate()==0){
					user = updateUserInfo(chat,user);
				}
			}
			long articleId = extensionContent.getArticle().getId();
			int articleChpaters = extensionContent.getArticleChapters();
			articleChapterlist = articleChapterServiceImpl.visitSeachChapter(articleId,articleChpaters);
			if(articleChapterlist.size()==0){
				return null;
			}
			
			for (ArticleChapter articleChapter:articleChapterlist) {
				Map<String,Object> map = new HashMap<String,Object>();
				//书籍章节内容
				String chapterTxt = getArticleChapter(articleChapter.getContentUrl());
				map.put("articleChapter", articleChapter);
				map.put("chapterTxt", chapterTxt);
				chapterList.add(map);
			}
			//最后一章
			ArticleChapter lastarticleChapter =articleChapterlist.get(articleChapterlist.size()-1);
			ArticleChapter articleChapter = this.articleChapterServiceImpl.readNextArticleChapter(articleId, lastarticleChapter.getChapterNo());
			if(null!=articleChapter){
				request.setAttribute("articleChapterid", articleChapter.getId());
			}else{
				request.setAttribute("articleChapterid", lastarticleChapter.getId());
			}
			request.setAttribute("user_id", user_id);
			request.getSession().setAttribute("userinfo", user);
			request.setAttribute("webchat", chat);
		} catch (Exception e) {
			logger.error(e.toString(),e);
		}
		
		request.setAttribute("extensionContent",extensionContent );
		request.setAttribute("articleChapterlist", articleChapterlist);
		request.setAttribute("chapterList", chapterList);
		
		
		request.setAttribute("httpUrl", "http://kuwx.xinxinwx.cn/read/");
		return "home/read/extension/extension_textReading";
	}
	
	 
	
	 
	
	public String getArticleChapter(String chapterurl){
		URL url =null;
		String chapterTxt = "";
		InputStream inputStream = null;
		HttpURLConnection conn= null;
		try {
			url = new URL(hosturl+chapterurl);
			
			try {
				conn = (HttpURLConnection) url.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			// 得到输入流
			try {
				inputStream = conn.getInputStream();
				//书籍章节内容
				chapterTxt = readInputStream(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(null!=inputStream){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}finally{
			if(null!=conn){
				conn.disconnect();
			}
		}
		return chapterTxt;
	}
}