package com.sinontech.controller.read.articlechapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.util.AppUtil;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.FileUtil;
import com.sinontech.util.ObjectExcelView;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;
import com.sinontech.util.Jurisdiction;
import com.sinontech.service.read.article.ArticleService;
import com.sinontech.service.read.articlechapter.ArticleChapterService;
import com.sinontech.service.read.articlechapterlog.ArticleChapterlogService;
import com.sinontech.service.read.articlechaptertemp.ArticleChapterTempService;
import com.sinontech.service.read.label.LabelService;

/** 
 * 类名称：ArticleChapterController
 * 创建人：FH 
 * 创建时间：2017-11-07
 */
@Controller
@RequestMapping(value="/articlechapter")
public class ArticleChapterController extends BaseController {
	
	String menuUrl = "articlechapter/list.do"; //菜单地址(权限用)
	@Resource(name="articlechapterService")
	private ArticleChapterService articlechapterService;
	@Resource(name="articlechaptertempService")
	private ArticleChapterTempService articlechaptertempService;
	@Resource(name="articleService")
	private ArticleService articleService;
	@Resource(name="labelService")
	private LabelService labelService;
	@Resource(name="articlechapterlogService")
	private ArticleChapterlogService articlechapterlogService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDAE_TIME", DateUtil.getTime());
		//pd.put("ARTICLECHAPTER_ID", this.get32UUID());	//主键
		articlechapterService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public ModelAndView delete(Page page){
		logBefore(logger, "删除ArticleChapter");
		ModelAndView mv = this.getModelAndView();
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			articlechapterService.delete(pd);
			articleService.updateChapternum(pd);
			articleService.upchapterNumber(pd);
			List<PageData>	varList = articlechapterService.listchapter(page);	//列出ArticleChapter列表
			mv.setViewName("reader/articlechapter/articlechapter_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		articlechapterService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 
	 *用途：批量修改章节价格
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-10 下午4:02:50
	 */
	@RequestMapping(value="/bathEditmoneys")
	@ResponseBody
	public String bathEditmoneys() throws Exception{
		logBefore(logger, "修改bathEditmoneys");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		PageData pd = new PageData();
		String state = "";
		int price = 0;
		int count = 0;
		pd = this.getPageData();
		try {
			String[] str = pd.getString("money").split(",");
			String[] id = pd.getString("ARTICLE_ID").split(",");
			pd.remove("money");
			for (int j = 0; j < id.length; j++) {
				pd.put("ARTICLE_ID", id[j]);
				for (int i = 0; i < str.length; i++) {
					count++;
					if(count==3){
						if("最后".equals(str[i-1])||"最后一章".equals(str[i-1])){
							str[i-1] = "100000000";
						}
						int a = (Integer.parseInt(str[i-2])-1);
						int b = (Integer.parseInt(str[i-1])-(Integer.parseInt(str[i-2])-1));
						try {
							price = Integer.parseInt(str[i]);
						} catch (Exception e) {
							continue;
						}
						if(price>0){
							pd.put("IS_FREE", 1);
						}else{
							pd.put("IS_FREE", 0);
						}
						pd.put("a", a);
						pd.put("b", b);
						pd.put("CONSUMES", price);
						List<PageData> pd1 = articlechapterService.findBylimit(pd);//查询章节
						for (int k = 0; k < pd1.size(); k++) {
							pd.put("ARTICLE_CHAPTER_ID", pd1.get(k).get("ARTICLE_CHAPTER_ID").toString());
							articlechapterService.editMoneys(pd);
						}
						count = 0;
					}
				}
				articleService.updateSum(pd);//更新书籍总价
			}
			state = "true";
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return state;
	}
	
	
	/**
	 * 
	 *用途：书籍详情页面章节批量编辑
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-9 上午10:47:30
	 */
	@RequestMapping(value="/chapterEditState")
	public void chapterEditState(PrintWriter out) throws Exception{
		logBefore(logger, "修改ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return ;} //校验权限
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String[] strid = pd.getString("ARTICLE_CHAPTER_ID").split(",");
			for (int i = 0; i < strid.length; i++) {
				pd.put("ARTICLE_CHAPTER_ID", strid[i]);
				articlechapterService.editstate(pd);
				out.write("success");
				out.close();
			}
			articleService.updateChapternum(pd);
			articleService.upchapterNumber(pd);
			articleService.updateSum(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	/**
	 * 
	 *用途：书籍详情页面书籍状态改变
	 *@return void
	 *@author 刘振
	 *2018-2-6 下午6:49:38
	 */
	@RequestMapping(value="/editstate")
	public void editstate(PrintWriter out) throws Exception{
		logBefore(logger, "修改ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return ;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			articlechapterService.editstate(pd);
			articleService.updateChapternum(pd);
			articleService.upchapterNumber(pd);
			articleService.updateSum(pd);
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	
	/**
	 * 
	 *用途：修改章节价格
	 *@return String
	 *@author 刘振
	 *2018-2-6 下午4:08:38
	 */
	@RequestMapping(value = "/updateConsums")
	@ResponseBody
	public String updateConsums() {
		logBefore(logger, "修改章节价格");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {return null;} // 校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = "";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(pd.getString("inpVal"));
		try {
			if (isNum.matches()) {
				pd.put("ARTICLE_CHAPTER_ID", pd.get("inpId").toString());
				int price = Integer.parseInt(String.valueOf(pd.get("inpVal")));
				if(price>0){
					pd.put("IS_FREE", 1);
				}else{
					pd.put("IS_FREE",0);
				}
				articlechapterService.updateConsums(pd);
				PageData pd1 = articlechapterService.findById(pd);
				articleService.updateSum(pd1);
				PageData pd2 = articleService.findById(pd1);
				msg = String.valueOf(pd2.get("COUNT_CONSUMES"));
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return msg;
	}
	
	
	/**
	 * 
	 * 用途：修改章节状态
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-14下午4:27:06
	 */
	@RequestMapping(value="/update")
	public ModelAndView update(Page page) throws Exception{
		logBefore(logger, "修改ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "update")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPDATE_TIME", DateUtil.getTime());
		articlechapterService.update(pd);
		PageData pd1 = articlechapterService.findByIdState(pd);
		articleService.updateChapternum(pd1);
		articleService.upchapterNumber(pd1);
		articleService.updateSum(pd1);
		List<PageData>	varList = articlechapterService.listchapter(page);	//列出ArticleChapter列表
		mv.setViewName("reader/articlechapter/articlechapter_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	/**
	 * 
	 * 用途：列表
	 * @param @param page
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-12下午5:32:14
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表ArticleChapter");
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
			List<PageData>	varList = articlechapterService.listchapter(page);	//列出ArticleChapter列表
			mv.setViewName("reader/articlechapter/articlechapter_list");
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
	 * 用途：去新增页面
	 * @param @return   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-13下午3:17:45
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增ArticleChapter页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("reader/articlechapter/articlechapter_add");
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
		logBefore(logger, "去修改ArticleChapter页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = articlechapterService.findById(pd);	//根据ID读取
			String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
			FileReader fr=new FileReader(projectpath+ pd.getString("CONTENT_URL"));
			System.out.println(projectpath+ pd.getString("CONTENT_URL"));
			BufferedReader br=new BufferedReader(fr);
			String line = "";
			String txt = "";
		    while((line = br.readLine())!=null){
		    	txt+=line+"\r\n";
		    }
			pd.put("txt", txt);
			mv.setViewName("reader/articlechapter/articlechapter_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			 br.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 
	 * @purpose：去章节编辑页面
	 * @return
	 * @return ModelAndView
	 * @author liuzhen
	 * @Time：2018-3-22 下午7:04:45
	 */
	@RequestMapping(value="/chapterBatchEdit")
	public ModelAndView chapterBatchEdit(Page page){
		logBefore(logger, "去章节编辑chapterBatchEdit页面");
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
			List<PageData> varchapterlist = articlechapterService.chapterBatchlist(page);
			mv.setViewName("reader/articlechapter/articleChapter_bachEdit");
			mv.addObject("pd", pd);
			mv.addObject("varchapterlist", varchapterlist);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	
	/**
	 * 
	 *用途：书籍详情页面进行章节编辑
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-6 下午7:09:43
	 */
	@RequestMapping(value="/goBookEdit")
	public ModelAndView goBookEdit(){
		logBefore(logger, "去修改ArticleChapter页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = articlechapterService.findById(pd);	//根据ID读取
			String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
			FileReader fr=new FileReader(projectpath+ pd.getString("CONTENT_URL"));
			BufferedReader br=new BufferedReader(fr);
			String line = "";
			String txt = "";
		    while((line = br.readLine())!=null){
		    	txt+=line;
		    }
			pd.put("txt", txt);
			mv.setViewName("reader/article/articlechapter_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			 br.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 *用途：书籍详情页面，编辑章节
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-6 下午7:16:41
	 */
	@RequestMapping(value="/articlebookChapteredit")
	public ModelAndView articlebookChapteredit(Page page) throws Exception{
		logBefore(logger, "修改Chapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		int CHAPTER_NO1 = Integer.parseInt(pd.getString("CHAPTER_NO1"));
		int CHAPTER_NO = Integer.parseInt(pd.getString("CHAPTER_NO"));
		if(CHAPTER_NO1<CHAPTER_NO){
			articlechapterService.editNoReduce(pd);
		}else if(CHAPTER_NO1>CHAPTER_NO){
			articlechapterService.editNoPlus(pd);
		}
		if(pd.getString("CHAPTER_STATE")==null||"".equals(pd.getString("CHAPTER_STATE"))){
			pd.put("CHAPTER_STATE", 0);
		}
		if(pd.getString("IS_FREE")==null||"".equals(pd.getString("IS_FREE"))){
			pd.put("IS_FREE", 0);
		}
		String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
		File writename = new File(projectpath+pd.getString("CONTENT_URL"));//读取文件
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(writename),"gbk");      
	    BufferedWriter writer=new BufferedWriter(write); 
	    writer.write(pd.getString("chaptercontent")); //写入到本地
	    writer.flush();
	    writer.close();
	    /* File editFilename = new File(filePath+pd.getString("CHAPTER_NAME")+".txt");  
		writename.renameTo(editFilename);	//修改文件名
	    */		
		pd.put("UPDAE_TIME", DateUtil.getTime());
		articlechapterService.edit(pd);
		page.setPd(pd);
		articleService.updateChapternum(pd);
		articleService.upchapterNumber(pd);
		articleService.updateSum(pd);
		PageData articleById = articleService.findById(pd);	//列出Article详情
		List<PageData> varlabellist = labelService.list(page);
		List<PageData> varchapterlist = articlechapterService.list(page);
		articleById.put("COUNT_LETTER",Double.parseDouble(articleById.get("COUNT_LETTER").toString())/10000);
		PageData chapterpd = articlechapterlogService.bookcountdata(pd);//查询书籍总数据
		PageData ydchapterpd = articlechapterlogService.bookcountdatayd(pd);//查询书籍昨天数据
		PageData dchapterpd = articlechapterlogService.bookcountdatad(pd);//查询书籍当天数据
		PageData mchapterpd = articlechapterlogService.bookcountdatam(pd);//查询书籍当月数据
		mv.setViewName("reader/article/article_findId");
		mv.addObject("articleById", articleById);
		mv.addObject("chapterpd", chapterpd);
		mv.addObject("ydchapterpd", ydchapterpd);
		mv.addObject("dchapterpd", dchapterpd);
		mv.addObject("mchapterpd", mchapterpd);
		mv.addObject("varchapterlist", varchapterlist);
		mv.addObject("varlabellist", varlabellist);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除ArticleChapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				articlechapterService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出ArticleChapter到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("id");	//1
			titles.add("书籍表主键");	//2
			titles.add("章节名称");	//3
			titles.add("章节序号");	//4
			titles.add("章节内容保存路径");	//5
			titles.add("是否收费(默认0免费 1收费)");	//6
			titles.add("'阅读币");	//7
			dataMap.put("titles", titles);
			List<PageData> varOList = articlechapterService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("ARTICLE_CHAPTER_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("ARTICLE_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("CHAPTER_NAME"));	//3
				vpd.put("var4", varOList.get(i).getString("CHAPTER_NO"));	//4
				vpd.put("var5", varOList.get(i).getString("CONTENT_URL"));	//5
				vpd.put("var6", varOList.get(i).get("IS_FREE").toString());	//6
				vpd.put("var7", varOList.get(i).get("CONSUMES").toString());	//7
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
	 * 用途：保存章节(单章)
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-14上午11:49:35
	 */
	@RequestMapping(value="/articleChapteradd")
	public ModelAndView articleChapteradd(Page page) throws Exception{
		logBefore(logger, "新增Chapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(null!=pd.getString("ARTICLECHAPTER_NO")&&!"".equals(pd.getString("ARTICLECHAPTER_NO").trim())){
			PageData nextChapter = articlechapterService.nextArticleChapter(pd);
			if(nextChapter==null){
				pd.put("CHAPTER_NO",1);
			}else{
				if(Integer.parseInt(pd.getString("ARTICLECHAPTER_NO"))+1>Integer.parseInt(nextChapter.get("CHAPTER_NO").toString())){
					pd.put("ARTICLECHAPTER_NO", Integer.parseInt(nextChapter.get("CHAPTER_NO").toString())+1);
				}
				articlechapterService.upnextno(pd);
				pd.put("CHAPTER_NO",pd.getString("ARTICLECHAPTER_NO"));
			}
		}else{
			PageData nextChapter = articlechapterService.nextArticleChapter(pd);
			if(nextChapter==null){
				pd.put("CHAPTER_NO",1);
			}else{
				pd.put("CHAPTER_NO", Integer.parseInt(nextChapter.get("CHAPTER_NO").toString())+1);
			}
		}
		if(pd.getString("CHAPTER_STATE")==null||"".equals(pd.getString("CHAPTER_STATE"))){
			pd.put("CHAPTER_STATE", 0);
		}
		if(pd.getString("IS_FREE")==null||"".equals(pd.getString("IS_FREE"))){
			pd.put("IS_FREE", 0);
		}
		pd.put("CREATE_TIME", DateUtil.getTime());
		pd.put("UPDATE_TIME", DateUtil.getTime());
		String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
		String filename = Const.FILEPATHBOOKCHAPTERARTICLE + pd.getString("ARTICLE_ID")+"/";//文件目录
		String get32uuid = this.get32UUID();//生成每个章节的文件名
		String  filePath = projectpath +  filename;//文件上传路径
		File writename = new File(filePath+get32uuid+".txt");
		FileUtil.createDir(filePath);//创建目录
		if(writename.exists()){
			writename.createNewFile();//创建文件
		}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(writename),"gbk");      
		BufferedWriter writer=new BufferedWriter(write);
		writer.write(pd.getString("CHAPTER_NAME")+"\r\n"+pd.getString("chaptercontent")); 
		writer.flush();
		writer.close();
		pd.put("CONTENT_URL",filename+get32uuid+".txt");
		pd.put("CONSUMES", 0);
		articlechapterService.save(pd);
		articleService.updateChapternum(pd);
		articleService.upchapterNumber(pd);
		List<PageData>	varList = articlechapterService.listchapter(page);	//列出ArticleChapter列表
		mv.setViewName("reader/articlechapter/articlechapter_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	/**
	 * 
	 * 用途：修改章节
	 * @param @param page
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView  
	 * @author 刘振
	 * @date 2017-12-18下午4:45:09
	 */
	@RequestMapping(value="/articleChapteredit")
	public ModelAndView articleChapteredit(Page page) throws Exception{
		logBefore(logger, "修改Chapter");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if(null!=pd.getString("CHAPTER_NO")&&!"".equals(pd.getString("CHAPTER_NO"))){
			PageData nextChapter = articlechapterService.nextArticleChapter(pd);
			if(nextChapter==null){
				pd.put("CHAPTER_NO",1);
			}else{
				if(Integer.parseInt(pd.getString("CHAPTER_NO"))+1>Integer.parseInt(nextChapter.get("CHAPTER_NO").toString())){
					pd.put("CHAPTER_NO", Integer.parseInt(nextChapter.get("CHAPTER_NO").toString())+1);
				}
			}
			int CHAPTER_NO1 = Integer.parseInt(pd.getString("CHAPTER_NO1"));
			int CHAPTER_NO = Integer.parseInt(pd.getString("CHAPTER_NO"));
			if(CHAPTER_NO1<CHAPTER_NO){
				articlechapterService.editNoReduce(pd);
			}else if(CHAPTER_NO1>CHAPTER_NO){
				articlechapterService.editNoPlus(pd);
			}
		}
		if(pd.getString("CHAPTER_STATE")==null||"".equals(pd.getString("CHAPTER_STATE"))){
			pd.put("CHAPTER_STATE", 0);
		}
		if(pd.getString("IS_FREE")==null||"".equals(pd.getString("IS_FREE"))){
			pd.put("IS_FREE", 0);
		}
		String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
		File writename = new File(projectpath+pd.getString("CONTENT_URL"));//读取文件
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(writename),"gbk");      
	    BufferedWriter writer=new BufferedWriter(write); 
	    writer.write(pd.getString("chaptercontent")); //写入到本地
	    writer.flush();
	    writer.close();
	   /* File editFilename = new File(filePath+pd.getString("CHAPTER_NAME")+".txt");  
		writename.renameTo(editFilename);	//修改文件名
	    */	
		pd.put("UPDATE_TIME", DateUtil.getTime());
		articlechapterService.edit(pd);
		articleService.upchapterNumber(pd);
		List<PageData>	varList = articlechapterService.listchapter(page);	//列出ArticleChapter列表
		mv.setViewName("reader/articlechapter/articlechapter_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	/**
	 * 
	 * 用途:去批量新增页面
	 * @author 刘振
	 * @return ModelAndView 
	 * @date 下午2:14:32
	 */
	@RequestMapping(value="/goBatchAdd")
	public ModelAndView goBatchAdd(){
		logBefore(logger, "去新增batchchapter_add页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("articleNumberY", 0);
			pd.put("articleNumber", 0);
			mv.setViewName("reader/articlechapter/batchchapter_add");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 
	 *用途：批量上传章节
	 *@return void
	 *@author 刘振
	 *2018-1-31 下午3:51:56
	 */
	@RequestMapping(value="/batchChapteraAdd")
	public ModelAndView batchChapteraAdd(@RequestParam(required=false)CommonsMultipartFile file,HttpServletResponse response){
		logBefore(logger, "书籍导入");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		String name = file.getOriginalFilename();
		logger.info(name);
		String fileName = "";
		try {
			pd.put("ARTICLE_NAME",name.substring(0, name.length()-4));
			System.out.println(pd.toString()+"====name");
			PageData articlepd = articleService.findByName(pd);//通过文件名匹配书籍
			pd.put("articleNumberY", 0);
			if(articlepd!=null){
				pd.put("articleNumberY", 1);
				pd.put("ARTICLE_ID",articlepd.get("ARTICLE_ID").toString());
				String projectpath = getRequest().getSession().getServletContext().getRealPath("/");//获取当前项目路径
				String  filePath = projectpath +  Const.FILEPATHBOOKCHAPTERARTICLE + pd.getString("ARTICLE_ID");		//文件上传路径
				String get32uuid = this.get32UUID();//生成每个章节的文件名
				fileName = FileUpload.fileUp(file, filePath, get32uuid);//上传文件
				FileInputStream fileInputStream = new FileInputStream(filePath+"/"+fileName);//读取文件
	            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");//转码
				BufferedReader br=new BufferedReader(inputStreamReader);
				String line = "";  //文本每一行
				String txt = "";  //章节内容
				String chagter = ""; //章节名称
				pd.put("CHAPTER_NO1", 1);
				int booknumber = 0;//统计书籍字数
				int i = Integer.parseInt(pd.get("CHAPTER_NO1").toString());
			    while((line = br.readLine())!=null){
			    	Pattern pattern = Pattern.compile("第.*章.*");
			    	Matcher matcher = pattern.matcher(line);
			    	boolean b= matcher.matches();
			    	if(line.length()>=4&&line.length()<=20&&b){
			    		if(i== Integer.parseInt(pd.get("CHAPTER_NO1").toString())){
			    			pd.put("CREATE_TIME",DateUtil.getTime());
			    			pd.put("UPDATE_TIME",DateUtil.getTime());
			    			FileUtil.createDir(filePath);//创建目录
			    			get32uuid = this.get32UUID();
			    			FileWriter fw = new FileWriter(filePath+"/"+ get32uuid+".txt");//文件保存路径和名字
			                BufferedWriter bw = new BufferedWriter(fw);
			                bw.close();
			                fw.close();
			                pd.put("CHAPTER_NAME","前言");
			                pd.put("CHAPTER_NO",i);
			                pd.put("CONTENT_URL",Const.FILEPATHBOOKCHAPTERARTICLE + pd.getString("ARTICLE_ID") +"/"+ get32uuid+".txt");
			                pd.put("NUMBER_CHAPTER",txt.length());
			                booknumber+=txt.length();
			                i++;
			    		}else{
			    			pd.put("CREATE_TIME",DateUtil.getTime());
			    			pd.put("UPDATE_TIME",DateUtil.getTime());
			    			get32uuid = this.get32UUID();
			    			FileWriter fw = new FileWriter(filePath+"/"+ get32uuid+".txt");//文件保存路径和名字
			                BufferedWriter bw = new BufferedWriter(fw);
			                txt = chagter+txt;
			                bw.write(txt);		//写入本地
			                bw.close(); 
			                fw.close();
			                if(chagter==null||"".equals(chagter)){
			                	chagter = "前言";
			                } 
			                pd.put("CHAPTER_NAME",chagter);
			                pd.put("CHAPTER_NO",i);
			                pd.put("CONTENT_URL",Const.FILEPATHBOOKCHAPTERARTICLE + pd.getString("ARTICLE_ID") +"/"+ get32uuid+".txt");
			                pd.put("NUMBER_CHAPTER",txt.length());
			                articlechaptertempService.save(pd);
			                booknumber+=txt.length();
			                i++;
			    		}
			    		chagter = line;
			    		txt = "";
			    	}else{
			    		txt+=line+"\r\n";
			    	}
			    }
			    get32uuid = this.get32UUID();
			    FileWriter fw = new FileWriter(filePath+"/"+ get32uuid+".txt");//文件保存路径和名字
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(txt);		//写入本地
                bw.close(); 
                fw.close();
                pd.put("CHAPTER_NAME",chagter);
                pd.put("CHAPTER_NO",i);
                pd.put("CONTENT_URL",Const.FILEPATHBOOKCHAPTERARTICLE + pd.getString("ARTICLE_ID") +"/"+ get32uuid+".txt");
                pd.put("NUMBER_CHAPTER",txt.length());
                articlechaptertempService.save(pd);
                PageData startpd = articlechaptertempService.findByStart(pd);
                PageData endpd = articlechaptertempService.findByEnd(pd);
                mv.addObject("endpd", endpd);
                mv.addObject("startpd", startpd);
                pd.put("number", i-Integer.parseInt(pd.get("CHAPTER_NO1").toString()));
                pd.put("booknumber", booknumber);
                pd.put("articleNumber", 1);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.setViewName("reader/articlechapter/batchchapter_add");
		mv.addObject("pd", pd);
		return mv;
	}	
	/**
	 * 
	 *用途：将临时表数据写入正式表
	 *@return ModelAndView
	 *@author 刘振
	 *2018-2-6 下午5:26:35
	 */
	@RequestMapping(value="/overAdd")
	public ModelAndView overAdd(Page page) throws Exception{
		logBefore(logger, "将临时表数据写入正式表");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> varlist = articlechaptertempService.listAll(pd);
		if(varlist.size()>0){
			articlechapterService.deleteArticleId(pd);
		}
		for (int i = 0; i < varlist.size(); i++) {
			pd.put("CREATE_TIME", DateUtil.getTime());
			pd.put("UPDAE_TIME", DateUtil.getTime());
			pd.put("CHAPTER_NAME", varlist.get(i).getString("CHAPTER_NAME"));
			pd.put("CHAPTER_NO", varlist.get(i).get("CHAPTER_NO").toString());
			pd.put("CONTENT_URL", varlist.get(i).getString("CONTENT_URL"));
			pd.put("NUMBER_CHAPTER", varlist.get(i).get("NUMBER_CHAPTER").toString());
			articlechapterService.savetemp(pd);
		}
		articleService.updateChapternum(pd);
		articleService.upchapterNumber(pd);
		articleService.updateSum(pd);
		articlechaptertempService.delAll(pd);
		List<PageData>	varList = articlechapterService.listchapter(page);	//列出ArticleChapter列表
		mv.setViewName("reader/articlechapter/articlechapter_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
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