package com.sinontech.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sinontech.controller.base.BaseController;
import com.sinontech.modle.Article;
import com.sinontech.modle.ArticleCategory;
import com.sinontech.modle.ArticleChapter;
import com.sinontech.modle.ArticleChapterLog;
import com.sinontech.modle.Html;
import com.sinontech.modle.HtmlModle;
import com.sinontech.modle.HtmlModleDetail;
import com.sinontech.modle.HtmlModleDetailInfo;
import com.sinontech.modle.HtmlModleInfo;
import com.sinontech.modle.Label;
import com.sinontech.modle.SearchLog;
import com.sinontech.modle.UserInfo;
import com.sinontech.modle.Webchat;
import com.sinontech.modle.WebchatReply;
import com.sinontech.pub.utils.DateUtil;
import com.sinontech.service.impl.ArticleCategoryServiceImpl;
import com.sinontech.service.impl.ArticleChapterLogServiceImpl;
import com.sinontech.service.impl.ArticleServiceImpl;
import com.sinontech.service.impl.HtmlModleDetailServiceImpl;
import com.sinontech.service.impl.HtmlModleServiceImpl;
import com.sinontech.service.impl.LabelServiceImpl;
import com.sinontech.service.impl.SearchkeyServiceImpl;
import com.sinontech.service.impl.SearchlogServiceImpl;
import com.sinontech.service.impl.SignlogServiceImpl;
import com.sinontech.service.impl.SignsetServiceImpl;
import com.sinontech.service.impl.UserInfoServiceImpl;
import com.sinontech.service.impl.WebchatReplyServiceImpl;
import com.sinontech.service.impl.WebchatServiceImpl;

import common.Logger;

@Controller("readcontroller")
@RequestMapping(value = "/read")
public class ReadController extends BaseController{
	protected static final Logger logger = Logger.getLogger(ReadController.class);
	
	@Autowired
	UserInfoServiceImpl userinfoService;
	@Autowired
	WebchatServiceImpl webchatServiceImpl;
	@Autowired
	SignlogServiceImpl signlogServiceImpl;
	@Autowired
	SignsetServiceImpl signsetServiceImpl;
	@Autowired
	HtmlModleServiceImpl htmlModleServiceImpl;
	@Autowired
	HtmlModleDetailServiceImpl htmlModleDetailServiceImpl;
	@Autowired
	ArticleServiceImpl articleServiceImpl;
	@Autowired
	SearchkeyServiceImpl searchkeyServiceImpl;
	@Autowired
	SearchlogServiceImpl searchlogServiceImpl;
	@Autowired
	ArticleCategoryServiceImpl articleCategoryServiceImpl;
	@Autowired
	ArticleChapterLogServiceImpl articleChapterLogServiceImpl;
	@Autowired
	WebchatReplyServiceImpl webchatReplyServiceImpl;
	@Autowired
	LabelServiceImpl labelServiceImpl;
	
	@RequestMapping(value = "/bookstore/{webchatId}/{placType}")
	public String bookstore(@PathVariable long webchatId,@PathVariable int placType,HttpServletRequest request, HttpServletResponse response) {
		//logger.info("欢迎进入+user=" );
		
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.info("-----------------"+chat.toString());  
 		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/read/bookstore/"+webchatId+"/"+placType);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		//书城首页顶部模块
//		int placType =1;
		Html html = this.htmlModleServiceImpl.findHtmlByPlacType(placType);
		if(null!=html){
			List<HtmlModleInfo> htmlModleInfolist = getHtmlInfo(html);
			 
			request.setAttribute("html", html);
			request.setAttribute("htmlModleInfolist", htmlModleInfolist);
			if(1==placType){
				//用户最新的阅读记录
				ArticleChapterLog articleChapterLog = this.articleChapterLogServiceImpl.getNewArticleChapterLogByUserId(user.getId());
				if(null!=articleChapterLog){
					request.setAttribute("articleChapterLog", articleChapterLog);
				}
			}
		}
		
//		request.getSession().setAttribute("userinfo", user);
		request.setAttribute("user", user);
		request.setAttribute("webchatId", webchatId);
		request.setAttribute("placType", placType);
		return "home/read/bookstore/bookStore";
	}
	
