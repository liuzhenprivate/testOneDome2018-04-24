package com.sinontech.controller.read.htmlmodle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.entity.read.Article;
import com.sinontech.entity.read.Html;
import com.sinontech.entity.read.HtmlModle;
import com.sinontech.entity.read.HtmlModleDetail;
import com.sinontech.entity.system.User;
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.PathUtil;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.print.SessionUtils;
import com.sinontech.service.read.article.ArticleService;
import com.sinontech.service.read.articlecategory.ArticleCategoryService;
import com.sinontech.service.read.html.HtmlService;
import com.sinontech.service.read.htmlmodle.HtmlmodleService;
import com.sinontech.service.read.htmlmodledetail.HtmlmodleDetailService;
import com.sinontech.service.read.webchat.WebchatService;

/** 
 * 类名称：HtmlmodleController
 * 创建人：FH 
 * 创建时间：2018-03-02
 */
@Controller
@RequestMapping(value="/htmlmodle")
public class HtmlmodleController extends BaseController {
	
	String menuUrl = "html/list.do"; //菜单地址(权限用)
	@Resource(name="htmlmodleService")
	private HtmlmodleService htmlmodleService;
	@Resource(name="htmlService")
	private HtmlService htmlService;
	@Resource(name="htmlmodledetailService")
	private HtmlmodleDetailService htmlmodledetailService;
	@Resource(name="webchatService")
	private WebchatService webchatService;
	@Resource(name="articleService")
	private ArticleService articleService;
	@Resource(name="articlecategoryService")
	private ArticleCategoryService articlecategoryService;
	
