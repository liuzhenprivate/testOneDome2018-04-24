package com.github.wxpay.sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import common.Logger;


public class TestWXPay {
	protected static final Logger logger = Logger.getLogger(TestWXPay.class);
	
    private WXPay wxpay;
    private WXPayConfigImpl config;
    private String out_trade_no;
    private String total_fee;

    public TestWXPay() throws Exception {
       config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
        total_fee = "1";
        // out_trade_no = "201701017496748980290321";
        out_trade_no = "201613091059590000003433-asd002"; 
    }
    
    /**
     * 查询订单
     * @param out_trade_no
     */
    public Map<String, String> doOrderQuery(String out_trade_no) {
    	Map<String, String> map = new  HashMap<String, String>();
        logger.info("查询订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "wx2017072415573362654b5c030265115779");
        try {
            map = wxpay.orderQuery(data);
            logger.info("rs="+map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 公众号支付  下单
     */
    public  Map<String, String> doUnifiedOrder(String pname,String tmid,String price,String ip,String openid,String notify_url) {
    	Map<String, String> map = new  HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        
        try {
        	notify_url = URLEncoder.encode(notify_url.trim(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        data.put("body", pname);
        data.put("out_trade_no", tmid);
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        data.put("total_fee", price);
        data.put("spbill_create_ip", ip);
        data.put("notify_url", notify_url);
        data.put("trade_type", "JSAPI");
        data.put("openid", openid);
        data.put("sign_type", "MD5");
//        data.put("product_id", "12");
        // data.put("time_expire", "20170112104120");
//        logger.info(data);
        try {
            map = wxpay.unifiedOrder(data);
//            logger.info(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public  Map<String, String> doUnifiedOrder() {
    	Map<String, String> map = new  HashMap<String, String>();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "WEB");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "115.236.8.253");
        data.put("notify_url", "http://renbao.ringsky.cn/renbao/notify");
        data.put("trade_type", "JSAPI");
        data.put("openid", "owanL1LaiHyDit2J_laRoM-NiZqw");
//        data.put("product_id", "12");
        // data.put("time_expire", "20170112104120");

        try {
            map = wxpay.unifiedOrder(data);
            logger.info(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    public void doOrderClose() {
        logger.info("关闭订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.closeOrder(data);
            logger.info(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doOrderQuery() {
        logger.info("查询订单");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "wx2017072415573362654b5c030265115779");
        try {
            Map<String, String> r = wxpay.orderQuery(data);
            logger.info("rs="+r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void doOrderReverse() {
        logger.info("撤销");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
//        data.put("transaction_id", "4008852001201608221962061594");
        try {
            Map<String, String> r = wxpay.reverse(data);
            logger.info(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 长链接转短链接
     * 测试成功
     */
    public void doShortUrl() {
        String long_url = "weixin://wxpay/bizpayurl?pr=etxB4DY";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("long_url", long_url);
        try {
            Map<String, String> r = wxpay.shortUrl(data);
            logger.info(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退款
     * 已测试
     */
    public void doRefund() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_trade_no);
        data.put("total_fee", total_fee);
        data.put("refund_fee", total_fee);
        data.put("refund_fee_type", "CNY");
        data.put("op_user_id", config.getMchID());

        try {
            Map<String, String> r = wxpay.refund(data);
            logger.info(r);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询退款
     * 已经测试
     */
    public void doRefundQuery() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_refund_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.refundQuery(data);
            logger.info(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对账单下载
     * 已测试
     */
    public void doDownloadBill() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("bill_date", "20161102");
        data.put("bill_type", "ALL");
        try {
            Map<String, String> r = wxpay.downloadBill(data);
            logger.info(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGetSandboxSignKey() throws Exception {
        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("mch_id", config.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        String sign = WXPayUtil.generateSignature(data, config.getKey());
        data.put("sign", sign);
        WXPay wxPay = new WXPay(config);
        String result = wxPay.requestWithoutCert("https://api.mch.weixin.qq.com/sandbox/pay/getsignkey", data, 10000, 10000);
        logger.info(result);
    }


//    public void doReport() {
//        HashMap<String, String> data = new HashMap<String, String>();
//        data.put("interface_url", "20160822");
//        data.put("bill_type", "ALL");
//    }

     
    public void testUnifiedOrderSpeed() throws Exception {
        TestWXPay dodo = new TestWXPay();

        for (int i=0; i<100; ++i) {
            long startTs = System.currentTimeMillis();
            out_trade_no = out_trade_no+i;
            dodo.doUnifiedOrder();
            long endTs = System.currentTimeMillis();
            logger.info(endTs-startTs);
            Thread.sleep(1000);
        }

    }

    public static void main(String[] args) throws Exception {
        logger.info("--------------->");
        TestWXPay dodo = new TestWXPay();
//        dodo.doUnifiedOrder();
        //701516E762121BAED53FABF6AA74E3AF975ABD4D81D00AE3D1A748C68BA11A74
        // dodo.doGetSandboxSignKey();

//         dodo.doUnifiedOrder();
//         dodo.doOrderQuery();
        // dodo.doDownloadBill();
        // dodo.doShortUrl();
        // dodo.test001();
        // dodo.doOrderQuery();
        // dodo.doOrderClose();
        // dodo.doRefund();
        // dodo.doRefundQuery();
        // dodo.doOrderReverse();
        // dodo.test001();
        // dodo.testUnifiedOrderSpeed();

       /* dodo.doOrderQuery();
        dodo.doOrderReverse();
        dodo.doOrderQuery();
        dodo.doOrderReverse();
        dodo.doOrderQuery();*/

        dodo.getaccess_token();
//        dodo.createCaidan();
//        dodo.delCaidan();
        
        
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
        logger.info(last);
        
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        // 打印
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        logger.info(format1.format(calendar.getTime()));
        logger.info("<---------------"); // wx2016112510573077
    }
    public void getuserinfo(){
    	String url="https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
    	String info="{"+
					   "\"user_list\": ["+
					       "{"+
					           "\"openid\": \"otvxTs4dckWG7imySrJd6jSi0CWE\","+ 
					           "\"lang\": \"zh_CN\""+
					     "  }, "+
					     "  {"+
					     "      \"openid\": \"otvxTs_JZ6SEiP0imdhpi50fuSZg\", "+
					     "      \"lang\": \"zh_CN\""+
					     "  }"+
					  " ]"+
					"}";
    	String rs = post(url,info);
    	logger.info(rs);
    }
    //https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
    //删除所有菜单
    public void delCaidan(){
    	String url="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=DjFXeBY5Yas9Vu4tNWDf1wvUkJelz6-U2QoU6wugNg6xFnNp2qqVw4Cx07tYDAIdpGwCR-C90T48PZcLJt104ATMcqHIqXWCcI8EiNT3x55kR8IDy0rSFgx7br7-W697ZCEaABATWY";
    	httpGet(url);
    }
    //创建菜单
    public void createCaidan(){
    	String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=DjFXeBY5Yas9Vu4tNWDf1wvUkJelz6-U2QoU6wugNg6xFnNp2qqVw4Cx07tYDAIdpGwCR-C90T48PZcLJt104ATMcqHIqXWCcI8EiNT3x55kR8IDy0rSFgx7br7-W697ZCEaABATWY";
    	String body=" {"+
					     "\"button\":["+
					     "{"+	
					         " \"type\":\"click\","+
					          "\"name\":\"流量充值\","+
					          "\"url\":\"http://renbao.qwqycl.com/renbao/user\","+
//					          "\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx19da6aeb8687d3d3&redirect_uri=http%3A%2F%2Frenbao.qwqycl.com%2Frenbao%2Fuser&response_type=code&scope=snsapi_base&connect_redirect=1#wechat_redirect\","+
					          "\"key\":\"V1001_FLOW_ORDER\""+
					      "}]}";
    	post(url,body);
    }
    public void getaccess_token() throws Exception {
//        WXPayConfigImpl config = WXPayConfigImpl.getInstance();
       /* HashMap<String, String> data = new HashMap<String, String>();
        data.put("grant_type", "client_credential");
        data.put("appid",  config.getAppID());
        data.put("secret", config.getKey());*/
//        String sign = WXPayUtil.generateSignature(data, config.getKey());
//        data.put("sign", sign);
//        WXPay wxPay = new WXPay(config);
//        String result = wxPay.requestWithoutCert("https://api.weixin.qq.com/cgi-bin/token", data, 10000, 10000);
        String result = httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx06a05bce4754a0d6&secret=ad021dd5ad5164d5cbe97497e85007d4");
        logger.info(result);
        
    }
    //https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
    public static String post(String url,String param) {
		PostMethod postMethod = new PostMethod(url);
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
			logger.info("post状态:"+status);
			logger.info("post结果:"+result);
		} catch (Exception e) {
			result="-1";
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return result;
	}
    
    public   String httpPost(String url){
		
		String result=null;
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		client.getParams().setHttpElementCharset("UTF-8");
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(10000);
		PostMethod post=new PostMethod(url);
		post.setFollowRedirects(true);
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
