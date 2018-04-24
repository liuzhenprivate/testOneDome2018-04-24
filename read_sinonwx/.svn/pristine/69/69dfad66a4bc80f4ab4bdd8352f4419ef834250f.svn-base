package com.sinontech.phone.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HtmlPaseUtils {
	public final static int ERR_CODE = -1;
	public final static int ERR_CODE_2 = -2;

	// 1分钟==6W 10*
	public final static Long sleepTime = 600000L;

	public static ResultModel readHtmlAsGBK(String myurl) {
		StringBuffer sb = new StringBuffer("");
		URL url;
		ResultModel bean = new ResultModel();
		try {
			url = new URL(myurl);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			// connection.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 5.2) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					httpURLConnection.getInputStream(), "gbk"));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			bean.setCode(ERR_CODE);
			bean.setMsg(exception);
			bean.setResult("");
			return bean;
		}
		bean.setCode(0);
		bean.setMsg("ok");
		bean.setResult(sb.toString());
		return bean;
	}
	
	public static ResultModel readHtmlAsGB2312(String myurl) {
		StringBuffer sb = new StringBuffer("");
		URL url;
		ResultModel bean = new ResultModel();
		try {
			url = new URL(myurl);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 5.2) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");
			InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), "GB2312");
			BufferedReader br = new BufferedReader(inputStreamReader);
			
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			s = new String(sb.toString().getBytes("GB2312"),"UTF-8");
			System.out.println(s.toString());
		} catch (Exception e) {
			e.printStackTrace();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			bean.setCode(ERR_CODE);
			bean.setMsg(exception);
			bean.setResult("");
			return bean;
		}
		bean.setCode(0);
		bean.setMsg("ok");
		bean.setResult(sb.toString());
		return bean;
	}

	public static ResultModel readHtmlAsUTF8(String myurl) {
		StringBuffer sb = new StringBuffer("");
		ResultModel bean = new ResultModel();
		URL url;
		try {
			url = new URL(myurl);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			// connection.setRequestProperty("User-Agent",
			// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  

//			connection
//					.setRequestProperty(
//							"User-Agent",
//							"Mozilla/5.0 (Windows NT 5.2) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30");
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			int indexOf = exception.indexOf(":");
			String substring = exception.substring(0, indexOf);
			if (substring.contains("java.io.FileNotFoundException")) {
				bean.setCode(ERR_CODE_2);
			} else {
				bean.setCode(ERR_CODE);
			}
			bean.setMsg(exception);
			bean.setResult("");
			return bean;
		}
		bean.setCode(0);
		bean.setMsg("ok");
		bean.setResult(sb.toString());
		return bean;
	}

	public static void sleepThead() {
		System.err.println("爬取失败，，正在睡眠10分钟………………");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final static String getByString(String url) throws Exception {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
          
        try {  
            HttpGet httpget = new HttpGet(url);  
            httpget.addHeader("Accept", "text/html");  
        httpget.addHeader("Accept-Charset", "utf-8");  
            httpget.addHeader("Accept-Encoding", "gzip");  
        httpget.addHeader("Accept-Language", "en-US,en");  
        httpget.addHeader("User-Agent",  
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");  
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {  
   
                public String handleResponse(  
                        final HttpResponse response) throws ClientProtocolException, IOException {  
                    int status = response.getStatusLine().getStatusCode();  
                    if (status >= 200 && status < 300) {  
                        HttpEntity entity = response.getEntity();  
                        System.out.println(status);  
                        return entity != null ? EntityUtils.toString(entity) : null;  
                    } else {  
                        System.out.println(status);  
                        Date date=new Date();  
                        System.out.println(date);  
                        System.exit(0);  
                        throw new ClientProtocolException("Unexpected response status: " + status);  
                    }  
                }  
            };  
            String responseBody = httpclient.execute(httpget, responseHandler);  
            return responseBody;  
        } finally {  
            httpclient.close();  
        }  
    }  

	public static class ResultModel {
		public ResultModel() {
		}

		int code;
		String msg;
		String result;

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "ResultModel [code=" + code + ", msg=" + msg + ", result="
					+ result + "]";
		}

	}
}
