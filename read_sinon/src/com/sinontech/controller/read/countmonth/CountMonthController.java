package com.sinontech.controller.read.countmonth;

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
import com.sinontech.util.AppUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.countmonth.CountMonthService;

/** 
 * 类名称：CountMonthController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/countmonth")
public class CountMonthController extends BaseController {
	
	String menuUrl = "countmonth/list.do"; //菜单地址(权限用)
	@Resource(name="countmonthService")
	private CountMonthService countmonthService;
	
	
	
	
	/**
	 * 
	 * 用途： 充值图标数据查询（近一年数据）
	 * @param @return
	 * @param @throws Exception   
	 * @return Object  
	 * @author 刘振
	 * @date 2017-12-5下午5:50:18
	 */
	@RequestMapping(value="/date")
	@ResponseBody
	public Object date() throws Exception{
		logBefore(logger, "图表数据date");
		PageData pd = new PageData();
		List<PageData>	varList = null;
		try{
			pd = this.getPageData();
			varList = countmonthService.date(pd);	//列出CountDay列表'
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	/**
	 * 
	 * @purpose：渠道充值图标数据查询（近一年数据）
	 * @return
	 * @throws Exception
	 * @return Object
	 * @author liuzhen
	 * @Time：2018-3-25 下午4:28:23
	 */
	@RequestMapping(value="/sysuserdate")
	@ResponseBody
	public Object sysuserdate() throws Exception{
		logBefore(logger, "图表数据date");
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData>	varList = null;
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try{
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				varList = countmonthService.sysuserdate(pd);	//列出CountDay列表'
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增CountMonth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("COUNTMONTH_ID", this.get32UUID());	//主键
		countmonthService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除CountMonth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			countmonthService.delete(pd);
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
		logBefore(logger, "修改CountMonth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		countmonthService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表CountMonth");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = countmonthService.list(page);	//列出CountMonth列表
			mv.setViewName("read/countmonth/countmonth_list");
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
		logBefore(logger, "去新增CountMonth页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/countmonth/countmonth_edit");
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
		logBefore(logger, "去修改CountMonth页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = countmonthService.findById(pd);	//根据ID读取
			mv.setViewName("read/countmonth/countmonth_edit");
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
		logBefore(logger, "批量删除CountMonth");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				countmonthService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出CountMonth到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("渠道ID");	//2
			titles.add("微信公众号ID");	//3
			titles.add("会员总数");	//4
			titles.add("充值总数");	//5
			titles.add("消费阅读币");	//6
			titles.add("消费人民币");	//7
			titles.add("统计月份");	//8
			titles.add("创建日期");	//9
			titles.add("更新时间");	//10
			dataMap.put("titles", titles);
			List<PageData> varOList = countmonthService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("COUNNT_MONTH_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("CHANNEL_ID").toString());	//2
				vpd.put("var3", varOList.get(i).get("WEHCHAT_ID").toString());	//3
				vpd.put("var4", varOList.get(i).get("USERS").toString());	//4
				vpd.put("var5", varOList.get(i).get("RECHARGES").toString());	//5
				vpd.put("var6", varOList.get(i).get("CONSUMES").toString());	//6
				vpd.put("var7", varOList.get(i).get("MONEY").toString());	//7
				vpd.put("var8", varOList.get(i).getString("COUNT_MONTH"));	//8
				vpd.put("var9", varOList.get(i).getString("CREATE_TIME"));	//9
				vpd.put("var10", varOList.get(i).getString("UPDATE_TIME"));	//10
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