package com.sinontech.controller.read.extensionconnect;

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
 * 类名称：AdoptController
 * 创建人：刘振
 * 创建时间：2018-01-26
 */
@Controller
@RequestMapping(value="/extensionconnect")
public class ExtensionConnectController extends BaseController {
	
	String menuUrl = "extensionconnect/list.do"; //菜单地址(权限用)
	@Resource(name="extensionconnectService")
	private ExtensionConnectService extensionconnectService;
	@Resource(name="extensioncontentService")
	private ExtensionContentService extensioncontentService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save() throws Exception {
		logBefore(logger, "新增ExtensionConnect");
		PageData pd = new PageData();
		pd = this.getPageData();
		// pd.put("EXTENSIONCONNECT_ID", this.get32UUID()); //主键
		
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String msg = "";
		if (null != user) {
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			PageData contenpd =  extensioncontentService.findById(pd);
			if(null!=contenpd){
				String http = contenpd.getString("CONTENT_URL");
				pd.put("EXTENSION_URL",http+"/"+ this.get32UUID()+ MD5.md5(uid+DateUtil.getTime()));
				
				//extensionconnectService.save(pd);
				msg = "true";
				System.out.println(pd.toString());
			}
		}
		return msg;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除ExtensionConnect");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			extensionconnectService.delete(pd);
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
		logBefore(logger, "修改ExtensionConnect");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		extensionconnectService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表ExtensionConnect");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = extensionconnectService.list(page);	//列出ExtensionConnect列表
			mv.setViewName("read/extensionconnect/extensionconnect_list");
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
		logBefore(logger, "去新增ExtensionConnect页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/extensionconnect/extensionconnect_edit");
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
		logBefore(logger, "去修改ExtensionConnect页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = extensionconnectService.findById(pd);	//根据ID读取
			mv.setViewName("read/extensionconnect/extensionconnect_edit");
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
		logBefore(logger, "批量删除ExtensionConnect");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				extensionconnectService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出ExtensionConnect到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("id");	//1
			titles.add("推广详情表主键ID");	//2
			titles.add("推广链接");	//3
			titles.add("创建时间");	//4
			titles.add("关注人数");	//5
			titles.add("引导人数");	//6
			titles.add("充值金额");	//7
			titles.add("充值笔数");	//8
			titles.add("充值人数");	//9
			titles.add("收益");	//10
			dataMap.put("titles", titles);
			List<PageData> varOList = extensionconnectService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("EXTENSION_CONNECT_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("EXTENSION_CONTENT_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("EXTENSION_URL"));	//3
				vpd.put("var4", varOList.get(i).getString("CREATE_TIME"));	//4
				vpd.put("var5", varOList.get(i).get("FOLLOW").toString());	//5
				vpd.put("var6", varOList.get(i).get("GUIDE").toString());	//6
				vpd.put("var7", varOList.get(i).get("RECHARGE").toString());	//7
				vpd.put("var8", varOList.get(i).get("RECHARGE_TIMES").toString());	//8
				vpd.put("var9", varOList.get(i).get("RECHARGE_PEOPLES").toString());	//9
				vpd.put("var10", varOList.get(i).get("PROFIT").toString());	//10
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
