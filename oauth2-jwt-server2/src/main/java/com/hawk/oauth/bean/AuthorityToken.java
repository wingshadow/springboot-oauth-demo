package com.hawk.oauth.bean;

import lombok.Data;

/**
 * @Title: AuthToken
 * @ProjectName spring-cloud-oauth2-demo
 * @Author Hawk
 * @Date 2021/1/8 15:06
 */
@Data
public class AuthorityToken {
    private String jti;
    private String refreshToken;
    private String accessToken;
    private Integer expiryTime;
    private String scope;

}
