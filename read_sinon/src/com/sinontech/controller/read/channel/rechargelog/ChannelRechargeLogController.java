package com.sinontech.controller.read.channel.rechargelog;

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
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.entity.system.User;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.service.read.countday.CountDayService;
import com.sinontech.service.read.countmonth.CountMonthService;
import com.sinontech.service.read.rechargelog.RechargeLogService;

/** 
 * 类名称：RechargeLogController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/channelrechargelog")
public class ChannelRechargeLogController extends BaseController {
	
	String menuUrl = "rechargelog/list.do"; //菜单地址(权限用)
	@Resource(name="rechargelogService")
	private RechargeLogService rechargelogService;
	@Resource(name = "countdayService")
	private CountDayService countdayService;
	@Resource(name = "countmonthService")
	private CountMonthService countmonthService;
	/**
	 * 
	 * 用途：去渠道充值页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-22 下午4:06:03
	 */
	@RequestMapping(value = "/sysuserRechargeDetails")
	public ModelAndView sysuserRechargeDetails(Page page) throws Exception {
		ModelAndView mv = this.getModelAndView();
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
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try {
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				if("-1".equals(pd.getString("RECHARGE_TYPE"))){
					pd.put("RECHARGE_TYPE", "");
				}
				page.setPd(pd);
				List<PageData> varList = rechargelogService.listsysuserRecharge(page);
				pd.put("TIME", "");
				PageData pdTotal = rechargelogService.sysUserRecharge(pd);
				pd.put("TIME", DateUtil.getDay());
				PageData pdDay = rechargelogService.sysUserRecharge(pd);
				pd.put("TIME", DateUtil.getMM());
				PageData pdMonth = rechargelogService.sysUserRecharge(pd);
				pd.put("TIME", DateUtil.getAfterDayDates("-1"));
				PageData pdYester = rechargelogService.sysUserRecharge(pd);
				mv.setViewName("readerchannel/rechargelog/sysuserRechargeDetails");
				mv.addObject("pdTotal", pdTotal);
				mv.addObject("pdDay", pdDay);
				mv.addObject("pdMonth", pdMonth);
				mv.addObject("pdYester", pdYester);
				mv.addObject("varList", varList);
				mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
			}
			mv.addObject("pd", pd);
			System.out.println(pd.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 
	 * @purpose：导出充值记录到excel
	 * @return
	 * @return ModelAndView
	 * @author liuzhen
	 * @Time：2018-4-8 下午5:46:37
	 */
	@RequestMapping(value="/sysuserRechargeExcel")
	public ModelAndView sysuserRechargeExcel(){
		logBefore(logger, "导出充值记录到excel");
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
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try{
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				if(null!=NAME &&!"".equals(NAME)){
					pd.put("NAME", NAME);
				}
				Map<String,Object> dataMap = new HashMap<String,Object>();
				List<String> titles = new ArrayList<String>();
				titles.add("昵称");	//1
				titles.add("读者ID");	//2
				titles.add("充值日期");	//3
				titles.add("关注状态");	//4
				titles.add("关联渠道");	//5
				titles.add("有效期");	//7
				titles.add("充值金额");	//8
				titles.add("获得阅读币");	//9
				titles.add("充值状态");	//10
				dataMap.put("titles", titles);
				List<PageData> varOList = rechargelogService.listsysuserRechargeExcel(pd);	//列出RechargeLog列表
				List<PageData> varList = new ArrayList<PageData>();
				for(int i=0;i<varOList.size();i++){
					PageData vpd = new PageData();
					vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//1
					vpd.put("var2", varOList.get(i).get("USERID").toString());	//2
					vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	//3
					if("1".equals(varOList.get(i).get("FOLLOWSTATE").toString())){
						vpd.put("var4", "已关注");	//4
					}else{
						vpd.put("var4", "未关注");	//4
					}
					vpd.put("var5", varOList.get(i).getString("NAME"));	//5
					if("".equals(varOList.get(i).getString("EXP_DATE"))||null==varOList.get(i).getString("EXP_DATE")){
						vpd.put("var6","永久有效" );	//7
					}else{
						vpd.put("var6",varOList.get(i).getString("EXP_DATE"));	//7
					}
					vpd.put("var7", varOList.get(i).get("MONEY").toString());	//8
					vpd.put("var8", varOList.get(i).get("BOOK_CURRENCY_ALL").toString());	//9
					vpd.put("var9","充值成功");	//10
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView();
				mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：去渠道充值统计页面   当天
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-24 上午11:09:33
	 */
	@RequestMapping(value = "/goStatisticsDay")
	public ModelAndView goStatisticsDay(Page page) {
		logBefore(logger, "去渠道充值统计页面 rechargeStatisticsDay   当天");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try {
			if (null != user) {
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				page.setPd(pd);
				List<PageData> list = new ArrayList<PageData>();
				List<PageData> varList = countdayService.channelcountDaylist(page);
				if(varList.size()>0){
					for (int i = 0; i <varList.size(); i++) {
						PageData pd1 = varList.get(i);
						String date = varList.get(i).getString("COUNT_DAY");
						pd.put("COUNT_DAY", date);
						pd.put("COUNT_MONTH", date.substring(0, 7));
						PageData pdday = countdayService.channelSumToDay(pd);
						if(null!=pdday){
							pd1.put("toDaySum", pdday.get("RECHARGES").toString());
						}
						PageData pdmonth = countmonthService.channelSumToMonth(pd);
						if(null!=pdmonth){
							pd1.put("toMonthSum", pdmonth.get("RECHARGES").toString());
						}
						list.add(pd1);
					}
				}
				mv.setViewName("readerchannel/rechargelog/rechargeStatisticsDay");
				mv.addObject("varList", list);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("pd", pd);
		return mv;
	}	
	
	
	
	/**
	 * 
	 * 用途：导出渠道充值列表到excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-22 下午4:06:12
	 */
	@RequestMapping(value="/goStatisticsDayExcel")
	public ModelAndView goStatisticsDayExcel(){
		logBefore(logger, "导出渠道充值列表到excel");
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
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try{
			if(null!=user){
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("充值时间");	//1
			titles.add("充值人数");	//2
			titles.add("充值笔数");	//3
			titles.add("充值金额");	//4
			titles.add("月充值总量");	//5
			titles.add("总充值量");	//6
			dataMap.put("titles", titles);
//			System.out.println(pd.toString());
			List<PageData> list = new ArrayList<PageData>();
			List<PageData> varList = countdayService.channelcountDaylistExcel(pd);
			if(varList.size()>0){
				for (int i = 0; i <varList.size(); i++) {
					PageData pd1 = varList.get(i);
					String date = varList.get(i).getString("COUNT_DAY");
					pd.put("COUNT_DAY", date);
					pd.put("COUNT_MONTH", date.substring(0, 7));
					PageData pdday = countdayService.channelSumToDay(pd);
					if(null!=pdday){
						pd1.put("toDaySum", pdday.get("RECHARGES").toString());
					}
					PageData pdmonth = countmonthService.channelSumToMonth(pd);
					if(null!=pdmonth){
						pd1.put("toMonthSum", pdmonth.get("RECHARGES").toString());
					}
					list.add(pd1);
				}
			}
			List<PageData> List = new ArrayList<PageData>();
			for(int i=0;i<list.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", list.get(i).getString("COUNT_DAY"));	//1
				vpd.put("var2", list.get(i).get("RECHARGEUSERS").toString());	//2
				vpd.put("var3", list.get(i).get("RECHARGETIMES").toString());	//3
				vpd.put("var4", list.get(i).get("RECHARGES").toString());	//4
				vpd.put("var5", list.get(i).get("toMonthSum").toString());	//5
				vpd.put("var6", list.get(i).get("toDaySum").toString());	//6
				List.add(vpd);
			}
			dataMap.put("varList", List);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：去渠道充值统计页面   本月
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-24 上午11:09:33
	 */
	@RequestMapping(value="/goStatisticsMonth")
	public ModelAndView goStatisticsMonth(Page page){
		logBefore(logger, "去渠道充值统计页面 rechargeStatisticsMonth  本月");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try {
			if (null != user) {
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				page.setPd(pd);
				List<PageData> varList = new ArrayList<PageData>();
				List<PageData> list = countmonthService.channelcountMonthlist(page);
				if(list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						PageData pd1 = list.get(i);
						pd.put("COUNT_MONTH", list.get(i).getString("COUNT_MONTH"));
						PageData pdmonth = countmonthService.channelMonthSum(pd);
						if(null!=pdmonth){
							pd1.put("toMonthSum", pdmonth.get("RECHARGES").toString());
						}
						varList.add(pd1);
					}
				}
				mv.addObject("varList", varList);
				mv.setViewName("readerchannel/rechargelog/rechargeStatisticsMonth");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		mv.addObject("pd", pd);
		return mv;
	}
	/**
	 * 
	 * @purpose：导出渠道充值列表到excel（月统计）
	 * @return
	 * @return ModelAndView
	 * @author liuzhen
	 * @Time：2018-3-25 下午4:05:17
	 */
	@RequestMapping(value="/goStatisticsMonthExcel")
	public ModelAndView goStatisticsMonthExcel(){
		logBefore(logger, "导出渠道充值列表到excel");
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
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try{
			if(null!=user){
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("充值时间");	//1
			titles.add("充值人数");	//2
			titles.add("充值笔数");	//3
			titles.add("充值金额");	//4
			titles.add("总充值量");	//5
			dataMap.put("titles", titles);
//			System.out.println(pd.toString());
			List<PageData> varList = new ArrayList<PageData>();
			List<PageData> list = countmonthService.channelcountMonthlistExcel(pd);
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					PageData pd1 = list.get(i);
					pd.put("COUNT_MONTH", list.get(i).getString("COUNT_MONTH"));
					PageData pdmonth = countmonthService.channelMonthSum(pd);
					if(null!=pdmonth){
						pd1.put("toMonthSum", pdmonth.get("RECHARGES").toString());
					}
					varList.add(pd1);
				}
			}
			List<PageData> List = new ArrayList<PageData>();
			for(int i=0;i<varList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varList.get(i).getString("COUNT_MONTH"));	//1
				vpd.put("var2", varList.get(i).get("RECHARGEUSERS").toString());	//2
				vpd.put("var3", varList.get(i).get("RECHARGETIMES").toString());	//3
				vpd.put("var4", varList.get(i).get("RECHARGES").toString());	//4
				vpd.put("var5", varList.get(i).get("toMonthSum").toString());	//5
				List.add(vpd);
			}
			dataMap.put("varList", List);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
			}
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
