package com.sinontech.controller.read.extensioncontent;

import java.io.BufferedReader;
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
import com.sinontech.service.read.articlecategory.ArticleCategoryService;
import com.sinontech.service.read.articlechapter.ArticleChapterService;
import com.sinontech.service.read.extensioncontent.ExtensionContentService;
import com.sinontech.service.read.pic.PicService;
import com.sinontech.service.read.piccategory.PicCategoryService;
import com.sinontech.service.read.title.TitleService;
import com.sinontech.service.read.webchat.WebchatService;
import com.sinontech.util.AppUtil;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.PageData;


/** 
 * 类名称：extensioncontentController
 * 创建人：FH 
 * 创建时间：2018-01-25
 */
@Controller
@RequestMapping(value="/extensioncontent")
public class ExtensionContentController extends BaseController {
	
	String menuUrl = "extensioncontent/list.do"; //菜单地址(权限用)
	@Resource(name="extensioncontentService")
	private ExtensionContentService extensioncontentService;
	@Resource(name="articleService")
	private ArticleService articleService;
	@Resource(name="articlechapterService")
	private ArticleChapterService articlechapterService;
	@Resource(name="titleService")
	private TitleService titleService;
	@Resource(name="picService")
	private PicService picService;
	@Resource(name="piccategoryService")
	private PicCategoryService piccategoryService;
	@Resource(name = "webchatService")
	private WebchatService webchatService;
	@Resource(name = "articlecategoryService")
	private ArticleCategoryService articleCategoryService;
	
	
	/**
	 * 
	 *用途：新增渠道推广
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-30 下午12:38:04
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		logBefore(logger, "新增渠道推广save");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if (null != user) {
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			//pd.put("ARTICLE_CHAPTER_ID", pd.getString("CHAPTERS"));
			PageData pd2 = articlechapterService.countindexchapter(pd);
			if ("1".equals(pd.getString("ISFORCE"))) {
				pd.put("FORCE_CHAPTER",  pd.getString("CHAPTERS"));
			}
			pd.put("ARTICLE_CHAPTERS",Integer.parseInt(pd2.get("indexs").toString()));
			pd.put("TITLE",pd.getString("TITLE"));
			pd.put("CREATE_TIME", DateUtil.getTimessss());
			String basePath =  pd.getString("HTTPURL");
			//String URLWX= "http://kuwx.xinxinwx.cn/wx/";
			pd.put("COVER", pd.getString("COVER").substring(basePath.length()-3, pd.getString("COVER").length()));
			extensioncontentService.save(pd);
			PageData timepd = extensioncontentService.findByTime(pd);
			pd.put("EXTENSION_CONTENT_ID", timepd.get("EXTENSION_CONTENT_ID").toString());
			pd.put("CONTENT_URL",basePath + "adopt/Manufacture.do?extension="
							+ timepd.get("EXTENSION_CONTENT_ID").toString()
							+ "&content=" + uid);// 生成的推广链接
			extensioncontentService.editURL(pd);
			mv.addObject("msg", "success");
			mv.setViewName("save_result");
		}
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除extensioncontent");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			extensioncontentService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 
	 *用途：删除推广
	 *@return void
	 *@author 刘振
	 *2018-2-8 上午11:33:40
	 */
	@RequestMapping(value="/del")
	public void del(PrintWriter out){
		logBefore(logger, "删除extensioncontent");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			extensioncontentService.del(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value = "/extensionedit")
	public ModelAndView extensionedit() throws Exception {
		logBefore(logger, "修改extensioncontent");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return null;
		} // 校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if (null != user) {
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			if ("1".equals(pd.getString("ISFORCE"))) {
				pd.put("FORCE_CHAPTER",pd.getString("FORCE_CHAPTER"));
			}else{
				pd.put("FORCE_CHAPTER",null);
			}
			extensioncontentService.extensionedit(pd);
			mv.addObject("msg", "success");
			mv.setViewName("save_result");
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-25 上午11:16:22
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		logBefore(logger, "列表extensioncontent");
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String sendtime = String.valueOf(pd.get("sendtime"));
		if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try {
			if (null != user) {
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				page.setPd(pd);
				List<PageData> varList = extensioncontentService.list(page); // 列出extensioncontent列表
				PageData pd1 = webchatService.findWebchatUserId(pd);
				if(null==pd1){
					pd.put("Webchat","1");
				}
				mv.setViewName("readerchannel/extensioncontent/extensioncontent_list");
				mv.addObject("varList", varList);
				mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 
	 *用途：去推广书籍（书籍列表）extension_book页面
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午2:36:29
	 */
	@RequestMapping(value = "/goExtensionbook")
	public ModelAndView goExtensionbook(Page page) {
		logBefore(logger, "去推广书籍（书籍列表）extension_book页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			System.out.println(pd.toString());
			page.setPd(pd);
			PageData pd1 = articleCategoryService.findById(pd);
			List<PageData> varList = articleService.extensionBooklistPage(page);
			List<PageData> chapterList = articleCategoryService.listAll(pd);
			mv.setViewName("readerchannel/extensioncontent/extension_book");
			mv.addObject("varList", varList);
			mv.addObject("chapterList", chapterList);
			mv.addObject("pd", pd);
			mv.addObject("pd1", pd1);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	
	/**
	 * 
	 *用途：修改推广内容
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-8 下午4:28:15
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(Page page){
		logBefore(logger, "去修改extensioncontent页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String ISFORCE = "";
			pd = extensioncontentService.findById(pd);	//根据ID读取
			if(null==pd.get("FORCE_CHAPTER")){
				pd.put("NO",Integer.parseInt(pd.get("ARTICLE_CHAPTERS").toString())+1);
				List<PageData> list = articlechapterService.seachExtensionForceChapter(pd);
				System.out.println(list.toString());
				ISFORCE = "2";
				mv.addObject("list", list);
			}else{
				pd.put("ARTICLE_CHAPTER_ID",pd.get("FORCE_CHAPTER").toString());
				PageData pd1 = articlechapterService.findById(pd);
				pd.put("NO",Integer.parseInt(pd.get("ARTICLE_CHAPTERS").toString())+1);
				List<PageData> list = articlechapterService.seachExtensionForceChapter(pd);
				page.setPd(pd);
				ISFORCE = "1";
				mv.addObject("pd1", pd1);
				mv.addObject("list", list);
			}
			
			mv.setViewName("readerchannel/extensioncontent/extension_edit");
			mv.addObject("pd", pd);
			mv.addObject("ISFORCE", ISFORCE);
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
		logBefore(logger, "批量删除extensioncontent");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				extensioncontentService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出extensioncontent到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("id");	//1
			titles.add("渠道id");	//2
			titles.add("封面");	//3
			titles.add("书籍di");	//4
			titles.add("书籍名称");	//5
			titles.add("章节数");	//6
			titles.add("创建时间");	//7
			titles.add("强制关注章节");	//8
			titles.add("推广链接");	//9
			titles.add("推广标题");	//10
			titles.add("关注人数");	//11
			titles.add("引导人数");	//12
			titles.add("充值金额");	//13
			titles.add("充值人数");	//14
			titles.add("充值笔数");	//15
			titles.add("收益");	//16
			titles.add("样式名称");	//17
			titles.add("下方标题名称");	//18
			titles.add("下方标题样式");	//19
			dataMap.put("titles", titles);
			List<PageData> varOList = extensioncontentService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("EXTENSION_CONTENT_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("COVER"));	//3
				vpd.put("var4", varOList.get(i).get("ARTICLE_ID").toString());	//4
				vpd.put("var5", varOList.get(i).getString("ARTICLE_NAME"));	//5
				vpd.put("var6", varOList.get(i).get("ARTICLE_CHAPTERS").toString());	//6
				vpd.put("var7", varOList.get(i).getString("CREATE_TIME"));	//7
				vpd.put("var8", varOList.get(i).get("FORCE_CHAPTER").toString());	//8
				vpd.put("var9", varOList.get(i).getString("CONTENT_URL"));	//9
				vpd.put("var10", varOList.get(i).getString("TITLE"));	//10
				vpd.put("var11", varOList.get(i).get("FOLLOW").toString());	//11
				vpd.put("var12", varOList.get(i).get("GUIDE").toString());	//12
				vpd.put("var13", varOList.get(i).get("RECHARGE").toString());	//13
				vpd.put("var14", varOList.get(i).get("RECHARGE_PEOPLES").toString());	//14
				vpd.put("var15", varOList.get(i).get("RECHARGE_TIMES").toString());	//15
				vpd.put("var16", varOList.get(i).get("PROFIT").toString());	//16
				vpd.put("var17", varOList.get(i).getString("CSSS"));	//17
				vpd.put("var18", varOList.get(i).getString("CLICK_TITLE"));	//18
				vpd.put("var19", varOList.get(i).getString("CLICK_TITLE_CSS"));	//19
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
	/**
	 * 
	 *用途：去文案标题页面
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午5:27:00
	 */
	@RequestMapping(value="/goExtensiontitle")
	public ModelAndView goExtensiontitle(Page page){
		logBefore(logger, "去文案标题extension_title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			List<PageData>  varList = titleService.list(page);
			mv.setViewName("readerchannel/extensioncontent/extension_title");
			mv.addObject("pd", pd);
			mv.addObject("varList", varList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 *用途：去文案封面页面
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午5:27:04
	 */
	@RequestMapping(value="/goExtensioncover")
	public ModelAndView goExtensioncover(){
		logBefore(logger, "去文案封面extension_cover页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	varList = picService.listAll(pd);	//列出Pic列表
			PageData category = this.piccategoryService.findById(pd);
			if(null!=category)
			System.out.println("category=="+category.toString());
			//查询所有图片分类
			PageData pd1 = new PageData();
			List<PageData> piccategorylist = this.piccategoryService.listAll(pd1);
			mv.setViewName("readerchannel/extensioncontent/extension_cover");
			mv.addObject("varList", varList);
			mv.addObject("piccategorylist", piccategorylist);
			mv.addObject("pd", pd);
			mv.addObject("category", category);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 *用途：去原文引导页面
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午5:27:09
	 */
	@RequestMapping(value="/goExtensiontemplate")
	public ModelAndView goExtensiontemplate(){
		logBefore(logger, "去原文引导extension_template页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("readerchannel/extensioncontent/extension_template");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 *用途：去正文模板页面
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午5:27:12
	 */
	@RequestMapping(value="/goExtensionoriginal")
	public ModelAndView goExtensionoriginal(){
		logBefore(logger, "去正文模板extension_origina页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("readerchannel/extensioncontent/extension_original");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 *用途：去推广页面详情编辑页面
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午4:21:23
	 */
	@RequestMapping(value="/goExtensioncopy")
	public ModelAndView goExtensioncopy(){
		logBefore(logger, "去推广页面详情编辑extension_details页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData> varList = new ArrayList<PageData>();
			List<PageData> chapter = articlechapterService.seachExtensionchapter(pd);
			if(chapter.size()>0){
				for (int i = 0; i < chapter.size(); i++) {
					pd.put("ARTICLE_CHAPTER_ID",chapter.get(i).get("ARTICLE_CHAPTER_ID").toString());
					PageData chapterpd = articlechapterService.extensionchapterid(pd);
					if(chapterpd!=null){
						PageData pd1 = new PageData();
						//读取txt章节
						String CONTENT_URL = chapterpd.getString("CONTENT_URL");
						String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
						String  filePath = projectpath +CONTENT_URL;//文件上传路径
						FileReader txtfile=new FileReader(filePath);//读取文件
						BufferedReader br=new BufferedReader(txtfile);
						String line = "";
						String txt = "";  
					    while((line = br.readLine())!=null){
					    	txt+=line+"\r\n";
					    }
					    pd1.put("CHAPTER_NAME", chapterpd.getString("CHAPTER_NAME"));
					    pd1.put("txt", txt);
					    varList.add(pd1);
					}
				}
			}
			mv.setViewName("readerchannel/extensioncontent/extension_details");
			pd.put("CHAPTER_NO", chapter.get(chapter.size()-1).get("CHAPTER_NO").toString());
			mv.addObject("chapter", chapter);
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
		
	
	
	/**
	 * 
	 *用途：生成推广链接
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-30 上午9:47:03
	 */
	@RequestMapping(value="/goExtensiongenerate")
	public ModelAndView goExtensiongenerate(Page page){
		logBefore(logger, "去生成推广链接extension_generate页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			PageData articlepd = articleService.findById(pd);
			PageData chapterpd = articlechapterService.findByNo(pd);
			PageData countindex = articlechapterService.countindexchapter(pd);
			pd.put("indexs",Integer.parseInt(countindex.get("indexs").toString())+1);
			page.setPd(pd);
			List<PageData> varList = articlechapterService.extensionlist(page);
			mv.setViewName("readerchannel/extensioncontent/extension_generate");
			mv.addObject("pd", pd);
			mv.addObject("articlepd", articlepd);
			mv.addObject("chapterpd", chapterpd);
			mv.addObject("varList", varList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 *用途：渠道详情页面查询推广内容
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-9 下午3:12:21
	 */
	@RequestMapping(value = "/sysuserExtensionDetails")
	public ModelAndView sysuserExtensionDetails(Page page) {
		logBefore(logger, "渠道详情页面查询推广内容列表extensioncontent");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String sendtime = String.valueOf(pd.get("sendtime"));
		if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		try {
			page.setPd(pd);
			List<PageData> varList = extensioncontentService.ExtensionDetailslist(page); // 列出extensioncontent列表
			mv.setViewName("reader/sysuser/sysuserExtensionDetails");
			mv.addObject("varList", varList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("pd", pd);
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