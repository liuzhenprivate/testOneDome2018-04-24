package com.sinontech.controller.read.user;

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
import com.sinontech.service.read.user.WXUserService;
import com.sinontech.service.system.user.UserService;

/** 
 * 类名称：WXUserController
 * 创建人：FH 
 * 创建时间：2017-10-30
 */
@Controller
@RequestMapping(value="/wxuser")
public class WXUserController extends BaseController {
	
	String menuUrl = "wxuser/list.do"; //菜单地址(权限用)
	@Resource(name="wxuserService")
	private WXUserService wxuserService;
	@Resource(name="statisticsBo")
	private StatisticsBo statisticsBo;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name="rechargelogService")
	private RechargeLogService rechargelogService;
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增WXUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("WXUSER_ID", this.get32UUID());	//主键
		wxuserService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除WXUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			wxuserService.delete(pd);
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
		logBefore(logger, "修改WXUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		wxuserService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表WXUser");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = wxuserService.list(page);	//列出WXUser列表
			
			PageData pdcount = wxuserService.countUsers(pd);
			PageData pdpayUser = wxuserService.countUserPay(pd);
			PageData pdcountFee = wxuserService.countFeeUsers(pd);
			
			pd.put("TIME", DateUtil.getDay());
			PageData pdcountFeeDay = wxuserService.countFeeUsers(pd);
			PageData pdday = wxuserService.countUsers(pd);
			PageData pdpayUserDay = wxuserService.countUserPay(pd);
			
			pd.put("TIME", DateUtil.getMM());
			PageData pdcountFeeMonth = wxuserService.countFeeUsers(pd);
			PageData pdmonth = wxuserService.countUsers(pd);
			PageData pdpayUserMonth = wxuserService.countUserPay(pd);
			
			pd.put("usersAll", pdcount.get("USERID"));//总人数
			pd.put("usersDay", pdday.get("USERID"));//今天阅读人数
			pd.put("usersMonth",  pdmonth.get("USERID"));//本月阅读人数
			
			pd.put("SysUserFans",  pdcountFee.get("USERID"));//渠道粉丝
			pd.put("WXFansFeeDay",  pdcountFeeDay.get("USERID"));//今天渠道粉丝
			pd.put("WXFansFeeMonth",  pdcountFeeMonth.get("USERID"));//本月渠道粉丝
			
			pd.put("SysUserFansFee",  pdpayUser.get("UsersFee"));//渠道付费用户
			pd.put("SysUserFansFeeDay",  pdpayUserDay.get("UsersFee"));//当天渠道付费用户
			pd.put("SysUserFansFeeMonth",  pdpayUserMonth.get("UsersFee"));//本月渠道付费用户
			
			mv.setViewName("reader/user/user_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
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
		logBefore(logger, "去新增WXUser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/user/user_edit");
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
		logBefore(logger, "去修改WXUser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = wxuserService.findById(pd);	//根据ID读取
			mv.setViewName("reader/user/user_edit");
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
		logBefore(logger, "批量删除WXUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				wxuserService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出WXUser到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("渠道");	//2
			titles.add("微信公众号ID  外键");	//3
			titles.add("用户编号ID");	//4
			titles.add("微信OPENID");	//5
			titles.add("微信昵称");	//6
			titles.add("累计充值金额");	//7
			titles.add("0 未知  1男  2女");	//8
			titles.add("省份");	//9
			titles.add("城市");	//10
			titles.add("国家");	//11
			titles.add("微信头像");	//12
			titles.add("用户特权信息");	//13
			titles.add("UNIONID");	//14
			titles.add("关注时间");	//15
			titles.add("书币");	//16
			titles.add("等级");	//17
			dataMap.put("titles", titles);
			List<PageData> varOList = wxuserService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("USER_ID").toString());	//1
				vpd.put("var2", varOList.get(i).getString("CHANNEL_ID"));	//2
				vpd.put("var3", varOList.get(i).getString("WEHCHAT_ID"));	//3
				vpd.put("var4", varOList.get(i).getString("USER_CODE"));	//4
				vpd.put("var5", varOList.get(i).getString("OPENID"));	//5
				vpd.put("var6", varOList.get(i).getString("NICKNAME"));	//6
				vpd.put("var7", varOList.get(i).get("RECHARGE_MONEY").toString());	//7
				vpd.put("var8", varOList.get(i).get("SEX").toString());	//8
				vpd.put("var9", varOList.get(i).getString("PROVINCE"));	//9
				vpd.put("var10", varOList.get(i).getString("CITY"));	//10
				vpd.put("var11", varOList.get(i).getString("COUNTRY"));	//11
				vpd.put("var12", varOList.get(i).getString("HEADIMGURL"));	//12
				vpd.put("var13", varOList.get(i).getString("PRIVILEGE"));	//13
				vpd.put("var14", varOList.get(i).getString("UNIONID"));	//14
				vpd.put("var15", varOList.get(i).getString("CTIME"));	//15
				vpd.put("var16", varOList.get(i).get("BOOK_CURRENCY").toString());	//16
				vpd.put("var17", varOList.get(i).getString("LEVEL"));	//17
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
	 * 用途：去读者详情
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-25上午10:09:31
	 */
	@RequestMapping(value="/readDetail")
	public ModelAndView readDetail(Page page){
		logBefore(logger, "去readNewsAll页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		try {
			mv.setViewName("reader/user/read_details");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 * 用途：去读者信息总览页面
	 * @author 刘振
	 * ModelAndView
	 * 2018-1-2 下午1:39:12
	 */
	@RequestMapping(value="/readNewsAll")
	public ModelAndView readNewsAll(Page page){
		logBefore(logger, "去读者信息总览页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			PageData wxpd = wxuserService.findById(pd);
			page.setPd(wxpd);
			List<PageData> varlist = wxuserService.listUser(page);
			mv.setViewName("reader/user/readerNewsAll");
			mv.addObject("varlist", varlist);
			mv.addObject("pd", pd);
			mv.addObject("wxpd", wxpd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	
	/**
	 * 
	 * 用途：去渠道读者详情页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 上午10:06:27
	 */
	@RequestMapping(value="/UserReadDetail")
	public ModelAndView UserReadDetail(){
		logBefore(logger, "去UserReadDetail页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/sysuser/UserReadDetail");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 
	 * 用途：去渠道读者信息总览页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 上午10:15:25
	 */
	@RequestMapping(value="/sysUserReadNewsAll")
	public ModelAndView sysUserReadNewsAll(){
		logBefore(logger, "去渠道读者信息总览页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			PageData wxpd = wxuserService.findById(pd);
			mv.setViewName("reader/sysuser/sysUserReadNewsAll");
			mv.addObject("pd", pd);
			mv.addObject("wxpd", wxpd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/***********渠道端**************/
	/**
	 * 
	 * 用途：渠道端：去渠道读者信息总览页面
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-23 下午2:35:46
	 */
	@RequestMapping(value="/channelReadNewsAll")
	public ModelAndView channelReadNewsAll(){
		logBefore(logger, "去渠道读者信息总览页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			PageData wxpd = wxuserService.findByIdChannel(pd);
			mv.setViewName("readerchannel/sysuser/sysUserReadNewsAll");
			mv.addObject("pd", pd);
			mv.addObject("wxpd", wxpd);
		} catch (Exception e) {
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