package com.sinontech.controller.read.pic;

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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.PathUtil;
import com.sinontech.util.Tools;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.pic.PicService;
import com.sinontech.service.read.piccategory.PicCategoryService;

/** 
 * 类名称：PicController
 * 创建人：FH 
 * 创建时间：2018-01-04
 */
@Controller
@RequestMapping(value="/pic")
public class PicController extends BaseController {
	
	String menuUrl = "pic/list.do"; //菜单地址(权限用)
	@Resource(name="picService")
	private PicService picService;
	@Resource(name="piccategoryService")
	private PicCategoryService piccategoryService;
	
	@RequestMapping(value="/addcategory")
	public void addcategory(PrintWriter out){
		logBefore(logger, "新增图片分类addcategory");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData> list = this.piccategoryService.listAll(pd);
			if(null!=list && list.size()>0){
				out.write("fail");
			}else{
				this.piccategoryService.save(pd);
				List<PageData> list1 = this.piccategoryService.listAll(pd);
				String piccid = "";
				if(null!=list1 && list1.size()>0){
					PageData picc = list1.get(0);
					piccid = String.valueOf(picc.get("PIC_CATEGORY_ID"));
				}
				out.write(piccid);
			}
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 新增
	 */
	@RequestMapping(value = "/save2")
	public ModelAndView save2(@RequestParam(required = false) MultipartFile file,HttpServletRequest request) throws Exception {
		logBefore(logger, "新增Pictures");
		String PIC_CATEGORY_ID = request.getParameter("PIC_CATEGORY_ID");
		System.out.println("PIC_CATEGORY_ID--"+PIC_CATEGORY_ID);
		Map<String, String> map = new HashMap<String, String>();
		String ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
			} else {
				System.out.println("上传失败");
			}
			PIC_CATEGORY_ID="1";
			pd.put("PIC_CATEGORY_ID", PIC_CATEGORY_ID);  
			pd.put("PIC_NAME", fileName); // 文件名
			pd.put("PIC_URL", Const.FILEPATHIMG+ffile + "/" + fileName); // 路径
			pd.put("CREATE_TIME", Tools.date2Str(new Date())); // 创建时间
			picService.save(pd);
		}
		ModelAndView mv = this.getModelAndView();
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request) throws Exception {
		logBefore(logger, "新增Pictures");
		String PIC_CATEGORY_ID = request.getParameter("PIC_CATEGORY_ID");
		String picurl = request.getParameter("picurl");
		System.out.println("PIC_CATEGORY_ID--"+PIC_CATEGORY_ID+"==picurl="+picurl);
		
		if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			String[] pics = picurl.split(",");
			for(String pic:pics){
				PageData pd = new PageData();
				pd.put("PIC_CATEGORY_ID", PIC_CATEGORY_ID);  
				pd.put("PIC_NAME", "1"); // 文件名
				pd.put("PIC_URL", pic); // 路径
				pd.put("CREATE_TIME", Tools.date2Str(new Date())); // 创建时间
				picService.save(pd);
			}
		}
		ModelAndView mv = this.getModelAndView();
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value = "/upfile")
	public void upfile(@RequestParam(required = false) MultipartFile file,PrintWriter out ) throws Exception {
		logBefore(logger, "upfilePictures");
		String ffile = DateUtil.getDays(), fileName = "";
		if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
			} else {
				System.out.println("上传失败");
			}
			String picurl = Const.FILEPATHIMG+ffile + "/" + fileName;
			System.out.println(picurl);
			out.write(picurl);
			out.close();
		}
		 
	}
	/**
	 * 新增
	 */
	@RequestMapping(value="/save1")
	public ModelAndView save1() throws Exception{
		logBefore(logger, "新增Pic");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("PIC_ID", this.get32UUID());	//主键
		picService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Pic");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			picService.delete(pd);
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
		logBefore(logger, "修改Pic");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		picService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(){
		logBefore(logger, "列表Pic");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData>	varList = picService.listAll(pd);	//列出Pic列表
			PageData category = this.piccategoryService.findById(pd);
			if(null!=category)
			System.out.println("category=="+category.toString());
			//查询所有图片分类
			PageData pd1 = new PageData();
			List<PageData> piccategorylist = this.piccategoryService.listAll(pd1);
			
			mv.setViewName("reader/pic/pic_list");
			mv.addObject("varList", varList);
			mv.addObject("piccategorylist", piccategorylist);
			mv.addObject("pd", pd);
			mv.addObject("category", category);
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
		logBefore(logger, "去新增Pic页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData> list = this.piccategoryService.listAll(pd);
			mv.setViewName("reader/pic/pic_add");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
			mv.addObject("list", list);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 用途说明：去修改分类
	 * @return
	 * 2018年1月11日下午1:48:37
	 * @auther ljj
	 */
	@RequestMapping(value="/goEditfl")
	public ModelAndView goEditfl(){
		logBefore(logger, "去修改分类Pic页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			PageData pd1 = new PageData();
			List<PageData> list = this.piccategoryService.listAll(pd1);
			mv.setViewName("reader/pic/pic_editfl");
			mv.addObject("msg", "save");
			mv.addObject("list", list);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 用途说明：修改图片分类
	 * @param out
	 * 2018年1月11日下午4:50:00
	 * @auther ljj
	 */
	@RequestMapping(value="/goEditflok")
	public void goEditflok(PrintWriter out){
		logBefore(logger, "去修改分类Pic页面");
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String picids = String.valueOf(pd.get("picids"));
			String PIC_CATEGORY_ID = String.valueOf(pd.get("PIC_CATEGORY_ID"));
			if(null!=PIC_CATEGORY_ID && !"".equals(PIC_CATEGORY_ID) && !"null".equals(PIC_CATEGORY_ID)){
				String[] picids1 = picids.split(",");
				for(String id:picids1){
					if(null!=id && !"".equals(id) && !"null".equals(id)){
						PageData pic = new PageData();
						pic.put("PIC_CATEGORY_ID", PIC_CATEGORY_ID);
						pic.put("PIC_ID", id);
						this.picService.edit(pic);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		out.write("success");
		out.close();
	}	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Pic页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	varList = picService.listAll(pd);	//列出Pic列表
			//查询所有图片分类
			PageData pd1 = new PageData();
			List<PageData> piccategorylist = this.piccategoryService.listAll(pd1);
			
			mv.setViewName("reader/pic/pic_listedit");
			mv.addObject("varList", varList);
			mv.addObject("piccategorylist", piccategorylist);
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
		logBefore(logger, "批量删除Pic");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				picService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Pic到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("类目名称");	//1
			titles.add("图片名称");	//2
			titles.add("图片路径");	//3
			titles.add("使用次数");	//4
			titles.add("上传时间");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = picService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("PIC_CATEGORY_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("PIC_NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("PIC_URL"));	//3
				vpd.put("var4", varOList.get(i).getString("USE_TIMES"));	//4
				vpd.put("var5", varOList.get(i).getString("CREATE_TIME"));	//5
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
