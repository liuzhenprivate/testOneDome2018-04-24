package com.sinontech.controller.read.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.sinontech.entity.system.User;
import com.sinontech.service.read.webchat.WebchatService;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.PageData;
import com.sinontech.util.PathUtil;

@Controller
@RequestMapping(value="/channelwebchat")
public class ChannelWebchatController extends BaseController{
	@Resource(name="webchatService")
	private WebchatService webchatService;
	public static void main(String[] args){
		ChannelWebchatController c = new ChannelWebchatController();
//		System.out.println(c.getAccessToken("wxabfb9969455a36cb","d8d82d3524f2ea741e448a2601e98e18"));
		String accessToken ="6_b91EyM2PQ2h22zbkHrgdoOAhv2UrOnQLNABbF5rTDBcPepO6km3207Ied0tV7VnzAqB1Rc2-xap-0cZcGy6iGwuSL9eTO_NLv6lRmQ_rO0vwNy0k--7FZMk2h_jJlhlWCIROnH2NVA_o7HyjETLdAAAODV";
		c.getmenu(accessToken);

	}
	/*小说热搜：
	账号：154592562@qq.com
	密码：yuedu0101*/
	public String getAccessToken(String appid,String appsecret){
		String accessToken =null;
		String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
		+appid+"&secret="+appsecret;
		String rs = httpPost(url);
		logger.info(rs);
		if(null!=rs){
			Map responseMap = JSON.parseObject(rs);
			accessToken = String.valueOf(responseMap.get("access_token"));
			String errcode = String.valueOf(responseMap.get("errcode"));
			String errmsg = String.valueOf(responseMap.get("errmsg"));
			if(null==accessToken || "".equals(accessToken)){
				logger.error(errcode+"="+errmsg);
			}
		}
		//6_iuG1UdCeLNtfxB22NJdZxqQX-sZLtxH5J5JDtVhyXORT4JbBFIMD_7ytvGenxCJjhOPH5fIYMi3a-HNgubps1nsvtakdpk3JVTZ16ZeNCu89khFJKkqlSOa4O0qopUiscygpRilJVY_iLZRDIPSjAGAOVY
		return accessToken;
	}
	
	public void getmenu(String accessToken){
		String url="https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accessToken;
		String rs = httpPost(url);
		logger.info(rs);
		if(null!=rs){
//			{"menu":{"button":[{"type":"view","name":"最近阅读","url":"https:\/\/w.qirexiaoshuo.com\/Book\/book_shelf\/?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"🔥精选小说","url":"https:\/\/w.qirexiaoshuo.com?islogin=yes&ADU=16651743","sub_button":[]},{"name":"我的","sub_button":[{"type":"view","name":"包月","url":"https:\/\/w.qirexiaoshuo.com\/book\/book_list\/type\/vip.html?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"免费","url":"https:\/\/w.qirexiaoshuo.com\/book\/book_list\/type\/free.html?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"充值","url":"https:\/\/w.qirexiaoshuo.com\/my\/index.html?islogin=yes&ADU=16651743","sub_button":[]}]}]}}
			Map responseMap = JSON.parseObject(rs);
			if(null!=responseMap){
				String menu = String.valueOf(responseMap.get("menu"));
				Map menuMap = JSON.parseObject(menu);
				if(null!=menuMap){
					String button = String.valueOf(menuMap.get("button"));
//					System.out.println(button);
					JSONArray array = JSON.parseArray(button);
					if(null!=array){
						for(Object obj:array){
							String strobj = obj.toString();
							Map objMap = JSON.parseObject(strobj);
							String name = String.valueOf(objMap.get("name"));
							String sub_button = String.valueOf(objMap.get("sub_button"));
							String type = String.valueOf(objMap.get("type"));
							String menuurl = String.valueOf(objMap.get("url"));
							System.out.println("=="+name+"="+type+"="+menuurl+"="+sub_button+"=");
						}
					}
				}
			}
		}
	}
	
