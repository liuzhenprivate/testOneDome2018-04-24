package com.sinontech.controller.read.singlog;

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

import com.sinontech.bo.read.StatisticsBo;
import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.singlog.SingLogService;
import com.sinontech.service.read.user.WXUserService;

/** 
 * 类名称：SingLogController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/singlog")
public class SingLogController extends BaseController {
	
	String menuUrl = "singlog/list.do"; //菜单地址(权限用)
	@Resource(name="singlogService")
	private SingLogService singlogService;
	@Resource(name="statisticsBo")
	private StatisticsBo statisticsBo;
	@Resource(name="wxuserService")
	private WXUserService wxuserService;
	
	
	/**
	 * 读者签到详情
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/readsSingData")
	public ModelAndView readsSingData(Page page){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			
			
			pd.put("CREATE_TIME", DateUtil.getMM());
			PageData pdMonth = singlogService.readsSingsMonth(pd);
			pd.put("SINGSMONTHUSER", pdMonth.get("SINGSMONTHUSER")); //本月签到次数
			pd.put("BOOK_CURRENCYS", pdMonth.get("BOOK_CURRENCYS")); //本月签到获得阅读币
			

			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增SingLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SINGLOG_ID", this.get32UUID());	//主键
		singlogService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除SingLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			singlogService.delete(pd);
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
		logBefore(logger, "修改SingLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		singlogService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表SingLog");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try{
			PageData pdSingLogUsers = statisticsBo.countSingLogUser();
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = singlogService.list(page);//列出SingLog列表
			pd.put("SINGLOGUSERS", pdSingLogUsers.get("SINGLOGUSERS"));//累计签到人数
			pd.put("CURRENCY", pdSingLogUsers.get("CURRENCY"));//累计签到阅读币
			pd.put("CURRENCYDAY", pdSingLogUsers.get("CURRENCYDAY"));//累每天计签到阅读币
			pd.put("SINGUSERSDAY", pdSingLogUsers.get("SINGUSERSDAY"));//每天签到人数
			pd.put("CURRENCYMONTH", pdSingLogUsers.get("CURRENCYMONTH"));//累计每月签到阅读币
			pd.put("SINGUSERSMONTH", pdSingLogUsers.get("SINGUSERSMONTH"));//累计每月签到人数
			mv.setViewName("read/singlog/singlog_list");
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
		logBefore(logger, "去新增SingLog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/singlog/singlog_edit");
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
		logBefore(logger, "去修改SingLog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = singlogService.findById(pd);	//根据ID读取
			mv.setViewName("read/singlog/singlog_edit");
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
		logBefore(logger, "批量删除SingLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				singlogService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出SingLog到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("用户id");	//2
			titles.add("渠道");	//3
			titles.add("微信公众号ID 外键");	//4
			titles.add("记录类型 默认1每日签到2领取奖励");	//5
			titles.add("对应奖励次数  对应奖励设置表的times字段");	//6
			titles.add("获取的书币");	//7
			titles.add("获取时间");	//8
			titles.add("签到月份");	//9
			dataMap.put("titles", titles);
			List<PageData> varOList = singlogService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("SIGN_LOG_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("CHANNEL_ID"));	//3
				vpd.put("var4", varOList.get(i).getString("WEHCHAT_ID"));	//4
				vpd.put("var5", varOList.get(i).get("LOG_TYPE").toString());	//5
				vpd.put("var6", varOList.get(i).get("TIMES").toString());	//6
				vpd.put("var7", varOList.get(i).get("BOOK_CURRENCY").toString());	//7
				vpd.put("var8", varOList.get(i).getString("CREATE_TIME"));	//8
				vpd.put("var9", varOList.get(i).getString("SIGN_MONTH"));	//9
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
	 * 用途：去读者签到记录
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-4 下午3:16:01
	 */
	@RequestMapping(value="/readerSignLog")
	public ModelAndView readerSignLog(Page page){
		logBefore(logger, "去读者签到记录");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			page.setPd(pd);
			List<PageData> varList = singlogService.listUser(page);
			pd.put("TIME", "");
			PageData pdCount = singlogService.readerSing(pd);
			if(null==pdCount.get("BOOK_CURRENCYS")){
				pdCount.put("BOOK_CURRENCYS",0);
			}
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = singlogService.readerSing(pd);
			if(null==pdMonth.get("BOOK_CURRENCYS")){
				pdMonth.put("BOOK_CURRENCYS",0);
			}
			pd.put("TIME", DateUtil.getDay());
			PageData pdDay = singlogService.readerSingDay(pd);
			
			List<PageData> wxvarList = wxuserService.readLists(page);
			
			System.out.println(pd.toString());
			mv.setViewName("reader/user/readerSignLog");
			mv.addObject("varList", varList);
			mv.addObject("wxvarList", wxvarList);
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 * 用途：读者管理：签到记录excel导出
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-8 下午3:50:22
	 */
	@RequestMapping(value="/exportExcelRead")
	public ModelAndView exportExcelRead(){
		logBefore(logger, "导出SingLog到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		String COMPANY = getRequest().getParameter("COMPANY");
		String USERID = getRequest().getParameter("USERID");
		String USER_CODE = getRequest().getParameter("USER_CODE");
		pd = this.getPageData();
		String sendtime = String.valueOf(pd.get("sendtime"));
		if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
			String[] times = sendtime.split(" - ");
			String timebegin = times[0];
			String timeend = times[1];
			pd.put("sendTimeBegin", timebegin);
			pd.put("sendTimeEnd", timeend);
		}
		
		if(null!=USERID &&!"null".equals(USERID)&&!"".equals(USERID)){
			pd.put("USERID", USERID);
		}
		if(null!=USER_CODE &&!"null".equals(USER_CODE)&&!"".equals(USER_CODE)){
			pd.put("USER_CODE", USER_CODE);
		}
		try{
			if(null!=COMPANY &&!"".equals(COMPANY)){
				String COMPANY1 = new String(COMPANY.getBytes("ISO-8859-1"),"UTF-8");
				pd.put("COMPANY", COMPANY1);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("签到渠道");	//1
			titles.add("渠道id");	//2
			titles.add("签到日期");	//3
			titles.add("签到阅读币");	//4
			titles.add("渠道签到累计");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = singlogService.listReadexcel(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("COMPANY"));	//1
				vpd.put("var2", varOList.get(i).get("USERID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var4", varOList.get(i).get("BOOK_CURRENCY").toString());	//4
				vpd.put("var5", varOList.get(i).get("SBC").toString());	//5
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
	 * 用途：渠道读者签到记录
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午5:14:44
	 */
	@RequestMapping(value="/sysUserReadSignLog")
	public ModelAndView sysUserReadSignLog(Page page){
		logBefore(logger, "渠道读者签到记录");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			page.setPd(pd);
			List<PageData> varList = singlogService.sysUserReadSignlist(page);
			pd.put("TIME", "");
			PageData pdCount = singlogService.sysUserReadSing(pd);
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = singlogService.sysUserReadSing(pd);
			System.out.println(pd.toString());
			PageData pdDay = singlogService.sysUserReadSingDay(pd); //渠道读者：获取当前渠道是否签到
			List<PageData> wxvarList = wxuserService.readLists(page);//渠道读者：获取渠道读者的渠道
			
//			System.out.println(pd.toString());
			mv.setViewName("reader/sysuser/sysUserReadSignLog");
			mv.addObject("varList", varList);
			mv.addObject("wxvarList", wxvarList);
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 * 用途：导出渠道读者签到记录到Excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午6:00:42
	 */
	@RequestMapping(value="/sysUserReadSingExcel")
	public ModelAndView sysUserReadSingExcel(){
		logBefore(logger, "导出渠道读者签到记录到Excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
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
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("签到日期");	//3
			titles.add("签到阅读币");	//4
			titles.add("累计签到");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = singlogService.listsysUserReadSingexcel(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var2", varOList.get(i).get("BOOK_CURRENCY").toString());	//4
				vpd.put("var3", varOList.get(i).get("SBC").toString());	//5
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
	
	/******渠道端*****/
	
	/**
	 * 
	 * 用途：渠道端：渠道读者签到记录
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-23 下午4:40:32
	 */
	@RequestMapping(value="/channelReadSignLog")
	public ModelAndView channelReadSignLog(Page page){
		logBefore(logger, "渠道端：渠道读者签到记录");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			page.setPd(pd);
			List<PageData> varList = singlogService.channelReadSignlistlistPage(page);
			for (int i = 0; i < varList.size(); i++) {
				PageData pd1 = singlogService.countSum(varList.get(i));
				varList.get(i).put("SBC", pd1.get("SUMBOOK_CURRENCY"));
			}
			pd.put("TIME", "");
			PageData pdCount = singlogService.channelReadSing(pd);
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = singlogService.channelReadSing(pd);
			pd.put("TIME", DateUtil.getDay());
			PageData pdDay = singlogService.channelReadSingDay(pd); //渠道读者：获取当前渠道是否签到
			
			mv.setViewName("readerchannel/sysuser/sysUserReadSignLog");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 * 用途：渠道端：导出渠道读者签到记录到Excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-23 下午4:40:24
	 */
	@RequestMapping(value="/channelReadSingExcel")
	public ModelAndView channelReadSingExcel(){
		logBefore(logger, "渠道端：导出渠道读者签到记录到Excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
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
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("签到日期");	//3
			titles.add("签到阅读币");	//4
			titles.add("累计签到");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = singlogService.channelUserReadSingexcel(pd);
			for (int i = 0; i < varOList.size(); i++) {
				PageData pd1 = singlogService.countSum(varOList.get(i));
				varOList.get(i).put("SBC", pd1.get("SUMBOOK_CURRENCY"));
			}
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var2", varOList.get(i).get("BOOK_CURRENCY").toString());	//4
				vpd.put("var3", varOList.get(i).get("SBC").toString());	//5
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