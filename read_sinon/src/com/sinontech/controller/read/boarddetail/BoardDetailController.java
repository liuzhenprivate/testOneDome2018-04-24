package com.sinontech.controller.read.boarddetail;

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
import com.sinontech.service.read.boarddetail.BoardDetailService;
import com.sinontech.util.AppUtil;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.PageData;


/** 
 * 类名称：BoardDetailController
 * 创建人：FH 
 * 创建时间：2018-03-07
 */
@Controller
@RequestMapping(value="/boarddetail")
public class BoardDetailController extends BaseController {
	
	String menuUrl = "boarddetail/list.do"; //菜单地址(权限用)
	@Resource(name="boarddetailService")
	private BoardDetailService boarddetailService;
	

	/**
	 * 
	 * 用途：列表
	 * ModelAndView
	 * @atrue liuzhen
	 * date：2018-3-7 下午10:19:02
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表BoardDetail");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = boarddetailService.list(page);	//列出BoardDetail列表
			for (int i = 0; i < varList.size(); i++) {
				PageData pd1 = boarddetailService.seachboarddetailCATEGORY(varList.get(i));
				varList.get(i).put("CATEGORY", pd1.getString("CATEGORY"));
			}
			mv.setViewName("reader/board/boarddetail_list");
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
	 * 用途：去榜单详情页面
	 * ModelAndView
	 * @atrue liuzhen
	 * date：2018-3-7 下午11:34:48
	 */
	@RequestMapping(value="/goBoardDetail")
	public ModelAndView goBoardDetail(){
		logBefore(logger, "去榜单详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/board/boarddetail");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 
	 * 用途：去手动添加页面
	 * ModelAndView
	 * @atrue liuzhen
	 * date：2018-3-8 上午9:25:35
	 */
	@RequestMapping(value="/goAddBooks")
	public ModelAndView goAddBooks(Page page){
		logBefore(logger, "去手动添加页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			/*List<PageData> list = boarddetailService.list(page);
			if(list.size()>0){
				String ARTICLE_ID = "";
				for (int i = 0; i < list.size(); i++) {
					ARTICLE_ID = ARTICLE_ID + list.get(i).get("ARTICLE_ID").toString()+",";
				}
				pd.put("ARTICLE_ID", ARTICLE_ID.subSequence(0, ARTICLE_ID.length()-1));
			}*/
			List<PageData> varList = boarddetailService.addBooksListAll(pd);
			System.out.println(pd.toString());
			mv.setViewName("reader/board/addBooks");
			mv.addObject("pd", pd);
			mv.addObject("varList", varList);
			System.out.println(pd.toString());
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 * 用途：新增手动添加书籍
	 * @return
	 * @throws Exception ModelAndView
	 * @atrue liuzhen
	 * date：2018-3-8 上午11:34:55
	 */
	@RequestMapping(value="/booksAdd")
	public ModelAndView booksAdd() throws Exception{
		logBefore(logger, "新增BoardDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("SORT_RULE", 1);
			PageData pd1 = boarddetailService.seachNextSort(pd);
			if(pd1==null){
				pd.put("SORT", 1);
			}else{
				
				pd.put("SORT", pd1.get("SORT").toString());
			}
			pd.put("CREATE_TIME",DateUtil.getTime());
			if(!"".equals(pd.getString("articleId"))&&null!=pd.getString("articleId")){
				String[] str = pd.getString("articleId").split(",");
				for (int i = 0; i < str.length; i++) {
					pd.put("ARTICLE_ID",str[i]);
					boarddetailService.save(pd);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 
	 * 用途：去批量编辑页面
	 * @return ModelAndView
	 * @atrue liuzhen
	 * date：2018-3-8 上午10:48:02
	 */
	@RequestMapping(value="/batchEdit")
	public ModelAndView batchEdit(Page page){
		logBefore(logger, "去批量编辑页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			page.setPd(pd);
			List<PageData>	varList = boarddetailService.list(page);	//列出BoardDetail列表
			for (int i = 0; i < varList.size(); i++) {
				PageData pd1 = boarddetailService.seachboarddetailCATEGORY(varList.get(i));
				varList.get(i).put("CATEGORY", pd1.getString("CATEGORY"));
			}
			mv.setViewName("reader/board/batchdetail_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
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
		logBefore(logger, "新增BoardDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BOARDDETAIL_ID", this.get32UUID());	//主键
		boarddetailService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除BoardDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			boarddetailService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 
	 * 用途：手动修改
	 * @return
	 * @throws Exception ModelAndViewTODO
	 * @author liuzhen
	 * date：2018-3-8 下午2:26:02
	 */
	@RequestMapping(value="/funedit")
	public ModelAndView funedit() throws Exception{
		logBefore(logger, "修改BoardDetail");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if(!"".equals(pd.getString("EDIT"))&&null!=pd.getString("EDIT")){
				if(!"".equals(pd.getString("BOARD_DETAIL_ID"))&&null!=pd.getString("BOARD_DETAIL_ID")){
					String[] str = pd.getString("BOARD_DETAIL_ID").split(",");
					if("del".equals(pd.getString("EDIT"))){
						for (int i = 0; i < str.length; i++) {
							pd.put("BOARD_DETAIL_ID", str[i]);
							boarddetailService.delete(pd);
						}
					}else{
						PageData pd1 = new PageData();
						if("top".equals(pd.getString("EDIT"))){
							pd.put("length", str.length);
							//根据需要制定的书籍数量，先增加榜单里书籍的排序
							boarddetailService.goUp(pd);
							for (int i = 0; i < str.length; i++) {
								pd.put("SORT", i+1);
								pd.put("BOARD_DETAIL_ID", str[i]);
								PageData pd2 = boarddetailService.findById(pd);
								if(!"1".equals(pd2.get("SORT_RULE").toString())){
									pd.put("SORT_RULE", 2);
								}else{
									pd.put("SORT_RULE", 1);
								}
								boarddetailService.edit(pd);
							}
						}else if("dow".equals(pd.getString("EDIT"))){
							//获取最后榜单最后一条数据
							pd1 = boarddetailService.seachNextSort(pd);
							for (int i = 0; i < str.length; i++) {
								pd.put("SORT", i+Integer.parseInt(pd1.get("SORT").toString())+1);
								pd.put("BOARD_DETAIL_ID", str[i]);
								PageData pd2 = boarddetailService.findById(pd);
								if(!"1".equals(pd2.get("SORT_RULE").toString())){
									pd.put("SORT_RULE", 2);
								}else{
									pd.put("SORT_RULE", 1);
								}
								boarddetailService.edit(pd);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 
	 * @purpose：还原手动修改的是数据为自动匹配
	 * @param pd
	 * @return
	 * @return ModelAndView
	 * @author liuzhen
	 * @Time：2018-4-23 下午3:26:02
	 */
	@RequestMapping(value = "/reduction")
	public ModelAndView reduction(){
		logBefore(logger, "还原手动修改的是数据为自动匹配");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SORT_RULE", 0);
		try {
			PageData pd2 = boarddetailService.findById(pd);
			if(pd2!=null){
				pd.put("SORT", pd2.get("SORT"));
				boarddetailService.edit(pd);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改BoardDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		boarddetailService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增BoardDetail页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("boarddetail/boarddetail/boarddetail_edit");
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
		logBefore(logger, "去修改BoardDetail页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = boarddetailService.findById(pd);	//根据ID读取
			mv.setViewName("boarddetail/boarddetail/boarddetail_edit");
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
		logBefore(logger, "批量删除BoardDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				boarddetailService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出BoardDetail到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("ID");	//1
			titles.add("榜单id");	//2
			titles.add("书籍Id");	//3
			titles.add("榜单详情类型（0总榜 1男 2女）");	//4
			titles.add("排序");	//5
			titles.add("创建时间");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = boarddetailService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("BOARD_DETAIL_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("BOARD_ID").toString());	//2
				vpd.put("var3", varOList.get(i).get("ARTICLE_ID").toString());	//3
				vpd.put("var4", varOList.get(i).get("BOARD_TYPE").toString());	//4
				vpd.put("var5", varOList.get(i).get("SORT").toString());	//5
				vpd.put("var6", varOList.get(i).getString("CREATE_TIME"));	//6
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