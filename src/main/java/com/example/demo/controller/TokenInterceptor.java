package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.example.demo.util.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getMethod());
        if (request.getMethod().equals("OPTIONS")){return true;}
        String pattern = ".*/staff.*";
        if(!Pattern.matches(pattern, request.getRequestURI())){
            return true;
        }else {
            String authorization = request.getHeader("Authorization");
            boolean verifyResult = JWTUtil.verify(authorization.substring(6), "ustc", "nsrl123");

//            if(verifyResult == true){
//                response.addHeader("newToken",token);
//                response.addHeader("Access-Control-Expose-Headers","newToken");
//            }
            return verifyResult;
        }
    }
}
