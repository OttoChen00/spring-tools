package com.smarthome.mvc;

import com.smarthome.model.Boy;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/3/9
 */

@RestController
public class MvcController {

    @GetMapping("/mvc/say")
    public void say(@Valid Boy boy, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            System.out.println(message);
        }
        System.out.println("boy"+boy);
    }
}
