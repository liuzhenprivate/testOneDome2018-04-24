package com.sinontech.controller.read.channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sinontech.controller.base.BaseController;
import com.sinontech.entity.system.User;
import com.sinontech.service.read.webchat.WebchatService;
import com.sinontech.service.read.webchatmenu.WebchatMenuService;
import com.sinontech.service.read.webchatreply.WebchatReplyService;
import com.sinontech.util.Const;
import com.sinontech.util.PageData;

@Controller
@RequestMapping(value="/webchatinterface")
public class ChannelWebchatInterface  extends BaseController{
	@Resource(name="webchatService")
	private WebchatService webchatService;
	@Resource(name="webchatmenuService")
	private WebchatMenuService webchatmenuService;
	@Resource(name="webchatreplyService")
	private WebchatReplyService webchatreplyService;
	
	/**
	 * 用途说明：自动回复接口
	 * @param WEBCHATID
	 * @param res
	 * 2018年2月11日下午5:13:27
	 * @auther ljj
	 */
	@RequestMapping(value="/index/{WEBCHATID}")
	public void index(@PathVariable Long WEBCHATID,HttpServletRequest request,HttpServletResponse res){
		logBefore(logger, "查询自动回复内容");
		logger.info("token=="+request.getRemoteHost()+"=ip=="+request.getQueryString());
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		logger.info(signature+"="+timestamp+"="+nonce+"="+echostr);
		try {
			BufferedReader br = null;
			String str, wholeStr = "";
			try {
				br = this.getRequest().getReader();
				StringBuffer bf = new StringBuffer();
				while((str = br.readLine()) != null){
					bf =bf.append(str);
				}
				wholeStr = bf.toString();
				System.out.println("registOrUpdateServerInf"+wholeStr);
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
					String MsgType = map.get("MsgType");
					String ToUserName = map.get("ToUserName");
					String FromUserName = map.get("FromUserName");
					maprs.put("ToUserName", FromUserName);
					maprs.put("FromUserName", ToUserName);
					maprs.put("CreateTime", String.valueOf(System.currentTimeMillis()));
					maprs.put("MsgType", "text");
					String replycontent = null;
					if(null!=MsgType && "text".equals(MsgType)){
						String Content = map.get("Content");
						
						if(null!=Content){
							PageData pd = new PageData();
							pd.put("WEBCHAT_ID", WEBCHATID);
							PageData webchat = this.webchatService.findById(pd);
							if(null!=webchat){
								pd.put("KEYWORDS", Content);
								List<PageData> list = this.webchatreplyService.listAll(pd);
								if(null!=list && list.size()>0){
									PageData reply = list.get(0);
									replycontent = String.valueOf(reply.get("CONTENT"));
								}
							}
						}
					}
					if(null!=replycontent){
						maprs.put("Content", replycontent);
					}else{
						PageData pd = new PageData();
						pd.put("WEBCHAT_ID", WEBCHATID);
						PageData webchat = this.webchatService.findById(pd);
						if(null!=webchat){
							pd.put("TYPE", "1");
							List<PageData> list = this.webchatreplyService.listAll(pd);
							if(null!=list && list.size()>0){
								PageData reply = list.get(0);
								replycontent = String.valueOf(reply.get("CONTENT"));
							}
						}
						if(null!=replycontent){
							maprs.put("Content", replycontent);
						}else{
							maprs.put("Content", "-----");
						}
						
					}
					String xml = createxml(maprs);
					write(res,xml);
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
	
	@RequestMapping(value="/followReply/{WEBCHATID}")
	public void followReply(@PathVariable Long WEBCHATID,HttpServletResponse res){
		logBefore(logger, "查询关注自动回复内容");
		try {
			BufferedReader br = null;
			String str, wholeStr = "";
			try {
				br = this.getRequest().getReader();
				StringBuffer bf = new StringBuffer();
				while((str = br.readLine()) != null){
					bf =bf.append(str);
				}
				wholeStr = bf.toString();
				System.out.println("followReplyServerInf"+wholeStr);
				//接收文本信息
				/*<xml> 
				 * <ToUserName>< ![CDATA[toUser] ]></ToUserName> 
				 * <FromUserName>< ![CDATA[FromUser] ]></FromUserName> 
				 * <CreateTime>123456789</CreateTime> 
				 * <MsgType>< ![CDATA[event] ]></MsgType> 
				 * <Event>< ![CDATA[SCAN] ]></Event> 
				 * <EventKey>< ![CDATA[SCENE_VALUE] ]></EventKey> 
				 * <Ticket>< ![CDATA[TICKET] ]></Ticket> 
				 * </xml>*/
				if(!"".equals(wholeStr)){
					Map<String,String> maprs =  new HashMap<String,String>();
					Map<String,String> map =  domxml(wholeStr);
					String MsgType = map.get("MsgType");
					String ToUserName = map.get("ToUserName");
					String FromUserName = map.get("FromUserName");
					maprs.put("ToUserName", FromUserName);
					maprs.put("FromUserName", ToUserName);
					maprs.put("CreateTime", String.valueOf(System.currentTimeMillis()));
					maprs.put("MsgType", "text");
					String replycontent = null;
					if(null!=MsgType && "text".equals(MsgType)){
						String Content = map.get("Content");
						
						if(null!=Content){
							PageData pd = new PageData();
							pd.put("WEBCHAT_ID", WEBCHATID);
							PageData webchat = this.webchatService.findById(pd);
							if(null!=webchat){
								pd.put("KEYWORDS", Content);
								List<PageData> list = this.webchatreplyService.listAll(pd);
								if(null!=list && list.size()>0){
									PageData reply = list.get(0);
									replycontent = String.valueOf(reply.get("CONTENT"));
								}
							}
						}
					}else if(null!=MsgType && "event".equals(MsgType)){
						String Event = map.get("Event");
						//关注，扫描关注
						if("subscribe".equals(Event) || "SCAN".equals(Event)){
							
						}else if("unsubscribe".equals(Event)){
							//取消关注
							
						}
					}
					if(null!=replycontent){
						maprs.put("Content", replycontent);
					}else{
						maprs.put("Content", "-----");
					}
					String xml = createxml(maprs);
					write(res,xml);
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
	public static void main(String[] args){
		 
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
            if(null!=root.getElementsByTagName("Content")){
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