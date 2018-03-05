package com.smarthome.regex;

import org.junit.Test;

import java.util.regex.*;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/2/28
 */
public class RegexTest {

    @Test
    public void test_01(){
        String str = "yin yu shi wo zui cai de yu yan";
        System.out.println(str);
        String reg = "\\b[a-z]{3}\\b";//匹配只有三个字母的单词

        //将规则封装成对象。
        Pattern p = Pattern.compile(reg);

        //让正则对象和要作用的字符串相关联。获取匹配器对象。
        Matcher m  = p.matcher(str);

        //System.out.println(m.matches());//其实String类中的matches方法。用的就是Pattern和Matcher对象来完成的。
        //只不过被String的方法封装后，用起来较为简单。但是功能却单一。

        // boolean b = m.find();//将规则作用到字符串上，并进行符合规则的子串查找。
        // System.out.println(b);
//         System.out.println(m.group());//用于获取匹配后结果。


        while(m.find())
        {
            System.out.println(m.group());
            System.out.println(m.start()+"...."+m.end());
            // start()  字符的开始下标（包含）
            //end()  字符的结束下标（不包含）
        }
    }

}
