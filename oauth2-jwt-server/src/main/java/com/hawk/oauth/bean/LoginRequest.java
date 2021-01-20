package com.hawk.oauth.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: LoginRequest
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/8 19:30
 * <pre>
 *     用户名/密码登陆
 * </pre>
 */
@Data
public class LoginRequest implements Serializable {
    String username;
    String password;
    Integer type;
}
