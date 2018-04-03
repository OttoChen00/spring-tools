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

        while(m.find())
        {
            System.out.println(m.group());
            System.out.println(m.start()+"...."+m.end());
            // start()  字符的开始下标（包含）
            //end()  字符的结束下标（不包含）
        }
    }

    /**
     * 回溯引用
     */
    @Test
    public void test_02(){
        String str="上上海市市市";

        System.out.println(str.replaceAll("(.)\\1+","$1"));

        System.out.println("===========找出重复字===========");
        Pattern p=Pattern.compile("(.)\\1+");
        Matcher m=p.matcher(str);
        while(m.find()){
            System.out.println(m.group()+"   位置：["+m.start()+","+m.end()+"]");
        }

        System.out.println("===========找出重复字母===========");
        str="aafdfdttffjjj";
        p=Pattern.compile("([a-z]+)\\1+");
        m=p.matcher(str);
        while(m.find()){
            System.out.println(m.group()+"   位置：["+m.start()+","+m.end()+"]");
        }

        //匹配html里的标题
        str="<h1>标题1</h1><h2>标题2</h2><h3>标题3</h3><h4>标题4</h5>";

        System.out.println("===========匹配标题(非回溯方法)===========");
        //<h4>标题4</h5>这个不符合的标题也会被匹配
        p=Pattern.compile("<h[1-6]>.*?</h[1-6]>");
        m=p.matcher(str);
        while(m.find()){
            System.out.println(m.group()+"   位置：["+m.start()+","+m.end()+"]");
        }

        System.out.println("===========匹配标题(回溯方法)===========");
        //<h4>标题4</h5>这个不符合的标题不会被匹配
        // \1表示前面第一个子表达式，\2表示前面第2个子表达式，依此类推
        p=Pattern.compile("<h([1-6])>.*?</h\\1>");
        m=p.matcher(str);
        while(m.find()){
            System.out.println(m.group()+"   位置：["+m.start()+","+m.end()+"]");
        }
    }

    /**
     * 回溯引用在替换中的作用
     */
    @Test
    public void test_05(){
        String str = "<h1>Index Page Home</h1>";
        Pattern pattern = Pattern.compile("(<[Hh][1-6]>)(.*)(</[Hh][1-6]>)");
        //?<=表示该子表达式之后的内容
        //?= 表示该表达式之前的内容
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group());// <h1>Index Page Home</h1>
            System.out.println(matcher.group(1));// <h1>
            System.out.println(matcher.group(2));// Index Page Home
            System.out.println(matcher.group(3));// </h1>
            System.out.println(str.replace(matcher.group(2),matcher.group(2).toUpperCase()));//<h1>INDEX PAGE HOME</h1>
        }
    }

    /**
     * 前后查找
     */
    @Test
    public void test_04(){
       String str = "<H1>Index Page Home</H1>";
        Pattern pattern = Pattern.compile("(?<=<[Hh][1-6]>)(.*)(?=</[Hh][1-6]>)");
        //?<=表示该子表达式之后的内容
        //?= 表示该表达式之前的内容
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);

            System.out.println(str.replace(group,group.toUpperCase()));//<H1>INDEX PAGE HOME</H1>
            System.out.println(matcher.replaceAll(group.toUpperCase()));//<H1>INDEX PAGE HOME</H1>
        }
    }


}
