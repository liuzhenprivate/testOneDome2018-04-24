package com.sinontech.pub.utils.ali;

import javax.crypto.Cipher;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sinontech.pub.utils.DateUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
 

public class RSA{
   
   public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
   
   public static void main(String[] args){
	   String publicKeyString="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5B0FitAaL3tehyzNp6gS96QPkuREWucJ0S70K+CqoDAMZU/y6bPsCrnc5Pv27uqumSgjPz3PaI8EPuNdqO2cKH/JPygJlRAP8LutMM19gT346O9cTRxVgwHUd4Uf363e8NoDS0S7YE0iOXk5yJfPchASCZoDv8782alklVXsbACA63yYocTBELj3iPW5WcIEItzZTuwaJguMd8AErZcXq2mpX3iTunIlVAdcaXmC4AxKPaW9GCbEi2sqfGitPs6glK5lm/SDuX9I2L1SVpbDX3uPqE8i61H56+Ww1XJjPeMGpvp4+5I+o/RZ+eZfrWIBDAqe1JBj2VlfvqtnpIecjQIDAQAB";  
	   String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkHQWK0Bove16HLM2nqBL3pA+S5ERa5wnRLvQr4KqgMAxlT/Lps+wKudzk+/bu6q6ZKCM/Pc9ojwQ+412o7Zwof8k/KAmVEA/wu60wzX2BPfjo71xNHFWDAdR3hR/frd7w2gNLRLtgTSI5eTnIl89yEBIJmgO/zvzZqWSVVexsAIDrfJihxMEQuPeI9blZwgQi3NlO7BomC4x3wAStlxeraalfeJO6ciVUB1xpeYLgDEo9pb0YJsSLayp8aK0+zqCUrmWb9IO5f0jYvVJWlsNfe4+oTyLrUfnr5bDVcmM94wam+nj7kj6j9Fn55l+tYgEMCp7UkGPZWV++q2ekh5yNAgMBAAECggEBAMnS45Rtir136aqveUVc72srPJw6WkATKhxp7p7jqBMSb+UzVzhud+kFiOsQAupfuRrI6GaLyXY5EwiRIczUbdVnOicmxVOdYn0bj6AeL2yp6BAvtvqClyXR+KnGnuzIaPev7DosFZyeoik3mGhh6KiCj3LvUWHjQNNTjCqUWxkxrML/Blhd1MMszpEIiPNW5+EZ36rMVmdA0Sed4E0vgNpX7Scmf85eWT4jVW92Md4zsRkScNrf9loQ9y5oYI9/0vU/FUvrs2P6Z7cAbxXZyBjlheQEdW7IbGMWPiiQosDNlpo5xMIr3FLB8GzXcdCAQerlILiG5kskNTUEfWmgFgECgYEA/YjEi/Bsu3LpdJi48ltNw4m3t21xhy5rGRlVBuJHWoo4Ptzr3Gh2MCHMlI43ZYbNw5j/iaGE3U+loYvFnVIbZ5pjOXesxDycXMpaYdnUp+RwfcAYbbQGFPOJeXarPIhrA+93cJ1OzIh4p4i20wLl/9aXBcaORnbGObIHm3uTNqcCgYEA5lT2dEoMqVsejNZsIf8pQOqDyWsslQAARbKyKWambEYZXYQohIIrQMv1LzDSo3mSqVd+mUSLgUFj+a854i+GJK5820fg5iEiclZDH9vxKargjMdkFnkkf58PNoceaZlIJ4534EM0njFjqlLqwHsTYD3BiIkHoULhsu+QXFhLbasCgYEAs/U+PW48X5kjKEW3ER/JxL+eDa67ZI6IKlqWxbobl6KAZe595AapzWRQSCniHEhhCTMyPsjM/CGqmU4SyKYdGZ62S3YsxWWYPyzKlqX9keZOCpQtQsEDiB+NNxAKX38dJr/08ZRW+EruCUy7KG/oPX5d2BWlq1JnRVCeobhHOAUCgYBmfaP09wZPTbZ4YOlY71uCAViHBeOeoZN9cBiLB5xCTab3z9jfrbRlbtw5FXaFO5GhPMeel7uj1l8QOYJ+PTtpkyMHyCIxBMl5Kx1PTeUv71NgEVFeFqXBDBwO3W0Q/B35TBHXftApHD0HC0JeR+XFS3WUtbTpcwNPRf5suPVHZQKBgEAgiSZqSivtCGPgf/oR0b2Kno0PrGHMJZzNcFtgQ3CA+pFIxyTKMNntdjfyq0m53Qyo4G6oFy4bBxlsvU05FbjL77oX+7ZT9+V708hFSnl3ag143QM6VyATJ/Rxlz7QRTQzhu8CpHsVqmfYG2IWRWggmZp6QN4j5I0N8fdSJ18G";
	     
	try {
		String sign1 = AlipaySignature.rsa256Sign("body=xwzx&charset=utf-8&mchid=xwzx&out_trade_no=6454777707669772742&payamount=1&paytime=2017-08-16 15:54:05&paytype=2&timestamp=1502870045591&total_amount=1.00&trade_no=TM111&trade_state_desc=支付成功&trade_status=SUCCESS&userguid=648000000001", privateKey, "utf-8");
		System.out.println(sign1);
//		String sign2 = AlipaySignature.rsaEncrypt("hello world", publicKeyString, "utf-8");
//		System.out.println(sign2+"===sign2=");
		
//		String s = AlipaySignature.rsaDecrypt(sign1,privateKey, "utf-8");
//		System.out.println(s);
//		boolean fl = AlipaySignature.rsa256CheckContent("hello world", sign1, publicKeyString, "utf-8");
//		System.out.println(fl);
//		Map<String,String> params = new HashMap<String,String>();
//		params.put("biz_content", "M0qGiGz+8kIpxe8aF4geWJdBn0aBTuJRQItLHo9R7o5JGhpic/MIUjvXo2BLB++BbkSq2OsJCEQFDZ0zK5AJYwvBgeRX30gvEj6eXqXRt16/IkB9HzAccEqKmRHrZJ7PjQWE0KfvDAHsJqFIeMvEYk1Zei2QkwSQPlso7K0oheo/iT+HYE8aTATnkqD/ByD9iNDtGg38pCa2xnnns63abKsKoV8h0DfHWgPH62urGY7Pye3r9FCOXA2Ykm8X4/Bl1bWFN/PFCEJHWe/HXj8KJKjWMO6ttsoV0xRGfeyUO8agu6t587Dl5ux5zD/s8Lbg5QXygaOwo3Fz1G8EqmGhi4+soEIQb8DBYanQOS3X+m46tVqBGMw8Oe+hsyIMpsjwF4HaPKMr37zpW3fe7xOMuimbZ0wq53YP/jhQv6XWodjT3mL0H5ACqcsSn727B5ztquzCPiwrqyjUHjJQQefFTzOse8snaWNQTUsQS7aLsHq0FveGpSBYORyA90qPdiTjXIkVP7mAiYiAIWW9pCEC7F3XtViKTZ8FRMM9ySicfuAlf3jtap6v2KPMtQv70X+hlmzO/IXB6W0Ep8DovkF5rB4r/BJYJLw/6AS0LZM9w5JfnAZhfGM2rKzpfNsgpOgEZS1WleG4I2hoQC0nxg9IcP0Hs+nWIPkEUcYNaiXqeBc=");
//		params.put("sign", "rlqgA8O+RzHBVYLyHmrbODVSANWPXf3pSrr82OCO/bm3upZiXSYrX5fZr6UBmG6BZRAydEyTIguEW6VRuAKjnaO/sOiR9BsSrOdXbD5Rhos/Xt7/mGUWbTOt/F+3W0/XLuDNmuYg1yIC/6hzkg44kgtdSTsQbOC9gWM7ayB4J4c=");
//		params.put("sign_type", "RSA");
//		params.put("charset", "utf-8");
//		String sign = AlipaySignature.rsaSign(params,privateKey,"utf-8");
//		String rs = AlipaySignature.checkSignAndDecrypt(params, publicKeyString, privateKey, true, true,"RSA");
//		System.out.println(rs);
		
		
		 
		
	} catch (AlipayApiException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
   }
   
   /**
   * RSA签名
   * @param content 待签名数据
   * @param privateKey 商户私钥
   * @param input_charset 编码格式
   * @return 签名值
   */
   public static String sign(String content, String privateKey, String input_charset)
   {
        try 
        {
         byte[] decode = Base64.decode(privateKey);
         PKCS8EncodedKeySpec priPKCS8   = new PKCS8EncodedKeySpec(decode );
           KeyFactory keyf= KeyFactory.getInstance("RSA");
           PrivateKey priKey= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
           e.printStackTrace();
        }
        
        return null;
    }
   
   /**
   * RSA验签名检查
   * @param content 待签名数据
   * @param sign 签名值
   * @param ali_public_key 支付宝公钥
   * @param input_charset 编码格式
   * @return 布尔值
   */
   public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
   {
      try 
      {
         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
           byte[] encodedKey = Base64.decode(ali_public_key);
           PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
         java.security.Signature signature = java.security.Signature
         .getInstance(SIGN_ALGORITHMS);
      
         signature.initVerify(pubKey);
         signature.update( content.getBytes(input_charset) );
      
         boolean bverify = signature.verify( Base64.decode(sign) );
         return bverify;
         
      } 
      catch (Exception e) 
      {
         e.printStackTrace();
      }
      
      return false;
   }
   
   /**
   * 解密
   * @param content 密文
   * @param private_key 商户私钥
   * @param input_charset 编码格式
   * @return 解密后的字符串
   */
   public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

   
   /**
   * 得到私钥
   * @param key 密钥字符串（经过base64编码）
   * @throws Exception
   */
   public static PrivateKey getPrivateKey(String key) throws Exception {

      byte[] keyBytes;
      
      keyBytes = Base64.decode(key);
      
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
      
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      
      PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
      
      return privateKey;
   }
}
