package com.sinontech.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import common.Logger;

/**
 * 
 * @author LJJ
 *
 */
public class RechareThread implements Runnable{
	protected static final Logger logger = Logger.getLogger(RechareThread.class);
	 
	private String url;
	private Map<String,String> map = new HashMap<String,String>();

    public RechareThread(String url,Map<String,String> map) { 
        this.url = url; 
        this.map=map;
    } 
    public static void main(String[] args) { 
    	RechareThread ds1 = new RechareThread("阿三",null); 
    	Thread t1 = new Thread(ds1); 
        t1.start();
    } 
    public void run() { 
    	logger.info("启动线程啦：url="+url.toString()+"=="+map.toString());	
    	//建议通知频率  15/30/60/180/1800/1800/1800/3600?秒
    	int k=1000;//秒
    	boolean flag =true;
    	int i=2;
    	int m=1;
    	do{
    		try {
				String rs =  httpPost(url.toString(),map);
				logger.info("url="+url.toString()+"=="+rs);
//    			String rs="";
    			logger.info(m*k+"=="+new Date());
    			/*if(m==8){
    				rs="success";
    			}*/
				if("success".equals(rs)){
					flag = false;
				}
				m=m*i;
				Thread.sleep(k*m);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    	}while(flag);
    	
    	logger.info("启动结束啦：url="+url.toString()+"=="+map.toString());	
        
    } 
    public   String httpPost(String url,Map<String,String> params){
		String result=null;
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		client.getParams().setHttpElementCharset("UTF-8");
		PostMethod post=new PostMethod(url);
		post.setFollowRedirects(false);
		InputStream inputStream = null;
		try{
			 if(params != null){  
		            Set<String> keySet = params.keySet();  
		            NameValuePair[] param = new NameValuePair[keySet.size()];  
		            int i = 0;  
		            for(String key : keySet){  
		                param[i] = new NameValuePair(key, params.get(key));  
		                i++;  
		            }  
		            post.setRequestBody(param);  
		        }  
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
