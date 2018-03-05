package com.smarthome.redis;

import com.phicomm.smarthome.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/2/7
 */
@RestController
@RequestMapping("/test")
public class RedisTemplateController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/redis/set")
    public void test_redis(String redisValue){
        System.out.println(redisValue);
        Long add = redisTemplate.opsForSet().add("spliderTaskKeyTest122", redisValue);
//        cache.putSetValue("spliderTaskKey", redisValue);
        System.out.println("add " + add );
        Set members = redisTemplate.opsForSet().members("spliderTaskKeyTest122");
        System.out.println(members.size());
        for (Object member : members) {
            System.out.println(member);
        }
    }
}
