package com.sinontech.controller.read.remittancelog;

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
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.countmonth.CountMonthService;
import com.sinontech.service.read.remittancelog.RemittanceLogService;

/** 
 * 类名称：RemittanceLogController
 * 创建人：FH 
 * 创建时间：2017-11-08
 */
@Controller
@RequestMapping(value="/remittancelog")
public class RemittanceLogController extends BaseController {
	
	String menuUrl = "remittancelog/list.do"; //菜单地址(权限用)
	@Resource(name="remittancelogService")
	private RemittanceLogService remittancelogService;
	@Resource(name = "countmonthService")
	private CountMonthService countmonthService;
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增RemittanceLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("REMITTANCELOG_ID", this.get32UUID());	//主键
		remittancelogService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除RemittanceLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			remittancelogService.delete(pd);
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
		logBefore(logger, "修改RemittanceLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		remittancelogService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表RemittanceLog");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			//累计打款
			PageData countpd = new PageData();
			PageData countrs = this.remittancelogService.sumAll(countpd);
			
			//累计充值
			PageData countUsers = countmonthService.countUsersAll(pd);
			
			//平台余额
			int balance = Integer.parseInt(String.valueOf(countUsers.get("RECHARGES")))
					-Integer.parseInt(String.valueOf(countrs.get("MONEY")));
			//当月月份YYYY-MM
			String month = DateUtil.getMM();
			PageData monthpd = new PageData();
			monthpd.put("MONTH", month);
			PageData monthrs = this.remittancelogService.sumAll(monthpd);
			
			//昨天的日期
			String yesday = DateUtil.getAfterDayDates("-1");
			PageData ysdaypd = new PageData();
			ysdaypd.put("sendTimeBegin", yesday);
			PageData ysdayrs = this.remittancelogService.sumAll(ysdaypd);
			
			
			pd = this.getPageData();
			String ctime = String.valueOf(pd.get("ctime"));
			if(null!=ctime && !"".equals(ctime) &&!"null".equals(ctime)){
				String[] times = ctime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			page.setPd(pd);
			List<PageData>	varList = remittancelogService.list(page);	//列出RemittanceLog列表
			mv.setViewName("reader/remittancelog/remittancelog_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("balance", balance);
			mv.addObject("monthrs", monthrs);
			mv.addObject("countrs", countrs);
			mv.addObject("ysdayrs", ysdayrs);
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
		logBefore(logger, "去新增RemittanceLog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/remittancelog/remittancelog_edit");
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
		logBefore(logger, "去修改RemittanceLog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = remittancelogService.findById(pd);	//根据ID读取
			mv.setViewName("read/remittancelog/remittancelog_edit");
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
		logBefore(logger, "批量删除RemittanceLog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				remittancelogService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出RemittanceLog到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("渠道名称");	//1
			titles.add("所属公司");	//2
			titles.add("申请时间");	//3
			titles.add("银行名称");	//4
			titles.add("银行账号");	//5
			titles.add("开户人姓名");	//6
			titles.add("提现金额");	//7
			titles.add("累计提现");	//8
			titles.add("状态");	//9
			dataMap.put("titles", titles);
			List<PageData> varOList = remittancelogService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", String.valueOf(varOList.get(i).get("NAME")));	//1
				vpd.put("var2", String.valueOf(varOList.get(i).get("COMPANY")));	//2
				vpd.put("var3", String.valueOf(varOList.get(i).get("CREATE_TIME")));	//3
				vpd.put("var4", String.valueOf(varOList.get(i).get("OPEN_CARD")));	//4
				vpd.put("var5", String.valueOf(varOList.get(i).get("CARD_NUM")));	//5
				vpd.put("var6", String.valueOf(varOList.get(i).get("ACCOUNT_NAME")));	//6
				vpd.put("var7", String.valueOf(varOList.get(i).get("MONEY")));	//7
				vpd.put("var8", String.valueOf(varOList.get(i).get("WITHDRAW")));	//8
				String state =String.valueOf(varOList.get(i).get("STATE"));
				String strstate="未打款";
				if("1".equals(state)){
					strstate="已打款";
				}else if("2".equals(state)){
					strstate="冻结";
				}else 
				vpd.put("var9", strstate);	//9
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