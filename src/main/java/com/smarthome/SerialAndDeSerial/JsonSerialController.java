package com.smarthome.SerialAndDeSerial;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/6/4
 */
@RestController
public class JsonSerialController {

    @PostMapping(value = "/serial")
    public SerialModel test(@RequestBody SerialModel serialModel){
        return serialModel;
    }
}
