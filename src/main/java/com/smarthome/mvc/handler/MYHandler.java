package com.smarthome.mvc.handler;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Description:
 * @Author: bing.chen
 * @Date: 2018/4/20
 */
public class MYHandler extends HandlerInterceptorAdapter {

    private int startTime;
    private int endTime;
    //依赖注入,请看配置文件
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("requestURI is " + requestURI);
        Enumeration<String> attributeNames = request.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String attribute = attributeNames.nextElement();
//            System.out.println(request.getAttribute(attribute));
        }

        Map<String, String[]> parameterMap = request.getParameterMap();

        Set keSet=parameterMap.entrySet();
        for(Iterator itr=keSet.iterator();itr.hasNext();){
            Map.Entry me=(Map.Entry)itr.next();
            Object ok=me.getKey();
            Object ov=me.getValue();
            String[] value=new String[1];
            if(ov instanceof String[]){
                value=(String[])ov;
            }else{
                value[0]=ov.toString();
            }

            for(int k=0;k<value.length;k++){
                System.out.println(ok+"="+value[k]);
            }
        }

        System.out.println("执行preHandle方法-->01");
        Calendar cal = Calendar.getInstance();
        //获得当前时间对应的小时数,例如：12:05-->12,13:15-->13
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (startTime <= hour && hour < endTime) {
            return true;  //通过拦截器，继续执行请求
        } else {//给定的时间之外禁止登录
            request.setAttribute("msg", "非登录时段");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return false;  //没有通过拦截器，返回登录页面
        }
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("执行postHandle方法-->02");
        super.postHandle(request, response, handler, modelAndView);
    }

    //整个请求执行完成后调用
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("执行afterCompletion方法-->03");
        super.afterCompletion(request, response, handler, ex);
    }

}
