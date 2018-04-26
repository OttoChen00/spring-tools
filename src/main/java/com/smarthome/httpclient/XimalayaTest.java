package com.smarthome.httpclient;

import com.google.common.hash.HashCode;
import com.smarthome.md5.MD5Util;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.junit.Test;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/4/19
 */
public class XimalayaTest {

    @Test
    public void test_01() throws Exception {
        String app_key = "7f5f5da2446210600115574003cb219a";
        String client_os_type = "2";
        String nonce = UUID.randomUUID().toString().replaceAll("-","");
        Long timestamp = System.currentTimeMillis()/1000 - 103;
        String q = "聪明与智慧";

        String paramStr = "app_key="+app_key+"&client_os_type="+client_os_type+"&nonce="+nonce+"&q="+q+"&timestamp="+timestamp;
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String base64Encode = base64Encoder.encode(paramStr.getBytes());
        base64Encode = base64Encode.replaceAll("\r\n", "");
        System.out.println(base64Encode);
        String app_secret = "7574ef6460bbb45d3852d5b61988b540";
        String serverAuthenticateStaticKey = "z0hh5l9A";

        String sha1Key = app_secret;

        final byte[] signature = gethmacsha1(base64Encode, sha1Key);
        System.out.println(Arrays.toString(signature));

        String sig = DigestUtils.md5DigestAsHex(signature);

        System.out.println(sig);

        String getUrl = "https://api.ximalaya.com/search/albums?";
        String encodeQ = URLEncoder.encode(q, "UTF-8");
//        String fullParm = "app_key="+app_key+"&client_os_type="+client_os_type+"&nonce="+nonce+"&q="+encodeQ+"&timestamp="+timestamp+"&sig="+sig;


        String albumListUrl = "https://api.ximalaya.com/v2/albums/list?";


        String categoriesListUrl = "https://api.ximalaya.com/categories/list";
        String categoriesFullParm = "app_key="+app_key+"&client_os_type="+client_os_type+"&nonce="+nonce+"&timestamp="+timestamp+"&sig="+sig;
        System.out.println(categoriesListUrl+categoriesFullParm);
        String s = HttpUtil.doGet(categoriesListUrl + categoriesFullParm);
        System.out.println(s);


    }

    @Test
    public void test_02() throws Exception {

        String param = "access_token=75dbec7f1fc289145a88690307757f9d&app_key=b617866c20482d133d5de66fceb37da3&client_os_type=2&device_id=08d833f5826e8wk&pack_id=com.app.test.android&q=聪明与智慧";

        BASE64Encoder base64Encoder = new BASE64Encoder();
        String base64Encode = base64Encoder.encode(param.getBytes());
        base64Encode = base64Encode.replaceAll("\r\n", "");
        System.out.println(base64Encode);

        String sha1Key = "4d8e605fa7ed546c4bcb33dee1381179";

        byte[] signature = gethmacsha1(base64Encode, sha1Key);
        System.out.println(Arrays.toString(signature));

        String hexString = MD5Util.byteArrayToHexString(signature);
        System.out.println(hexString);
//        byte[] md5Digest = DigestUtils.md5Digest(signature);
//        System.out.println(new String(md5Digest,"GBK"));
        String digestAsHex = DigestUtils.md5DigestAsHex(signature);
        System.out.println(digestAsHex);
    }

    public static byte[] gethmacsha1(String data, String key) throws Exception{
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");//加密密钥
        Mac localMac = Mac.getInstance("HmacSHA1");
        localMac.init(localSecretKeySpec);
        localMac.update(data.getBytes("UTF-8"));
        byte[] bb = localMac.doFinal();
        return bb;
    }

}
