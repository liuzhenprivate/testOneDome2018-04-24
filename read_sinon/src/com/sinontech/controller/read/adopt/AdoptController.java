package com.sinontech.controller.read.adopt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.entity.system.User;
import com.sinontech.service.read.article.ArticleService;
import com.sinontech.service.read.articlechapter.ArticleChapterService;
import com.sinontech.service.read.extensionconnect.ExtensionConnectService;
import com.sinontech.service.read.extensioncontent.ExtensionContentService;
import com.sinontech.util.AppUtil;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.MD5;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.PageData;

/** 
 * 类名称：ExtensionConnectController
 * 创建人：FH 
 * 创建时间：2018-01-25
 */
@Controller
@RequestMapping(value="/adopt")
public class AdoptController extends BaseController {
	
	@Resource(name="extensionconnectService")
	private ExtensionConnectService extensionconnectService;
	@Resource(name="extensioncontentService")
	private ExtensionContentService extensioncontentService;
	@Resource(name="articlechapterService")
	private ArticleChapterService articlechapterService;
	@Resource(name="articleService")
	private ArticleService articleService;
	
	
	/**
	 * 
	 * 用途：生成渠道推广链接
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-26 下午3:46:55
	 */
	@RequestMapping(value = "/Manufacture")
	public String Manufacture() throws Exception {
		logBefore(logger, "新增Manufacture");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("USER_ID", pd.get("content").toString());
		pd.put("EXTENSION_CONTENT_ID", pd.get("extension").toString());
		PageData contenpd = extensioncontentService.findById(pd);
		
		pd.put("ARTICLE_ID",contenpd.get("ARTICLE_ID").toString());
		pd.put("ARTICLE_CHAPTERS",Integer.parseInt(contenpd.get("ARTICLE_CHAPTERS").toString()));
		
		String ARTICLE_CHAPTERS = contenpd.get("ARTICLE_CHAPTERS").toString() + this.get32UUID();
		String ARTICLE_ID = contenpd.get("ARTICLE_ID").toString();
		String EXTENSION_CONTENT_ID = pd.get("extension").toString();
		/*
		 * a_cs = ARTICLE_CHAPTERS
		 * a_i = ARTICLE_ID + MD5
		 * e_c_i  = extension = EXTENSION_CONTENT_ID
		 * s_ui  = content = USER_ID
		 */
		return "redirect:http://kuwx.xinxinwx.cn/wx/articlechapter/visit/"+EXTENSION_CONTENT_ID+".do?a_cs="+ARTICLE_CHAPTERS+"&a_i="+ARTICLE_ID+"&e_c_i="+EXTENSION_CONTENT_ID;
		
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
