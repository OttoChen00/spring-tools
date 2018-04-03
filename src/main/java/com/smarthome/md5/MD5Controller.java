package com.smarthome.md5;

import com.smarthome.model.Boy;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/3/9
 */

@RestController
public class MD5Controller {

    @GetMapping("/md5")
    public void say() {
        byte[] bytes = DigestUtils.md5Digest("hello world".getBytes());
        System.out.println(new String(bytes));
    }
}
