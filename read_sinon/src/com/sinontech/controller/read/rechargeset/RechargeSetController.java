package com.sinontech.controller.read.rechargeset;

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
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.rechargeset.RechargeSetService;

/** 
 * 类名称：RechargeSetController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/rechargeset")
public class RechargeSetController extends BaseController {
	
	String menuUrl = "rechargeset/list.do"; //菜单地址(权限用)
	@Resource(name="rechargesetService")
	private RechargeSetService rechargesetService;
	
	/**
	 * 
	 * 用途：充值新增
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-20下午4:12:23
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增RechargeSet");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.getString("SORT_NUM")!=null&&!"".equals(pd.getString("SORT_NUM"))){
			rechargesetService.numberupone(pd);
		}else{
			PageData pdnext = rechargesetService.seachnext();
			if(pdnext!=null){
				pd.put("SORT_NUM",Integer.parseInt(pdnext.get("SORT_NUM").toString())+1);
			}else{
				pd.put("SORT_NUM",1);
			}
		}
		if("永久有效".equals(pd.getString("EXP_DATE"))||"".equals(pd.getString("EXP_DATE"))){
			pd.put("EXP_DATE", "");
		}
		pd.put("RECHARGE_NAME", "普通充值");
		pd.put("RECHARGE_TYPE", 2);
		pd.put("MONEY", Integer.parseInt(pd.get("MONEY").toString())*100);
		pd.put("BOOK_CURRENCY_ALL", (Integer.parseInt(pd.get("BOOK_CURRENCY").toString()))
				+(Integer.parseInt(pd.get("BOOK_CURRENCY_GIFT").toString())));
		rechargesetService.save(pd);
		List<PageData>	varList = rechargesetService.list(page);	//列出RechargeSet列表
		for (int i = 0; i < varList.size(); i++) {
			varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
		}
		mv.setViewName("reader/rechargeset/rechargeset_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 
	 * 用途：删除
	 * @param @param out
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-21上午10:19:56
	 */
	@RequestMapping(value="/delete")
	public ModelAndView delete(){
		logBefore(logger, "删除RechargeSet");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			rechargesetService.delete(pd);
			List<PageData>	varList = rechargesetService.list(page);	//列出RechargeSet列表
			mv.setViewName("reader/rechargeset/rechargeset_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：充值修改
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-20下午4:12:39
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改RechargeSet");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.getString("SORT_NUM")!=null&&!"".equals(pd.getString("SORT_NUM").trim())){
			int SORT_NUM1 = Integer.parseInt(pd.getString("SORT_NUM1"));
			int SORT_NUM = Integer.parseInt(pd.getString("SORT_NUM"));
			if(SORT_NUM1<SORT_NUM){
				rechargesetService.editNoReduce(pd);
				System.out.println("减");
			}else if(SORT_NUM1>SORT_NUM){
				rechargesetService.editNoPlus(pd);
				System.out.println("加");
			}
		}else{
			pd.put("SORT_NUM",pd.getString("SORT_NUM1"));
		}
		pd.put("BOOK_CURRENCY_ALL", (Integer.parseInt(pd.get("BOOK_CURRENCY").toString()))
				+(Integer.parseInt(pd.get("BOOK_CURRENCY_GIFT").toString())));
		if("永久有效".equals(pd.getString("EXP_DATE"))||"".equals(pd.getString("EXP_DATE"))){
			pd.put("EXP_DATE", "");
		}
		pd.put("UPDATE_TIME",DateUtil.getTime());
		pd.put("MONEY", Integer.parseInt(pd.get("MONEY").toString())*100);
		rechargesetService.edit(pd);
//		System.out.println(pd.toString());
		List<PageData>	varList = rechargesetService.list(page);	//列出RechargeSet列表
		for (int i = 0; i < varList.size(); i++) {
			varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
		}
		mv.setViewName("reader/rechargeset/rechargeset_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表RechargeSet");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = rechargesetService.list(page);	//列出RechargeSet列表
			for (int i = 0; i < varList.size(); i++) {
				varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
			}
			mv.setViewName("reader/rechargeset/rechargeset_list");
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
		logBefore(logger, "去新增RechargeSet页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/rechargeset/rechargeset_add");
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
		logBefore(logger, "去修改RechargeSet页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = rechargesetService.findById(pd);	//根据ID读取
			pd.put("MONEY", pd.put("MONEY", Integer.parseInt(pd.get("MONEY").toString())/100));
			mv.setViewName("reader/rechargeset/rechargeset_edit");
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
		logBefore(logger, "批量删除RechargeSet");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				rechargesetService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出RechargeSet到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("充值类型(默认0 普通充值1月卡2年卡)");	//2
			titles.add("免费时间段");	//3
			titles.add("状态 默认0正常1隐藏2删除");	//4
			titles.add("充值金额");	//5
			titles.add("对应书币");	//6
			titles.add("赠送书币");	//7
			titles.add("'总书币");	//8
			titles.add("可用范围");	//9
			titles.add("购买限制");	//10
			titles.add("有效期");	//11
			titles.add("排序");	//12
			titles.add("备注");	//13
			titles.add("创建时间");	//14
			dataMap.put("titles", titles);
			List<PageData> varOList = rechargesetService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("RECHARGE_SET_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("RECHARGE_TYPE").toString());	//2
				vpd.put("var3", varOList.get(i).getString("FREE_TIME"));	//3
				vpd.put("var4", varOList.get(i).get("STATE").toString());	//4
				vpd.put("var5", varOList.get(i).get("MONEY").toString());	//5
				vpd.put("var6", varOList.get(i).get("BOOK_CURRENCY").toString());	//6
				vpd.put("var7", varOList.get(i).get("BOOK_CURRENCY_GIFT").toString());	//7
				vpd.put("var8", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//8
				vpd.put("var9", varOList.get(i).get("USE_SCOPE").toString());	//9
				vpd.put("var10", varOList.get(i).getString("BUY_LIMIT"));	//10
				vpd.put("var11", varOList.get(i).getString("EXP_DATE"));	//11
				vpd.put("var12", varOList.get(i).get("SORT_NUM").toString());	//12
				vpd.put("var13", varOList.get(i).getString("MEMO"));	//13
				vpd.put("var14", varOList.get(i).getString("CREATE_TIME"));	//14
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
	 * 用途：修改充值设置状态（显示/隐藏）
	 * @param @param page
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-18下午6:19:50
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Page page) throws Exception{
		logBefore(logger, "修改ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "update")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDATE_TIME", DateUtil.getTime());
		rechargesetService.update(pd);
		List<PageData>	varList = rechargesetService.list(page);	//列出RechargeSet列表
		mv.setViewName("reader/rechargeset/rechargeset_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	/*-----------------------vip--------------------------*/	
	/**
	 * 
	 * 用途：vip列表
	 * @param @param page
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-20上午10:23:36
	 */
	@RequestMapping(value="/listvip")
	public ModelAndView listvip(Page page){
		logBefore(logger, "列表RechargeSetVip");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = rechargesetService.listvip(page);	//列出RechargeSetVip列表
			for (int i = 0; i < varList.size(); i++) {
				varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
			}
			mv.setViewName("reader/rechargeset/rechargesetvip_list");
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
	 * 用途：删除
	 * @param @param out
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-21上午10:20:22
	 */
	@RequestMapping(value="/deletevip")
	public ModelAndView deletevip(){
		logBefore(logger, "删除RechargeSeVIP");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			rechargesetService.delete(pd);
			List<PageData>	varList = rechargesetService.listvip(page);	//列出RechargeSetVIP列表
			mv.setViewName("reader/rechargeset/rechargesetvip_list");
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
	 * 用途：修改vip充值设置状态（显示/隐藏）
	 * @param @param page
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-21上午10:18:41
	 */
	@RequestMapping(value="/updatevip")
	public ModelAndView updatevip(Page page) throws Exception{
		logBefore(logger, "修改ArticleChapterVIP");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "update")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDATE_TIME", DateUtil.getTime());
		rechargesetService.update(pd);
		List<PageData>	varList = rechargesetService.listvip(page);	//列出RechargeSetVIP列表
		mv.setViewName("reader/rechargeset/rechargesetvip_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	

	/**
	 * 
	 * 用途：去vip新增页面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-20下午4:09:34
	 */
	@RequestMapping(value="/goVipAdd")
	public ModelAndView goVipAdd(){
		logBefore(logger, "去新增RechargeSetVIP页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/rechargeset/rechargesetvip_add");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 
	 * 用途：vip充值新增
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-20下午5:52:33
	 */
	@RequestMapping(value="/savevip")
	public ModelAndView savevip() throws Exception{
		logBefore(logger, "新增RechargeSetVIP");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.getString("SORT_NUM")!=null&&!"".equals(pd.getString("SORT_NUM"))){
			rechargesetService.numberupone(pd);
		}else{
			PageData pdnext = rechargesetService.seachnext();
			if(pdnext!=null){
				pd.put("SORT_NUM",Integer.parseInt(pdnext.get("SORT_NUM").toString())+1);
			}else{
				pd.put("SORT_NUM",1);
			}
		}
		String startTime = pd.getString("startTime");
		String endTime = pd.getString("endTime");
		if(startTime!=null&&!"".equals(startTime)&&endTime!=null&&!"".equals(endTime)){
			pd.put("FREE_TIME",startTime +"-"+ endTime );
		}else {
			pd.put("FREE_TIME","");
		}
		if(pd.getString("EXP_DATE").length()!=10){
			pd.put("EXP_DATE", "");
		}
		pd.put("MONEY", Integer.parseInt(pd.get("MONEY").toString())*100);
		rechargesetService.save(pd);
		System.out.println(pd.toString());
		List<PageData>	varList = rechargesetService.listvip(page);	//列出RechargeSetVIP列表
		for (int i = 0; i < varList.size(); i++) {
			varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
		}
		mv.setViewName("reader/rechargeset/rechargesetvip_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 
	 * 用途：去vip修改页面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-20下午4:09:21
	 */
	@RequestMapping(value="/goVipEdit")
	public ModelAndView goVipEdit(){
		logBefore(logger, "去修改RechargeSetVIP页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = rechargesetService.findById(pd);	//根据ID读取
			if(null!=pd.getString("FREE_TIME")&&!"".equals(pd.getString("FREE_TIME"))){
				String FREE_TIME[] = pd.getString("FREE_TIME").split("-");
				pd.put("startTime", FREE_TIME[0]);
				pd.put("endTime", FREE_TIME[1]);
			}
			pd.put("MONEY",Integer.parseInt(pd.get("MONEY").toString())/100);
			mv.setViewName("reader/rechargeset/rechargesetvip_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 * 用途：vip修改
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-21上午11:00:30
	 */
	@RequestMapping(value="/editvip")
	public ModelAndView editvip() throws Exception{
		logBefore(logger, "修改RechargeSet");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		Page page = new Page();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(pd.getString("SORT_NUM")!=null&&!"".equals(pd.getString("SORT_NUM").trim())){
			int SORT_NUM1 = Integer.parseInt(pd.getString("SORT_NUM1"));
			int SORT_NUM = Integer.parseInt(pd.getString("SORT_NUM"));
			if(SORT_NUM1<SORT_NUM){
				rechargesetService.editNoReduce(pd);
				System.out.println("减");
			}else if(SORT_NUM1>SORT_NUM){
				rechargesetService.editNoPlus(pd);
				System.out.println("加");
			}
		}else{
			pd.put("SORT_NUM",pd.getString("SORT_NUM1"));
		}
		if("永久有效".equals(pd.getString("EXP_DATE"))||"".equals(pd.getString("EXP_DATE"))
				||pd.getString("EXP_DATE").length()!=10){
			pd.put("EXP_DATE", "");
		}
		String startTime = pd.getString("startTime");
		String endTime = pd.getString("endTime");
		if(startTime!=null&&!"".equals(startTime)&&endTime!=null&&!"".equals(endTime)){
			pd.put("FREE_TIME",startTime +"-"+ endTime );
		}else {
			pd.put("FREE_TIME","");
		}
		pd.put("UPDATE_TIME",DateUtil.getTime());
		pd.put("MONEY", Integer.parseInt(pd.get("MONEY").toString())*100);
		rechargesetService.edit(pd);
		System.out.println(pd.toString());
		List<PageData>	varList = rechargesetService.listvip(page);	//列出RechargeSet列表
		for (int i = 0; i < varList.size(); i++) {
			varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
		}
		mv.setViewName("reader/rechargeset/rechargesetvip_list");
		mv.addObject("varList", varList);
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