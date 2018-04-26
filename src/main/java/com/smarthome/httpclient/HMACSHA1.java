package com.smarthome.httpclient;
  
import com.smarthome.md5.MD5Util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;  
import javax.crypto.Mac;  
import javax.crypto.spec.SecretKeySpec;  
  
/** 
 * 1. hmac_sha1编码结果需要转换成hex格式 
 *  
 * 2. java中base64的实现和php不一致,其中java并不会在字符串末尾填补=号以把字节数补充为8的整数 
 *  
 * 3. hmac_sha1并非sha1, hmac_sha1是需要共享密钥的 
 *  
 * @author LEI 
 *  
 */  
public class HMACSHA1 {  
  
    private static final String HMAC_SHA1 = "HmacSHA1";
  
    /** 
     * 生成签名数据_HmacSHA1加密 
     *  
     * @param data
     *            待加密的数据
     * @param key
     *            加密使用的key
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException 
     */  
    public static String getSignature(String data, String key) throws Exception {
  
        byte[] keyBytes = key.getBytes();  
        // 根据给定的字节数组构造一个密钥。  
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);  
        Mac mac = Mac.getInstance(HMAC_SHA1);  
        mac.init(signingKey);  
  
        byte[] rawHmac = mac.doFinal(data.getBytes());  
  
        String hexBytes = byte2hex(rawHmac);
        String string = MD5Util.byteArrayToHexString(rawHmac);
        return string;
    }  
  
    private static String byte2hex(final byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。  
            stmp = (java.lang.Integer.toHexString(b[n] & 0xFF));  
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs;  
    }  
  
    // /**  
    // * @param args  
    // */  
    // public static void main(String[] args) {  
    // try {  
    // System.out.println(HMACSHA1.getSignature("abc", "abc"));  
    // } catch (Exception e) {  
    // e.printStackTrace();  
    // }  
    // }  

    public static byte[] hamcsha1(byte[] data, byte[] key)
    {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

}  