package com.bs.blog.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    //preHandle:在方法调用前使用
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //判断用户是否登录，未登录重定向到登录页面
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            System.out.println("asdfgh");
            return false;
        }
        return true;
    }
}
