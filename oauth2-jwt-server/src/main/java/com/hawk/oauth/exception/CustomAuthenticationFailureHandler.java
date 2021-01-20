package com.hawk.oauth.exception;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: CustomAuthenticationFailureHandler
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/9 15:21
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        Map<String,Object> error = new HashMap<>();
        if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)){
            error.put("code",20001);
            error.put("msg","用户不存在");
        }else if (exception.getClass().isAssignableFrom(LockedException.class)) {
            error.put("code",20004);
            error.put("msg","用户账户已冻结");
        }else if(exception.getClass().isAssignableFrom(InvalidSmsCodeException.class)){
            error.put("code",70006);
            error.put("msg","无效短信验证码");
        }
        response.getWriter().write(JSON.toJSONString(error));
    }
}
