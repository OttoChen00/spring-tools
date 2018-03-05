//package com.smarthome.redis;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.swing.*;
//import java.util.Set;
//
///**
// * @Description:
// * @Author: bing.chen
// * @Date: 2018/2/7
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = RedisTemplateTest.class)
//public class RedisTemplateTest {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void test_01(){
//        Set<String> members = redisTemplate.opsForSet().members("spliderTaskKey");
//        for (String member : members) {
//            System.out.println(member);
//        }
//    }
//
//
//}
