package com.sinontech.controller.read.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.Page;
import com.sinontech.entity.system.User;
import com.sinontech.service.read.webchat.WebchatService;
import com.sinontech.service.read.webchatmenu.WebchatMenuService;
import com.sinontech.service.read.webchatreply.WebchatReplyService;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.Jurisdiction;
import com.sinontech.util.PageData;
import com.sinontech.util.PathUtil;

@Controller
@RequestMapping(value="/channelwebchatreply")
public class ChannelWebchatReplyController extends BaseController{
	@Resource(name="webchatService")
	private WebchatService webchatService;
	@Resource(name="webchatmenuService")
	private WebchatMenuService webchatmenuService;
	@Resource(name="webchatreplyService")
	private WebchatReplyService webchatreplyService;
	
	 
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表WebchatReply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String sendtime = String.valueOf(pd.get("ctime"));
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
			Object WEBCHAT_ID =null;
			if(null!=user){
				Long uid = user.getUSER_ID();
				PageData pd2 = new PageData();
				pd2.put("USER_ID", uid);
				PageData webchat = this.webchatService.findWebchatUserId(pd2);
				WEBCHAT_ID = webchat.get("WEBCHAT_ID");
			}else{
				return null;
			}
			pd.put("TYPE", "0");
			pd.put("WEBCHAT_ID", WEBCHAT_ID);
//			System.out.println(pd.toString());
			page.setPd(pd);
			List<PageData>	varList = webchatreplyService.list(page);	//列出WebchatReply列表
			PageData pd1 = new PageData();
			pd1.put("WEBCHAT_ID", WEBCHAT_ID);
			pd1.put("TYPE", "1");
			List<PageData> list = this.webchatreplyService.listAll(pd1);
			if(null!=list && list.size()>0){
				mv.addObject("reply", list.get(0));
			}
			mv.setViewName("readerchannel/webchat/webchatreply_list");
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
		logBefore(logger, "去新增WebchatReply页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("readerchannel/webchat/webchatreply_edit");
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
		logBefore(logger, "去修改WebchatReply页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = webchatreplyService.findById(pd);	//根据ID读取
			mv.setViewName("readerchannel/webchat/webchatreply_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/goLook")
	public ModelAndView goLook(){
		logBefore(logger, "去查看WebchatReply页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = webchatreplyService.findById(pd);	//根据ID读取
			mv.setViewName("readerchannel/webchat/webchatreply_content");
			mv.addObject("msg", "look");
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
		logBefore(logger, "新增WebchatReply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if(null!=user){
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			PageData webchat = this.webchatService.findWebchatUserId(pd);
			pd.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
			pd.put("CREATE_TIME", DateUtil.getTime());	 
			pd.put("TYPE", 0);
			String LOSE_TIME = String.valueOf(pd.get("LOSE_TIME"));
			if(null==LOSE_TIME || "".equals(LOSE_TIME) || "null".equals(LOSE_TIME)){
				pd.put("LOSE_TIME", "");
				pd.put("VALID_HOURS", "永久");
			}else{
				String VALID_HOURS = DateUtil.getDatePoor(LOSE_TIME);
				pd.put("VALID_HOURS", VALID_HOURS);
			}
			System.out.println(pd.toString());
			webchatreplyService.save(pd);
		}
		
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value="/savereply")
	public ModelAndView savereply() throws Exception{
		logBefore(logger, "新增WebchatReply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String WEBCHATREPLY_ID = String.valueOf(pd.get("WEBCHATREPLY_ID"));
		if(null==WEBCHATREPLY_ID || "".equals(WEBCHATREPLY_ID)){
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData webchat = this.webchatService.findWebchatUserId(pd);
				pd.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
				pd.put("CREATE_TIME", DateUtil.getTime());	 
				pd.put("TYPE", 1);
				pd.put("KEYWORDS", 1);
				pd.put("LOSE_TIME", "2099-12-31");
				pd.put("VALID_HOURS", "1");
				webchatreplyService.save(pd);
				
			}
		}else{
			webchatreplyService.edit(pd);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除WebchatReply");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			webchatreplyService.delete(pd);
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
		logBefore(logger, "修改WebchatReply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String LOSE_TIME = String.valueOf(pd.get("LOSE_TIME"));
		if(null==LOSE_TIME || "".equals(LOSE_TIME) || "null".equals(LOSE_TIME)){
			pd.put("LOSE_TIME", "");
			pd.put("VALID_HOURS", "永久");
		}else{
			String VALID_HOURS = DateUtil.getDatePoor(LOSE_TIME);
			pd.put("VALID_HOURS", VALID_HOURS);
		}
		this.webchatreplyService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
}