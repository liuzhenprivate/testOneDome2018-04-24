package com.sinontech.controller.read.title;

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

import com.sinontech.entity.system.User;
import com.sinontech.util.FileUpload;
import com.sinontech.util.PathUtil;
import com.sinontech.util.print.ExcelUtil;
import com.sinontech.util.print.SessionUtils;
import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.title.TitleService;
import com.sinontech.service.read.title.TitleTempService;
import com.sinontech.service.read.titlecategory.TitleCategoryService;

/** 
 * 类名称：TitleController
 * 创建人：FH 
 * 创建时间：2018-01-04
 */
@Controller
@RequestMapping(value="/title")
public class TitleController extends BaseController {
	
	String menuUrl = "title/list.do"; //菜单地址(权限用)
	@Resource(name="titleService")
	private TitleService titleService;
	@Resource(name="titlecategoryService")
	private TitleCategoryService titlecategoryService;
	//批量上传中间表
	@Resource(name="titletempService")
	private TitleTempService titletempService;
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
//		String filePath = "D:/apache-tomcat-6.0.43/webapps/local_ivr1/"+ Const.FILEPATHFILERINGMAKE + ffile;	
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE + ffile;		//文件上传路径
//		logger.info(filePath);
//		D:/apache-tomcat-6.0.43/webapps/local_ivr1/WEB-INF/classes/../../uploadFiles/file/20150812
		int flag = 0;
		fileName = FileUpload.fileUp(file, filePath, this.get32UUID());		
//		logger.info(fileName);
		File f = new File(filePath+"/"+fileName);
		try {
			int k=0;
			List<String[][]> s = ExcelUtil.read(f);
			for(String[][] a:s){
   			 for(int j=1;j<a.length;j++) {  
   				k++;
   				String TITLE_CATEGORY_NAME = a[j][0].trim().replace("?", "");
   				if(null==TITLE_CATEGORY_NAME || "".equals(TITLE_CATEGORY_NAME) || "null".equals(TITLE_CATEGORY_NAME)){
   					continue;
   				}
   				String CONTENT = a[j][1].trim().replace("?", "");
   				if(null==CONTENT || "".equals(CONTENT) ||"null".equals(CONTENT)){
   					continue;
   				}
   				String TITLE_CATEGORY_ID ="";
   				if("男生".equals(TITLE_CATEGORY_NAME)){
   					TITLE_CATEGORY_ID ="1";
   				}else if("女生".equals(TITLE_CATEGORY_NAME)){
   					TITLE_CATEGORY_ID ="2";
   				}else{
   					continue;
   				}
   				
   	            PageData pd = new PageData();
   	            pd.put("TITLE_CATEGORY_ID", TITLE_CATEGORY_ID);	
   	            pd.put("CATEGORY_NAME", TITLE_CATEGORY_NAME);	
	   	 		pd.put("CONTENT", CONTENT);	
	   	 		pd.put("CREATE_TIME", DateUtil.getTime());	
	   	 		try {
					this.titletempService.save(pd);
				} catch (Exception e) {
					e.printStackTrace();
				}
	   	 		 
   	          }  
			}
			PageData pd = new PageData();
			List<PageData> list = new ArrayList<PageData>();
			try {
				list = this.titletempService.list(page);
				pd.put("curnums", list.size());
				mv.addObject("varList", list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//总共条数
			pd.put("allnums", k-1);
			//有效条数
			mv.addObject("pd", pd);
		} catch (BiffException e) {
			flag = 2;
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			flag = 3;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		 
		mv.setViewName("reader/title/title_listadd");
		return mv;
	}
	@RequestMapping(value="/listtemp")
	public ModelAndView listtemp(Page page){
		logBefore(logger, "列表Title");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = titletempService.list(page);	//列出Title列表
			mv.setViewName("reader/title/title_listadd");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 用途说明：批量导入数据插入数据库
	 * @return
	 * @throws Exception
	 * 2018年1月5日下午4:09:49
	 * @auther ljj
	 */
	@RequestMapping(value="/addlistok")
	public ModelAndView addlistok() throws Exception{
		logBefore(logger, "批量新增Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		List<PageData> list = (List<PageData>) session.getAttribute("titlelistadd");
		if(null!=list && list.size()>0){
			for(PageData pd:list){
				titleService.save(pd);
			}
		}
		 return new ModelAndView("redirect:/title/list.do"); 
	}
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save( )  {
		logBefore(logger, "新增Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null ;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CREATE_TIME", DateUtil.getTime());	//主键
		try {
			titleService.save(pd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			titleService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	@RequestMapping(value="/deletetemp")
	public void deletetemp(PrintWriter out){
		logBefore(logger, "删除Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			titletempService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	@RequestMapping(value="/addalltemp")
	public ModelAndView addalltemp(Page page){
		logBefore(logger, "将中间表数据导入到Title表");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData> list1 = titletempService.listAll(pd);
			for(PageData title:list1){
				this.titleService.save(title);
			}
			this.titletempService.deleteAll(pd);
			List<PageData>	varList = titleService.list(page);	//列出Title列表
			PageData pd1 = new PageData();
			List<PageData> list = this.titlecategoryService.listAll(pd1);
			mv.setViewName("reader/title/title_list");
			mv.addObject("varList", varList);
			mv.addObject("titlecategorylist", list);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/deletealltemp")
	public ModelAndView deletealltemp(Page page){
		logBefore(logger, "删除Title");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			titletempService.deleteAll(pd);
			List<PageData>	varList = titleService.list(page);	//列出Title列表
			PageData pd1 = new PageData();
			List<PageData> list = this.titlecategoryService.listAll(pd1);
			mv.setViewName("reader/title/title_list");
			mv.addObject("varList", varList);
			mv.addObject("titlecategorylist", list);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		titleService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value="/edittemp")
	public ModelAndView edittemp() throws Exception{
		logBefore(logger, "修改Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		titletempService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Title");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = titleService.list(page);	//列出Title列表
			PageData pd1 = new PageData();
			List<PageData> list = this.titlecategoryService.listAll(pd1);
			mv.setViewName("reader/title/title_list");
			mv.addObject("varList", varList);
			mv.addObject("titlecategorylist", list);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/editlist")
	public ModelAndView editlist(Page page){
		logBefore(logger, "列表Title");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = titleService.list(page);	//列出Title列表
			mv.setViewName("reader/title/title_listedit");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 去批量新增页面
	 */
	@RequestMapping(value="/goAddlist")
	public ModelAndView goAddlist(){
		logBefore(logger, "去批量新增Title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/title/title_listadd");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/title/title_add");
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
		logBefore(logger, "去修改Title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = titleService.findById(pd);	//根据ID读取
			mv.setViewName("reader/title/title_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	 
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdittemp")
	public ModelAndView goEdittemp(){
		logBefore(logger, "去修改Title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = titletempService.findById(pd);	//根据ID读取
			mv.setViewName("reader/title/title_edittemp");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/goLooktemp")
	public ModelAndView goLooktemp(){
		logBefore(logger, "去查看Title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = titletempService.findById(pd);	//根据ID读取
			mv.setViewName("reader/title/title_content");
			mv.addObject("msg", "look");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 用途说明：查看标题详情
	 * @return
	 * 2018年1月12日上午10:21:25
	 * @auther ljj
	 */
	@RequestMapping(value="/goLook")
	public ModelAndView goLook(){
		logBefore(logger, "去查看Title页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = titleService.findById(pd);	//根据ID读取
			mv.setViewName("reader/title/title_content");
			mv.addObject("msg", "look");
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
		logBefore(logger, "批量删除Title");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				titleService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Title到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("类目名称");	//1
			titles.add("标题名称");	//2
			titles.add("标题内容");	//3
			titles.add("使用次数");	//4
			titles.add("添加时间");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = titleService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TITLE_CATEGORY_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("TITLE_NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("CONTENT"));	//3
				vpd.put("var4", varOList.get(i).getString("USE_TIMES"));	//4
				vpd.put("var5", varOList.get(i).getString("CREATE_TIME"));	//5
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
