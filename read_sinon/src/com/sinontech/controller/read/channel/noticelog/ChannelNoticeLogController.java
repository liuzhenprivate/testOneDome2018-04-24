package com.sinontech.controller.read.channel.noticelog;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.http.HttpRequest;
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
import com.sinontech.util.DateUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.noticelog.NoticeLogService;

/** 
 * 类名称：NoticeLogController
 * 创建人：FH 
 * 创建时间：2017-11-01
 */
@Controller
@RequestMapping(value="/channelnoticelog")
public class ChannelNoticeLogController extends BaseController {
	
	String menuUrl = "noticelog/list.do"; //菜单地址(权限用)
	@Resource(name="noticelogService")
	private NoticeLogService noticelogService;
	
	/**
	 * 
	 * 用途：公告列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-22 下午5:50:21
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "渠道公告列表NoticeLog");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		try{
			pd = this.getPageData();
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				String sendtime = String.valueOf(pd.get("sendtime"));
				if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
					String[] times = sendtime.split(" - ");
					String timebegin = times[0];
					String timeend = times[1];
					pd.put("sendTimeBegin", timebegin);
					pd.put("sendTimeEnd", timeend);
				}
				pd.put("TIME", DateUtil.getDay());
				page.setPd(pd);
				List<PageData>	varList = noticelogService.sysUserlist(page);	//列出NoticeLog列表
				System.out.println(varList.toString());
				mv.setViewName("readerchannel/noticelog/noticelog_list");
				mv.addObject("varList", varList);
//				mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			}
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出NoticeLog到excel");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("公告标题");	//1
			titles.add("公告内容");	//2
			titles.add("状态");	//3
			titles.add("推送时间");	//4
			titles.add("管理员ID");	//5
			titles.add("创建时间");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = noticelogService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TITLE"));	//1
				vpd.put("var2", varOList.get(i).getString("CONTENT"));	//2
				vpd.put("var3", varOList.get(i).get("STATE").toString());	//3
				vpd.put("var4", varOList.get(i).getString("SEND_TIME"));	//4
				vpd.put("var5", varOList.get(i).getString("ADMIN_ID"));	//5
				vpd.put("var6", varOList.get(i).getString("CTIME"));	//6
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
