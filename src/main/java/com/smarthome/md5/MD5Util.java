package com.smarthome.md5;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import com.smarthome.utils.OptDateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class MD5Util {

    private static final Logger LOGGER = LogManager.getLogger(MD5Util.class);

    private static final String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 根据流获取MD5值
     * 
     * @param inputStream
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5ByInputStream(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = inputStream.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        }
        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }

    /**
     * 校验文件md5值是否合法
     * 
     * @param multipartFile
     * @param fileMd5
     * @return
     */
    public static boolean isLegalFileMd5(MultipartFile multipartFile, String fileMd5) {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
            String md5ByIs = getMD5ByInputStream(inputStream);
            LOGGER.debug("md5ByIs--->" + md5ByIs);
            LOGGER.debug("fileMd5--->" + fileMd5);
            if (fileMd5.equals(md5ByIs)) {
                return true;
            }
        } catch (IOException e) {
            LOGGER.error("isLegalFileMd5 error " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("isLegalFileMd5 NoSuchAlgorithmException error " + e.getMessage());
        }
        return false;
    }

    public static String getSign(Map<String, Object> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key="; //+ Const.WMS_TONGBU_KEY;
        LOGGER.info("Sign Before MD5:" + result);
        result = md5Encode(result).toUpperCase();
        LOGGER.info("Sign Result:" + result);
        return result;
    }

    /**
     * MD5编码
     *
     * @param origin
     *            原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String md5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception e) {
            LOGGER.error("md5Encode error " + e.getMessage());
        }
        return resultString;
    }
    
    /**MD5编码32位小写
     * @param origin
     * @return
     */
    public static String md5Encode32Lower(String origin) {
        return md5Encode(origin).toLowerCase();
    }
    
    /**MD5编码32位大写
     * @param origin
     * @return
     */
    public static String md5Encode32Upper(String origin) {
        return md5Encode(origin).toUpperCase();
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder resultSb = new StringBuilder();
        for (byte bt : bytes) {
            resultSb.append(byteToHexString(bt));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     *
     * @param b
     *            要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }
    
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        String appid = "common_demo";
        stringBuilder.append(appid);
//        stringBuilder.append(" ");
        String appSecret = "455545bffcf44ec5323e67a9274e3b38";
        stringBuilder.append(appSecret);
//        stringBuilder.append(" ");
        String requestBody = "{\"uid\":\"000000001\",\"qid\":\"222222222\",\"raw_query\":\"\",\"req_num\":\"10\",\"res_from_time\":\"\",\"res_end_time\":\"\",\"query_info\":{\"news_category\":\"娱乐\",\"date_time\":\"\",\"person\":\"杨幂\",\"location\":\"\",\"keywords\":\"\",\"srcfrom\":\"\",\"only_voice\":False}}";
        stringBuilder.append(requestBody);
//        stringBuilder.append(" ");
        long timestamp = OptDateUtil.getNowTimeLong();
        System.out.println(timestamp);
        stringBuilder.append("1522237638");
        String parmStr = stringBuilder.toString();
        System.out.println(parmStr);
        System.out.println(md5Encode(parmStr));
        System.out.println(md5Encode("hello world"));
    }

    @Test
    public void test_01(){
        StringBuilder stringBuilder = new StringBuilder();
        String appid = "common_demo";
        stringBuilder.append(appid);
        String appSecret = "455545bffcf44ec5323e67a9274e3b38";
        stringBuilder.append(appSecret);
        String requestBody ="";
        stringBuilder.append(requestBody);
        long timestamp = OptDateUtil.getNowTimeLong();
        System.out.println(timestamp);
        stringBuilder.append(timestamp);
        String parmStr = stringBuilder.toString();
        System.out.println(parmStr);
        System.out.println(md5Encode(parmStr));
//        System.out.println(md5Encode("hello world"));
    }
}
