package com.sinontech.controller.read.countday;

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

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.sinontech.entity.system.User;
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.countday.CountDayService;
import com.sinontech.service.read.user.WXUserService;

/** 
 * 类名称：CountDayController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/countday")
public class CountDayController extends BaseController {
	
	String menuUrl = "countday/list.do"; //菜单地址(权限用)
	@Resource(name="countdayService")
	private CountDayService countdayService;
	@Resource(name="statisticsBo")
	private StatisticsBo statisticsBo;
	@Resource(name="wxuserService")
	private WXUserService wxuserService;
	
	/**
	 * 
	 * 用途： 充值图标数据查询
	 * @param @return
	 * @param @throws Exception   
	 * @return Object  
	 * @author 刘振
	 * @date 2017-12-5下午5:49:39
	 */
	@RequestMapping(value="/date")
	@ResponseBody
	public Object date() throws Exception{
		logBefore(logger, "图表数据date");
		PageData pd = new PageData();
		List<PageData>	varList = null;
		try{
			pd = this.getPageData();
			varList = countdayService.date(pd);	//列出CountDay列表'
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return varList;
	}
	
	/**
	 * 
	 * @purpose：渠道 充值图标数据查询
	 * @return
	 * @throws Exception
	 * @return Object
	 * @author liuzhen
	 * @Time：2018-3-25 下午4:27:49
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
				varList = countdayService.sysuserdate(pd);	//列出CountDay列表'
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
		logBefore(logger, "新增CountDay");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("COUNTDAY_ID", this.get32UUID());	//主键
		countdayService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除CountDay");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			countdayService.delete(pd);
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
		logBefore(logger, "修改CountDay");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		countdayService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表CountDay");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = countdayService.list(page);	//列出CountDay列表
			mv.setViewName("read/countday/countday_list");
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
	 * 用途：Data列表
	 * @param @param page
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-5下午5:49:51
	 */
	@RequestMapping(value="/listData")
	public ModelAndView listData(Page page){
		logBefore(logger, "列表CountDay");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("startTime", timebegin);
				pd.put("endTime", timeend);
			}
			page.setPd(pd);
			PageData pdcount = wxuserService.countUsers(pd);
			pd.put("TIME", DateUtil.getDay());
			PageData pdday = wxuserService.countUsers(pd);
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdysday = wxuserService.countUsers(pd);
			pd.put("TIME", DateUtil.getMM());
			PageData pdmonth = wxuserService.countUsers(pd);
			
			PageData pdDayData = statisticsBo.statisticsDayData();
			PageData pdMonthData = statisticsBo.statisticsMonthData();
			PageData pdCountUsers = statisticsBo.statisticsCountUsers();
			PageData pdYesterdayData = statisticsBo.statisticsCountYesterdayData();
			PageData pdSysUser = statisticsBo.statisticsSysUser();
			PageData pdUserFans = statisticsBo.statisticsUserFans();
			List<PageData> listDayData = countdayService.countTimeData(page);
			pd.put("usersAll", pdcount.get("USERID"));//总人数
			pd.put("recharges", pdCountUsers.get("recharges") );//总充值数
			pd.put("rechargeTimes", pdCountUsers.get("rechargeTimes") );//总充值笔数
			
			pd.put("countUserid", pdYesterdayData.get("countUserid"));//昨天充值人数
			pd.put("SumUserIds", pdYesterdayData.get("SumUserIds"));//昨天复充人数
			pd.put("userYesterDay", pdysday.get("USERID"));//昨天阅读人数
			pd.put("rechargesYesterDay", pdYesterdayData.get("rechargesYesterDay"));//昨天充值数
			pd.put("rechargeTimesYesterDay", pdYesterdayData.get("rechargeTimesYesterDay"));//昨天充值数
			
			pd.put("usersDay", pdday.get("USERID"));//今天阅读人数
			pd.put("rechargesDay", pdDayData.get("rechargesDay"));//今天充值数
			pd.put("rechargeTimesDay", pdDayData.get("rechargeTimesDay"));//今天充值笔数
			
			pd.put("usersMonth",  pdmonth.get("USERID"));//本月阅读人数
			pd.put("rechargesMonth",  pdMonthData.get("rechargesMonth"));//本月充值数
			pd.put("rechargeTimesMonth",  pdMonthData.get("rechargeTimesMonth"));//本月充值笔数
			
			pd.put("countSysUsers",  pdSysUser.get("countSysUsers"));// 渠道数
			pd.put("SysUserFans",  pdUserFans.get("SysUserFans"));//渠道粉丝
			pd.put("SysUserFansFee",  pdUserFans.get("SysUserFansFee"));//渠道付费粉丝
			
			pd.put("listDayData",listDayData);// 统计startTime到endTime时间之内每天的数据
			
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			mv.setViewName("system/admin/default");
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
		logBefore(logger, "去新增CountDay页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/countday/countday_edit");
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
		logBefore(logger, "去修改CountDay页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = countdayService.findById(pd);	//根据ID读取
			mv.setViewName("read/countday/countday_edit");
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
		logBefore(logger, "批量删除CountDay");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				countdayService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出CountDay到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("渠道ID");	//2
			titles.add("微信公众号id");	//3
			titles.add("会员总数");	//4
			titles.add("充值总数");	//5
			titles.add("消费阅读币");	//6
			titles.add("消费人民币");	//7
			titles.add("统计日期");	//8
			titles.add("创建日期");	//9
			titles.add("更新时间");	//10
			dataMap.put("titles", titles);
			List<PageData> varOList = countdayService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("COUNT_DAY_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).get("WEBCHAT_ID").toString());	//3
				vpd.put("var4", varOList.get(i).get("USERS").toString());	//4
				vpd.put("var5", varOList.get(i).get("RECHARGES").toString());	//5
				vpd.put("var6", varOList.get(i).get("CONSUMES").toString());	//6
				vpd.put("var7", varOList.get(i).get("MONEY").toString());	//7
				vpd.put("var8", varOList.get(i).getString("COUNT_DAY"));	//8
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
	
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excelOut")
	public ModelAndView excelOut(){
		logBefore(logger, "导出CountDay到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String sendtime = String.valueOf(pd.get("sentTime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("startTime", timebegin);
				pd.put("endTime", timeend);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("时间");	//1
			titles.add("充值人数");	//2
			titles.add("充值笔数");	//3
			titles.add("充值金额");	//4
			titles.add("月累计");	//5
			titles.add("总累计");	//6
			dataMap.put("titles", titles);
			System.out.println(pd.toString());
			List<PageData> varOList = countdayService.countTimeDataoutExcel(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("RECHARGETIME"));	//1
				vpd.put("var2", varOList.get(i).get("RECHARGEUSERS").toString());	//2
				vpd.put("var3", varOList.get(i).get("RECHARGETIMES").toString());	//3
				vpd.put("var4", varOList.get(i).get("RECHARGEDAYALL").toString());	//4
				vpd.put("var5", varOList.get(i).get("RECHARGEMONTHALL").toString());	//5
				vpd.put("var6", varOList.get(i).get("RECHARGEALL").toString());	//6
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