	@RequestMapping(value="/cancelhtml")
	public ModelAndView cancelhtml(){
		logBefore(logger, "去cancelhtml");
		PageData pd = new PageData();	
		pd = this.getPageData();
		String htmlid = String.valueOf(pd.get("htmlid"));
		return new ModelAndView("redirect:/htmlmodle/lookhtml?HTML_ID="+htmlid);
	}	
	/**
	 * 用途说明：完成并更新到数据库
	 * @param response
	 * 2018年3月16日下午4:58:54
	 * @auther ljj
	 */
	@RequestMapping(value="/finishhtml")
	public void finishhtml(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			if(null!=htmlidstr  ){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				logger.debug(html.toString());
				if(null!=html){
					 List<HtmlModle> modles = html.getHtmlmodles();
					 pd.put("HTML_ID", htmlidstr);
					 PageData htmlpd = this.htmlService.findById(pd);
					 String htmlplactype = String.valueOf(htmlpd.get("PLACE_TYPE"));
					 List<PageData> list = this.htmlmodleService.listAll(htmlpd);
					 if(null!=list && list.size()>0){
						 for(PageData pd1:list){
							 String place = String.valueOf(pd1.get("PLACE"));
							 if("1".equals(htmlplactype) && "1".equals(place)){
								 this.htmlmodledetailService.deleteByHTMLMODLE_ID(pd1);
							 }else{
								 this.htmlmodleService.delete(pd1);
							 }
						 }
					 }
					 if(null!=modles && modles.size()>0){
						 for(HtmlModle modle:modles){
							 PageData modpd = new PageData();
							 modpd.put("HTMLMODLE_ID", modle.getId());
							 //书城首页的第一个modle不做删除
							 int placetype = html.getPlacType();
							 int place = modle.getPlace();
							 String mid = String.valueOf(modle.getId());
							 System.out.println(modle.toString());
							 if(placetype==1 && place==1){
								 //this.htmlmodledetailService.deleteByHTMLMODLE_ID(modpd);
							 }else{
								 //this.htmlmodleService.delete(modpd);
								 PageData hmodle = new PageData();
								 hmodle.put("HTMLMODLE_ID", mid);
								 hmodle.put("PLACE", modle.getPlace());
								 hmodle.put("MODLENAME", modle.getName());
								 hmodle.put("DIV_CLASS", modle.getDivClass());
								 hmodle.put("NUMS", modle.getNums());
								 hmodle.put("MODLE_TYPE", modle.getModleType());
								 hmodle.put("HTML_ID", htmlidstr);
								 hmodle.put("CREATE_TIME", DateUtil.getTime());
								 
								 this.htmlmodleService.save(hmodle);
								 
							 }
							
								 List<HtmlModleDetail> details = modle.getHtmlModleDetaillist();
								 if(null!=details && details.size()>0){
									 for(HtmlModleDetail detail:details){
										 PageData hmodledetail = new PageData();
										 hmodledetail.put("ARTICLE_ID", detail.getArticleID());
										 hmodledetail.put("HTML_URL", detail.getHtmlUrl());
										 hmodledetail.put("IMG_URL", detail.getImgUrl());
										 hmodledetail.put("SORT", detail.getSort());
										 hmodledetail.put("SUBHEAD", detail.getSubhead());
										 hmodledetail.put("TITLE", detail.getTitle());
										 hmodledetail.put("HTMLMODLE_ID", mid);
										 hmodledetail.put("CREATE_TIME", DateUtil.getTime());
										 this.htmlmodledetailService.save(hmodledetail);
									 }
								 }
							 
							 
						 }
					 }
				}
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	/**
	 * 用途说明：删除海报
	 * @param response
	 * 2018年3月16日下午4:49:34
	 * @auther ljj
	 */
	@RequestMapping(value="/delmodledetailhb")
	public void delmodledetailhb(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			HtmlModle curmodle = null;
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			String numstr = String.valueOf(pd.get("num"));
			if(null!=htmlidstr && null!=modleidstr && numstr!=null){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					long modleid = Long.parseLong(modleidstr);
					int num = Integer.parseInt(numstr);
					 curmodle = getHtmlModle(html,modleid);
					 if(null!=curmodle){
						 List<HtmlModleDetail> details = curmodle.getHtmlModleDetaillist();
						 List<HtmlModleDetail> details1 = new ArrayList<HtmlModleDetail>();
						 if(null!=details && details.size()>0){
							 for(HtmlModleDetail detail:details){
								 int sort = detail.getSort();
								 //原先有存在的情况
								 if(num!=sort){
									 details1.add(detail);
								 }
							 }
						 } 
						 curmodle.setHtmlModleDetaillist(details1);
						 html = upHtml(html,curmodle);
						 session.setAttribute("HTML"+htmlidstr,html);
						 System.out.println(html.toString());
					 }
				}
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	
	/**
	 * 用途说明：修改专题的标题
	 * @param response
	 * 2018年3月16日下午1:30:26
	 * @auther ljj
	 */
	@RequestMapping(value="/updatemodledetail")
	public void updatemodledetail(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			HtmlModle curmodle = null;
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			String title = String.valueOf(pd.get("title"));
			String numstr = String.valueOf(pd.get("num"));
			String htmlUrl = String.valueOf(pd.get("htmlUrl"));
			String subhead = String.valueOf(pd.get("subhead"));
			if(null!=htmlidstr && null!=modleidstr && numstr!=null){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					long modleid = Long.parseLong(modleidstr);
					int num = Integer.parseInt(numstr);
					 curmodle = getHtmlModle(html,modleid);
					 if(null!=curmodle){
						 List<HtmlModleDetail> details = curmodle.getHtmlModleDetaillist();
						 List<HtmlModleDetail> details1 = new ArrayList<HtmlModleDetail>();
						 HtmlModleDetail curdetail = new HtmlModleDetail();
						 if(null!=details && details.size()>0){
							 boolean flag = true;
							 for(HtmlModleDetail detail:details){
								 int sort = detail.getSort();
								 //原先有存在的情况
								 if(num==sort){
									 if(null!=title && !"".equals(title) && !"null".equals(title)){
										 detail.setTitle(title);
									 }
									 if(null!=subhead && !"".equals(subhead) && !"null".equals(subhead)){
										 detail.setSubhead(subhead);
									 }
									 if(null!=htmlUrl && !"".equals(htmlUrl) && !"null".equals(htmlUrl)){
										 detail.setHtmlUrl(htmlUrl);
									 }
									 flag = false;
								 }
								 details1.add(detail);
							 }
							 //原先不存在的情况
							 if(flag){
								 curdetail.setId(2l);
								 if(null!=title && !"".equals(title) && !"null".equals(title)){
									 curdetail.setTitle(title);
								 }
								 if(null!=subhead && !"".equals(subhead) && !"null".equals(subhead)){
									 curdetail.setSubhead(subhead);
								 }
								 if(null!=htmlUrl && !"".equals(htmlUrl) && !"null".equals(htmlUrl)){
									 curdetail.setHtmlUrl(htmlUrl);
								 }
								 curdetail.setSort(num);
								 details1.add(curdetail);
							 }
							 
						 }else{
							 curdetail.setId(2l);
							 if(null!=title && !"".equals(title) && !"null".equals(title)){
								 curdetail.setTitle(title);
							 }
							 if(null!=subhead && !"".equals(subhead) && !"null".equals(subhead)){
								 curdetail.setSubhead(subhead);
							 }
							 if(null!=htmlUrl && !"".equals(htmlUrl) && !"null".equals(htmlUrl)){
								 curdetail.setHtmlUrl(htmlUrl);
							 }
							 curdetail.setSort(1);
							 details1.add(curdetail);
						 }
						 
						 
						 
						 html = upHtml(html,curmodle);
						 session.setAttribute("HTML"+htmlidstr,html);
						 System.out.println(html.toString());
					 }
				}
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	
	/**
	 * 用途说明：上传专题图片
	 * @param file
	 * @param request
	 * @param response
	 * 2018年3月16日下午1:25:47
	 * @auther ljj
	 */
	@RequestMapping(value="/uploadfile")
	public void uploadfile(@RequestParam(required=false)CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response){

		String htmlidstr = request.getParameter("htmlid");
		String modleidstr = request.getParameter("modleid");
		String numstr = request.getParameter("num");
		
		System.out.println(htmlidstr+"==modleid="+modleidstr);
		String name = file.getOriginalFilename();
		logger.info(name);
		String  ffile = DateUtil.getDays(), fileName = "";
		String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG+ ffile;		//文件上传路径
		fileName = FileUpload.fileUp(file, filePath, this.get32UUID());	
		String imgurl = Const.FILEPATHIMG+ ffile+"/"+fileName;
		logger.info(imgurl);
		
		if(null!=htmlidstr && null!=modleidstr && null!=numstr){
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			Html html = (Html)session.getAttribute("HTML"+htmlidstr);
			if(null!=html){
				System.out.println("1==="+html.toString());
				long modleid = Long.parseLong(modleidstr);
				int num = Integer.parseInt(numstr);
				HtmlModle curmodle = getHtmlModle(html,modleid);
				 if(null!=curmodle){
					 List<HtmlModleDetail> details = curmodle.getHtmlModleDetaillist();
					 List<HtmlModleDetail> details1 = new ArrayList<HtmlModleDetail>();
					 HtmlModleDetail curdetail = new HtmlModleDetail();
					 if(null!=details && details.size()>0){
						 boolean flag = true;
						 for(HtmlModleDetail detail:details){
							 int sort = detail.getSort();
							 //原先有存在的情况
							 if(num==sort){
								 detail.setImgUrl(imgurl);
								 flag = false;
							 }
							 details1.add(detail);
						 }
						 //原先不存在的情况
						 if(flag){
							 curdetail.setId(2l);
							 curdetail.setImgUrl(imgurl);
							 curdetail.setSort(num);
							 details1.add(curdetail);
						 }
						  
					 }else{
						 curdetail.setId(2l);
						 curdetail.setImgUrl(imgurl);
						 curdetail.setSort(1);
						 details1.add(curdetail);
					 }
					 curmodle.setHtmlModleDetaillist(details1);
					 html = upHtml(html,curmodle);
					 session.setAttribute("HTML"+htmlidstr,html);
					 System.out.println(html.toString());
				 }
			}
		}
		PrintWriter pw = SessionUtils.getPrintWriter(response );
		pw.print(Const.FILEPATHIMG+ ffile+"/"+fileName);
	}
	@RequestMapping(value="/getbook")
	public ModelAndView getbook(){
		logBefore(logger, "去getbook页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		System.out.println(pd.toString());
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		try {
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			String key = String.valueOf(pd.get("SEARCHKEY"));
			List<PageData> list1 = new ArrayList<PageData>();
			List<PageData> list = null;
			//if(null!=key && !"".equals(key) && !"null".equals(key)){
				pd.put("STATE1", 1);
				list = this.articleService.listAll(pd);
			//}
			HtmlModle modle = null;
			if(null!=htmlidstr && null!=modleidstr ){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				long modleid = Long.parseLong(modleidstr);
				if(null!=html){
					modle = getHtmlModle(html,modleid);
				}
			}
			if(null!=modle){
				List<HtmlModleDetail> details = modle.getHtmlModleDetaillist();
				if(null!=details && details.size()>0){
					if(null!=list && list.size()>0){
//						System.out.println(list.size());
						for(PageData article:list){
//							System.out.println(article.toString());
							long aid = Long.parseLong(String.valueOf(article.get("ARTICLE_ID")));
							boolean flag = true;
							for(HtmlModleDetail detail:details){
								if(detail.getArticleID()==aid){
									flag = false;
									break;
								}
							}
							if(flag){
								list1.add(article);
							}
						}
					}
					mv.addObject("nums", details.size());
					mv.addObject("cnums",modle.getNums()-details.size());
				}else{
					list1 = list;
					mv.addObject("nums", 0);
					mv.addObject("cnums",modle.getNums());
				}
			}
			mv.setViewName("reader/htmlmodle/getbook");
			mv.addObject("pd", pd);
			mv.addObject("list", list1);
//			logger.info(list.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	public HtmlModle getHtmlModle(Html html,long modleid){
		HtmlModle modle = null;
		if(null!=html){
			List<HtmlModle> modles = html.getHtmlmodles();
			if(null!=modles && modles.size()>0){
				for(HtmlModle hmodle:modles){
					if(hmodle.getId()==modleid){
						modle = hmodle;
						break;
					}
				}
			}
			
		}
		return modle;
	}
	/**
	 * 用途说明：删除模板详情中的一条记录
	 * @param response
	 * 2018年3月15日下午4:50:29
	 * @auther ljj
	 */
	@RequestMapping(value="/delmodledetail")
	public void delmodledetail(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			HtmlModle curmodle = null;
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			String detailidstr = String.valueOf(pd.get("detailid"));
			if(null!=htmlidstr && null!=modleidstr && null!=detailidstr){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					System.out.println("1==="+html.toString());
					long modleid = Long.parseLong(modleidstr);
					long detailid = Long.parseLong(detailidstr);
					 curmodle = getHtmlModle(html,modleid);
					 if(null!=curmodle){
						 List<HtmlModleDetail> details = curmodle.getHtmlModleDetaillist();
						 List<HtmlModleDetail> details1 = new ArrayList<HtmlModleDetail>();
						 if(null!=details && details.size()>0){
							 boolean flag = true;
							 for(HtmlModleDetail detail:details){
								 long did = detail.getId();
								 if(did == detailid){
									 flag = false;
									 continue;
								 } 
								 if(!flag){
									 detail.setSort(detail.getSort()-1);
								 } 
								 details1.add(detail);
							 }
						 }
						 curmodle.setHtmlModleDetaillist(details1);
						 html = upHtml(html,curmodle);
						 session.setAttribute("HTML"+htmlidstr,html);
					 }
				}
			}
			
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			if(null!=curmodle){
				JSONArray arr = JSONArray.fromObject(curmodle);
//				System.out.println(curmodle.toString());
				String json = arr.toString();
//				System.out.println(json);
				out.write(json);
				out.flush();
				out.close();
				
			}else{
				out.write("0");
				out.close();
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	
	/**
	 * 用途说明：新增模板详情中的一条记录
	 * @param response
	 * 2018年3月15日下午4:51:12
	 * @auther ljj
	 */
	@RequestMapping(value="/addmodledetail")
	public void addmodledetail(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			HtmlModle curmodle = null;
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			String articleidstr = String.valueOf(pd.get("articleid"));
			if(null!=htmlidstr && null!=modleidstr && null!=articleidstr){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					System.out.println("1==="+html.toString());
					long modleid = Long.parseLong(modleidstr);
					 curmodle = getHtmlModle(html,modleid);
					 if(null!=curmodle){
						 List<HtmlModleDetail> details = curmodle.getHtmlModleDetaillist();
						 if(null==details){
							 details = new ArrayList<HtmlModleDetail>();
						 }
						 String[] aids = articleidstr.split(",");
						 System.out.println("aids="+aids.toString());
						 long newdetailid = 1l;
						 int sort = 1;
							for(String aidstr:aids){
								System.out.println("aidstr="+aidstr);
								try {
									long articleid = Long.parseLong(aidstr);
									 if(details.size()>0){
										 HtmlModleDetail detail = details.get(details.size()-1);
										 long detailid = detail.getId();
										 newdetailid = detailid+1;
										 sort = detail.getSort()+1;
									 }
									 HtmlModleDetail newdetail = new HtmlModleDetail();
									 newdetail.setArticleID(articleid);
									 newdetail.setId(newdetailid);
									 PageData pd1 = new PageData();
									 pd1.put("ARTICLE_ID", articleid);
									 PageData articlepd = this.articleService.findById(pd1);
									 Article article = getArticleByPd(articlepd);
									 newdetail.setArticle(article);
									 newdetail.setSort(sort);
									 details.add(newdetail);
								} catch (Exception e) {
								}
								
							}
							if(null!=curmodle){
								 curmodle.setHtmlModleDetaillist(details);
								 html = upHtml(html,curmodle);
								 session.setAttribute("HTML"+htmlidstr,html);
							}
						  
					 }
					 
				}
			}
			
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			if(null!=curmodle){
				JSONArray arr = JSONArray.fromObject(curmodle);
//				System.out.println(curmodle.toString());
				String json = arr.toString();
//				System.out.println(json);
				out.write(json);
				out.flush();
				out.close();
				
			}else{
				out.write("0");
				out.close();
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	/**
	 * 用途说明：更新书籍模块信息
	 * @param response
	 * 2018年3月15日下午6:36:54
	 * @auther ljj
	 */
	@RequestMapping(value="/updatearticlemodle")
	public void updatearticlemodle(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			HtmlModle curmodle = null;
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			String name = String.valueOf(pd.get("name"));
			String nums = String.valueOf(pd.get("nums"));
			String divclass = String.valueOf(pd.get("divclass"));
			if(null!=htmlidstr && null!=modleidstr){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					long modleid = Long.parseLong(modleidstr);
					 curmodle = getHtmlModle(html,modleid);
					 if(null!=curmodle){
						 if(null!=name && !"".equals(name) && !"null".equals(name)){
							 curmodle.setName(name);
						 }
						 if(null!=divclass && !"".equals(divclass) && !"null".equals(divclass)){
							 curmodle.setDivClass(divclass);
						 }
						 if(null!=nums && !"".equals(nums) && !"null".equals(nums)){
							 try {
								int num = Integer.parseInt(nums);
								 curmodle.setNums(num);
							} catch (Exception e) {
							}
						 }
						 html = upHtml(html,curmodle);
						 session.setAttribute("HTML"+htmlidstr,html);
						 System.out.println(html.toString());
					 }
				}
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	/**
	 * 用途说明：更新Html的HtmlModle
	 * @param html
	 * @param modle
	 * @return
	 * 2018年3月15日下午4:42:39
	 * @auther ljj
	 */
	public Html upHtml(Html html,HtmlModle modle){
		if(null!=html && modle!=null){
			List<HtmlModle> modles = html.getHtmlmodles();
			List<HtmlModle> modles1 = new ArrayList<HtmlModle>();
			for(HtmlModle modle1:modles){
				if(modle1.getId()==modle.getId()){
					modles1.add(modle);
				}else{
					modles1.add(modle1);
				}
			}
			html.setHtmlmodles(modles1);
		}
		
		return html;
	}
	/**
	 * 用途说明：获取模板信息
	 * @param response
	 * 2018年3月15日下午4:23:22
	 * @auther ljj
	 */
	@RequestMapping(value="/getmodle")
	public void getmodle(HttpServletResponse response){
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			pd = this.getPageData();
			System.out.println(pd.toString());
			HtmlModle curmodle = null;
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			if(null!=htmlidstr && null!=modleidstr ){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					System.out.println("1==="+html.toString());
					long modleid = Long.parseLong(modleidstr);
					 curmodle = getHtmlModle(html,modleid);
				}
			}
			PrintWriter out;
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			if(null!=curmodle){
				JSONArray arr = JSONArray.fromObject(curmodle);
				System.out.println(curmodle.toString());
				String json = arr.toString();
				System.out.println(json);
				out.write(json);
				out.flush();
				out.close();
				
			}else{
				out.write("0");
				out.close();
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}	
	}
	/**
	 * 用途说明：添加空模板
	 * @param out
	 * 2018年3月14日下午4:21:56
	 * @auther ljj
	 */
	@RequestMapping(value="/addmodle")
	public void addmodle(PrintWriter out){
		logBefore(logger, "addmodle-modle");
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			
			pd = this.getPageData();
			System.out.println(pd.toString());
			//1书籍2专题
			String typestr = String.valueOf(pd.get("type"));
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			//新ID
			String modleidstr = String.valueOf(pd.get("modleid"));
			//新ID之前的ID
			String curmodleidstr = String.valueOf(pd.get("curmodleid"));
			if(null!=htmlidstr && null!=modleidstr  && null!=typestr){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					System.out.println("1==="+html.toString());
					long modleid = Long.parseLong(modleidstr);
					long curmodleid = 0l;
					if(null!=curmodleidstr && !"".equals(curmodleidstr) && !"null".equals(curmodleidstr)){
						try {
							curmodleid = Long.parseLong(curmodleidstr);
						} catch (Exception e) {
						}
					}
					int type = Integer.parseInt(typestr);
					List<HtmlModle> modles = html.getHtmlmodles();
					List<HtmlModle> modles1 = new ArrayList<HtmlModle>();
					HtmlModle newmodle = new HtmlModle();
					newmodle.setId(modleid);
					newmodle.setModleType(type);
					newmodle.setPlace(1);
					if(1==type){
						newmodle.setNums(3);
						newmodle.setDivClass("freeAdmission");
					}else{
						newmodle.setNums(1);
						newmodle.setDivClass("freeAdmission");
					}
					
					if(modles!=null && modles.size()>0){
						HtmlModle curmodle = null;
						if(curmodleid>0l){
							for(HtmlModle modle:modles){
								long mid = modle.getId();
								if(mid==curmodleid){
									curmodle = modle;
									break;
								}
							}
						}
						if(null==curmodle){
							modles1 = modles;
							//当前模板为空，则加到最后面
							HtmlModle lastmodle = modles.get(modles.size()-1);
							newmodle.setPlace(lastmodle.getPlace()+1);
							modles1.add(newmodle);
						}else{
							//当前模板不为空，则加到当前后面
							int place = curmodle.getPlace();
							for(int i=0;i<place;i++){
								modles1.add(modles.get(i));
							}
							newmodle.setPlace(place+1);
							modles1.add(newmodle);
							for(int k=place;k<modles.size();k++){
								HtmlModle modle = modles.get(k);
								modle.setPlace(modle.getPlace()+1);
								modles1.add(modle);
							}
						}
					}else{
						modles1.add(newmodle);
					}
					 
					html.setHtmlmodles(modles1);
					System.out.println("4==="+html.toString());
					session.setAttribute("HTML"+htmlidstr, html); 
				}
			}
			
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 用途说明：删除模板
	 * @param out
	 * 2018年3月14日下午3:45:22
	 * @auther ljj
	 */
	@RequestMapping(value="/delmodle")
	public void delmodle(PrintWriter out){
		logBefore(logger, "delmodle-modle");
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			
			pd = this.getPageData();
			System.out.println(pd.toString());
			String type = String.valueOf(pd.get("type"));
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			if(null!=htmlidstr && null!=modleidstr){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					System.out.println("1==="+html.toString());
					long modleid = Long.parseLong(modleidstr);
					List<HtmlModle> modles = html.getHtmlmodles();
					List<HtmlModle> modles1 = new ArrayList<HtmlModle>();
					boolean flag = false;
					int place = 0;
					for(HtmlModle modle:modles){
						long mid = modle.getId();
						if(mid==modleid){
							flag = true; 
							place = modle.getPlace();
						} 
						if(!flag){
							modles1.add(modle);
						}else{
							if(modle.getPlace()!=place){
								modle.setPlace(modle.getPlace()-1);
								modles1.add(modle);
							}
						}
					}
					 
						html.setHtmlmodles(modles1);
					System.out.println("3==="+html.toString());
					session.setAttribute("HTML"+htmlidstr, html); 
				}
			}
			
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 用途说明：上移下移模块
	 * @param out
	 * 2018年3月14日下午3:45:00
	 * @auther ljj
	 */
	@RequestMapping(value="/move")
	public void move(PrintWriter out){
		logBefore(logger, "move-modle");
		PageData pd = new PageData();
		try{
			Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
			Session session = currentUser.getSession();
			
			pd = this.getPageData();
			System.out.println(pd.toString());
			String type = String.valueOf(pd.get("type"));
			String htmlidstr = String.valueOf(pd.get("htmlid"));
			String modleidstr = String.valueOf(pd.get("modleid"));
			if(null!=htmlidstr && null!=modleidstr){
				Html html = (Html)session.getAttribute("HTML"+htmlidstr);
				if(null!=html){
					System.out.println("1==="+html.toString());
					long modleid = Long.parseLong(modleidstr);
					HtmlModle curmodle = null;
					List<HtmlModle> modles = html.getHtmlmodles();
					List<HtmlModle> modles1 = new ArrayList<HtmlModle>();
					for(HtmlModle modle:modles){
						long mid = modle.getId();
						if(mid==modleid){
							curmodle = modle;
							break;
						}
					}
					if(null!=curmodle){
						int place = curmodle.getPlace();
						if("1".equals(type)){
							//上移
							place = place-1;
							if(place>1){
								for(int i=0;i<place-1;i++){
									modles1.add(modles.get(i));
								}
							}
							HtmlModle modle1 = modles.get(place-1);
							HtmlModle modle2 = modles.get(place);
							modle2.setPlace(place);
							modle1.setPlace(place+1);
							modles1.add(modle2);
							modles1.add(modle1);
							if((place+1)<modles.size()){
								for(int i=place+1;i<modles.size();i++){
									modles1.add(modles.get(i));
								}
							}
							 
						}else{
							//下移
							place = place+1;
							if(place>2){
								for(int i=0;i<place-2;i++){
									modles1.add(modles.get(i));
								}
							}
							HtmlModle modle1 = modles.get(place-2);
							HtmlModle modle2 = modles.get(place-1);
							modle2.setPlace(place-1);
							modle1.setPlace(place);
							modles1.add(modle2);
							modles1.add(modle1);
							if((place)<modles.size()){
								for(int i=place;i<modles.size();i++){
									modles1.add(modles.get(i));
								}
							}
						}
						html.setHtmlmodles(modles1);
					}
					System.out.println("2==="+html.toString());
					session.setAttribute("HTML"+htmlidstr, html); 
				}
			}
			
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	@RequestMapping(value="/showhtml")
	public ModelAndView showhtml(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logBefore(logger, "look Htmlmodle");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		String HTML_ID =  String.valueOf(pd.get("HTML_ID")) ;
		Html html = new Html();
		if(null!=HTML_ID  ){
			html = (Html)session.getAttribute("HTML"+HTML_ID);
		}
		mv.addObject("HTML_ID", HTML_ID);
		session.setAttribute("HTML"+HTML_ID, html);
		System.out.println(html.toString());
		mv.setViewName("reader/htmlmodle/htmlmodleshow");
		mv.addObject("pd", pd);
		
		return mv;
	}
	
	@RequestMapping(value="/lookhtml")
	public ModelAndView lookhtml(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logBefore(logger, "look Htmlmodle");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		Html html = new Html();
		String HTML_ID =  String.valueOf(pd.get("HTML_ID")) ;
		PageData htmlpd = this.htmlService.findById(pd);
		long modleid = 0l;
		if(null!=htmlpd){
			PageData pd2 = new PageData();
			PageData maxpd = this.htmlmodleService.getMaxPd(pd2);
			if(null!=maxpd){
				modleid = Long.parseLong(String.valueOf(maxpd.get("HTMLMODLE_ID")));
			}
			html = getHtmlByPd(htmlpd);
				List<PageData> list = this.htmlmodleService.listAll(htmlpd);
				if(null!=list && list.size()>0){
					List<HtmlModle> htmlmodlelist = new ArrayList<HtmlModle>();
					for(PageData htmlmodlepd:list){
						HtmlModle htmlmodle = getHtmlModleByPd(htmlmodlepd);
//						htmlmodle.setHtml(html);
						List<PageData> modlelist = this.htmlmodledetailService.listAll(htmlmodlepd);
						if(null!=modlelist){
							List<HtmlModleDetail> htmlmodledetaillist = new ArrayList<HtmlModleDetail>();
							for(PageData htmlmodledetailpd:modlelist){
								HtmlModleDetail htmlModleDetail = getHtmlModleDetailByPd(htmlmodledetailpd);
								if(htmlmodle.getModleType()==1){
									PageData pd1 = new PageData();
									pd1.put("ARTICLE_ID", htmlModleDetail.getArticleID());
									PageData articlepd = this.articleService.findById(pd1);
									Article article = getArticleByPd(articlepd);
									htmlModleDetail.setArticle(article);
								}
								htmlmodledetaillist.add(htmlModleDetail);
							}
							htmlmodle.setHtmlModleDetaillist(htmlmodledetaillist); 
						}
						htmlmodlelist.add(htmlmodle);
					}
					html.setHtmlmodles(htmlmodlelist);
				}
		}
		mv.addObject("modleid", modleid);
		mv.addObject("HTML_ID", HTML_ID);
		session.setAttribute("HTML"+HTML_ID, html);
		System.out.println(html.toString());
		mv.setViewName("reader/htmlmodle/htmlmodle_list");
		mv.addObject("pd", pd);
		
		return mv;
	}
	
	public HtmlModleDetail getHtmlModleDetailByPd(PageData pd){
		HtmlModleDetail htmlModleDetail = new HtmlModleDetail();
		if(null!=pd){
			try {
				long id = Long.parseLong(String.valueOf(pd.get("HTMLMODLE_DETAIL_ID")));
				htmlModleDetail.setId(id);
			} catch (NumberFormatException e) {
			}
			try {
				long htmlmodleId = Long.parseLong(String.valueOf(pd.get("HTMLMODLE_ID")));
				htmlModleDetail.setHtmlmodleId(htmlmodleId);;
			} catch (NumberFormatException e) {
			}
			
			String title = String.valueOf(pd.get("TITLE"));
			htmlModleDetail.setTitle(title);
			
			String subhead = String.valueOf(pd.get("SUBHEAD"));
			htmlModleDetail.setSubhead(subhead);
			
			String content = String.valueOf(pd.get("CONTENT"));
			htmlModleDetail.setContent(content);
			
			String imgUrl = String.valueOf(pd.get("IMG_URL"));
			htmlModleDetail.setImgUrl(imgUrl);
			
			String htmlUrl = String.valueOf(pd.get("HTML_URL"));
			htmlModleDetail.setHtmlUrl(htmlUrl);
			
			try {
				int htmlType = Integer.parseInt(String.valueOf(pd.get("HTML_TYPE")));
				htmlModleDetail.setHtmlType(htmlType);
			} catch (NumberFormatException e) {
			}
			try {
				int sort  = Integer.parseInt(String.valueOf(pd.get("SORT")));
				htmlModleDetail.setSort(sort);
			} catch (NumberFormatException e) {
			}
			try {
				long articleID = Long.parseLong(String.valueOf(pd.get("ARTICLE_ID")));
				htmlModleDetail.setArticleID(articleID);;
			} catch (NumberFormatException e) {
			}
			String memo = String.valueOf(pd.get("MEMO"));
			htmlModleDetail.setMemo(memo);
			String createTime = String.valueOf(pd.get("CREATE_TIME"));
			htmlModleDetail.setCreateTime(createTime);
		}
		return htmlModleDetail;
	}
	
	
	public HtmlModle getHtmlModleByPd(PageData pd){
		HtmlModle htmlmodle = new HtmlModle();
		if(null!=pd){
			try {
				long id = Long.parseLong(String.valueOf(pd.get("HTMLMODLE_ID")));
				htmlmodle.setId(id);
			} catch (NumberFormatException e) {
			}
			try {
				int place = Integer.parseInt(String.valueOf(pd.get("PLACE")));
				htmlmodle.setPlace(place);
			} catch (NumberFormatException e) {
			}
			
			String divClass = String.valueOf(pd.get("DIV_CLASS"));
			htmlmodle.setDivClass(divClass);
			String modlename = String.valueOf(pd.get("MODLENAME"));
			htmlmodle.setName(modlename);
			try {
				int nums = Integer.parseInt(String.valueOf(pd.get("NUMS")));
				htmlmodle.setNums(nums);
			} catch (NumberFormatException e) {
			}
			try {
				int modleType = Integer.parseInt(String.valueOf(pd.get("MODLE_TYPE")));
				htmlmodle.setModleType(modleType);
			} catch (NumberFormatException e) {
			}
			String createTime = String.valueOf(pd.get("CREATE_TIME"));
			htmlmodle.setCreateTime(createTime);
			
			
		}
		return htmlmodle;
	}
	public Html getHtmlByPd(PageData pd){
		Html html = new Html();
		if(null!=pd){
			try {
				long id = Long.parseLong(String.valueOf(pd.get("HTML_ID")));
				html.setId(id);
			} catch (NumberFormatException e) {
			}
			 
			try {
				int placType = Integer.parseInt(String.valueOf(pd.get("PLACE_TYPE")));
				html.setPlacType(placType);
			} catch (NumberFormatException e) {
			}
			
			String name = String.valueOf(pd.get("NAME"));
			html.setName(name);
			try {
				int htmlType = Integer.parseInt(String.valueOf(pd.get("HTML_TYPE")));
				html.setHtmlType(htmlType);
			} catch (NumberFormatException e) {
			}
			try {
				int state  = Integer.parseInt(String.valueOf(pd.get("STATE")));
				html.setState(state);
			} catch (NumberFormatException e) {
			}
			String htmlUrl = String.valueOf(pd.get("HTML_URL"));
			html.setHtmlUrl(htmlUrl);;
			try {
				long pvs = Long.parseLong(String.valueOf(pd.get("PVS")));
				html.setPvs(pvs);
			} catch (NumberFormatException e) {
			}
			String memo = String.valueOf(pd.get("MEMO"));
			html.setMemo(memo);
			String createTime = String.valueOf(pd.get("CREATE_TIME"));
			html.setCreateTime(createTime);
		}
		return html;
	}
	
	public Article getArticleByPd(PageData pd){
		Article article = new Article();
		if(null!=pd){
			try {
				long id = Long.parseLong(String.valueOf(pd.get("ARTICLE_ID")));
				article.setId(id);
			} catch (NumberFormatException e) {
			}
			try {
				long articleCategoryId = Long.parseLong(String.valueOf(pd.get("ARTICLE_CATEGORY_ID")));
				article.setArticleCategoryId(articleCategoryId);
			} catch (NumberFormatException e) {
			}
			String articleCode = String.valueOf(pd.get("ARTICLE_CODE"));
			article.setArticleCode(articleCode);
			String author = String.valueOf(pd.get("AUTHOR"));
			article.setAuthor(author);
			String articleName = String.valueOf(pd.get("ARTICLE_NAME"));
			article.setArticleName(articleName);
			try {
				int feeType = Integer.parseInt(String.valueOf(pd.get("FEE_TYPE")));
				article.setFeeType(feeType);
			} catch (NumberFormatException e) {
			}
			try {
				long payConsumes = Long.parseLong(String.valueOf(pd.get("PAY_WAY")));
				article.setPayConsumes(payConsumes);
			} catch (NumberFormatException e) {
			}
			try {
				long payConsumes = Long.parseLong(String.valueOf(pd.get("PAY_CONSUMES")));
				article.setPayConsumes(payConsumes);
			} catch (NumberFormatException e) {
			}
			try {
				int isHost = Integer.parseInt(String.valueOf(pd.get("IS_HOT")));
				article.setIsHost(isHost);
			} catch (NumberFormatException e) {
			}
			String summary = String.valueOf(pd.get("SUMMARY"));
			article.setSummary(summary);
			try {
				int countLetter = Integer.parseInt(String.valueOf(pd.get("COUNT_LETTER")));
				article.setCountLetter(countLetter);
			} catch (NumberFormatException e) {
			}
			try {
				int countChapters = Integer.parseInt(String.valueOf(pd.get("COUNT_CHAPTERS")));
				article.setCountChapters(countChapters);
			} catch (NumberFormatException e) {
			}
			try {
				int state  = Integer.parseInt(String.valueOf(pd.get("STATE")));
				article.setState(state);
			} catch (NumberFormatException e) {
			}
			
			try {
				int collection  = Integer.parseInt(String.valueOf(pd.get("COLLECTION")));
				article.setCollection(collection);
			} catch (NumberFormatException e) {
			}
			try {
				int readers  = Integer.parseInt(String.valueOf(pd.get("READERS")));
				article.setReaders(readers);
			} catch (NumberFormatException e) {
			}
			try {
				int displayReaders  = Integer.parseInt(String.valueOf(pd.get("DISPLAY_READERS")));
				article.setDisplayReaders(displayReaders);
			} catch (NumberFormatException e) {
			}
			
			try {
				long countConsumes = Long.parseLong(String.valueOf(pd.get("COUNT_CONSUMES")));
				article.setCountConsumes(countConsumes);
			} catch (NumberFormatException e) {
			}
			try {
				int channelType  = Integer.parseInt(String.valueOf(pd.get("CHANNEL_TYPE")));
				article.setChannelType(channelType);
			} catch (NumberFormatException e) {
			}
			try {
				int serialState  = Integer.parseInt(String.valueOf(pd.get("SERIAL_STATE")));
				article.setSerialState(serialState);
			} catch (NumberFormatException e) {
			}
			
			String bookCover = String.valueOf(pd.get("BOOK_COVER"));
			article.setBookCover(bookCover);
			try {
				int recommend  = Integer.parseInt(String.valueOf(pd.get("RECOMMEND")));
				article.setRecommend(recommend);
			} catch (NumberFormatException e) {
			}
			String createTime = String.valueOf(pd.get("CREATE_TIME"));
			article.setCreateTime(createTime);
			String category = String.valueOf(pd.get("CATEGORY"));
			article.setCategory(category);
		}
		return article;
	}
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Htmlmodle");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("HTMLMODLE_ID", this.get32UUID());	//主键
		htmlmodleService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Htmlmodle");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			htmlmodleService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Htmlmodle");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		htmlmodleService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Htmlmodle");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = htmlmodleService.list(page);	//列出Htmlmodle列表
			mv.setViewName("read/htmlmodle/htmlmodle_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Htmlmodle页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/htmlmodle/htmlmodle_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Htmlmodle页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = htmlmodleService.findById(pd);	//根据ID读取
			mv.setViewName("read/htmlmodle/htmlmodle_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Htmlmodle");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				htmlmodleService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Htmlmodle到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("页面ID");	//1
			titles.add("样式");	//2
			titles.add("模板类型");	//3
			titles.add("创建时间");	//4
			dataMap.put("titles", titles);
			List<PageData> varOList = htmlmodleService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("HTML_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("DIV_CLASS"));	//2
				vpd.put("var3", varOList.get(i).getString("MODLE_TYPE"));	//3
				vpd.put("var4", varOList.get(i).getString("CREATE_TIME"));	//4
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}