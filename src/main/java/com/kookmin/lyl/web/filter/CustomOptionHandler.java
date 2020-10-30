package com.kookmin.lyl.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomOptionHandler extends HandlerInterceptorAdapter {
    private static final String TOKEN = "X-AUTH-TOKEN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(TOKEN);

        System.out.println("HANDLE!!!!!");

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        return true;
    }
}
