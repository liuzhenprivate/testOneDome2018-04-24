package com.sinontech.util.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSON;

public class WXService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WXService s = new WXService();
		 
//		String r=	s.getaccess_token("wx19da6aeb8687d3d3","dba45754ea1d6e87cc337c2437717c33");
//		System.out.println(r);
//			s.getuserinfo("owanL1LaiHyDit2J_laRoM-NiZqw", "owanL1LaiHyDit2J_laRoM-NiZqw");
		 
	}
	 
	
	public UserInfo getuserinfo(String access_token,String openid){
    	/*String url="https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+access_token;
    	String info="{"+
					   "\"user_list\": ["+
					       "{"+
					           "\"openid\": \""+openid+"\","+ 
					           "\"lang\": \"zh_CN\""+
					     "  }"+
					  " ]"+
					"}";*/
//    	String rs = post(url,info);
//		{"subscribe":1,"openid":"owanL1LaiHyDit2J_laRoM-NiZqw","nickname":"布衣","sex":0,"language":"zh_CN","city":"","province":"",
		//"country":"","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaEINSag9WvlhrfE6WalK4PdtibS2vkhRib55TIr7j9dL2ghN5v2AcbicGTF1hO6icFZZOjBDe0c8NRiaNKg\/0","subscribe_time":1502163079,"remark":"","groupid":0,"tagid_list":[]}
		//{"errcode":40001,"errmsg":"invalid credential, access_token is invalid or not latest hint: [fhbTHA0611vr54!]"}

		UserInfo user = new UserInfo();
    	String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN ";
    	String respStr = httpGet(url);
    	System.out.println(respStr);
    	Map responseMap = JSON.parseObject(respStr);
    	String errcode = String.valueOf(responseMap.get("errcode"));
    	if(null!=errcode && !"".equals(errcode) && !"null".equals(errcode)){
    		return null;
    	}
        String nickname = String.valueOf(responseMap.get("nickname"));
        String sex = String.valueOf(responseMap.get("sex"));
        String city = String.valueOf(responseMap.get("city"));
        String province = String.valueOf(responseMap.get("province"));
        String country = String.valueOf(responseMap.get("country"));
        String headimgurl = String.valueOf(responseMap.get("headimgurl"));
        user.setNickname(nickname);
        if(null!=sex && !"".equals(sex) && !"null".equals(sex)){
        	 try {
				user.setSex(Integer.parseInt(sex));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
        }
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);
        user.setHeadimgurl(headimgurl);
        
    	System.out.println(respStr);
    	
    	return user;
    }
	
	 public String getaccess_token(String appid,String secret)   {
       String respStr = httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
    		   +appid+"&secret="+secret);
       Map responseMap = JSON.parseObject(respStr);
       String access_token = String.valueOf(responseMap.get("access_token"));
        
       System.out.println(respStr);
       return access_token;
       
   }
	 public   String httpGet(String url){
			
			String result=null;
			HttpClient client = new HttpClient();
			client.getParams().setContentCharset("UTF-8");
			client.getParams().setHttpElementCharset("UTF-8");
			client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
			client.getHttpConnectionManager().getParams().setSoTimeout(10000);
			GetMethod get=new GetMethod(url);
			get.setFollowRedirects(true);
			InputStream inputStream = null;
			try{
				client.executeMethod(get);
				inputStream = get.getResponseBodyAsStream();  
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
				StringBuffer stringBuffer = new StringBuffer();  
				String str= "";  
				while((str = br.readLine()) != null){  
				stringBuffer .append(str );  
				}
				result = stringBuffer.toString();
			}
			catch(Exception ex){
			}finally{
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				get.releaseConnection();
			}
			return result;
		}
}
