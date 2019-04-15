package com.miaosha.interceptor;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.utils.JwtUtils;
import com.miaosha.utils.LoginToken;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authentication-Token");
        if(! (handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
//        if(method.isAnnotationPresent(LoginToken.class)) {
//            return true;
//        }else {
//            //token = "eyJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50IjoiYWRtaW4iLCJleHAiOjE1NTQ3Nzg0OTh9.GlNCrSh1KcgaliaS0kINMqD4B04-CzN5rW5pjFS_VHQ";
//            try{
//                String account = JwtUtils.validateToken(token);
//                if(account==null || account.isEmpty()) {
//                    throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
//                }else {
//                    return true;
//                }
//            }catch (io.jsonwebtoken.JwtException e) {
//                throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
//            }
//        }
        return true;
    }
}
