package com.sinontech.pub.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

import com.zjlp.face.pay.util.rsa.SlPayUtil;

public class FacePayService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param goodName 商品名称
	 * @param num 商品数量
	 * @param price 价格
	 * @param tradeNo 订单号
	 * @param token 
	 * @param partnerNo 商户号
	 * @param cell 手机号
	 * @param remark 备注
	 * @param userId
	 * @param syncUrl 同步返回地址
	 * @param businessType 业务编号
	 * @return
	 */
    public String pay(String goodName, String num, String price, String tradeNo, String token, String partnerNo,
                      String cell,String remark, String syncUrl,String asyncUrl) {
        Map<String, String> params = new HashMap<String, String>();
        String rs = null;
        try {
            params.put("goodName", URLEncoder.encode(goodName, "utf-8"));//商品名称
            
            params.put("num", num);
            params.put("price", price);
            params.put("tradeNo", tradeNo);
//            params.put("businessType", businessType.toString());
            params.put("partnerNo", partnerNo);
            params.put("cell", cell);
//            params.put("userId", userId.toString());
            params.put("syncUrl", syncUrl);
            params.put("businessType", "104");
            params.put("remark", URLEncoder.encode(remark, "utf-8"));
            params.put("asyncUrl", asyncUrl);
            String htmlText = SlPayUtil.getReqForm("TestConstantsUtil.SL_PAY_GETWAY", "TestConstantsUtil.PUBLIC_KEY",
                "UTF_8", params);
            rs = htmlText;
//            model.addAttribute("htmlText", htmlText);
        } catch (UnsupportedEncodingException e) {
        }
        return rs;
    }

}
