package com.smarthome.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/4/26
 */
@Controller
public class HtmlController {

    @GetMapping("/login")
    public String login() {
        System.out.println("login");
        return "/login";
    }

    @GetMapping("/chat")
    public String chat() {
        System.out.println("chat");
        return "/chat";
    }
}
