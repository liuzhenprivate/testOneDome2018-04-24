package com.sinontech.controller.read.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sinontech.service.read.html.HtmlService;
import com.sinontech.service.read.webchat.WebchatService;
import com.sinontech.service.read.webchatmenu.WebchatMenuService;
import com.sinontech.util.Const;
import com.sinontech.util.DateUtil;
import com.sinontech.util.FileUpload;
import com.sinontech.util.PageData;
import com.sinontech.util.PathUtil;

@Controller
@RequestMapping(value="/channelwebchatmenu")
public class ChannelWebchatMenuController extends BaseController{
	@Resource(name="webchatService")
	private WebchatService webchatService;
	@Resource(name="webchatmenuService")
	private WebchatMenuService webchatmenuService;
	@Resource(name="htmlService")
	private HtmlService htmlService;
	
	public static void main(String[] args){
		ChannelWebchatMenuController c = new ChannelWebchatMenuController();
//		System.out.println(c.getAccessToken("wxabfb9969455a36cb","d8d82d3524f2ea741e448a2601e98e18"));
		String accessToken ="6_VjiGZnug1FQCD8CLW-lMnFmT0FO0psmo_7EEL_8I5lhzofOOQH1QmA84HDP71okh3CBSQxea5z2_L6CCPJO4yZ0go187Xkt82XenrNK8gGsS8sJvVp5HnVzGb8-3sRoN6PlGqBCzt2khqDieMKNhAJARPS";
		String s="";
		c.delmenu(accessToken);
		String menu="{\"menu\":{\"button\":[{\"name\":\"最近阅读\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/Book\\/book_shelf\\/?islogin=yes&ADU=16651743\"},{\"name\":\"我的\",\"sub_button\":[{\"name\":\"免费\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/book\\/book_list\\/type\\/free.html?islogin=yes&ADU=16651743\"},{\"name\":\"充值\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/my\\/index.html?islogin=yes&ADU=16651743\"}]}]}}";
		      menu= "{\"menu\":{\"button\":[{\"type\":\"view\",\"name\":\"最近阅读\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/Book\\/book_shelf?islogin=yes&ADU=16651743\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"精选小说\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com?islogin=yes&ADU=16651743\",\"sub_button\":[]},{\"name\":\"我的\",\"sub_button\":[{\"type\":\"view\",\"name\":\"包月\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/book\\/book_list\\/type\\/vip.html?islogin=yes&ADU=16651743\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"免费\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/book\\/book_list\\/type\\/free.html?islogin=yes&ADU=16651743\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"充值\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/my\\/index.html?islogin=yes&ADU=16651743\",\"sub_button\":[]}]}]}}";
		      menu="{\"button\":[{\"name\":\"最近阅读\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https:\\/\\/w.qirexiaoshuo.com\\/Book\\/book_shelf\\/?islogin=yes&ADU=16651743\"}]}";
		      menu="{\"button\":[{\"name\":\"最近阅读\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/Book/book_shelf/?islogin=yes&ADU=16651743\"},{\"name\":\"精选小说\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com?islogin=yes&ADU=16651743\"},{\"name\":\"我的\",\"sub_button\":[{\"name\":\"包月\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/book/book_list/type/vip.html?islogin=yes&ADU=16651743\"},{\"name\":\"免费\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/book/book_list/type/free.html?islogin=yes&ADU=16651743\"},{\"name\":\"充值\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/my/index.html?islogin=yes&ADU=16651743\"}]}]}";
		      menu="{\"button\":[{\"name\":\"最近阅读\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/Book/book_shelf/?islogin=yes&ADU=16651743\"},{\"name\":\"精选小说\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com?islogin=yes&ADU=16651743\"},{\"name\":\"我的\",\"sub_button\":[{\"name\":\"包月\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/book/book_list/type/vip.html?islogin=yes&ADU=16651743\"},{\"name\":\"免费\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/book/book_list/type/free.html?islogin=yes&ADU=16651743\"},{\"name\":\"充值\",\"sub_button\":[],\"type\":\"view\",\"url\":\"https://w.qirexiaoshuo.com/my/index.html?islogin=yes&ADU=16651743\"}]}]}";
		      String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		String rs = null;
		try {
			rs =c.creatememuhttp(url,menu);
			System.out.println(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
			if(null==accessToken || "".equals(accessToken)|| "null".equals(accessToken)){
				logger.error(errcode+"="+errmsg);
				accessToken="";
			}
		}
		//6_iuG1UdCeLNtfxB22NJdZxqQX-sZLtxH5J5JDtVhyXORT4JbBFIMD_7ytvGenxCJjhOPH5fIYMi3a-HNgubps1nsvtakdpk3JVTZ16ZeNCu89khFJKkqlSOa4O0qopUiscygpRilJVY_iLZRDIPSjAGAOVY
		return accessToken;
	}
	
	
	public String createmenu(PageData webchat,String strmenu){
		String msg ="";
		try {
			String APPID = String.valueOf(webchat.get("APPID"));
			String APPSECRET = String.valueOf(webchat.get("APPSECRET"));
			String accessToken = String.valueOf(webchat.get("ACCESS_TOKEN"));
			if(null==accessToken || "".equals(accessToken) || "null".equals(accessToken)){
				accessToken = getAccessToken(APPID,APPSECRET);
				if(null!=accessToken){
					webchat.put("ACCESS_TOKEN", accessToken);
					this.webchatService.edit(webchat);
				}
			}
			String code = delmenu(accessToken);
			boolean flag =false;
			if(null!=code){
				 if("0".equals(code)){
					 flag =true;
				 }else if("42001".equals(code)){
					
						accessToken = getAccessToken(APPID,APPSECRET);
						if(null!=accessToken){
							webchat.put("ACCESS_TOKEN", accessToken);
							this.webchatService.edit(webchat);
							flag =true;
						}
				 }
			}
			if(flag){
				String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
				String rs = null;
				rs = creatememuhttp(url,strmenu);
				logger.info(rs);
				if(null!=rs){
					Map responseMap = JSON.parseObject(rs);
					//{"errcode":0,"errmsg":"ok"}
	//				{"errcode":42001,"errmsg":"access_token expired hint: [xJzlnA0121vr63!]"}
					String errcode = String.valueOf(responseMap.get("errcode"));
					String errmsg = String.valueOf(responseMap.get("errmsg"));
					if("0".equals(errcode)){
						msg = "0";
					}else{
						msg = errcode+"="+errmsg;
					}
					
					if("42001".equals(errcode)){
						accessToken = getAccessToken(APPID,APPSECRET);
						if(null!=accessToken){
							webchat.put("ACCESS_TOKEN", accessToken);
							this.webchatService.edit(webchat);
							createmenu(webchat,strmenu);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView index(){
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
				PageData pd1 = new PageData();
				pd1.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
				pd1.put("PID", "0");
				List<PageData> mlist = this.webchatmenuService.listAll(pd1);
				for(PageData menu:mlist){
					PageData pd2 = new PageData();
					pd2.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
					pd2.put("PID", menu.get("WEBCHATMENU_ID"));
					List<PageData> clist = this.webchatmenuService.listAll(pd2);
					menu.put("clist", clist);
				}
				mv.setViewName("readerchannel/webchat/menuset");
				mv.addObject("webchat", webchat);
				mv.addObject("mlist", mlist);
				System.out.println(mlist.toString());
			}
			
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 
	 * @purpose：查询可设置的链接
	 * @param pd
	 * @return
	 * @return ModelAndView
	 * @author liuzhen
	 * @Time：2018-4-23 下午2:59:45
	 */
	@RequestMapping(value = "/getListUrl")
	public ModelAndView getListUrl(){
		logBefore(logger, "查询可设置的链接");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData(); 
		pd = this.getPageData();
		List<PageData> htmlList = null;
		try {
			pd.put("STATE",1);
			//htmlList = htmlService.listAll(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.addObject("pd", pd);
		mv.addObject("htmlList", htmlList);
		mv.setViewName("readerchannel/webchat/site_edit");
		return null;
	}
	
	
	
	/**
	 * 用途说明：应用到微信公众号
	 * @param response
	 * @throws Exception
	 * 2018年1月30日下午3:47:57
	 * @auther ljj
	 */
	@RequestMapping(value="/savetowebchat")
	public void savetowebchat(HttpServletResponse response) throws Exception{
		logBefore(logger, "savetowebchat");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		PrintWriter out;
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		StringBuffer sb = new StringBuffer("{\"button\":");
		if(null!=user){
			Long uid = user.getUSER_ID();
			pd.put("USER_ID", uid);
			PageData webchat = this.webchatService.findWebchatUserId(pd);
			PageData pd1 = new PageData();
			pd1.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
			pd1.put("PID", "0");
			List<PageData> mlist = this.webchatmenuService.listAll(pd1);
			List<PageData> mlistrs = new ArrayList<PageData>();
			List<PageData> list = new ArrayList<PageData>();
			if(null!=mlist && mlist.size()>0){
				for(PageData menu:mlist){
					PageData menurs = new PageData();
					menurs.put("name", menu.get("NAME"));
					PageData pd2 = new PageData();
					pd2.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
					pd2.put("PID", menu.get("WEBCHATMENU_ID"));
					List<PageData> clist = this.webchatmenuService.listAll(pd2);
					System.out.println(clist.toString());
					List<PageData> clistrs = new ArrayList<PageData>();
					if(null!=clist && clist.size()>0){
						for(PageData cmenu:clist){
							PageData cenurs = new PageData();
							cenurs.put("name", cmenu.get("NAME"));
							cenurs.put("type", cmenu.get("TYPE"));
							cenurs.put("url", cmenu.get("MENUURL"));
							cenurs.put("sub_button", list);
							clistrs.add(cenurs);
						}
					}else{
						menurs.put("type", menu.get("TYPE"));
						menurs.put("url", menu.get("MENUURL"));
					}
					menurs.put("sub_button", clistrs);
					mlistrs.add(menurs);
				}
				net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(mlistrs);
				String rs = arr.toString();
				sb.append(rs);
				sb.append("}");
				String msg = createmenu(webchat,sb.toString());
				out.write(msg);
			}else{
				out.write("err");
			}
		}else{
			out.write("err");
		}
		System.out.println(sb.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/getmenu")
	public void getmenu(HttpServletResponse response) throws Exception{
		logBefore(logger, "getmenu");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		PrintWriter out;
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		if(null!=user){
			Long uid = user.getUSER_ID();
			PageData pd1 = new PageData();
			pd1.put("USER_ID", uid);
			PageData webchat = this.webchatService.findWebchatUserId(pd1);
			if(null!=webchat){
				pd.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
				List<PageData> list = this.webchatmenuService.listAll(pd);
				if(null!=list && list.size()>0){
					PageData menu = list.get(0);
					String rs = String.valueOf(menu.get("NAME")+"===="+menu.get("MENUURL"));
					System.out.println(rs);
					out.write(rs);
					
				}else{
					out.write("err");
				}
			}else{
				out.write("err");
			}
		}else{
			out.write("err");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 用途说明：删除菜单
	 * @param out
	 * @throws Exception
	 * 2018年1月30日下午1:21:05
	 * @auther ljj
	 */
	@RequestMapping(value="/delmenu")
	public void delmenu(PrintWriter out) throws Exception{
		logBefore(logger, "新增delmenu");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if(null!=user){
			Long uid = user.getUSER_ID();
			PageData pd1 = new PageData();
			pd1.put("USER_ID", uid);
			PageData webchat = this.webchatService.findWebchatUserId(pd1);
			if(null!=webchat){
				pd.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
				List<PageData> list = this.webchatmenuService.listAll(pd);
				if(null!=list && list.size()>0){
					PageData menu = list.get(0);
					String pid = String.valueOf(menu.get("WEBCHATMENU_ID"));
					if(null!=pid && !"0".equals(pid)){
						PageData pd2 = new PageData();
						pd2.put("PID", pid);
						//二级子菜单
						this.webchatmenuService.deleteByPID(pd2);
					}
					//删除当前菜单
					this.webchatmenuService.delete(menu);
				}
			}
		}
	}
	/**
	 * 用途说明：新增和编辑菜单
	 * @param out
	 * @throws Exception
	 * 2018年1月30日上午11:25:13
	 * @auther ljj
	 */
	@RequestMapping(value="/editmenu")
	public void editmenu(PrintWriter out) throws Exception{
		logBefore(logger, "新增editmenu");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if(null!=user){
			String PDIV = String.valueOf(pd.get("PDIV"));
			Long uid = user.getUSER_ID();
			PageData pd1 = new PageData();
			pd1.put("USER_ID", uid);
			PageData webchat = this.webchatService.findWebchatUserId(pd1);
			if(null!=webchat){
				pd.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
				List<PageData> list = this.webchatmenuService.listAll(pd);
				if(null!=list && list.size()>0){
					PageData menu = list.get(0);
					pd.put("WEBCHATMENU_ID", menu.get("WEBCHATMENU_ID"));
					this.webchatmenuService.edit(pd);
					out.write("success");
				}else{
					pd.put("CREATE_TIME", DateUtil.getTime());
					if(null!=PDIV && !"".equals(PDIV) && !"0".equals(PDIV)){
						//二级子菜单
						PageData pd2 = new PageData();
						pd2.put("WEBCHAT_ID", webchat.get("WEBCHAT_ID"));
						pd2.put("SOURT", PDIV);
						List<PageData> plist = this.webchatmenuService.listAll(pd2);
						if(null!=plist && plist.size()>0){
							PageData pmenu = plist.get(0);
							pd.put("PID", pmenu.get("WEBCHATMENU_ID"));
							pd.put("TYPE", "view");
							this.webchatmenuService.save(pd);
							out.write("success");
						}else{
							out.write("2");
						}
					}else{
						pd.put("PID", "0");
						this.webchatmenuService.save(pd);
						out.write("success");
					}
				}
				out.close();
			}else{
				//公众号不存在
				out.write("1");
				out.close();	
			}
		}
	}
	
	public String creatememuhttp(String url,String sendStr) throws Exception {
		System.out.println("调用servlet开始=================");
//	       sendStr = new String(sendStr.getBytes(), "utf-8");
	       BufferedReader reader = null;
	       StringBuffer buffer = new StringBuffer();
	       try {
	           String strMessage = "";
	           // 接报文的地址
	           URL uploadServlet = new URL(url);
	 
	           HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet
	                  .openConnection();
	           // 设置连接参数
	           servletConnection.setRequestMethod("POST");
	           servletConnection.setDoOutput(true);
	           servletConnection.setDoInput(true);
	           servletConnection.setAllowUserInteraction(true);
	           servletConnection.setUseCaches(false);
	           servletConnection.setRequestProperty("Content-type", "application/json;charset=UTF-8"); 
	           
	           // 开启流，写入XML数据
//	           PrintWriter output = new PrintWriter(new OutputStreamWriter(servletConnection.getOutputStream(),"utf-8"));  
	           OutputStream output = servletConnection.getOutputStream();
	           System.out.println("发送的报文：");
	           System.out.println(sendStr.toString());
	 
	           output.write(sendStr.toString().getBytes("utf8"));
	           output.flush();
	           output.close();
	 
	           // 获取返回的数据
	           InputStream inputStream = servletConnection.getInputStream();
	           reader = new BufferedReader(new InputStreamReader(inputStream));
	           while ((strMessage = reader.readLine()) != null) {
	              buffer.append(strMessage);
	           }
	 
	           System.out.println("接收返回值:" + buffer);
	 
	       } catch (java.net.ConnectException e) {
	           throw new Exception();
	       } finally {
	           if (reader != null) {
	              reader.close();
	           }
	 
	       }
	       return buffer.toString();
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
	
	public String delmenu(String accessToken){
		String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;
		String rs = httpPost(url);
		logger.info(rs);
		String errcode = null;
		if(null!=rs){
			//{"errcode":42001,"errmsg":"access_token expired hint: [MxpV7a0065vr64!]"}
			Map responseMap = JSON.parseObject(rs);
			errcode = String.valueOf(responseMap.get("errcode"));
			String errmsg = String.valueOf(responseMap.get("errmsg"));
		}
		return errcode;
	}
}
