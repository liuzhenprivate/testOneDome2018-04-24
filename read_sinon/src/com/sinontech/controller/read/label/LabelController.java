package com.sinontech.controller.read.label;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

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
import com.sinontech.util.AppUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.categorylabel.CategoryLabelService;
import com.sinontech.service.read.label.LabelService;
import com.sinontech.service.read.labelcategory.LabelCategoryService;

/** 
 * 类名称：LabelController
 * 创建人：FH 
 * 创建时间：2017-11-07
 */
@Controller
@RequestMapping(value="/label")
public class LabelController extends BaseController {
	
	String menuUrl = "label/list.do"; //菜单地址(权限用)
	@Resource(name="labelService")
	private LabelService labelService;
	@Resource(name="labelcategoryService")
	private LabelCategoryService labelcategoryService;
	@Resource(name="categorylabelService")
	private CategoryLabelService categorylabelService;
	
	/**
	 * 
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * 用途：新增
	 * @author 刘振
	 * @date 2017-12-5下午5:46:14
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Label");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String[] str = pd.getString("LABEL_NAME").split(",");
		if(str.length==1){
			str = pd.getString("LABEL_NAME").split("，");
		}
		if(str.length>0){
			for (int i = 0; i < str.length; i++) {
				pd.put("LABEL_NAME", str[i]);
				PageData pd1 = labelService.seachlabel(pd);
				if(null==pd1){//判断标签是否重复
					if(null!=pd.getString("CATEGORY_NAME") && !"".equals(pd.getString("CATEGORY_NAME"))){
						PageData LabelCategory = labelcategoryService.findCategoryName(pd);//查询指定分类名的id
						if(null!=LabelCategory){
							labelService.save(pd);//保存标签
							pd.put("LABEL_CATEGORY_ID", LabelCategory.get("LABEL_CATEGORY_ID"));
							Thread.sleep(500);
							PageData pd2 = labelService.seachlabel(pd);
							pd.put("LABEL_ID", pd2.get("LABEL_ID"));
							categorylabelService.save(pd);//保存标签对应的标签类
						}
					}
				}
			}
			mv.addObject("msg","success");
			mv.setViewName("save_result");
		}
		
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Label");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			labelService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 用途：删除标签
	 * @param @param page
	 * @param @return   
	 * @return Object  
	 * @author 刘振
	 * @date 2017-12-5下午5:46:30
	 */
	@RequestMapping(value="/delId")
	@ResponseBody
	public Object delId(Page page){
		logBefore(logger, "删除Label");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		JSONArray jsonArray = JSONArray.fromObject(getRequest().getParameter("delid")); 
		PageData pd = new PageData(); 
		List<PageData>	varList = null;
		try{
			pd = this.getPageData();
			for (int i = 0; i < jsonArray.size();i++) {
				pd.put("LABEL_ID", jsonArray.get(i));
				labelService.delete(pd);
				//labelcategoryService.deleteCategoryLabelid(pd);
				categorylabelService.delLabelId(jsonArray.get(i).toString());
			}
			varList = labelService.listAll(pd);	//列出Label所有
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	
	/**
	 * 
	 * 用途：修改
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-5下午5:46:45
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Label");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String[] str = pd.getString("labelid").split(",");
		if(str.length!=0||str==null){
			for (int i = 0; i < str.length; i++) {
				pd.put("LABEL_CATEGORY_ID",0);
				pd.put("LABEL_ID", str[i]);
				PageData pageDta = labelService.findById(pd);
				pd.put("LABEL_NAME", pageDta.getString("LABEL_NAME"));
				labelService.editname(pd);
			}
		}else{
			labelService.edit(pd);
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 
	 * 用途：列表
	 * @param @param page
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-5下午5:46:55
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Label");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData>	varList = labelService.list(page);	//列出Label列表
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
	 * 用途：根据标签类目ID查询标签
	 * @param @param page
	 * @param @return   
	 * @return Object  
	 * @author 刘振
	 * @date 2017-12-5下午5:47:05
	 */
	@RequestMapping(value="/listJson")
	@ResponseBody
	public Object listJson(Page page){
		logBefore(logger, "列表Label");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		PageData pd = new PageData();
		pd.put("LABEL_NAME", getRequest().getParameter("LABEL_NAME"));
		List<PageData>	varList = null;
		try{
			pd = this.getPageData();
			page.setPd(pd);
			varList = labelService.list(page);	//列出Label列表
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
		logBefore(logger, "去新增Label页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/label/label_edit");
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
	public ModelAndView goEdit(Page page){
		logBefore(logger, "去修改Label页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	varList = labelService.list(page);	//列出Label列表
			mv.addObject("varList", varList);
			mv.setViewName("reader/label/label_edit");
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
		logBefore(logger, "批量删除Label");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				labelService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Label到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("id");	//1
			titles.add("所属标签类目id");	//2
			titles.add("标签名称");	//3
			dataMap.put("titles", titles);
			List<PageData> varOList = labelService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("LABEL_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("LABEL_CATEGORY").toString());	//2
				vpd.put("var3", varOList.get(i).getString("LABEL_NAME"));	//3
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