package com.hawk.oauth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Title: SmsCodeNoExitsException
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/12 15:24
 */
public class SmsCodeNoExitsException extends AuthenticationException {
    public SmsCodeNoExitsException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsCodeNoExitsException(String msg) {
        super(msg);
    }
}
