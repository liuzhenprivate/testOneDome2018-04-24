package com.sinontech.controller.read.article;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;



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
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.FileUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.PathUtil;
import com.sinontech.util.print.ExcelUtil;
import com.sinontech.service.read.article.ArticleService;
import com.sinontech.service.read.article.ArticleTempService;
import com.sinontech.service.read.articlecategory.ArticleCategoryService;
import com.sinontech.service.read.articlechapter.ArticleChapterService;
import com.sinontech.service.read.articlechapterlog.ArticleChapterlogService;
import com.sinontech.service.read.articlelabel.ArticleLabelService;
import com.sinontech.service.read.label.LabelService;

/** 
 * 类名称：ArticleController
 * 创建人：FH 
 * 创建时间：2017-11-07
 */
@Controller
@RequestMapping(value="/article")
public class ArticleController extends BaseController {
	String menuUrl = "article/list.do"; //菜单地址(权限用)
	@Resource(name="articleService")
	private ArticleService articleService;
	@Resource(name="labelService")
	private LabelService labelService;
	@Resource(name="articlechapterlogService")
	private ArticleChapterlogService articlechapterlogService;
	@Resource(name="articlechapterService")
	private ArticleChapterService articlechapterService;
	@Resource(name="articlelabelService")
	private ArticleLabelService articlelabelService;
	//书籍信息导入中间表
	@Resource(name="articletempService")
	private ArticleTempService articletempService;
	@Resource(name="articlecategoryService")
	private ArticleCategoryService articlecategoryService;
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Article");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		articleService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Article");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			articleService.delete(pd);
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
		logBefore(logger, "修改Article");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		articleService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 
	 * @purpose：修改书籍阅读人数
	 * @throws Exception
	 * @return void
	 * @author liuzhen
	 * @Time：2018-4-2 下午4:19:56
	 */
	@RequestMapping(value="/updateReads")
	public void updateReads() throws Exception{
		logBefore(logger, "修改书籍阅读人数");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String reg="^\\d+$";
		String str = pd.getString("DISPLAY_READERS");
		System.out.println(pd.toString());
		if(str.matches(reg)){
			if(Integer.parseInt(str)>=0){
				articleService.updateReads(pd);
			}
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
	}
	
	
	/**
	 * 
	 * 用途：下架修改
	 * @param @param page
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-5下午5:48:03
	 */
	@RequestMapping(value="/editState")
	public ModelAndView editState(Page page) throws Exception{
		logBefore(logger, "修改Article");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		articleService.editState(pd);
		try {
			pd = this.getPageData();
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			mv.setViewName("reader/article/article_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：列表
	 * @param @param page
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-5下午5:44:56
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Article");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try{
			pd = this.getPageData();
			if(null!=getRequest().getParameter("STATE")){
				String STATE = pd.getString("STATE");
				if("未上架".equals(STATE)){
					pd.put("STATE1", '0');
				}else if("已上架".equals(STATE)){
					pd.put("STATE1", '1');
				}else if("已下架".equals(STATE)){
					pd.put("STATE1", '2');
				}else if("已删除".equals(STATE)){
					pd.put("STATE1", "-1");
				}
				pd.put("STATE",STATE);
			}
			if(null!=getRequest().getParameter("CHANNEL_TYPE")){
				String CHANNEL_TYPE = pd.getString("CHANNEL_TYPE");
				if("男".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '0');
				}else if("女".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '1');
				}
				pd.put("CHANNEL_TYPE",CHANNEL_TYPE);
			}
			if(null!=getRequest().getParameter("IS_HOT")){
				String IS_HOT = pd.getString("IS_HOT");
				if("否".equals(IS_HOT)){
					pd.put("IS_HOT1", '0');
				}else if("是".equals(IS_HOT)){
					pd.put("IS_HOT1", '1');
				}
				pd.put("IS_HOT",IS_HOT);
			}
			if(null!=getRequest().getParameter("SERIAL_STATE")){
				String SERIAL_STATE = pd.getString("SERIAL_STATE");
				if("连载中".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '0');
				}else if("已完结".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '1');
				}
				pd.put("SERIAL_STATE",SERIAL_STATE);
			}
			/*if(null!=getRequest().getParameter("PAY_WAY")){
				String PAY_WAY = pd.getString("PAY_WAY");
				if("阅读币购买阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '0');
				}else if("免费阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '1');
				}
				pd.put("PAY_WAY",PAY_WAY);
			}*/
			if(null!=getRequest().getParameter("CATEGORY")){
				String CATEGORY = pd.getString("CATEGORY");
				if(!"全部类型".equals(CATEGORY)){
					pd.put("CATEGORY1",CATEGORY);
				}
				pd.put("CATEGORY",CATEGORY);
			}
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("startTime", timebegin);
				pd.put("endTime", timeend);
			}
			page.setPd(pd);
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			mv.setViewName("reader/article/article_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：书籍详情查询
	 * @param @param page
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-5下午6:18:14
	 */
	@RequestMapping(value="/findById")
	public ModelAndView findById(Page page){
		logBefore(logger, "列表Article");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String ARTICLE_ID = getRequest().getParameter("ARTICLE_ID");
			pd.put("ARTICLE_ID", ARTICLE_ID);
			String CHAPTER_NAME = getRequest().getParameter("CHAPTER_NAME");
			pd.put("CHAPTER_NAME", CHAPTER_NAME);
			String CHAPTER_TIME = String.valueOf(getRequest().getParameter("CHAPTER_TIME"));
			pd.put("CHAPTER_TIME", CHAPTER_TIME);
			if(null!=CHAPTER_TIME && !"".equals(CHAPTER_TIME) &&!"null".equals(CHAPTER_TIME)){
				String[] times = CHAPTER_TIME.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			page.setPd(pd);
			PageData articleById = articleService.findById(pd);	//列出Article详情
			List<PageData> varlabellist = labelService.listfindbookid(pd);
			List<PageData> varchapterlist = articlechapterService.list(page);
			articleById.put("COUNT_LETTER",Double.parseDouble(articleById.get("COUNT_LETTER").toString())/10000);
			PageData chapterpd = articlechapterlogService.bookcountdata(pd);//查询书籍总数据
			if("0".equals(pd.getString("batch"))){
				mv.setViewName("reader/article/article_findIdedit");
			}else{
				mv.setViewName("reader/article/article_findId");
			}
			mv.addObject("articleById", articleById);
			mv.addObject("chapterpd", chapterpd);
			mv.addObject("varchapterlist", varchapterlist);
			mv.addObject("varlabellist", varlabellist);
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
		logBefore(logger, "去新增Article页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/article/addarticle");
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
		logBefore(logger, "去修改Article页面");
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		try {
			List<PageData> varlabellist = labelService.list(page);//获取指定书籍标所有签名
			String LABEL_NAME = "";
			for (PageData pageData : varlabellist) {
				LABEL_NAME= LABEL_NAME + pageData.getString("LABEL_NAME")+",";
			}
			pd = articleService.findById(pd);	//根据ID读取
			pd.put("ycTypeae",0);
			pd.put("EDITLABEL_NAME", LABEL_NAME);
//			System.out.println(pd.toString());
			mv.setViewName("reader/article/addarticle");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			mv.addObject("ycTypeae", pd.get("ycTypeae"));
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
		logBefore(logger, "批量删除Article");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				articleService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Article到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("书籍id");	//1
			titles.add("书籍类目ID");	//2
			titles.add("书籍编号");	//3
			titles.add("书籍名称");	//4
			titles.add("作者");	//5
			titles.add("付费类型(默认0免费 1付费)");	//6
			titles.add("付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)");	//7
			titles.add("总阅读币");	//8
			titles.add("是否热门(默认0否 1是)");	//9
			titles.add("简介");	//10
			titles.add("总字数");	//11
			titles.add("总章节");	//12
			titles.add("状态(默认0未上架 2已上架 2下架 -1删除)");	//13
			titles.add("添加时间");	//14
			titles.add("阅读人数");	//15
			titles.add("购买书籍总阅读币");	//16
			titles.add("频道  0男频1女频");	//17
			dataMap.put("titles", titles);
			List<PageData> varOList = articleService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("ARTICLE_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("ARTICLE_CATEGORY_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("ARTICLE_CODE"));	//3
				vpd.put("var4", varOList.get(i).getString("ARTICLE_NAME"));	//4
				vpd.put("var5", varOList.get(i).getString("AUTHOR"));	//5
				vpd.put("var6", varOList.get(i).get("FEE_TYPE").toString());	//6
				vpd.put("var7", varOList.get(i).get("PAY_WAY").toString());	//7
				vpd.put("var8", varOList.get(i).get("PAY_CONSUMES").toString());	//8
				vpd.put("var9", varOList.get(i).get("IS_HOT").toString());	//9
				vpd.put("var10", varOList.get(i).getString("SUMMARY"));	//10
				vpd.put("var11", varOList.get(i).get("COUNT_LETTER").toString());	//11
				vpd.put("var12", varOList.get(i).get("COUNT_CHAPTERS").toString());	//12
				vpd.put("var13", varOList.get(i).get("STATE").toString());	//13
				vpd.put("var14", varOList.get(i).getString("CREATE_TIME"));	//14
				vpd.put("var15", varOList.get(i).get("READERS").toString());	//15
				vpd.put("var16", varOList.get(i).get("COUNT_CONSUMES").toString());	//16
				vpd.put("var17", varOList.get(i).get("CHANNEL_TYPE").toString());	//17
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
	 * 用途：添加书籍
	 * @param @param file
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-7上午9:56:37
	 */
	@RequestMapping(value="/adduploadfile")
	public ModelAndView adduploadfile(@RequestParam(required=false)CommonsMultipartFile file,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		int bookid = 0;
		int ARTICLE_CODE = 0;
		try {
			PageData pdNext = articleService.findNext();
			bookid = Integer.parseInt(pdNext.get("ARTICLE_ID").toString())+1;
			ARTICLE_CODE = Integer.parseInt(pdNext.get("ARTICLE_CODE").toString())+1;
			String str = getRequest().getParameter("LABEL");
			System.out.println(str);
			String name = file.getOriginalFilename();
			logger.info(name);
			String fileName = "";
			String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
			String  filePath = projectpath +  Const.FILEPATHIMGBOOK + bookid;		//文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID());		
			File f = new File(filePath+"/"+fileName);
			pd.put("ARTICLE_NAME",getRequest().getParameter("ARTICLE_NAME"));
			pd.put("BOOK_COVER",Const.FILEPATHIMGBOOK+bookid+"/"+f.getName());
			pd.put("AUTHOR",getRequest().getParameter("AUTHOR"));
			String FEE_TYPE = pd.getString("FEE_TYPE");
			String IS_HOT = pd.getString("IS_HOT");
			
			if("付费".equals(FEE_TYPE)){
				pd.put("FEE_TYPE",1);
			}else{
				pd.put("FEE_TYPE",0);
			}
			/*if("VIP免费阅读".equals(PAY_WAY)){
				pd.put("PAY_WAY",1);
			}else{
				pd.put("PAY_WAY",0);
			}*/
			if("是".equals(IS_HOT)){
				pd.put("IS_HOT",1);
			}else{
				pd.put("IS_HOT",0);
			}
			pd.put("RECOMMEND",getRequest().getParameter("RECOMMEND"));
			pd.put("SUMMARY",getRequest().getParameter("SUMMARY"));
			pd.put("ARTICLE_CATEGORY_ID",getRequest().getParameter("ARTICLE_CATEGORY_ID"));
			pd.put("CREATE_TIME",DateUtil.getTime());
			pd.put("ARTICLE_CODE", ARTICLE_CODE);
			pd.put("UPDATE_TIME",DateUtil.getTime());
			articleService.addBook(pd);
			String LABEL[] = str.split(",");
			if(LABEL.length!=0){
				List<PageData> varliastlabel = labelService.listData(LABEL);
				pd.put("ARTICLE_ID", bookid);
				for (int i = 0; i < varliastlabel.size(); i++) {
					pd.put("LABEL_ID",varliastlabel.get(i).get("LABEL_ID") );
					articlelabelService.save(pd);
				}
			}
			Page page = new Page();
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			mv.setViewName("reader/article/article_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：编辑书籍
	 * @param @param file
	 * @param @param response
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-7上午9:56:37
	 */
	@RequestMapping(value="/edituploadfile")
	public ModelAndView edituploadfile(@RequestParam(required=false)CommonsMultipartFile file,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		try {
			String str = getRequest().getParameter("LABEL");
			String ARTICLE_ID = getRequest().getParameter("ARTICLE_ID");
			String biaoshi = getRequest().getParameter("biaoshi");
			String ARTICLE_NAME = getRequest().getParameter("ARTICLE_NAME");
			String AUTHOR = getRequest().getParameter("AUTHOR");
			String FEE_TYPE = getRequest().getParameter("FEE_TYPE");
			String PAY_WAY = getRequest().getParameter("PAY_WAY");
			String IS_HOT = getRequest().getParameter("IS_HOT");
			String BOOK_COVER =getRequest().getParameter("BOOK_COVER");
			String ARTICLE_CATEGORY_ID = getRequest().getParameter("ARTICLE_CATEGORY_ID");
			pd.put("ARTICLE_CATEGORY_ID",ARTICLE_CATEGORY_ID);
			pd.put("BOOK_COVER",BOOK_COVER);
			//更改封面
			if("1".equals(biaoshi)){
				System.out.println("--更换封面--");
				String name = file.getOriginalFilename();
				logger.info(name);
				String fileName = "";
				String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
				String  filePath = projectpath +  Const.FILEPATHIMGBOOK + ARTICLE_ID;		//文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID());		
				File f = new File(filePath+"/"+fileName);
				pd.put("BOOK_COVER",Const.FILEPATHIMGBOOK + ARTICLE_ID+"/"+f.getName());
			}
			pd.put("ARTICLE_NAME",ARTICLE_NAME);
			pd.put("AUTHOR",AUTHOR);
			
			if("付费".equals(FEE_TYPE)){
				pd.put("FEE_TYPE",1);
			}else{
				pd.put("FEE_TYPE",0);
			}
			if("VIP免费阅读".equals(PAY_WAY)){
				pd.put("PAY_WAY",1);
			}else{
				pd.put("PAY_WAY",0);
			}
			if("是".equals(IS_HOT)){
				pd.put("IS_HOT",1);
			}else{
				pd.put("IS_HOT",0);
			}
			pd.put("RECOMMEND",getRequest().getParameter("RECOMMEND"));
			pd.put("SUMMARY",getRequest().getParameter("SUMMARY"));
			pd.put("ARTICLE_ID",ARTICLE_ID);
			articleService.editfile(pd);
			String LABEL[] = str.split(",");
			if(LABEL.length>0){
				articlelabelService.editdel(pd);
				List<PageData> varliastlabel = labelService.listData(LABEL);
				pd.put("ARTICLE_ID", ARTICLE_ID);
				for (int i = 0; i < varliastlabel.size(); i++) {
					pd.put("LABEL_ID",varliastlabel.get(i).get("LABEL_ID") );
					articlelabelService.save(pd);
				}
			}
			Page page = new Page();
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			mv.setViewName("reader/article/article_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/*
	 * 去书籍批量编辑页面
	 */
	@RequestMapping(value="/batctEdit")
	public ModelAndView batctEdit(Page page){
		logBefore(logger, "去书籍批量编辑页面");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(null!=getRequest().getParameter("STATE")){
				String STATE = pd.getString("STATE");
				if("未上架".equals(STATE)){
					pd.put("STATE1", '0');
				}else if("已上架".equals(STATE)){
					pd.put("STATE1", '1');
				}else if("已下架".equals(STATE)){
					pd.put("STATE1", '2');
				}else if("已删除".equals(STATE)){
					pd.put("STATE1", "-1");
				}
				pd.put("STATE",STATE);
			}
			if(null!=getRequest().getParameter("CHANNEL_TYPE")){
				String CHANNEL_TYPE = pd.getString("CHANNEL_TYPE");
				if("男".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '0');
				}else if("女".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '1');
				}
				pd.put("CHANNEL_TYPE",CHANNEL_TYPE);
			}
			if(null!=getRequest().getParameter("IS_HOT")){
				String IS_HOT = pd.getString("IS_HOT");
				if("否".equals(IS_HOT)){
					pd.put("IS_HOT1", '0');
				}else if("是".equals(IS_HOT)){
					pd.put("IS_HOT1", '1');
				}
				pd.put("IS_HOT",IS_HOT);
			}
			if(null!=getRequest().getParameter("SERIAL_STATE")){
				String SERIAL_STATE = pd.getString("SERIAL_STATE");
				if("连载中".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '0');
				}else if("已完结".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '1');
				}
				pd.put("SERIAL_STATE",SERIAL_STATE);
			}
			if(null!=getRequest().getParameter("PAY_WAY")){
				String PAY_WAY = pd.getString("PAY_WAY");
				if("阅读币购买阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '0');
				}else if("免费阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '1');
				}
				pd.put("PAY_WAY",PAY_WAY);
			}
			if(null!=getRequest().getParameter("CATEGORY")){
				String CATEGORY = pd.getString("CATEGORY");
				if(!"全部类型".equals(CATEGORY)){
					pd.put("CATEGORY1",CATEGORY);
				}
				pd.put("CATEGORY",CATEGORY);
			}
			System.out.println(pd.toString());
			page.setPd(pd);
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			System.out.println(varList.toString());
			mv.setViewName("reader/article/batch_edit");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：修改书籍上/下架状态,和删除书籍
	 * @param @param page
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-11上午11:37:05
	 */
	@RequestMapping(value="/batchEditState")
	public ModelAndView batchEditState(Page page){
		logBefore(logger, "书籍批量编辑");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			if(getRequest().getParameter("EDITARTICLE_ID")!=null){
				String DATA_IDS = pd.getString("EDITARTICLE_ID");
				if(null != DATA_IDS && !"".equals(DATA_IDS)){
					if(getRequest().getParameter("batchnum")!=null){
						String number = pd.getString("batchnum");
						String ArrayDATA_IDS[] = DATA_IDS.split(",");
						if("1".equals(number)){
							pdList.add(pd);
							map.put("list", pdList);
							articleService.bacthAllStateup(ArrayDATA_IDS);
						}else if("2".equals(number)){
							pdList.add(pd);
							map.put("list", pdList);
							articleService.bacthAllStatedown(ArrayDATA_IDS);
						}else if("3".equals(number)){
							pdList.add(pd);
							map.put("list", pdList);
							articleService.deleteAll(ArrayDATA_IDS);
						}
					}
					pd.put("msg", "ok");
				}else{
					pd.put("msg", "no");
				}
			}
			if(null!=getRequest().getParameter("STATE")){
				String STATE = pd.getString("STATE");
				if("未上架".equals(STATE)){
					pd.put("STATE1", '0');
				}else if("已上架".equals(STATE)){
					pd.put("STATE1", '1');
				}else if("已下架".equals(STATE)){
					pd.put("STATE1", '2');
				}else if("已删除".equals(STATE)){
					pd.put("STATE1", "-1");
				}
				pd.put("STATE",STATE);
			}
			if(null!=getRequest().getParameter("CHANNEL_TYPE")){
				String CHANNEL_TYPE = pd.getString("CHANNEL_TYPE");
				if("男".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '0');
				}else if("女".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '1');
				}
				pd.put("CHANNEL_TYPE",CHANNEL_TYPE);
			}
			if(null!=getRequest().getParameter("IS_HOT")){
				String IS_HOT = pd.getString("IS_HOT");
				if("否".equals(IS_HOT)){
					pd.put("IS_HOT1", '0');
				}else if("是".equals(IS_HOT)){
					pd.put("IS_HOT1", '1');
				}
				pd.put("IS_HOT",IS_HOT);
			}
			if(null!=getRequest().getParameter("SERIAL_STATE")){
				String SERIAL_STATE = pd.getString("SERIAL_STATE");
				if("连载中".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '0');
				}else if("已完结".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '1');
				}
				pd.put("SERIAL_STATE",SERIAL_STATE);
			}
			if(null!=getRequest().getParameter("PAY_WAY")){
				String PAY_WAY = pd.getString("PAY_WAY");
				if("阅读币购买阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '0');
				}else if("免费阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '1');
				}
				pd.put("PAY_WAY",PAY_WAY);
			}
			if(null!=getRequest().getParameter("CATEGORY")){
				String CATEGORY = pd.getString("CATEGORY");
				if(!"全部类型".equals(CATEGORY)){
					pd.put("CATEGORY1",CATEGORY);
				}
				pd.put("CATEGORY",CATEGORY);
			}
			page.setPd(pd);
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			mv.setViewName("reader/article/batch_edit");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：去新增章节页面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-11下午4:30:40
	 */
	@RequestMapping(value="/goAddChapter")
	public ModelAndView goAddChapter(){
		logBefore(logger, "去新增章节页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			PageData countChapterpd =  articlechapterService.countChapter(pd);
			PageData countarticlepd =  articleService.findById(pd);
			mv.addObject("countChapterpd", countChapterpd);
			mv.addObject("countarticlepd", countarticlepd);
			mv.addObject("pd", pd);
			mv.setViewName("reader/article/artcle_addChapter");
			mv.addObject("msg", "save");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 * 用途：保存章节(单章)
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-11下午5:29:37
	 */
	@RequestMapping(value="/articleChapteradd")
	public ModelAndView articleChapteradd() throws Exception{
		logBefore(logger, "新增Chapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		if(null!=pd.getString("ARTICLECHAPTER_NO")&&!"".equals(pd.getString("ARTICLECHAPTER_NO").trim())){
			articlechapterService.upnextno(pd);
			pd.put("CHAPTER_NO",pd.getString("ARTICLECHAPTER_NO"));
		}else{
			PageData nextChapter = articlechapterService.nextArticleChapter(pd);
			if(nextChapter==null){
				pd.put("CHAPTER_NO",1);
			}else{
				pd.put("CHAPTER_NO", Integer.parseInt(nextChapter.get("CHAPTER_NO").toString())+1);
			}
		}
		if(pd.getString("CHAPTER_STATE")==null||"".equals(pd.getString("CHAPTER_STATE"))){
			pd.put("CHAPTER_STATE", 0);
		}
		if(pd.getString("IS_FREE")==null||"".equals(pd.getString("IS_FREE"))){
			pd.put("IS_FREE", 0);
		}
		pd.put("CREATE_TIME", DateUtil.getTime());
		pd.put("UPDATE_TIME", DateUtil.getTime());
		
		String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
		String filename =  Const.FILEPATHBOOKCHAPTERARTICLE + pd.getString("ARTICLE_ID")+"/";//文件目录
		String  filePath = projectpath + filename;//文件上传路径
		String get32uuid = this.get32UUID();//生成每个章节的文件名
		File writename = new File(filePath+get32uuid+".txt");
		FileUtil.createDir(filePath);//创建目录
		if(writename.exists()){
			writename.createNewFile();//创建文件
		}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(writename),"gbk");      
	    BufferedWriter writer=new BufferedWriter(write); 
	    writer.write(pd.getString("chaptercontent")); 
	    writer.flush();
	    writer.close();
	    
	    pd.put("CONTENT_URL",filename+get32uuid+".txt");
		articlechapterService.save(pd);
		articleService.updateChapternum(pd);
		articleService.upchapterNumber(pd);
		return new ModelAndView("redirect:/article/findById.do?ARTICLE_ID="+pd.get("ARTICLE_ID"));
	}
	
	/**
	 * 
	 * 用途：匹配书籍
	 * @param @return   
	 * @return Map<String,String>  
	 * @author 刘振
	 * @date 2017-12-13下午3:40:20
	 */
	@RequestMapping(value="/matchingBook")
	@ResponseBody
	public Object matchingBook(){
		logBefore(logger, "列表Article");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		PageData pd = new PageData();
		List<PageData>	varList = null;
		try{
			pd = this.getPageData();
			varList = articleService.matchingBook(pd);	//列出Article列表
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	/**
	 * 
	 * 用途：去批量新增Article页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-18 上午9:57:08
	 */
	@RequestMapping(value="/batchAddarticle")
	public ModelAndView batchAddarticle(){
		logBefore(logger, "去批量新增Article页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/article/article_batchAdd");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 * 保存上传的文件,同时将数据保存在会话中
	 */
	@RequestMapping(value="/uploadfile")
	public ModelAndView uploadfile(@RequestParam(required=false)CommonsMultipartFile file,HttpServletResponse response,Page page){
		ModelAndView mv = new ModelAndView();
		String name = file.getOriginalFilename();
		logger.info(name);
		String  ffile = DateUtil.getDays(), fileName = "";
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE + ffile;		//文件上传路径
		int flag = 0;
		fileName = FileUpload.fileUp(file, filePath, this.get32UUID());		
		File f = new File(filePath+"/"+fileName);
		try {
			int k=0;
			List<String[][]> s = ExcelUtil.read(f);
			for(String[][] a:s){
   			 for(int j=1;j<a.length;j++) {  
   				k++;
   				String ARTICLE_CODE = a[j][0].trim().replace("?", "");
   				if(null==ARTICLE_CODE || "".equals(ARTICLE_CODE) || "null".equals(ARTICLE_CODE)){
   					continue;
   				}
   				String ARTICLE_NAME = a[j][1].trim().replace("?", "");
   				if(null==ARTICLE_NAME || "".equals(ARTICLE_NAME) ||"null".equals(ARTICLE_NAME)){
   					continue;
   				}
   				String AUTHOR = a[j][2].trim().replace("?", "");
   				if(null==AUTHOR || "".equals(AUTHOR) ||"null".equals(AUTHOR)){
   					continue;
   				}
   				String FEE_TYPE = a[j][3].trim().replace("?", "");
   				if(null==FEE_TYPE || "".equals(FEE_TYPE) ||"null".equals(FEE_TYPE)){
   					continue;
   				}
   				String PAY_WAY = a[j][4].trim().replace("?", "");
   				if(null==PAY_WAY || "".equals(PAY_WAY) ||"null".equals(PAY_WAY)){
   					continue;
   				}
   				String IS_HOT = a[j][5].trim().replace("?", "");
   				if(null==IS_HOT || "".equals(IS_HOT) ||"null".equals(IS_HOT)){
   					continue;
   				}
   				String SUMMARY = a[j][6].trim().replace("?", "");
   				if(null==SUMMARY || "".equals(SUMMARY) || "null".equals(SUMMARY)){
   					SUMMARY = "";
   				}
   				String LABEL = a[j][7].trim().replace("?", "");
   				if(null==LABEL || "".equals(LABEL) || "null".equals(LABEL)){
   					LABEL = "";
   				}
   				if("免费".equals(FEE_TYPE)){
   					FEE_TYPE ="0";
   				}else if("付费".equals(FEE_TYPE)){
   					FEE_TYPE ="1";
   				}else{
   					continue;
   				}
   				
   				if("阅读币".equals(PAY_WAY)){
   					PAY_WAY ="0";
   				}else if("VIP免费".equals(PAY_WAY)){
   					PAY_WAY ="1";
   				}else{
   					continue;
   				}
   				
   				if("是".equals(IS_HOT)){
   					IS_HOT ="0";
   				}else if("否".equals(IS_HOT)){
   					IS_HOT ="1";
   				}else{
   					continue;
   				}
   	            PageData pd = new PageData();
   	            pd.put("ARTICLE_CODE", ARTICLE_CODE);	
   	            pd.put("ARTICLE_NAME", ARTICLE_NAME);	
	   	 		pd.put("AUTHOR", AUTHOR);
	   	 		pd.put("FEE_TYPE", FEE_TYPE);
	   	 		pd.put("PAY_WAY", PAY_WAY);
	   	 		pd.put("IS_HOT", IS_HOT);
	   	 		pd.put("SUMMARY", SUMMARY);
	   	 		pd.put("LABEL", LABEL);
	   	 		pd.put("CREATE_TIME", DateUtil.getTime());	
	   	 		try {
					this.articletempService.save(pd);
				} catch (Exception e) {
					e.printStackTrace();
				}
	   	 		 
   	          }  
			}
			PageData pd = new PageData();
			List<PageData> list = new ArrayList<PageData>();
			try {
				list = this.articletempService.list(page);
				pd.put("curnums", list.size());
				mv.addObject("varList", list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//总共条数
			pd.put("allnums", k-1);
			//有效条数
			mv.addObject("pd", pd);
			System.out.println(pd.toString());
		} catch (BiffException e) {
			flag = 2;
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			flag = 3;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		 
		mv.setViewName("reader/article/article_batchAdd");
		return mv;
	}
	/**
	 * 
	 * 用途：
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-19 下午1:52:45
	 */
	@RequestMapping(value="/listtemp")
	public ModelAndView listtemp(Page page){
		logBefore(logger, "列表Article");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = articletempService.list(page);	//列出article列表
			mv.setViewName("reader/article/article_batchAdd");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 
	 * 用途：删除导入书籍
	 * @author 刘振
	 * void
	 * 时间:2018-1-19 下午2:03:06
	 */
	@RequestMapping(value="/deletetemp")
	public void deletetemp(PrintWriter out){
		logBefore(logger, "删除Article");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			articletempService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	/**
	 * 
	 *用途：去推广书籍详情goExtensionchapter
	 *@return ModelAndView
	 *@author 刘振
	 *2018-1-29 下午3:26:46
	 */
	@RequestMapping(value="/goExtensionchapter")
	public ModelAndView goExtensionchapter(Page page){
		logBefore(logger, "去推广书籍详情goExtensionchapter");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String ARTICLE_ID = getRequest().getParameter("ARTICLE_ID");
			pd.put("ARTICLE_ID", ARTICLE_ID);
			page.setPd(pd);
			PageData articleById = articleService.findById(pd);	//列出Article详情
			List<PageData> varlabellist = labelService.list(page);
			List<PageData> varchapterlist = articlechapterService.listchapterExtension(page);
			articleById.put("COUNT_LETTER",Double.parseDouble(articleById.get("COUNT_LETTER").toString())/10000);
			mv.setViewName("readerchannel/extensioncontent/extension_chapter");
			mv.addObject("articleById", articleById);
			mv.addObject("varchapterlist", varchapterlist);
			mv.addObject("varlabellist", varlabellist);
			mv.addObject("pd", pd);
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