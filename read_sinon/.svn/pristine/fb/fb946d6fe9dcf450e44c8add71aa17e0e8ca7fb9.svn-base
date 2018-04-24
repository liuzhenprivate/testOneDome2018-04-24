package com.sinontech.controller.read.categorylabel;

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
import com.sinontech.service.read.categorylabel.CategoryLabelService;
import com.sinontech.service.read.labelcategory.LabelCategoryService;
import com.sinontech.util.AppUtil;
import com.sinontech.util.Const;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.PageData;


/** 
 * 类名称：CategoryLabelController
 * 创建人：FH 
 * 创建时间：2017-12-05
 */
@Controller
@RequestMapping(value="/categorylabel")
public class CategoryLabelController extends BaseController {
	
	String menuUrl = "categorylabel/list.do"; //菜单地址(权限用)
	@Resource(name="categorylabelService")
	private CategoryLabelService categorylabelService;
	@Resource(name="labelcategoryService")
	private LabelCategoryService labelcategoryService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增CategoryLabel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CATEGORY_NAME", pd.getString("CATEGORY_NAME"));
		String[] str = pd.getString("labelid").split(",");
		if(str.length!=0){
			PageData pd1 = labelcategoryService.findCategoryName(pd);
			if(null!=pd1){
				pd.put("LABEL_CATEGORY_ID", pd1.get("LABEL_CATEGORY_ID"));
				for (int i = 0; i < str.length; i++) {
					pd.put("LABEL_ID", str[i]);
					PageData pd2 = categorylabelService.findByLabelandCategoryId(pd);
					if(null==pd2){
						categorylabelService.save(pd);
					}
				}
			}
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
		logBefore(logger, "删除CategoryLabel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			categorylabelService.delete(pd);
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
		logBefore(logger, "修改CategoryLabel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		categorylabelService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/delLabelId")
	public ModelAndView delLabelId() throws Exception{
		logBefore(logger, "修改CategoryLabel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String labelId = pd.getString("labelid");
		categorylabelService.delLabelId(labelId.substring(0, labelId.length()-1));
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表CategoryLabel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = categorylabelService.list(page);	//列出CategoryLabel列表
			mv.setViewName("read/categorylabel/categorylabel_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 列表(全部)
	 */
	@RequestMapping(value="/listAll")
	public ModelAndView listAll(){
		logBefore(logger, "列表CategoryLabel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData>	varList = categorylabelService.listAll(pd);	//列出CategoryLabel列表
			mv.setViewName("reader/label/label_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * json获取Data标签
	 */
	@RequestMapping(value="/listJson")
	@ResponseBody
	public Object listJson(Page page){
		logBefore(logger, "列表CategoryLabel");
		PageData pd = new PageData();
		List<PageData>	varList = null;
		PageData listcategory = null;
		try{
			pd = this.getPageData();
			if(null!=pd.getString("CATEGORY_NAME")&&!"".equals(pd.getString("CATEGORY_NAME"))){
				listcategory =  labelcategoryService.findCategoryName(pd);
			}
			if(null!=listcategory){
				pd.put("LABEL_CATEGORY_ID", listcategory.get("LABEL_CATEGORY_ID"));
				pd.put("CATEGORY_NAME","");
			}else{
				pd.put("CATEGORY_NAME","");
				pd.put("LABEL_CATEGORY_ID","");
			}
			varList = categorylabelService.listData(pd);	//列出CategoryLabel列表
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增CategoryLabel页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/categorylabel/categorylabel_edit");
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
		logBefore(logger, "去修改CategoryLabel页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = categorylabelService.findById(pd);	//根据ID读取
			mv.setViewName("read/categorylabel/categorylabel_edit");
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
		logBefore(logger, "批量删除CategoryLabel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				categorylabelService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出CategoryLabel到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("标签表主键id");	//2
			titles.add("标签类目标主键id");	//3
			dataMap.put("titles", titles);
			List<PageData> varOList = categorylabelService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("CATEGORYLABEL_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("LABEL_ID").toString());	//2
				vpd.put("var3", varOList.get(i).get("LABEL_CATEGORY_ID").toString());	//3
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
