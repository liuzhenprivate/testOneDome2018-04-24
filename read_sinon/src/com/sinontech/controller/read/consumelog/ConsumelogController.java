package com.sinontech.controller.read.consumelog;

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
import com.sinontech.service.read.consumelog.ConsumelogService;

/** 
 * 类名称：ConsumelogController
 * 创建人：FH 
 * 创建时间：2017-11-08
 */
@Controller
@RequestMapping(value="/consumelog")
public class ConsumelogController extends BaseController {
	
	String menuUrl = "consumelog/list.do"; //菜单地址(权限用)
	@Resource(name="consumelogService")
	private ConsumelogService consumelogService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Consumelog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("CONSUMELOG_ID", this.get32UUID());	//主键
		consumelogService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Consumelog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			consumelogService.delete(pd);
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
		logBefore(logger, "修改Consumelog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		consumelogService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 
	 * 用途：消费管理列表
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-10 上午11:31:49
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Consumelog");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(pd.getString("STATE")!=null){
				String STATE = new String(pd.getString("STATE").getBytes("iso-8859-1"),"UTF-8");
				pd.put("STATE",STATE );
			}
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			pd.put("TIME","");
			PageData pdConsume = consumelogService.pdConsumelog(pd);
			pd.put("TIME", DateUtil.getDay());
			PageData pdConsumeDay = consumelogService.pdConsumelog(pd);
			pd.put("TIME", DateUtil.getMM());
			PageData pdConsumeMonth = consumelogService.pdConsumelog(pd);
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdConsumeYDay = consumelogService.pdConsumelog(pd);
			page.setPd(pd);
			List<PageData>	varList = consumelogService.sumlist(page);	//列出Consumelog列表
			mv.setViewName("reader/consumelog/consumelog_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("pdConsume", pdConsume);
			mv.addObject("pdConsumeDay", pdConsumeDay);
			mv.addObject("pdConsumeMonth", pdConsumeMonth);
			mv.addObject("pdConsumeYDay", pdConsumeYDay);
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
		logBefore(logger, "去新增Consumelog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/consumelog/consumelog_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	

	/**
	 * 
	 * @purpose：去阅读币想详情页面
	 * @return
	 * @return ModelAndView
	 * @author liuzhen
	 * @Time：2018-3-21 下午3:54:00
	 */
	@RequestMapping(value="/goCURRENCY")
	public ModelAndView goCURRENCY(){
		logBefore(logger, "去阅读币想详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/consumelog/consumelog_detile");
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
		logBefore(logger, "去修改Consumelog页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = consumelogService.findById(pd);	//根据ID读取
			mv.setViewName("read/consumelog/consumelog_edit");
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
		logBefore(logger, "批量删除Consumelog");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				consumelogService.deleteAll(ArrayDATA_IDS);
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
	
	/**
	 * 
	 * 用途：导出消费记录excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-10 下午3:00:21
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Consumelog到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("昵称");	//1
			titles.add("平台ID");	//2
			titles.add("关联渠道");	//3
			titles.add("最近消费日期");	//4
			titles.add("关注状态");	//5
			titles.add("累计阅读币");	//6
			titles.add("剩余阅读币");	//7
			titles.add("使用阅读币");	//8
			titles.add("签到阅读币");	//9
			titles.add("最近消费");	//10
			dataMap.put("titles", titles);
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("sendTimeBegin", timebegin);
				pd.put("sendTimeEnd", timeend);
			}
			if(pd.getString("names")!=null&&!"".equals(pd.getString("names"))){
				String names = new String(pd.getString("names").getBytes("ISO-8859-1"),"UTF-8");
				pd.put("names", names);
			}
			List<PageData> varOList = consumelogService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("NICKNAME"));	//1
				vpd.put("var2", varOList.get(i).get("USER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("NAME"));	//3
				vpd.put("var4", varOList.get(i).get("CREATE_TIME"));	//4
				if(null!=varOList.get(i).get("FOLLOWSTATE")&&!"".equals(varOList.get(i).get("FOLLOWSTATE"))){
					if("0".equals(varOList.get(i).get("FOLLOWSTATE").toString())){
						vpd.put("var5", "未关注");	//5
					}else{
						vpd.put("var5", "已关注");	//5
					}
				}else{
					vpd.put("var5", "未关注");	//5
				}
				vpd.put("var6", varOList.get(i).get("CUMULATIVE_CURRENCY").toString());	//6
				vpd.put("var7", varOList.get(i).get("BOOKCURRENCYS").toString());	//7
				vpd.put("var8", varOList.get(i).get("BOOK_CURRENCY").toString());	//8
				vpd.put("var9", varOList.get(i).get("QBOOK_CURRENCY").toString());	//9
				vpd.put("var10", varOList.get(i).getString("ARTICLE_NAME")+"\r\n"
						+varOList.get(i).getString("CHAPTER_NAME"));	//10
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
	 * 用途：渠道读者消费记录信息
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午4:36:05
	 */
	@RequestMapping(value="/sysUserReadConsumelog")
	public ModelAndView sysUserReadConsumelog(Page page){
		logBefore(logger, "渠道读者消费记录信息");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
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
			pd.put("TIME","");
			PageData pdConsume = consumelogService.sysUserpdConsumelog(pd);
			pd.put("TIME", DateUtil.getDay());
			PageData pdConsumeDay = consumelogService.sysUserpdConsumelog(pd);
			pd.put("TIME", DateUtil.getMM());
			PageData pdConsumeMonth = consumelogService.sysUserpdConsumelog(pd);
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdConsumeYDay = consumelogService.sysUserpdConsumelog(pd);
			page.setPd(pd);
			List<PageData>	varList = consumelogService.sysUserReadConsumelist(page);	//列出sysUserReadConsumeLog列表
			mv.setViewName("reader/sysuser/sysUserReadConsumeLog");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("pdConsume", pdConsume);
			mv.addObject("pdConsumeDay", pdConsumeDay);
			mv.addObject("pdConsumeMonth", pdConsumeMonth);
			mv.addObject("pdConsumeYDay", pdConsumeYDay);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：导出渠道读者消费记录信息到Excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午5:02:27
	 */
	@RequestMapping(value="/sysUserReadConsumeExcel")
	public ModelAndView sysUserReadConsumeExcel(){
		logBefore(logger, "导出渠道消费信息excel");
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
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("消费日期");	//2
			titles.add("消费阅读币");	//3
			titles.add("消费书籍");	//4
			titles.add("消费章节");	//5
			dataMap.put("titles", titles);
			List<PageData>	varOList = consumelogService.listsysUserReadConsumeExce(pd);;	//列出渠道账户信息
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				System.out.println(varOList.get(i).getString("CREATE_TIME"));
				
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//4
				vpd.put("var2",varOList.get(i).get("CONSUMES").toString());	//3
				vpd.put("var3", varOList.get(i).getString("ARTICLE_NAME"));	//4
				vpd.put("var4", varOList.get(i).getString("CHAPTER_NAME"));	//5
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
	 * 用途：去读者消费记录
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-22 下午2:58:09
	 */
	@RequestMapping(value="/readerConsumeLog")
	public ModelAndView readerConsumeLog(Page page){
		logBefore(logger, "去读者消费记录");
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
			List<PageData> varList = consumelogService.listUser(page);
			pd.put("TIME", "");
			PageData pdCount = consumelogService.readerChapter(pd);
			if(null==pdCount.get("FEES")){
				pdCount.put("FEES",0);
			}
			pd.put("TIME", DateUtil.getDay());
			PageData pdDay = consumelogService.readerChapter(pd);
			if(null==pdDay.get("FEES")){
				pdDay.put("FEES",0);
			}
			pd.put("TIME", DateUtil.getMM());
			PageData pdMonth = consumelogService.readerChapter(pd);
			if(null==pdMonth.get("FEES")){
				pdMonth.put("FEES",0);
			}
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdYesterday = consumelogService.readerChapter(pd);
			if(null==pdYesterday.get("FEES")){
				pdYesterday.put("FEES",0);
			}
			mv.setViewName("reader/user/readerChapterLog");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("pdCount", pdCount);
			mv.addObject("pdDay", pdDay);
			mv.addObject("pdMonth", pdMonth);
			mv.addObject("pdYesterday", pdYesterday);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	@RequestMapping(value="/exportExcelRead")
	public ModelAndView exportExcelRead(){
		logBefore(logger, "导出ArticleChapterlog到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		String ARTICLE_NAME = getRequest().getParameter("ARTICLE_NAME");
		String USERID = getRequest().getParameter("USERID");
		String OPENID = getRequest().getParameter("OPENID");
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
		if(null!=OPENID &&!"null".equals(OPENID)&&!"".equals(OPENID)){
			pd.put("OPENID", OPENID);
		}
		try{
			if(null!=ARTICLE_NAME &&!"".equals(ARTICLE_NAME)){
				pd.put("ARTICLE_NAME", ARTICLE_NAME);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("关联渠道");	//1
			titles.add("渠道ID");	//2
			titles.add("消费日期");	//3
			titles.add("消费阅读币");//4
			titles.add("消费书籍");	//5
			titles.add("消费章节");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = consumelogService.listUserexcel(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("NAME").toString());	//1
				vpd.put("var2", varOList.get(i).get("USERID").toString());	//2
				vpd.put("var3", varOList.get(i).get("CREATE_TIME").toString());	//3
				vpd.put("var4", varOList.get(i).get("FEE").toString());	//4
				vpd.put("var5", varOList.get(i).get("ARTICLE_NAME").toString());	//5
				vpd.put("var6", varOList.get(i).get("CHAPTER_NAME").toString());	//6
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
	
	
	/******渠道端*******/
	
	/**
	 * 
	 * 用途：渠道端：渠道读者消费记录信息
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午4:36:05
	 */
	@RequestMapping(value="/channelReadConsumelog")
	public ModelAndView channelReadConsumelog(Page page){
		logBefore(logger, "渠道端：渠道读者消费记录信息");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
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
			pd.put("TIME","");
			PageData pdConsume = consumelogService.channelpdConsumelog(pd);
			pd.put("TIME", DateUtil.getDay());
			PageData pdConsumeDay = consumelogService.channelpdConsumelog(pd);
			pd.put("TIME", DateUtil.getMM());
			PageData pdConsumeMonth = consumelogService.channelpdConsumelog(pd);
			pd.put("TIME", DateUtil.getAfterDayDates("-1"));
			PageData pdConsumeYDay = consumelogService.channelpdConsumelog(pd);
			page.setPd(pd);
			List<PageData>	varList = consumelogService.channelReadConsumelist(page);	//列出channelReadConsumeLog列表
			mv.setViewName("readerchannel/sysuser/sysUserReadConsumeLog");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject("pdConsume", pdConsume);
			mv.addObject("pdConsumeDay", pdConsumeDay);
			mv.addObject("pdConsumeMonth", pdConsumeMonth);
			mv.addObject("pdConsumeYDay", pdConsumeYDay);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 
	 * 用途：渠道端：导出渠道读者消费记录信息到Excel
	 * @author 刘振
	 * ModelAndView
	 * 时间:2018-1-16 下午5:02:27
	 */
	@RequestMapping(value="/channelReadConsumeExcel")
	public ModelAndView channelReadConsumeExcel(){
		logBefore(logger, "渠道端：导出渠道消费信息excel");
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
			if(null!=pd.getString("names")){
				String names = new String(pd.getString("names").getBytes("iso-8859-1"),"UTF-8");
				pd.put("names",names);
			}
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("消费日期");	//2
			titles.add("消费阅读币");	//3
			titles.add("消费书籍");	//4
			titles.add("消费章节");	//5
			dataMap.put("titles", titles);
			List<PageData>	varOList = consumelogService.channelReadConsumeExce(pd);;	//列出渠道账户信息
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				System.out.println(varOList.get(i).getString("CREATE_TIME"));
				
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//4
				vpd.put("var2",varOList.get(i).get("CONSUMES").toString());	//3
				vpd.put("var3", varOList.get(i).getString("ARTICLE_NAME"));	//4
				vpd.put("var4", varOList.get(i).getString("CHAPTER_NAME"));	//5
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
