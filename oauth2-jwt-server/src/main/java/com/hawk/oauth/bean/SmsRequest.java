package com.hawk.oauth.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: SmsRequest
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/12 9:42
 * <pre>
 *     短信验证码登陆
 * </pre>
 */
@Data
public class SmsRequest implements Serializable {
    private String mobile;
    private String code;
}
