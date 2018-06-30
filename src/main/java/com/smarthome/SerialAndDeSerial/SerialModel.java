package com.smarthome.SerialAndDeSerial;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/6/4
 */
public class SerialModel {

    private String name;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("singer_name")
    public String getSex() {
        return sex;
    }

    @JsonProperty("singerName")
    public void setSex(String sex) {
        this.sex = sex;
    }
}
