package com.sinontech.pub.utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.alipay.api.internal.util.AlipaySignature;

public class RSACryptography {
	public static String data="hello world";  
    public static String publicKeyString="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5B0FitAaL3tehyzNp6gS96QPkuREWucJ0S70K+CqoDAMZU/y6bPsCrnc5Pv27uqumSgjPz3PaI8EPuNdqO2cKH/JPygJlRAP8LutMM19gT346O9cTRxVgwHUd4Uf363e8NoDS0S7YE0iOXk5yJfPchASCZoDv8782alklVXsbACA63yYocTBELj3iPW5WcIEItzZTuwaJguMd8AErZcXq2mpX3iTunIlVAdcaXmC4AxKPaW9GCbEi2sqfGitPs6glK5lm/SDuX9I2L1SVpbDX3uPqE8i61H56+Ww1XJjPeMGpvp4+5I+o/RZ+eZfrWIBDAqe1JBj2VlfvqtnpIecjQIDAQAB";  
    public static String privateKeyString="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkHQWK0Bove16HLM2nqBL3pA+S5ERa5wnRLvQr4KqgMAxlT/Lps+wKudzk+/bu6q6ZKCM/Pc9ojwQ+412o7Zwof8k/KAmVEA/wu60wzX2BPfjo71xNHFWDAdR3hR/frd7w2gNLRLtgTSI5eTnIl89yEBIJmgO/zvzZqWSVVexsAIDrfJihxMEQuPeI9blZwgQi3NlO7BomC4x3wAStlxeraalfeJO6ciVUB1xpeYLgDEo9pb0YJsSLayp8aK0+zqCUrmWb9IO5f0jYvVJWlsNfe4+oTyLrUfnr5bDVcmM94wam+nj7kj6j9Fn55l+tYgEMCp7UkGPZWV++q2ekh5yNAgMBAAECggEBAMnS45Rtir136aqveUVc72srPJw6WkATKhxp7p7jqBMSb+UzVzhud+kFiOsQAupfuRrI6GaLyXY5EwiRIczUbdVnOicmxVOdYn0bj6AeL2yp6BAvtvqClyXR+KnGnuzIaPev7DosFZyeoik3mGhh6KiCj3LvUWHjQNNTjCqUWxkxrML/Blhd1MMszpEIiPNW5+EZ36rMVmdA0Sed4E0vgNpX7Scmf85eWT4jVW92Md4zsRkScNrf9loQ9y5oYI9/0vU/FUvrs2P6Z7cAbxXZyBjlheQEdW7IbGMWPiiQosDNlpo5xMIr3FLB8GzXcdCAQerlILiG5kskNTUEfWmgFgECgYEA/YjEi/Bsu3LpdJi48ltNw4m3t21xhy5rGRlVBuJHWoo4Ptzr3Gh2MCHMlI43ZYbNw5j/iaGE3U+loYvFnVIbZ5pjOXesxDycXMpaYdnUp+RwfcAYbbQGFPOJeXarPIhrA+93cJ1OzIh4p4i20wLl/9aXBcaORnbGObIHm3uTNqcCgYEA5lT2dEoMqVsejNZsIf8pQOqDyWsslQAARbKyKWambEYZXYQohIIrQMv1LzDSo3mSqVd+mUSLgUFj+a854i+GJK5820fg5iEiclZDH9vxKargjMdkFnkkf58PNoceaZlIJ4534EM0njFjqlLqwHsTYD3BiIkHoULhsu+QXFhLbasCgYEAs/U+PW48X5kjKEW3ER/JxL+eDa67ZI6IKlqWxbobl6KAZe595AapzWRQSCniHEhhCTMyPsjM/CGqmU4SyKYdGZ62S3YsxWWYPyzKlqX9keZOCpQtQsEDiB+NNxAKX38dJr/08ZRW+EruCUy7KG/oPX5d2BWlq1JnRVCeobhHOAUCgYBmfaP09wZPTbZ4YOlY71uCAViHBeOeoZN9cBiLB5xCTab3z9jfrbRlbtw5FXaFO5GhPMeel7uj1l8QOYJ+PTtpkyMHyCIxBMl5Kx1PTeUv71NgEVFeFqXBDBwO3W0Q/B35TBHXftApHD0HC0JeR+XFS3WUtbTpcwNPRf5suPVHZQKBgEAgiSZqSivtCGPgf/oR0b2Kno0PrGHMJZzNcFtgQ3CA+pFIxyTKMNntdjfyq0m53Qyo4G6oFy4bBxlsvU05FbjL77oX+7ZT9+V708hFSnl3ag143QM6VyATJ/Rxlz7QRTQzhu8CpHsVqmfYG2IWRWggmZp6QN4j5I0N8fdSJ18G";
      
    public static void main(String[] args) throws Exception {  
        // TODO Auto-generated method stub  
    	AlipaySignature a = new AlipaySignature();
    	Map<String,String> params = new HashMap<String,String>();
    	String privateKey="";
    	String charset ="utf-8";
    	a.rsaSign(params, privateKey, charset);
          
       /* //获取公钥   
        PublicKey publicKey=getPublicKey(publicKeyString);  
          
        //获取私钥   
        PrivateKey privateKey=getPrivateKey(privateKeyString);        
          
        //公钥加密  
        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);    
        System.out.println("加密后："+new String(encryptedBytes,"utf-8"));  
          
        //私钥解密  
        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);        
        System.out.println("解密后："+new String(decryptedBytes,"utf-8"));  */
    }  
      
    /*//将base64编码后的公钥字符串转成PublicKey实例  
    public static PublicKey getPublicKey(String publicKey) throws Exception{  
        byte[ ] keyBytes=java.util.Base64.getDecoder().decode(publicKey.getBytes());  
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePublic(keySpec);    
    }  
      
    //将base64编码后的私钥字符串转成PrivateKey实例  
    public static PrivateKey getPrivateKey(String privateKey) throws Exception{  
        byte[ ] keyBytes=java.util.Base64.getDecoder().decode(privateKey.getBytes());  
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");  
        return keyFactory.generatePrivate(keySpec);  
    }  */
      
    //公钥加密  
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{  
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        return cipher.doFinal(content);  
    }  
      
    //私钥解密  
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{  
        Cipher cipher=Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        return cipher.doFinal(content);  
    }  
}
