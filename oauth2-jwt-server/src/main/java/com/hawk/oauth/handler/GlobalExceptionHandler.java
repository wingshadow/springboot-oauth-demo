package com.hawk.oauth.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: GlobalExceptionHandler
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/12 16:02
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Map exception(HttpServletRequest request, Exception e) {
        Map<String,Object> error = new HashMap<>();
        error.put("code",10001);
        error.put("msg","10001");
        return error;
    }
}
