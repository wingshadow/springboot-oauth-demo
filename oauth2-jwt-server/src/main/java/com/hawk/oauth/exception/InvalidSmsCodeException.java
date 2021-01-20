package com.hawk.oauth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Title: SmsCodeException
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/12 15:19
 */
public class InvalidSmsCodeException extends AuthenticationException {
    public InvalidSmsCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidSmsCodeException(String msg) {
        super(msg);
    }
}
