package com.alipay.api.share;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import com.sinontech.pub.utils.DateUtil;

public class ShareSign {
    public static void main(String[] args) {
        String jsapi_ticket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    };
    
	public static String getJsapiTicket(ServletContext application,String appid,String secret) {
		String tokenDateNow = DateUtil.getStringNow();
		String jsapiTicket = "";
		String ticketDate = "";
		Object ticketDateObj = application.getAttribute(appid+"tokenDate");
		Object jsapiTicketObj = application.getAttribute(appid+"jsapiTicket");
		if (ticketDateObj == null || jsapiTicketObj == null) {
			jsapiTicket = getNewJsapiTicket(appid, secret);
			application.setAttribute(appid+"jsapiTicket", jsapiTicket);
			application.setAttribute(appid+"tokenDate", tokenDateNow);
		} else {
			ticketDate = ticketDateObj + "";
			jsapiTicket = jsapiTicketObj + "";
			Date date = DateUtil.convertDate(ticketDate, "yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (DateUtil.isOnehour(cal)) {
			} else {
				jsapiTicket = getNewJsapiTicket(appid, secret);
				application.removeAttribute(appid+"jsapiTicket");
				application.removeAttribute(appid+"tokenDate");
				application.setAttribute(appid+"jsapiTicket", jsapiTicket);
				application.setAttribute(appid+"tokenDate", tokenDateNow);
			}
		}
		//System.out.println("my=======jsapiTicket==="+jsapiTicket);
		return jsapiTicket;
	}

	public static String getNewJsapiTicket(String appid,String secret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid +"&secret="+secret+""; // 正式号
		String result = "";
		String acc = "";
		String jsapiTicket = "";
		URL urls;
		try {
			urls = new URL(url);
			HttpURLConnection httpURLConn = (HttpURLConnection) urls
					.openConnection();
			httpURLConn.setDoOutput(true);
			httpURLConn.setRequestMethod("GET");
			httpURLConn.setIfModifiedSince(999999999);
			httpURLConn.connect();
			InputStream in = httpURLConn.getInputStream();
			BufferedReader bd = new BufferedReader(new InputStreamReader(in));
			while ((result = bd.readLine()) != null) {
				JSONObject obj = JSONObject.fromObject(result);
				acc = obj.getString("access_token");
//				System.out.println("obj==="+obj);
//				System.out.println("access_token===="+acc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlTicket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + acc + "&type=jsapi";
		try {
			urls = new URL(urlTicket);
			HttpURLConnection httpURLConn = (HttpURLConnection) urls
					.openConnection();
			httpURLConn.setDoOutput(true);
			httpURLConn.setRequestMethod("GET");
			httpURLConn.setIfModifiedSince(999999999);
			httpURLConn.connect();
			InputStream in = httpURLConn.getInputStream();
			BufferedReader bd = new BufferedReader(new InputStreamReader(in));
			while ((result = bd.readLine()) != null) {
				JSONObject obj = JSONObject.fromObject(result);
				jsapiTicket = obj.getString("ticket");
//				System.out.println("jsapiTicket==="+jsapiTicket);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsapiTicket;
	}
    

    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        //System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
