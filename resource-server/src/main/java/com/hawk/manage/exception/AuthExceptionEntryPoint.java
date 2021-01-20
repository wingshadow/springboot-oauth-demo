package com.hawk.manage.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: AuthExceptionEntryPoint
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/9 14:41
 */
@Slf4j
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Map<String,Object> error = new HashMap<>();
        Throwable cause = authException.getCause();

        log.info("auth:{}", request.getHeader("Authorization"));

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if(cause instanceof InvalidTokenException) {
                error.put("code",70002);
                error.put("msg","token无效");

            }if(cause instanceof OAuth2AccessDeniedException){
                error.put("code",70001);
                error.put("msg","资源访问权限受限");
            }else{
                error.put("code",70005);
                error.put("msg","token缺失或不合法");
            }
            response.getWriter().write(JSON.toJSONString(error));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
