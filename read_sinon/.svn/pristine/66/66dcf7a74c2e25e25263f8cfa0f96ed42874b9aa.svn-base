package com.sinontech.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class HttpUtils {

	private static final String URL="http://60.191.88.84:8087/charge/open/order.do";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String time = DateUtil.getTime();
		String data="{\"logpwd\":\"shualian\",\"productcode\":\"12100862000070\",\"orderid\":\"TM1498121731206\",\"mobile\":\"13758133121\",\"timelit\":\""+time+"\"}";
		System.out.println(data);
		String datastr = Security.encrypt(data);
		System.out.println(datastr);
		try{  
            byte[] encodeBase64 = Base64.encodeBase64(datastr.getBytes("UTF-8"));  
            datastr =new String(encodeBase64);
            System.out.println("RESULT: " + datastr);  
        } catch(UnsupportedEncodingException e){  
            e.printStackTrace();  
        } 
		String json="{\"logname\":\"shualian\",\"data\":\""+datastr+"\"}";
		 System.out.println(json);
		post(json);
	}

	/**
	 * 只有一个参数
	 * @param url
	 * @param param
	 * @return
	 */
	public static String post(String param) {
		PostMethod postMethod = new PostMethod(URL);
		String result = null;
		int status = 0;
		try {
			RequestEntity requestEntity = new ByteArrayRequestEntity(param.getBytes(), "utf-8");
			postMethod.setRequestEntity(requestEntity);
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
			httpClient.getParams().setContentCharset("UTF-8");
			status = httpClient.executeMethod(postMethod);
			result = postMethod.getResponseBodyAsString();
			System.out.println("post状态:"+status);
			System.out.println("post结果:"+result);
		} catch (Exception e) {
			result="-1";
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return result;
	}
}
