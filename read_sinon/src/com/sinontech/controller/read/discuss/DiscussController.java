package com.sinontech.controller.read.discuss;

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
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.article.ArticleService;
import com.sinontech.service.read.discuss.DiscussService;

/** 
 * 类名称：DiscussController
 * 创建人：FH 
 * 创建时间：2018-03-02
 */
@Controller
@RequestMapping(value="/discuss")
public class DiscussController extends BaseController {
	
	String menuUrl = "discuss/list.do"; //菜单地址(权限用)
	@Resource(name="discussService")
	private DiscussService discussService;
	@Resource(name="articleService")
	private ArticleService articleService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Discuss");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("DISCUSS_ID", this.get32UUID());	//主键
		discussService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Discuss");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			System.out.println(pd.toString());
			discussService.delete(pd);
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
		logBefore(logger, "修改Discuss");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		discussService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 
	 * 用途：修改书籍显示人数
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author liuzhen
	 * 时间 2018-3-6 下午4:24:14
	 */
	@RequestMapping(value="/updateReads")
	public ModelAndView updateReads() throws Exception{
		logBefore(logger, "修改书籍显示人数");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		discussService.editDisplayReads(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/**
	 * 
	 * 用途：列表
	 * @param @param page
	 * @param @return
	 * @return ModelAndView
	 * @author liuzhen
	 * 时间 2018-3-6 下午2:21:46
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Discuss");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(null!=getRequest().getParameter("STATE")){
				String STATE = pd.getString("STATE");
				if("未上架".equals(STATE)){
					pd.put("STATE1", '0');
				}else if("已上架".equals(STATE)){
					pd.put("STATE1", '1');
				}else if("已下架".equals(STATE)){
					pd.put("STATE1", '2');
				}else if("已删除".equals(STATE)){
					pd.put("STATE1", "-1");
				}
				pd.put("STATE",STATE);
			}
			if(null!=getRequest().getParameter("CHANNEL_TYPE")){
				String CHANNEL_TYPE = pd.getString("CHANNEL_TYPE");
				if("男".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '0');
				}else if("女".equals(CHANNEL_TYPE)){
					pd.put("CHANNEL_TYPE1", '1');
				}
				pd.put("CHANNEL_TYPE",CHANNEL_TYPE);
			}
			if(null!=getRequest().getParameter("IS_HOT")){
				String IS_HOT = pd.getString("IS_HOT");
				if("否".equals(IS_HOT)){
					pd.put("IS_HOT1", '0');
				}else if("是".equals(IS_HOT)){
					pd.put("IS_HOT1", '1');
				}
				pd.put("IS_HOT",IS_HOT);
			}
			if(null!=getRequest().getParameter("SERIAL_STATE")){
				String SERIAL_STATE = pd.getString("SERIAL_STATE");
				if("连载中".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '0');
				}else if("已完结".equals(SERIAL_STATE)){
					pd.put("SERIAL_STATE1", '1');
				}
				pd.put("SERIAL_STATE",SERIAL_STATE);
			}
			if(null!=getRequest().getParameter("PAY_WAY")){
				String PAY_WAY = pd.getString("PAY_WAY");
				if("阅读币购买阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '0');
				}else if("免费阅读".equals(PAY_WAY)){
					pd.put("PAY_WAY1", '1');
				}
				pd.put("PAY_WAY",PAY_WAY);
			}
			if(null!=getRequest().getParameter("CATEGORY")){
				String CATEGORY = pd.getString("CATEGORY");
				if(!"全部类型".equals(CATEGORY)){
					pd.put("CATEGORY1",CATEGORY);
				}
				pd.put("CATEGORY",CATEGORY);
			}
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("startTime", timebegin);
				pd.put("endTime", timeend);
			}
			page.setPd(pd);
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			for (int i = 0; i < varList.size(); i++) {
				PageData pd1 = discussService.articleCountdiscuss(varList.get(i));
				varList.get(i).put("Countdiscuss", pd1.get("Countdiscuss"));
			}
			mv.setViewName("reader/discuss/book_list");
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
	 * 用途：去书评列表
	 * @param @param page
	 * @param @return
	 * @return ModelAndView
	 * @author liuzhen
	 * 时间 2018-3-6 下午2:21:59
	 */
	@RequestMapping(value="/goDiscuss")
	public ModelAndView goDiscuss(Page page){
		logBefore(logger, "去Discuss页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String sendtime = String.valueOf(pd.get("sendtime"));
			if(null!=sendtime && !"".equals(sendtime) &&!"null".equals(sendtime)){
				String[] times = sendtime.split(" - ");
				String timebegin = times[0];
				String timeend = times[1];
				pd.put("startTime", timebegin);
				pd.put("endTime", timeend);
			}
			page.setPd(pd);
			List<PageData>	varList = discussService.list(page);	//列出Article列表
			System.out.println(varList.toString());
			mv.setViewName("reader/discuss/discuss_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 
	 * 用途：去书评批量编辑
	 * @param @param page
	 * @param @return
	 * @return ModelAndView
	 * @author liuzhen
	 * 时间 2018-3-6 下午2:22:25
	 */
	@RequestMapping(value="/batchDiscuss_edit")
	public ModelAndView batchDiscuss_edit(Page page){
		logBefore(logger, "去Discuss页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			List<PageData>	varList = discussService.list(page);	//列出Article列表
			mv.setViewName("reader/discuss/discussEdit_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 * 用途：查看书评内容
	 * @param @return
	 * @return ModelAndView
	 * @author liuzhen
	 * 时间 2018-3-6 下午2:22:52
	 */
	@RequestMapping(value="/goLook")
	public ModelAndView goLook(){
		logBefore(logger, "去修改Discuss页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = discussService.findById(pd);	//根据ID读取
			mv.setViewName("reader/discuss/discuss_content");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 
	 * 用途：批量删除书评
	 * @param @param out
	 * @return void
	 * @author liuzhen
	 * 时间 2018-3-6 下午2:23:15
	 */
	@RequestMapping(value="/batchDelete")
	public void batchDelete(PrintWriter out){
		logBefore(logger, "批量删除Discuss");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(!"".equals(pd.getString("DISCUSS_ID"))&&null!=pd.getString("DISCUSS_ID")){
				String[] str = pd.getString("DISCUSS_ID").split(",");
				for (int i = 0; i < str.length; i++) {
					pd.put("DISCUSS_ID", str[i]);
					discussService.delete(pd);
				}
				out.write("success");
				out.close();
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Discuss页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("read/discuss/discuss_edit");
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
		logBefore(logger, "去修改Discuss页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = discussService.findById(pd);	//根据ID读取
			mv.setViewName("read/discuss/discuss_edit");
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
		logBefore(logger, "批量删除Discuss");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				discussService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Discuss到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("书籍ID");	//1
			titles.add("读者ID");	//2
			titles.add("点赞数");	//3
			titles.add("几星");	//4
			titles.add("评论内容");	//5
			titles.add("状态");	//6
			titles.add("平台ID");	//7
			titles.add("评论时间");	//8
			titles.add("审核时间");	//9
			titles.add("审核备注说明");	//10
			titles.add("审核人");	//11
			dataMap.put("titles", titles);
			List<PageData> varOList = discussService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("ARTICLE_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("USERID").toString());	//2
				vpd.put("var3", varOList.get(i).get("ZANS").toString());	//3
				vpd.put("var4", varOList.get(i).get("LEVELS").toString());	//4
				vpd.put("var5", varOList.get(i).getString("CONTENT"));	//5
				vpd.put("var6", varOList.get(i).get("STATE").toString());	//6
				vpd.put("var7", varOList.get(i).getString("USER_CODE"));	//7
				vpd.put("var8", varOList.get(i).getString("CREATE_TIME"));	//8
				vpd.put("var9", varOList.get(i).getString("CHECK_TIME"));	//9
				vpd.put("var10", varOList.get(i).getString("MEMO"));	//10
				vpd.put("var11", varOList.get(i).getString("ADMIN"));	//11
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