	public List<HtmlModleInfo> getHtmlInfo(Html html){
		List<HtmlModleInfo> htmlModleInfolist = new ArrayList<HtmlModleInfo>();
		List<HtmlModle> list = this.htmlModleServiceImpl.getHtmlModleByHtmlId(html.getId());
		if(null!=list){
			for(HtmlModle modle:list){
				HtmlModleInfo modleinfo = getHtmlModleInfo(modle);
				//模板类型1书籍 2专题
				int type = modleinfo.getModleType();
				List<HtmlModleDetail> detaillist= this.htmlModleDetailServiceImpl.gethtmlModleDetailByHtmlmodleId(modle.getId());
				if(null!=detaillist){
					List<HtmlModleDetailInfo> infolist = new ArrayList<HtmlModleDetailInfo>();
					for(HtmlModleDetail htmlModleDetail:detaillist){
						HtmlModleDetailInfo info = getHtmlModleDetailInfo(htmlModleDetail);
						if(type==1){
							Article article = this.articleServiceImpl.getArticleByArticleId(htmlModleDetail.getArticleID());
							info.setArticle(article);
						}
						infolist.add(info);
					}
					modleinfo.setHtmlModleDetaillist(infolist);
				}
				htmlModleInfolist.add(modleinfo);
			}
		}
		return htmlModleInfolist;
	}
	
	@RequestMapping(value = "/articleCategory/{webchatId}")
	public String articleCategory(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response) {
		//logger.info("欢迎进入+user=" );
		
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		//logger.info("-----------------"+chat.toString());    
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/read/articleCategory/"+webchatId);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		List<ArticleCategory> girllist = this.articleCategoryServiceImpl.getListByCategoryType(0);
		List<ArticleCategory> boylist = this.articleCategoryServiceImpl.getListByCategoryType(1);
		request.setAttribute("webchatId", webchatId);
		request.setAttribute("girllist", girllist);
		request.setAttribute("boylist", boylist);
		return "home/read/bookstore/classify";
	}
	
	@RequestMapping(value = "/search/{webchatId}")
	public String search(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response) {
		//logger.info("欢迎进入+user=" );
		
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/read/search/"+webchatId);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		//logger.info("-----------------"+chat.toString());    
		String key = request.getParameter("key");
		if(null!=key && !"".equals(key) && !"null".equals(key)){
			SearchLog log =new SearchLog();
			log.setCreateTime(DateUtil.getStringNow());
			log.setKeyword(key);
			if(null!=user){
				log.setUserId(user.getId());
			}
			this.searchlogServiceImpl.saveSearchLog(log);
		}
		List<Article> list = this.articleServiceImpl.getArticleByKey(key);
		System.out.println(list.toString());
//		UserInfo user = this.userinfoService.getUserInfoById(webchatId,userid);
//		request.getSession().setAttribute("userinfo", user);
		request.setAttribute("key", key);
		request.setAttribute("list", list);
		request.setAttribute("webchatId", webchatId);
		request.setAttribute("user", user);
		return "home/read/bookstore/searchresult";
	}
	
