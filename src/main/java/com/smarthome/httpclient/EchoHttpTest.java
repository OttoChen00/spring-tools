package com.smarthome.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.UUID;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/6/29
 */
public class EchoHttpTest {

    @Test
    public void test_text_nlu(){
//        String url = "http://172.31.34.128/echo/text/nlu";
        String url = "http://localhost:8805/text/nlu";

        String a = "我想听最新的新闻";
        String b = "今天天气怎么样";
        String c = "给我讲个笑话";
        String d = "我想听周杰伦的歌";
        String e = "你今年几岁了";
        String f = "随便放首歌";
        String g = "我想听周杰伦的千里之外";
        String h = "我想听周杰伦的曹操";
        String i0 = "播放歌曲周杰伦的海草舞";
        String i1 = "我想听说散就散";
        String j = "你叫什么名字";
        String y = "我想听杨幂的新闻";
        String z = "距离端午节还有多少天";

        String[] strings = {a, b, c, d, e, f, g, h, i0, i1, f,j,z,y};

        int totalTime = 0;
        int successTime = 0;
        for (int i = 1; i <= 100; i++) {
            int random = (int) (Math.random()*10);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String param = "{\n" +
                    "\t\"device_id\":\""+uuid+"\",\n" +
                    "\t\"text\":\""+strings[random]+"\",\n" +
                    "\t\"ai_type\":\"dm\",\n" +
                    "\t\"record_id\":\"11233dggswerr44555dsf12dse11\"\n" +
                    "}";
            try {
                long begin = System.currentTimeMillis();
                String post = HttpUtil.doPost(url, param);
                JSONObject jsonObject = JSON.parseObject(post);
                long end = System.currentTimeMillis();
                long time = end - begin;
                System.out.println("cost time " + time);
                totalTime += time;
                int status = jsonObject.getIntValue("status");
                if (status == 0) {
                    successTime += 1;
                }
                System.out.println("第"+i+"次");
                System.out.println(strings[random]);
                System.out.println("status is "+status);
                System.out.println(post);
                System.out.println("平均时间=" + totalTime/i +"  成功次数="+successTime);
                System.out.println("====================");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("汇总 ： 平均时间=" + totalTime/100 +"  成功次数="+successTime);
    }


    @Test
    public void test_01(){
        test_text_nlu();
    }

    @Test
    public void test_02(){
        test_text_nlu();
    }

    @Test
    public void test_03(){
        test_text_nlu();
    }

    @Test
    public void test_04(){
        test_text_nlu();
    }

}
