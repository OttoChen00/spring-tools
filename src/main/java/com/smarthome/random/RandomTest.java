package com.smarthome.random;

import org.junit.Test;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/4/17
 */
public class RandomTest {

    @Test
    public void test_01(){
        int total = 0;
        for (int i = 0; i < 100; i++) {
            int num = (int) (Math.random()*4);
            System.out.println(num);
            if (num < 3) {
                total ++;
            }
        }
        System.out.println(total);
    }
}