	public void delmenu(String accessToken){
		String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;
		String rs = httpPost(url);
		logger.info(rs);
		if(null!=rs){
//			{"menu":{"button":[{"type":"view","name":"最近阅读","url":"https:\/\/w.qirexiaoshuo.com\/Book\/book_shelf\/?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"🔥精选小说","url":"https:\/\/w.qirexiaoshuo.com?islogin=yes&ADU=16651743","sub_button":[]},{"name":"我的","sub_button":[{"type":"view","name":"包月","url":"https:\/\/w.qirexiaoshuo.com\/book\/book_list\/type\/vip.html?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"免费","url":"https:\/\/w.qirexiaoshuo.com\/book\/book_list\/type\/free.html?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"充值","url":"https:\/\/w.qirexiaoshuo.com\/my\/index.html?islogin=yes&ADU=16651743","sub_button":[]}]}]}}
			Map responseMap = JSON.parseObject(rs);
			String access_token = String.valueOf(responseMap.get("access_token"));
			String errcode = String.valueOf(responseMap.get("errcode"));
			String errmsg = String.valueOf(responseMap.get("errmsg"));
		}
	}
	public void createmenu(String accessToken){
		String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		String rs = httpPost(url);
		logger.info(rs);
		if(null!=rs){
//			{"menu":{"button":[{"type":"view","name":"最近阅读","url":"https:\/\/w.qirexiaoshuo.com\/Book\/book_shelf\/?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"🔥精选小说","url":"https:\/\/w.qirexiaoshuo.com?islogin=yes&ADU=16651743","sub_button":[]},{"name":"我的","sub_button":[{"type":"view","name":"包月","url":"https:\/\/w.qirexiaoshuo.com\/book\/book_list\/type\/vip.html?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"免费","url":"https:\/\/w.qirexiaoshuo.com\/book\/book_list\/type\/free.html?islogin=yes&ADU=16651743","sub_button":[]},{"type":"view","name":"充值","url":"https:\/\/w.qirexiaoshuo.com\/my\/index.html?islogin=yes&ADU=16651743","sub_button":[]}]}]}}
			Map responseMap = JSON.parseObject(rs);
			String access_token = String.valueOf(responseMap.get("access_token"));
			String errcode = String.valueOf(responseMap.get("errcode"));
			String errmsg = String.valueOf(responseMap.get("errmsg"));
		}
	}
	@RequestMapping(value="/menuset")
	public ModelAndView menuset(){
		logBefore(logger, "去菜单设置页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData webchat = this.webchatService.findWebchatUserId(pd);
				mv.setViewName("readerchannel/webchat/menuset");
				mv.addObject("webchat", webchat);
			}
			
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/index")
	public ModelAndView index(){
		logBefore(logger, "去公众号后台配置页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData webchat = this.webchatService.findWebchatUserId(pd);
				if(null!=webchat){
					mv.setViewName("readerchannel/webchat/webchat");
					mv.addObject("webchat", webchat);
				}else{
					mv.setViewName("readerchannel/webchat/webchat_edit");
				}
			}
			
			mv.addObject("msg", "savewebchat");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/siteset")
	public ModelAndView siteset(){
		logBefore(logger, "去站点设置页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData webchat = this.webchatService.findWebchatUserId(pd);
				if(null!=webchat){
					mv.setViewName("readerchannel/webchat/site");
					mv.addObject("webchat", webchat);
				}else{
					mv.setViewName("readerchannel/webchat/site_edit");
				}
			}
			
			mv.addObject("msg", "editwebchat");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	@RequestMapping(value="/editsiteset")
	public ModelAndView editsiteset() throws Exception{
		logBefore(logger, "新增siteset");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if(null!=user){
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			webchatService.edit(pd);
		}
		return new ModelAndView("redirect:siteset");
	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/savewebchat")
	public ModelAndView savewebchat(@RequestParam(required = false) MultipartFile file,HttpServletRequest request) throws Exception{
		logBefore(logger, "新增webchat");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if(null!=user){
			String ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
				pd.put("PIC_URL",  Const.FILEPATHIMG+ffile + "/" + fileName);
			}  
			String NAME = request.getParameter("NAME");
			String TYPE = request.getParameter("TYPE");
			String WEBCHAT_CODE = request.getParameter("WEBCHAT_CODE");
			String APPID = request.getParameter("APPID");
			String APPSECRET = request.getParameter("APPSECRET");
			String TOKEN = request.getParameter("TOKEN");
			pd.put("NAME", NAME);
			pd.put("TYPE", TYPE);
			pd.put("WEBCHAT_CODE", WEBCHAT_CODE);
			pd.put("APPID", APPID);
			pd.put("APPSECRET", APPSECRET);
			pd.put("TOKEN", TOKEN);
			
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			pd.put("CREATE_TIME", DateUtil.getTime());	 
			webchatService.save(pd);
		}
		return new ModelAndView("redirect:index");
	}
	@RequestMapping(value="/editwebchat")
	public ModelAndView editwebchat(@RequestParam(required = false) MultipartFile file,HttpServletRequest request) throws Exception{
		logBefore(logger, "新增webchat");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		String ffile = DateUtil.getDays(), fileName = "";
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
			fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
			pd.put("PIC_URL",  Const.FILEPATHIMG+ffile + "/" + fileName);
		}  
		String WEBCHAT_ID = request.getParameter("WEBCHAT_ID");
		String NAME = request.getParameter("NAME");
		String TYPE = request.getParameter("TYPE");
		String WEBCHAT_CODE = request.getParameter("WEBCHAT_CODE");
		String APPID = request.getParameter("APPID");
		String APPSECRET = request.getParameter("APPSECRET");
		String TOKEN = request.getParameter("TOKEN");
		pd.put("WEBCHAT_ID", WEBCHAT_ID);
		pd.put("NAME", NAME);
		pd.put("TYPE", TYPE);
		pd.put("WEBCHAT_CODE", WEBCHAT_CODE);
		pd.put("APPID", APPID);
		pd.put("APPSECRET", APPSECRET);
		pd.put("TOKEN", TOKEN);
		System.out.println(pd.toString());
		this.webchatService.edit(pd);
		return new ModelAndView("redirect:index");
	}
	
	@RequestMapping(value="/goeditwebchat")
	public ModelAndView goeditwebchat(){
		logBefore(logger, "去修改webchat页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData webchat = webchatService.findWebchatUserId(pd);	//根据ID读取
				mv.setViewName("readerchannel/webchat/webchat_edit");
				mv.addObject("msg", "editwebchat");
				mv.addObject("webchat", webchat);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	@RequestMapping(value="/goeditsiteset")
	public ModelAndView goeditsiteset(){
		logBefore(logger, "去修改siteset页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if(null!=user){
				Long uid = user.getUSER_ID();
				pd.put("USER_ID", uid);
				PageData webchat = webchatService.findWebchatUserId(pd);	//根据ID读取
				logger.info(webchat.toString());
				mv.setViewName("readerchannel/webchat/site_edit");
				mv.addObject("msg", "editsiteset");
				mv.addObject("webchat", webchat);
				logger.info(webchat.toString());
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	public   String httpPost(String url ){
		logger.info(url);
//		logger.info("deviceid="+deviceid+"[]keyword="+keyword);
		String result=null;
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		client.getParams().setHttpElementCharset("UTF-8");
		PostMethod post=new PostMethod(url);
		post.setFollowRedirects(false);
		InputStream inputStream = null;
		try{
			client.executeMethod(post);
			inputStream = post.getResponseBodyAsStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
			StringBuffer stringBuffer = new StringBuffer();  
			String str= "";  
			while((str = br.readLine()) != null){  
			stringBuffer .append(str );  
			}
			result = stringBuffer.toString();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			post.releaseConnection();
		}
		return result;
	}
}