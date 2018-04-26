package com.smarthome.mvc;

import com.smarthome.mvc.handler.MYHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Component
public class MyWebConfig extends WebMvcConfigurerAdapter {
  
    /** 
     * 注册 拦截器 
     */  
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MYHandler()).addPathPatterns("/**");
    }
  
} 