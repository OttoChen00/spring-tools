package com.smarthome.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/3/9
 */
@Component
public class Boy {

    private int id;
    private String name;
    @Min(value = 18,message = "年龄不能小于18")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