	@RequestMapping(value = "/listCategoryArticle/{webchatId}/{articleCategoryId}")
	public String listCategoryArticle(@PathVariable long webchatId,@PathVariable long articleCategoryId,HttpServletRequest request, HttpServletResponse response) {
		//logger.info("欢迎进入+user=" );
		Webchat chat = this.webchatServiceImpl.getWebchatById(webchatId);
		if(null==chat){
			return null;
		}
		UserInfo user = (UserInfo) request.getSession().getAttribute("userinfo");
		if(null==user){
			String CODE = request.getParameter("code");
			if(null!=CODE && !"".equals(CODE) && !"null".equals(CODE)){
					//通过CODE获取到用户信息
				 	user = getUserInfo(chat,CODE);
				 	if(null!=user){
				 		request.getSession().setAttribute("userinfo", user);
				 	}else{
				 		return null;
				 	}
			}else{
				return getWXCode(chat,"/read/listCategoryArticle/"+webchatId+"/"+articleCategoryId);
			}
		}else{
			//判断未关注用户更新用户信息
			if(user.getFollowstate()==0){
				user = updateUserInfo(chat,user);
			}
		} 
		String  sort = "";
		if(request.getParameter("sort")!=null&&!"".equals(request.getParameter("sort"))){
			sort = request.getParameter("sort");
		}else{
			sort = "0";
		}
		String labelId = "";
		if(request.getParameter("labelId")!=null&&!"".equals(request.getParameter("labelId"))){
			labelId = request.getParameter("labelId");
		}
		String feeType = null;
		if(request.getParameter("feeType")!=null&&!"".equals(request.getParameter("feeType"))){
			feeType = request.getParameter("feeType");
		}
		String serialState = null;
		if(request.getParameter("serialState")!=null&&!"".equals(request.getParameter("serialState"))){
			serialState = request.getParameter("serialState");
		}
		ArticleCategory articleCategory = this.articleCategoryServiceImpl.getArticleCategoryById(articleCategoryId);
		List<Article> list = this.articleServiceImpl.getArticleByArticleCategoryId(articleCategoryId,sort,labelId,feeType,serialState);
		List<Label> labelList = this.labelServiceImpl.getLabelList();
		request.setAttribute("labelList", labelList);
		request.setAttribute("list", list);
		request.setAttribute("webchatId", webchatId);
		request.setAttribute("articleCategory", articleCategory);
		request.setAttribute("user", user);
		request.setAttribute("sort", sort);
		request.setAttribute("labelId", labelId);
		request.setAttribute("feeType", feeType);
		request.setAttribute("serialState", serialState);
		return "home/read/bookstore/categoryArticle";
	}
	 
