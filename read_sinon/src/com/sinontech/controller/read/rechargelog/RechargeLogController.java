package com.sinontech.controller.read.rechargelog;

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
import com.sinontech.service.read.rechargelog.RechargeLogService;

/** 
 * 类名称：RechargeLogController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/rechargelog")
public class RechargeLogController extends BaseController {
	
	String menuUrl = "rechargelog/list.do"; //菜单地址(权限用)
	@Resource(name="rechargelogService")
	private RechargeLogService rechargelogService;
	@Resource(name="statisticsBo")
	private StatisticsBo statisticsBo;
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增RechargeLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
//		pd.put("RECHARGELOG_ID", this.get32UUID());	//主键
		rechargelogService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除RechargeLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			rechargelogService.delete(pd);
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
		logBefore(logger, "修改RechargeLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		rechargelogService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 
	 * 用途：充值记录列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-9 下午1:48:47
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表RechargeLog");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(pd.getString("FOLLOWSTATE")!=null){
				if("已关注".equals(pd.getString("FOLLOWSTATE"))){
					pd.put("FOLLOWSTATE1", 1);
				}else if("未关注".equals(pd.getString("FOLLOWSTATE"))){
					pd.put("FOLLOWSTATE1", 0);
				}
				pd.put("FOLLOWSTATE", pd.getString("FOLLOWSTATE"));
			}
			if(pd.getString("RECHARGE_TYPE")!=null){
				if("普通充值".equals(pd.getString("RECHARGE_TYPE"))){
					pd.put("RECHARGE_TYPE1",0);
				}else if("VIP充值".equals(pd.getString("RECHARGE_TYPE"))){
					pd.put("RECHARGE_TYPE1",1);
				}
				pd.put("RECHARGE_TYPE",pd.getString("RECHARGE_TYPE"));
			}
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			//System.out.println(pd.toString());
			page.setPd(pd);
			List<PageData>	varList = rechargelogService.list(page);	//列出RechargeLog列表
			if(varList.size()>0){
				for (int i = 0; i < varList.size(); i++) {
					varList.get(i).put("MONEY", Double.parseDouble(varList.get(i).get("MONEY").toString())/100);
				}
			}
			
			
			PageData pdDayData = statisticsBo.statisticsDayData();
			PageData pdMonthData = statisticsBo.statisticsMonthData();
			PageData pdCountUsers = statisticsBo.statisticsCountUsers();
			PageData pdYesterdayData = statisticsBo.statisticsCountYesterdayData();
			pd.put("recharges", pdCountUsers.get("recharges") );// 总充值数
			pd.put("rechargeTimes", pdCountUsers.get("rechargeTimes") );// 总充值笔数
			
			pd.put("rechargesYesterDay", pdYesterdayData.get("rechargesYesterDay"));// 昨日充值
			pd.put("rechargeTimesYesterDay", pdYesterdayData.get("rechargeTimesYesterDay"));// 昨日总充值笔数
			
			pd.put("rechargesDay", pdDayData.get("rechargesDay"));// 当日充值
			pd.put("rechargeTimesDay", pdDayData.get("rechargeTimesDay"));// 当日总充值笔数
			
			pd.put("rechargesMonth",  pdMonthData.get("rechargesMonth"));// 本月充值
			pd.put("rechargeTimesMonth",  pdMonthData.get("rechargeTimesMonth"));// 当月总充值笔数
			mv.setViewName("reader/rechargelog/rechargelog_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 列表Data
	 * @return
	 */
	@RequestMapping(value="/listData")
	public ModelAndView listData(){
		logBefore(logger, "列表RechargeLog");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String CTIME = getRequest().getParameter("CTIME");//注册时间
		String CTIMESTATE = getRequest().getParameter("CTIMESTATE");//关注状态
		String STATE = getRequest().getParameter("STATE");//充值状态
		try{
			pd = this.getPageData();
			if("".equals(CTIME) && null!= CTIME){
				pd.put("CTIME", CTIME);
			}
			if("".equals(STATE) && null!= STATE){
				pd.put("STATE", STATE);
			}
			pd.put("CTIMESTATE", CTIMESTATE);	
			List<PageData>	varList = rechargelogService.listData(pd);	//列出RechargeLog列表
			PageData pdDayData = statisticsBo.statisticsDayData();
			PageData pdMonthData = statisticsBo.statisticsMonthData();
			PageData pdCountUsers = statisticsBo.statisticsCountUsers();
			PageData pdYesterdayData = statisticsBo.statisticsCountYesterdayData();
			pd.put("recharges", pdCountUsers.get("recharges") );// 总充值数
			pd.put("rechargeTimes", pdCountUsers.get("rechargeTimes") );// 总充值笔数
			
			pd.put("rechargesYesterDay", pdYesterdayData.get("rechargesYesterDay"));// 昨日充值
			pd.put("rechargeTimesYesterDay", pdYesterdayData.get("rechargeTimesYesterDay"));// 昨日总充值笔数
			
			pd.put("rechargesDay", pdDayData.get("rechargesDay"));// 当日充值
			pd.put("rechargeTimesDay", pdDayData.get("rechargeTimesDay"));// 当日总充值笔数
			
			pd.put("rechargesMonth",  pdMonthData.get("rechargesMonth"));// 本月充值
			pd.put("rechargeTimesMonth",  pdMonthData.get("rechargeTimesMonth"));// 当月总充值笔数
			mv.setViewName("read/rechargelog/rechargelog_list");
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
		logBefore(logger, "去新增RechargeLog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/rechargelog/rechargelog_edit");
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
		logBefore(logger, "去修改RechargeLog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = rechargelogService.findById(pd);	//根据ID读取
			mv.setViewName("read/rechargelog/rechargelog_edit");
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
		logBefore(logger, "批量删除RechargeLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				rechargelogService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出RechargeLog到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if(pd.getString("FOLLOWSTATE")!=null){
				String FOLLOWSTATE = new String(pd.getString("FOLLOWSTATE").getBytes("iso-8859-1"),"utf-8");
				if("已关注".equals(FOLLOWSTATE)){
					pd.put("FOLLOWSTATE1", 1);
				}else if("未关注".equals(FOLLOWSTATE)){
					pd.put("FOLLOWSTATE1", 0);
				}
				pd.put("FOLLOWSTATE", FOLLOWSTATE);
			}
			if(pd.getString("RECHARGE_TYPE")!=null){
				String RECHARGE_TYPE = new String(pd.getString("RECHARGE_TYPE").getBytes("iso-8859-1"),"utf-8");
				if("普通充值".equals(RECHARGE_TYPE)){
					pd.put("RECHARGE_TYPE1",0);
				}else if("VIP充值".equals(RECHARGE_TYPE)){
					pd.put("RECHARGE_TYPE1",1);
				}
				pd.put("RECHARGE_TYPE",RECHARGE_TYPE);
			}
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			if(pd.getString("names")!=null&&!"".equals(pd.getString("names"))){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"utf-8");
				pd.put("names", names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("昵称");	//2
			titles.add("管理渠道");	//2
			titles.add("渠道ID");	//3
			titles.add("充值日期");	//4
			titles.add("VIP状态");	//5
			titles.add("充值类型");	//6
			titles.add("有效期");	//8
			titles.add("充值金额");	//9
			titles.add("获得阅读币");//10
			titles.add("平台收益");	//11
			dataMap.put("titles", titles);
			List<PageData>	varOList = rechargelogService.listexcel(pd);	//列出RechargeLog列表
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).get("USER_ID").toString());	//3
				vpd.put("var4", varOList.get(i).getString("CREATE_TIME"));	//4
				vpd.put("var5", varOList.get(i).get("ISVIP").toString());	//5
				vpd.put("var6", varOList.get(i).get("RECHARGE_TYPE").toString());	//6
				vpd.put("var7", varOList.get(i).getString("EXP_DATE"));	//8
				vpd.put("var8", varOList.get(i).get("MONEY").toString());	//9
				vpd.put("var9", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//10
				vpd.put("var10", varOList.get(i).get("INCOME").toString());	//11
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
	 * 用途：指定读者充值记录
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-4 上午10:45:38
	 */
	@RequestMapping(value="/readReechargeLog")
	public ModelAndView readReechargeLog(Page page){
		logBefore(logger, "指定读者充值记录");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			System.out.println(pd.toString()+"-------");
			page.setPd(pd);
			List<PageData>	varList = rechargelogService.listRead(page);	//列出RechargeLog列表
			if(varList.size()>0){
				for (int i = 0; i < varList.size(); i++) {
					varList.get(i).put("MONEY", Integer.parseInt(varList.get(i).get("MONEY").toString())/100);
				}
			}
			pd.put("TIME", "");
			PageData pdCount = rechargelogService.rechargelogData(pd);
			if(null==pdCount.get("recharges")){
				pdCount.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getDay());
			PageData pdDay = rechargelogService.rechargelogData(pd);
			if(null==pdDay.get("recharges")){
				pdDay.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = rechargelogService.rechargelogData(pd);
			if(null==pdMonth.get("recharges")){
				pdMonth.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdYesterday = rechargelogService.rechargelogData(pd);
			if(null==pdYesterday.get("recharges")){
				pdYesterday.put("recharges",0);
			}
			mv.setViewName("reader/user/readReechargeLog");
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
			mv.addObject("pdYesterday", pdYesterday);
			mv.addObject("varList", varList);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：导出指定读者充值记录到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-4 上午11:45:38
	 */
	@RequestMapping(value="/exportExcelRead")
	public ModelAndView exportExcelRead(){
		logBefore(logger, "导出指定读者充值记录到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		String NAME = getRequest().getParameter("NAME");
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
			if(null!=NAME &&!"".equals(NAME)){
				pd.put("NAME", NAME);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("管理渠道");	//1
			titles.add("渠道ID");	//2
			titles.add("充值日期");	//3
			titles.add("VIP状态");	//4
			titles.add("充值类型");	//5
			titles.add("充值额度");	//6
			titles.add("有效期");	//7
			titles.add("充值金额");	//8
			titles.add("获得阅读币");	//9
			titles.add("平台收益");	//10
			titles.add("渠道充值");	//11
			dataMap.put("titles", titles);
			List<PageData>	varOList = rechargelogService.listReadexcel(pd);	//列出RechargeLog列表
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NAME"));	//1
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var4", varOList.get(i).get("ISVIP").toString());	//4
				vpd.put("var5", varOList.get(i).get("RECHARGE_TYPE").toString());	//5
				vpd.put("var6","--");	//6
				vpd.put("var7", varOList.get(i).getString("EXP_DATE"));	//7
				vpd.put("var8", varOList.get(i).get("MONEY").toString());	//8
				vpd.put("var9", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//9
				vpd.put("var10", varOList.get(i).get("INCOME").toString());	//10
				vpd.put("var11", varOList.get(i).get("INCOME").toString());	//11
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
	 * 用途： 渠道读者充值列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午3:33:50
	 */
	@RequestMapping(value="/sysUserReadReechargeLog")
	public ModelAndView sysUserReadReechargeLog(Page page){
		logBefore(logger, "指定读者充值记录");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
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
			List<PageData>	varList = rechargelogService.sysUserlistRechargelog(page);	//列出RechargeLog列表
			pd.put("TIME", "");
			PageData pdCount = rechargelogService.sysUserrechargelogData(pd);
			if(null==pdCount.get("recharges")){
				pdCount.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getDay());
			PageData pdDay = rechargelogService.sysUserrechargelogData(pd);
			if(null==pdDay.get("recharges")){
				pdDay.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = rechargelogService.sysUserrechargelogData(pd);
			if(null==pdMonth.get("recharges")){
				pdMonth.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdYesterday = rechargelogService.sysUserrechargelogData(pd);
			if(null==pdYesterday.get("recharges")){
				pdYesterday.put("recharges",0);
			}
			mv.setViewName("reader/sysuser/sysUserReadReechargeLog");
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
			mv.addObject("pdYesterday", pdYesterday);
			mv.addObject("varList", varList);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：导出 渠道读者充值列表到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午4:26:55
	 */
	@RequestMapping(value="/sysUserReadReechargeExcel")
	public ModelAndView sysUserReadReechargeExcel(){
		logBefore(logger, "导出 渠道读者充值列表到excel");
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
			titles.add("充值日期");	//3
			titles.add("VIP状态");	//4
			titles.add("充值类型");	//5
			titles.add("充值额度");	//6
			titles.add("有效期");	//7
			titles.add("充值金额");	//8
			titles.add("获得阅读币");	//9
			titles.add("平台收益");	//10
			titles.add("累计充值");	//11
			dataMap.put("titles", titles);
			List<PageData>	varOList = rechargelogService.listsysUserReadexcel(pd);	//列出RechargeLog列表
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var2", varOList.get(i).get("ISVIP").toString());	//4
				vpd.put("var3", varOList.get(i).get("RECHARGE_TYPE").toString());	//5
				vpd.put("var4","--");	//6
				vpd.put("var5", varOList.get(i).getString("EXP_DATE"));	//7
				vpd.put("var6", varOList.get(i).get("MONEY").toString());	//8
				vpd.put("var7", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//9
				vpd.put("var8", varOList.get(i).get("INCOME").toString());	//10
				vpd.put("var9", varOList.get(i).get("RECHARGE_MONEY").toString());	//11
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
	
	/*******渠道端*******/
	/**
	 * 
	 * 用途： 渠道端:渠道读者充值列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午3:33:50
	 */
	@RequestMapping(value="/channelReadReechargeLog")
	public ModelAndView channelReadReechargeLog(Page page){
		logBefore(logger, " 渠道端:渠道读者充值列表");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
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
			List<PageData>	varList = rechargelogService.channelRechargelog(page);	//列出RechargeLog列表
			pd.put("TIME", "");
			PageData pdCount = rechargelogService.channelrechargelog(pd);
			if(null==pdCount.get("recharges")){
				pdCount.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getDay());
			PageData pdDay = rechargelogService.channelrechargelog(pd);
			if(null==pdDay.get("recharges")){
				pdDay.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = rechargelogService.channelrechargelog(pd);
			if(null==pdMonth.get("recharges")){
				pdMonth.put("recharges",0);
			}
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdYesterday = rechargelogService.channelrechargelog(pd);
			if(null==pdYesterday.get("recharges")){
				pdYesterday.put("recharges",0);
			}
			mv.setViewName("readerchannel/sysuser/sysUserReadReechargeLog");
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
			mv.addObject("pdYesterday", pdYesterday);
			mv.addObject("varList", varList);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：导出 渠道读者充值列表到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午4:26:55
	 */
	@RequestMapping(value="/channelReadReechargeExcel")
	public ModelAndView channelReadReechargeExcel(){
		logBefore(logger, "渠道端：导出 渠道读者充值列表到excel");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
			titles.add("充值日期");	//3
			titles.add("VIP状态");	//4
			titles.add("充值类型");	//5
			titles.add("充值额度");	//6
			titles.add("有效期");	//7
			titles.add("充值金额");	//8
			titles.add("获得阅读币");	//9
			titles.add("平台收益");	//10
			titles.add("累计充值");	//11
			dataMap.put("titles", titles);
			List<PageData>	varOList = rechargelogService.channelrechargelogexcel(pd);	//列出RechargeLog列表
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//3
				vpd.put("var2", varOList.get(i).get("ISVIP").toString());	//4
				vpd.put("var3", varOList.get(i).get("RECHARGE_TYPE").toString());	//5
				vpd.put("var4","--");	//6
				vpd.put("var5", varOList.get(i).getString("EXP_DATE"));	//7
				vpd.put("var6", varOList.get(i).get("MONEY").toString());	//8
				vpd.put("var7", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//9
				vpd.put("var8", varOList.get(i).get("MONEY").toString());	//10
				vpd.put("var9", varOList.get(i).get("RECHARGE_MONEY").toString());	//11
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