	@RequestMapping(value = "/token/{webchatId}")
	public void token(@PathVariable long webchatId,HttpServletRequest request, HttpServletResponse response) {
		logger.info("token=="+request.getRemoteHost()+"=ip=="+request.getQueryString());
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String openid = request.getParameter("openid");
		logger.info(signature+"="+timestamp+"="+nonce+"="+echostr);
//		response("success",response);
		try {
			BufferedReader br = null;
			String str, wholeStr = "";
			try {
				br = request.getReader();
				StringBuffer bf = new StringBuffer();
				while((str = br.readLine()) != null){
					bf =bf.append(str);
				}
				wholeStr = bf.toString();
				System.out.println("registOrUpdateServerInf===="+wholeStr);
				//接收文本信息
				/*<xml>  
				<ToUserName>< ![CDATA[toUser] ]></ToUserName>  
				<FromUserName>< ![CDATA[fromUser] ]></FromUserName>  
				<CreateTime>1348831860</CreateTime>  
				<MsgType>< ![CDATA[text] ]></MsgType>  
				<Content>< ![CDATA[this is a test] ]></Content>  
				<MsgId>1234567890123456</MsgId>  
				</xml>*/
				if(!"".equals(wholeStr)){
					Map<String,String> maprs =  new HashMap<String,String>();
					Map<String,String> map =  domxml(wholeStr);
					System.out.println("map=="+map.toString());
					String MsgType = map.get("MsgType");
					String ToUserName = map.get("ToUserName");
					String FromUserName = map.get("FromUserName");
					String CreateTime = map.get("CreateTime");
					maprs.put("ToUserName", FromUserName);
					maprs.put("FromUserName", ToUserName);
					maprs.put("CreateTime", CreateTime);
					maprs.put("MsgType", "text");
					String replycontent = null;
					if(null!=MsgType && "text".equals(MsgType)){
						String Content = map.get("Content");
						System.out.println("Content=="+Content);
						if(null!=Content){
							 Webchat webchat = this.webchatServiceImpl.getWebchatById(webchatId);
							if(null!=webchat){
								List<WebchatReply> list = this.webchatReplyServiceImpl.getWebchatReplyByKey(webchatId,  Content); 
								if(null!=list && list.size()>0){
									replycontent = list.get(0).getContent();
								}else{
									response("success",response);
									return ;
								}
							}else{
								response("success",response);
								return ;
							}
						}else{
							response("success",response);
							return ;
						}
					}else if(null!=MsgType && "event".equals(MsgType)){
						String Event = map.get("Event");
						UserInfo user = this.userinfoService.getUserInfoByOpenId(openid);
						//关注，扫描关注
						if("subscribe".equals(Event) || "SCAN".equals(Event)){
							Webchat webchat = this.webchatServiceImpl.getWebchatById(webchatId);
							List<WebchatReply> list = this.webchatReplyServiceImpl.getWebchatReply(webchatId, 1); 
							if(null!=list && list.size()>0){
								replycontent = list.get(0).getContent();
							}else{
								if(null!=webchat){
									replycontent ="欢迎关注「"+webchat.getName()+"」";
								}else{
									replycontent ="欢迎关注";
								}
							}
							if(null!=user){
								
								if(null!=webchat){
									ArticleChapterLog articleChapterlog = this.articleChapterLogServiceImpl.getNewArticleChapterLogByUserId(user.getId());
									if(null!=articleChapterlog){
										replycontent ="欢迎关注「"+webchat.getName()+"」, 您上次看到了《"+articleChapterlog.getArticle().getArticleName()+"》"+articleChapterlog.getChapterName()+",&lt;a href=&quot;"+webchat.getDomainName()+"/articlechapter/articlechapterGetById/"
												+webchatId+"/"+articleChapterlog.getArticleChapter().getId()+"/"+user.getId()+"/0.do&quot;&gt;【点此继续阅读】&lt;/a&gt;";
										System.out.println("replycontent===================="+replycontent);
									}
								}
							}
						}else if("unsubscribe".equals(Event)){
							//取消关注
							if(null!=user){
								if(user.getFollowstate()==1){
									user.setFollowstate(0);
									this.userinfoService.updateUserInfo(user);
								}
							}
							response("success",response);
							return ;
						}
					}else{
						response("success",response);
						return ;
					}
					if(null!=replycontent){
						maprs.put("Content", replycontent);
					}else{
						response("success",response);
						return ;
						
					}
					String xml = createxml(maprs);
					System.out.println("xml="+xml);
					write(response,xml);
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(null!=br){
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		 
	}
	
	@RequestMapping(value = "/pay/notify")
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		logger.info("notify=="+request.getRemoteHost()+"=ip");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		logger.info(signature+"="+timestamp+"="+nonce+"="+echostr);
		 response(echostr,response);
	}
	
	public   Map<String,String> domxml(String xml){
		Map<String,String> map = new HashMap<String,String>();
		//创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //创建一个DocumentBuilder的对象
        StringReader reader = null; 
        InputSource source = null;  
        try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            reader = new StringReader(xml);  
            source = new InputSource(reader);//使用字符流创建新的输入源  
            Document document = db.parse(source);  
            //通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
//            Document document = db.parse("books.xml");
            //获取所有book节点的集合
            Element root = document.getDocumentElement();
            if(null!=root.getElementsByTagName("ToUserName").item(0)){
            	String ToUserName = root.getElementsByTagName("ToUserName").item(0).getTextContent();
            	map.put("ToUserName", ToUserName);
            }
            if(null!=root.getElementsByTagName("FromUserName").item(0)){
            	String FromUserName = root.getElementsByTagName("FromUserName").item(0).getTextContent();
            	map.put("FromUserName", FromUserName);
            }
            if(null!=root.getElementsByTagName("CreateTime").item(0)){
            	String CreateTime = root.getElementsByTagName("CreateTime").item(0).getTextContent();
            	map.put("CreateTime", CreateTime);
            }
            if(null!=root.getElementsByTagName("MsgType")){
	            //text、image、voice、video、shortvideo、location、消息类型，link
	            String MsgType = root.getElementsByTagName("MsgType").item(0).getTextContent();
	            map.put("MsgType", MsgType);
            }
            if(null!=root.getElementsByTagName("Content").item(0)){
            	String Content = root.getElementsByTagName("Content").item(0).getTextContent();
            	map.put("Content", Content);
            }
            if(null!=root.getElementsByTagName("MsgId").item(0)){
            	String MsgId = root.getElementsByTagName("MsgId").item(0).getTextContent();
            	map.put("MsgId", MsgId);
            }
            if(null!=root.getElementsByTagName("PicUrl").item(0)){
            	String PicUrl = root.getElementsByTagName("PicUrl").item(0).getTextContent();
            	map.put("PicUrl", PicUrl);
            }
            if(null!=root.getElementsByTagName("MediaId").item(0)){
            	String MediaId = root.getElementsByTagName("MediaId").item(0).getTextContent();
            	map.put("MediaId", MediaId);
            }
            if(null!=root.getElementsByTagName("Format").item(0)){
            	String Format = root.getElementsByTagName("Format").item(0).getTextContent();
            	map.put("Format", Format);
            }
            if(null!=root.getElementsByTagName("Recognition").item(0)){
            	String Recognition = root.getElementsByTagName("Recognition").item(0).getTextContent();
            	map.put("Recognition", Recognition);
            }
            if(null!=root.getElementsByTagName("ThumbMediaId").item(0)){
            	String ThumbMediaId = root.getElementsByTagName("ThumbMediaId").item(0).getTextContent();
            	map.put("ThumbMediaId", ThumbMediaId);
            }
            if(null!=root.getElementsByTagName("Location_X").item(0)){
            	String Location_X = root.getElementsByTagName("Location_X").item(0).getTextContent();
            	map.put("Location_X", Location_X);
            }
            if(null!=root.getElementsByTagName("Location_Y").item(0)){
            	String Location_Y = root.getElementsByTagName("Location_Y").item(0).getTextContent();
            	map.put("Location_Y", Location_Y);
            }
            if(null!=root.getElementsByTagName("Scale").item(0)){
	            //地图缩放大小
	            String Scale = root.getElementsByTagName("Scale").item(0).getTextContent();
	            map.put("Scale", Scale);
            }
            if(null!=root.getElementsByTagName("Label").item(0)){
	            //地理位置信息
	            String Label = root.getElementsByTagName("Label").item(0).getTextContent();
	            map.put("Label", Label);
            }
            if(null!=root.getElementsByTagName("Title").item(0)){
	            //消息标题
	            String Title = root.getElementsByTagName("Title").item(0).getTextContent();
	            map.put("Title", Title);
            }
            if(null!=root.getElementsByTagName("Description").item(0)){
	            //消息描述
	            String Description = root.getElementsByTagName("Description").item(0).getTextContent();
	            map.put("Description", Description);
            }
            if(null!=root.getElementsByTagName("Url").item(0)){
	            //消息链接
	            String Url = root.getElementsByTagName("Url").item(0).getTextContent();
	            map.put("Url", Url);
            }
            
            if(null!=root.getElementsByTagName("Event").item(0)){
	            //事件类型，subscribe
	            String Event = root.getElementsByTagName("Event").item(0).getTextContent();
	            map.put("Event", Event);
            }
            if(null!=root.getElementsByTagName("EventKey").item(0)){
	            //事件KEY值，qrscene_为前缀，后面为二维码的参数值
	            String EventKey = root.getElementsByTagName("EventKey").item(0).getTextContent();
	            map.put("EventKey", EventKey);
            }
            if(null!=root.getElementsByTagName("Ticket").item(0)){
	            //二维码的ticket，可用来换取二维码图片
	            String Ticket = root.getElementsByTagName("Ticket").item(0).getTextContent();
	            map.put("Ticket", Ticket);
            }
           System.out.println(map.toString());
//            System.out.println(ToUserName+"="+FromUserName+"="+CreateTime+"="+MsgType+"="+Content+"="+MsgId);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }     
        return map;
	}
	public   String createxml(Map<String,String> map){
		StringBuffer xml = new StringBuffer("<xml>");
		if(null!=map && map.size()>0){
			for (Map.Entry<String, String> entry : map.entrySet()) { 
				String key = entry.getKey();
				String value = entry.getValue();
				xml.append("<").append(key).append(">")
				.append(value).append("</").append(key).append(">");
			    System.out.println("Key = " + key + ", Value = " + value); 
			}
		}
		xml.append("</xml>");
		return xml.toString();
	}
	public void write(HttpServletResponse response,String responsejson){
		PrintWriter out;
		response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
			System.out.println(responsejson);
			out.write(responsejson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
    
}